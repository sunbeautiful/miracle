package com.miracle.user.config;

import com.miracle.user.service.UserService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author sqq
 * @description Spring Security核心配置类
 * @date 2021/12/14 13:54
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserService userService;

  @Resource
  private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

  @Resource
  private LoginFailureHandler loginFailureHandler;


  @Bean
  public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
    return new JwtAuthenticationTokenFilter();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    // NoOpPasswordEncoder.getInstance(); 不加密
    auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS).permitAll()
        .antMatchers("/v1/user/register").permitAll()
        .antMatchers("/miracle", "/swagger-ui/**", "/api-docs/**").permitAll();

    http.formLogin()
        .permitAll()//默认登录界面
        .successHandler(jwtAuthenticationSuccessHandler)
        // 自定义失败拦截器
        .failureHandler(loginFailureHandler)
        .loginProcessingUrl("/login")
        .and().csrf().disable();

    //配置自己的jwt验证过滤器
//    http.addFilterBefore(authenticationTokenFilterBean(),
//        UsernamePasswordAuthenticationFilter.class);

  }
}
