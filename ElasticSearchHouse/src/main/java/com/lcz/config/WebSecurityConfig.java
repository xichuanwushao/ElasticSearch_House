package com.lcz.config;

import com.lcz.enums.RoleEnum;
import com.lcz.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    /**
     * Http权限控制
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
        //资源访问权限
        http.authorizeRequests()
                .antMatchers("/admin/login").permitAll()//管理员登录入口
                .antMatchers("/static/**").permitAll()//静态资源
                .antMatchers("/user/login").permitAll()//用户登录入口
                .antMatchers("/admin/**").hasRole(RoleEnum.ADMIN.name())
                .antMatchers("/user/**").hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.USER.name())
                .antMatchers("/api/user/**").hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.USER.name())
                .and()
                .formLogin()
                .loginProcessingUrl("/login") //配置角色登录处理入口
//                .successHandler(myAuthenticationSuccessHandler)  自定义拦截器 AuthFilter 会覆盖这里的配置
//                .failureHandler(authFailHandler())        自定义拦截器 AuthFilter 会覆盖这里的配置
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/page")
                .deleteCookies("JESSIONID")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(urlEntryPoint())
                .accessDeniedPage("/403");
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 自定义认证策略
     *
     * @throws Exception
     */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").and();
        auth.authenticationProvider(authProvider()).eraseCredentials(true);
    }

    @Bean
    public AuthProvider authProvider() {
        return new AuthProvider();
    }

    @Bean
    public LoginUrlEntryPoint urlEntryPoint() {
        return new LoginUrlEntryPoint("/user/login");
    }

    @Bean
    public LoginAuthFailHander authFailHandler() {
        return new LoginAuthFailHander(urlEntryPoint());
    }


    @Bean
    public AuthenticationManager authenticationManager() {
        AuthenticationManager authenticationManager = null;
        try {
            authenticationManager = super.authenticationManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationManager;
    }

    @Bean
    public AuthFilter authFilter() {
        AuthFilter authFilter = new AuthFilter();
        authFilter.setAuthenticationManager(authenticationManager());
        authFilter.setAuthenticationFailureHandler(authFailHandler());
        authFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        return authFilter;
    }
}
