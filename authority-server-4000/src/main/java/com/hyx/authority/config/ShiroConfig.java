package com.hyx.authority.config;

import com.hyx.authority.jwt.JwtFilter;
import com.hyx.authority.jwt.JwtRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/8 16:58
 */

@Configuration
@Slf4j
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("jwtflter",new JwtFilter());
        shiroFilterFactoryBean.setFilters(filters);

        //拦截器. authc:需要认证，anon无须认证
        //filterChainDefinitionMap.put("/home", "noSessionCreation,jwtflter");禁用Session会话
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        filterChainDefinitionMap.put("/home", "noSessionCreation,jwtflter"); //通过JwtFilter
        // 配置不会被拦截的链接 顺序判断

        //匿名通过的链接
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/login", "anon");

        //需要登录认证
        filterChainDefinitionMap.put("/menu/*", "noSessionCreation,jwtflter");
        filterChainDefinitionMap.put("/user/*", "noSessionCreation,jwtflter");
        filterChainDefinitionMap.put("/goods/*", "noSessionCreation,jwtflter");
        //未授权界面;
        //shiroFilterFactoryBean.setUnauthorizedUrl("/home/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     *  创建DefaultWebSecurityManager
     * @param jwtRealm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("jwtRealm") JwtRealm jwtRealm){
        log.info("defaultWebSecurityManager");
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(jwtRealm);
        securityManager.setSessionManager(mySessionManager());//注入SessionDao
        return securityManager;
    }

    /**
     *  创建Realm对象
     */
    @Bean("jwtRealm")
    public JwtRealm jwtRealm(){
        JwtRealm jwtRealm = new JwtRealm();
        jwtRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return jwtRealm;
    }

    /**
     * 自定义Session管理器
     */
    @Bean
    public MySessionManager mySessionManager() {
        return new MySessionManager();
    }

    /**
     * 代理生成器，需要借助SpringAOP来扫描@RequiresRoles和@RequiresPermissions等注解。
     * 生成代理类实现功能增强，从而实现权限控制。
     * 需要配合AuthorizationAttributeSourceAdvisor一起使用，否则权限注解无效。
     * 开启AOP 对shiro 的bean 的动态代理
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator lifecycleBeanPostProcessor() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 这里需要设置为True，否则 @RequiresPermissions 注解验证不生效
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 上面配置的DefaultAdvisorAutoProxyCreator相当于一个切面，下面这个类就相当于切点了，两个一起才能实现注解权限控制。
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defaultWebSecurityManager);
        return advisor;
    }

    /**
     *
     * 启用shiro 内部 Bean 生命周期管理
     * @return
     */
    /*@Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return LifecycleBeanPostProcessor();
    }*/

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }
}
