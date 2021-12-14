package com.miracle.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sqq
 * @description
 * @date 2021/12/10 13:55
 */
@RestController
@RequestMapping(path = "/v1/user")
@Tag(name = "用户中心")
public class UserController {

  @RequestMapping(path = "/login")
  @Operation(summary = "登录")
  public String login(@RequestParam(name = "name") String name,
      @RequestParam(name = "password") String password) {
    return "success";

  }

}
