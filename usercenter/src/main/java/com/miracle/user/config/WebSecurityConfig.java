package com.miracle.user.config;

import com.miracle.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author sqq
 * @description
 * @date 2021/12/14 13:54
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserService userService;

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    // 使用不使用加密算法保持密码
//    return NoOpPasswordEncoder.getInstance();

  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        //设置哪些路径可以直接访问，不需要认证
        .antMatchers("/login", "/v1/user/register", "/miracle").permitAll()
        .antMatchers("/swagger-ui/**").permitAll()
        .antMatchers("/api-docs/**").permitAll()
        .anyRequest()  // 任何请求都需要身份验证
        .authenticated()
        .and()
        .formLogin()
        .and().csrf().disable();//关闭cors
  }
}
