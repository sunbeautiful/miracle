package com.miracle.user.uttils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.miracle.user.config.JwtProperties;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @Author sqq
 * @Description
 * @Date 2021/12/17 10:47
 **/
@Component
public class JwtTokenUtil {

  @Resource
  private JwtProperties jwtProperties;

  //生成token

  public String generateToken(String userName) {
    // 私钥和加密算法
    Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getTokenSecret());
    long expireTime = jwtProperties.getExpireTime();
    Date date = new Date(System.currentTimeMillis() + expireTime * 60000 * 24);
    Map<String, Object> header = new HashMap<>(2);
    header.put("Type", "Jwt");
    header.put("alg", "HS256");
    // 返回token字符串
    return JWT.create()
        .withHeader(header)
        .withClaim("userName", userName)
        .withJWTId(UUID.randomUUID().toString())
        .withClaim("isPwdExpire", expireTime)
        .withExpiresAt(date)
        .sign(algorithm);
  }


  /**
   * 检验token是否正确
   */
  public DecodedJWT verify(String token) {
    Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getTokenSecret());
    JWTVerifier verifier = JWT.require(algorithm).build();
    return verifier.verify(token);
  }

}
