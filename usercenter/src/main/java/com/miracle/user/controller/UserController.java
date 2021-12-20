package com.miracle.user.controller;

import com.miracle.common.Result;
import com.miracle.user.dto.UserDto;
import com.miracle.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @Autowired
  UserService userService;

  @GetMapping(path = "/login")
  @Operation(summary = "登录")
  public Result login(@RequestParam(name = "name") String name,
      @RequestParam(name = "password") String password) {

    return Result.succeed("success");

  }

  @PostMapping(path = "/register")
  @Operation(summary = "注册")
  public Result register(@RequestBody UserDto cmd) {
    userService.register(cmd);
    return Result.succeed();
  }

}
