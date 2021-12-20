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
 * @description Spring Security核心配置类
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
/*    http.authorizeRequests()
        .antMatchers("/v1/user/login", "/v1/user/register").permitAll()
        .antMatchers("/miracle", "/swagger-ui/**", "/api-docs/**").permitAll()
        .and()
        .formLogin()
        //自定义认证成功处理器
        .successHandler(new JwtAuthenticationSuccessHandler())
        // 自定义失败拦截器
        .failureHandler(new LoginFailureHandler())
        // 自定义登录拦截URI
//        .loginProcessingUrl("/login")
        .and()
        //token的验证方式不需要开启csrf的防护
        .csrf().disable()
        // 自定义认证失败类
        .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        // 自定义权限不足处理类
//        .accessDeniedHandler(jwtAccessDeniedHandler)
        .and()
        //设置无状态的连接,即不创建session
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .antMatchers("/login").permitAll()
        //配置允许匿名访问的路径
        .anyRequest().authenticated();
    // 解决跨域问题（重要）  只有在前端请求接口时才发现需要这个
    http.cors().and().csrf().disable();

    //配置自己的jwt验证过滤器
    http
        .addFilterBefore(new JwtAuthorizationTokenFilter(),
            UsernamePasswordAuthenticationFilter.class);

    // disable page caching
    http.headers().cacheControl();*/
  }
}
