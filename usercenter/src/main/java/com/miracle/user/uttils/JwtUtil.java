package com.miracle.user.uttils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author sqq
 * @Description
 * @Date 2021/12/17 10:47
 **/
public class JwtUtil {

//  private Clock clock = DefaultClock.INSTANCE;

  /**
   * 生成签名
   */
  public static String sign(String userId, String userName, String isPwdExpire,
      long expireTime, String tokenSecret) {
    // 设置过期时间
    Date date = new Date(System.currentTimeMillis() + expireTime * 60000 * 24);
    // 私钥和加密算法
    Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
    // 设置头部信息
    Map<String, Object> header = new HashMap<>(2);
    header.put("Type", "Jwt");
    header.put("alg", "HS256");
    // 返回token字符串
    return JWT.create()
        .withHeader(header)
        .withClaim("userId", userId)
        .withJWTId(UUID.randomUUID().toString())
        .withClaim("userName", userName)
        .withClaim("isPwdExpire", isPwdExpire)
        .withExpiresAt(date)
        .sign(algorithm);
  }

  /**
   * 检验token是否正确
   */
  public static DecodedJWT verify(String token, String tokenSecret) {
    Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
    JWTVerifier verifier = JWT.require(algorithm).build();
    return verifier.verify(token);
  }


  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, userDetails.getUsername());
  }

  private String doGenerateToken(Map<String, Object> claims, String subject) {
    final Date createdDate = clock.now();
    final Date expirationDate = calculateExpirationDate(createdDate);

    return JWT.create()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(createdDate)
        .setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();

    return JWT.create()
        .withHeader(header)
        .withClaim("userId", userId)
        .withJWTId(UUID.randomUUID().toString())
        .withClaim("userName", userName)
        .withClaim("isPwdExpire", isPwdExpire)
        .withExpiresAt(date)
        .sign(algorithm);


  }

  private Date calculateExpirationDate(Date createdDate) {
    return new Date(createdDate.getTime() + expiration);
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    SecurityUserDetails user = (SecurityUserDetails) userDetails;
    final String username = getUsernameFromToken(token);
    return (username.equals(user.getUsername())
        && !isTokenExpired(token)
    );
  }

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody();
  }


  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(clock.now());
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }
}
