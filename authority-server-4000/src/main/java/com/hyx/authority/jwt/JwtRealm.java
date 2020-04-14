package com.hyx.authority.jwt;

import com.hyx.authority.dao.LoginDao;
import com.hyx.authority.utils.JwtTokenUtils;
import com.hyx.common.entities.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:37
 */
public class JwtRealm extends AuthorizingRealm {

    @Resource
    private LoginDao loginDao;

    /**
     * 权限授予
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken)authenticationToken;
        String token = (String)jwtToken.getPrincipal();

        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        User user = jwtTokenUtils.decodeToken(token);

        User user1 = loginDao.CheckUser(user);
        return new SimpleAuthenticationInfo(user,Boolean.TRUE, getName());
    }

    /**
     *  配置该Realm支持JWTToken
     */
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}
