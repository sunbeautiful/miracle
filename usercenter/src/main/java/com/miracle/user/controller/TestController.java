package com.miracle.user.controller;

import com.miracle.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sqq
 * @description
 * @date 2021/12/20 14:48
 */
@RestController
@RequestMapping(path = "/v1/user")
@Tag(name = "测试模块")
public class TestController {

  @PostMapping(path = "/test")
  @Operation(summary = "注册")
  public Result test() {
    return Result.succeed("oh ! test success ");
  }

}
