package com.miracle.user.uttils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author sqq
 * @Description
 * @Date 2021/12/17 10:47
 **/
public class JwtUtil {

  /**
   * 生成token（最关键）
   *
   * @param claims
   * @return
   */
  public static String generateToken(Map<String, Claim> claims, String tokenSecret,
      long expireTime) {
    // 设置过期时间
    // 私钥和加密算法
    Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
    Date date = new Date(System.currentTimeMillis() + expireTime * 60000 * 24);
    // 设置头部信息
    Map<String, Object> header = new HashMap<>(2);
    header.put("Type", "Jwt");
    header.put("alg", "HS256");
    // 返回token字符串
    return JWT.create()
        .withHeader(header)
        .withClaim("userId", String.valueOf(claims.get("userId")))
        .withJWTId(UUID.randomUUID().toString())
        .withClaim("userName", String.valueOf(claims.get("userName")))
        .withClaim("isPwdExpire", String.valueOf(claims.get("isPwdExpire")))
        .withExpiresAt(date)
        .sign(algorithm);
  }


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

}
