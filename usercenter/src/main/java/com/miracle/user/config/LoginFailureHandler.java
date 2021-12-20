package com.miracle.user.config;

/**
 * @author sqq
 * @description 自定义认证失败类：当用户输入错误的账号或者密码时，就会进入这个处理类，同样要在配置类中指明
 * @date 2021/12/20 15:11
 * @Component public class LoginFailureHandler implements AuthenticationFailureHandler {
 * @Override public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse
 * response, AuthenticationException exception) throws IOException, ServletException {
 * System.out.println("JwtAuthenticationEntryPoint:" + exception.getMessage());
 * response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "凭证错误"); } }
 */
