package com.miracle.user.service;

import com.miracle.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sqq
 * @description
 * @date 2021/12/14 10:12
 */
public class UserService {

  @Autowired
  UserRepository userRepository;

}
