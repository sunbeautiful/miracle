package com.miracle.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sqq
 * @description jwt配置类
 * @date 2021/12/17 11:01
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

  private long expireTime;

  private String tokenSecret;

  public long getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(long expireTime) {
    this.expireTime = expireTime;
  }

  public String getTokenSecret() {
    return tokenSecret;
  }

  public void setTokenSecret(String tokenSecret) {
    this.tokenSecret = tokenSecret;
  }
}
