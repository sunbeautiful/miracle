package com.miracle.user.config;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.miracle.user.uttils.JwtUtil;
import java.io.IOException;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author sqq
 * @description JwtAuthorizationTokenFilter jwt token拦截
 * @date 2021/12/17 14:17
 */
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

  public static final String BEARER = "Bearer ";

  @Autowired
  JwtProperties jwtProperties;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {
    String authentication = request.getHeader("Authentication");
    if (authentication != null && authentication.startsWith(BEARER)) {
      String token = authentication.substring(7);
      DecodedJWT jwt = JwtUtil.verify(token, jwtProperties.getTokenSecret());
      Map<String, Claim> claims = jwt.getClaims();
//      SecurityContextHolder.getContext().setAuthentication(jwtLoginToken);
      filterChain.doFilter(request, response);
    }

  }
}
