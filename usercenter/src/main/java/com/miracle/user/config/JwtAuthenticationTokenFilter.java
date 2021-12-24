package com.miracle.user.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.miracle.user.uttils.JwtTokenUtil;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author sqq
 * @description
 * @date 2021/12/24 14:54
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  @Resource
  JwtTokenUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = request.getHeader("Authorization");
    DecodedJWT verify = jwtUtil.verify(token);
//    String userName = verify.getClaim("userName").asString();

    //将authentication放入SecurityContextHolder中
//    UsernamePasswordAuthenticationToken authentication =
//        new UsernamePasswordAuthenticationToken(userName, null, userDetails.getAuthorities());
//    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }
}
