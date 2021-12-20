package com.miracle.user.config;

import com.miracle.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author sqq
 * @description Spring Security配置类
 * @date 2021/12/14 13:54
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserService userService;

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    // NoOpPasswordEncoder.getInstance(); 不加密
    auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        //设置哪些路径可以直接访问，不需要认证
        .antMatchers("/v1/user/login", "/v1/user/register").permitAll()
        .antMatchers("/miracle", "/swagger-ui/**", "/api-docs/**").permitAll()
        .anyRequest()  // 任何请求都需要身份验证
        .authenticated()
        .and()
        .formLogin()//默认登录界面
        .and().csrf().disable();//csrf
  }
}
