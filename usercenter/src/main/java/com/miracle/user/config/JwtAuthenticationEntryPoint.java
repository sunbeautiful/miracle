package com.miracle.user.config;

import com.alibaba.fastjson.JSON;
import com.miracle.common.Result;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author sqq
 * @description 无凭证处理类
 * @date 2021/12/20 15:08
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {

    String json = JSON.toJSONString(Result.succeed(HttpServletResponse.SC_UNAUTHORIZED));
    response.getWriter().write(json);
    response.setContentType("text/json;charset=utf-8");
  }
}
