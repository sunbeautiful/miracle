package com.miracle.user.controller;

import com.miracle.user.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sqq
 * @description
 * @date 2021/12/14 14:16
 */
@RestController
@RequestMapping(path = "/v1/user")
@Tag(name = "第三方登录")
public class ThirdPartyController {

  @PostMapping(path = "/third-party/")
  public void create(@RequestBody UserDto userDto) {
    System.out.println("success");
  }

}
