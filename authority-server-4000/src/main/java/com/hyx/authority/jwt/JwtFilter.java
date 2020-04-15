package com.hyx.authority.jwt;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:39
 *
 * 自定义 JwtFilter
 */
public class JwtFilter extends AccessControlFilter {

    /**
     * 判断是否认证成功
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        return (subject!=null && subject.isAuthenticated());
    }

    /**
     * 验证未通过
     * 则查看 Header 是否携带 Token
     * 有则重新认证
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("jwt");
        if(StringUtils.isEmpty(token)){
            return false;
        }

        JwtToken jwtToken = new JwtToken(token);
        Subject subject = SecurityUtils.getSubject();
        subject.login(jwtToken);
        return true;
    }
}
