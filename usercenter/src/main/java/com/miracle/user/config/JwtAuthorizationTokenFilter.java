package com.miracle.user.config;

/**
 * @author sqq
 * @description jwt token拦截
 * @date 2021/12/17 14:17
 * @Component public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {
 * <p>
 * public static final String BEARER = "Bearer ";
 * @Autowired JwtProperties jwtProperties;
 * @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse
 * response, FilterChain filterChain) throws IOException, ServletException { String authentication =
 * request.getHeader("Authentication"); if (authentication != null &&
 * authentication.startsWith(BEARER)) { String token = authentication.substring(7); DecodedJWT jwt =
 * JwtUtil.verify(token, jwtProperties.getTokenSecret()); Map<String, Claim> claims =
 * jwt.getClaims();
 * <p>
 * UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken( claims, null,
 * null); SecurityContextHolder.getContext().setAuthentication(auth); filterChain.doFilter(request,
 * response); }
 * <p>
 * } }
 */
