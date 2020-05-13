package com.zxd.bbs.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/toLogin");


        Map<String, String> filterMap = new LinkedHashMap();



        filterMap.put("/login", "anon");
        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;


    }



    @Bean("securityManager")
    public SecurityManager getDefaultWebSecurityManager(@Qualifier("servletContainerSessionManager")SessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getUserRealm());
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }


    @Bean
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

    @Bean("servletContainerSessionManager")
    public ServletContainerSessionManager getServletContainerSessionManager(){
        return new ServletContainerSessionManager();
    }



}
