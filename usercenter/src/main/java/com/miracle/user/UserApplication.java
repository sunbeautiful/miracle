package com.miracle.user;

import com.miracle.common.annotation.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author sqq
 * @Description
 * @Date 2021/12/10 13:54
 **/
@SpringBootApplication
@EnableSwagger
public class UserApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }

}
