package com.miracle.user.config;

import com.alibaba.fastjson.JSON;
import com.miracle.common.Result;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author sqq
 * @description
 * @date 2021/12/24 16:48
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    // 指定响应格式是json
    String json = JSON.toJSONString(Result.failed("认证失败"));
    response.setContentType("text/json;charset=utf-8");
    response.getWriter().write(json);
  }
}
