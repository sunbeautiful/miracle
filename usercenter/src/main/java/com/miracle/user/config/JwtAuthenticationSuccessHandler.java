package com.miracle.user.config;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.miracle.common.Result;
import com.miracle.user.uttils.JwtUtil;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author sqq
 * @description 自定义认证成功处理类：当用户认证成功之后，我们要在这里为用户生成token,并返回给用户
 * @date 2021/12/20 15:15
 */
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  JwtProperties jwtProperties;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    Map<String, Claim> principal = (Map<String, Claim>) authentication.getPrincipal();
    String token = JwtUtil
        .generateToken(principal, jwtProperties.getTokenSecret(), jwtProperties.getExpireTime());

    //将生成的authentication放入容器中，生成安全的上下文
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String json = JSON.toJSONString(Result.succeed(token));
    response.getWriter().write(json);


  }


}
