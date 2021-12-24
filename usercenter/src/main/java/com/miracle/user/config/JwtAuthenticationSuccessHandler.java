package com.miracle.user.config;

import com.alibaba.fastjson.JSON;
import com.miracle.common.Result;
import com.miracle.user.uttils.JwtTokenUtil;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author sqq
 * @description 登录成功后返回token给前端
 * @date 2021/12/20 17:48
 */
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Resource
  JwtTokenUtil jwtUtil;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Authentication authentication)
      throws IOException {
    String token = jwtUtil.generateToken(authentication.getName());
    //将生成的authentication放入容器中，生成安全的上下文
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String json = JSON.toJSONString(Result.succeed(token));
    httpServletResponse.setContentType("text/json;charset=utf-8");
    httpServletResponse.getWriter().write(json);
  }
}
