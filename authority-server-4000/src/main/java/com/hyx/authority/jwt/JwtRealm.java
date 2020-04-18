package com.hyx.authority.jwt;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyx.authority.dao.LoginDao;
import com.hyx.authority.utils.JwtTokenUtils;
import com.hyx.common.utils.RedisUtils;
import com.hyx.common.entities.SpUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:37
 */
public class JwtRealm extends AuthorizingRealm {

    @Resource
    private LoginDao loginDao;

    @Resource
    RedisUtils redisUtils;

    /**
     * 权限授予
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SpUser user  = (SpUser)principals.getPrimaryPrincipal();
        authorizationInfo.addStringPermission("select");
        return authorizationInfo;
    }

    /**
     * 身份认证
     * @param  authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken)authenticationToken;
        String token = (String)jwtToken.getPrincipal();

        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        SpUser user = jwtTokenUtils.parseToken(token);

        String tempUser = JSONObject.toJSONString( redisUtils.hmget("com;hyx:authority:"+user.getUsername()));
        SpUser temp = JSONObject.parseObject(tempUser,SpUser.class);

        if(temp.getUsername() == null){
            QueryWrapper<SpUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username",user.getUsername());
            temp = loginDao.selectOne(queryWrapper);
            if(temp != null){
                Map<String,Object> map = new HashMap<>();
                map.put("username",temp.getUsername());
                map.put("password",temp.getPassword());
                map.put("salt",temp.getSalt());
                redisUtils.hmset("com;hyx:authority:"+user.getUsername(),map);
            }
        }

        if(temp == null){
            return null;
        }
        return new SimpleAuthenticationInfo(temp,temp.getPassword(), ByteSource.Util.bytes(temp.getCredentialsSalt()), getName());
    }

    /**
     *  配置该Realm支持JWTToken
     */
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}
