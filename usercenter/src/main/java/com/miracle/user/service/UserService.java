package com.miracle.user.service;

import com.miracle.common.constants.UserStates;
import com.miracle.user.config.JwtProperties;
import com.miracle.user.domain.User;
import com.miracle.user.dto.UserDto;
import com.miracle.user.repository.UserRepository;
import java.util.Collections;
import java.util.Optional;
import javax.annotation.Resource;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sqq
 * @description
 * @date 2021/12/14 10:12
 */
@Service
public class UserService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Resource
  JwtProperties jwtProperties;

  /**
   * 自定义UserDetailsService
   */
  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<User> firstByName = userRepository.findFirstByName(username);
    if (firstByName.isPresent()) {
      User user = firstByName.get();
      return new org.springframework.security.core.userdetails.User(user.getName(),
          user.getPassword(),
          //测试用
          Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + "admin")));
    }
    throw new UsernameNotFoundException("account not exist");

  }

  @Transactional(rollbackFor = Exception.class)
  public void register(UserDto cmd) {
    Optional<User> firstByName = userRepository.findFirstByName(cmd.getName());
    Validate.isTrue(!firstByName.isPresent(), "account already exist");
//    String password = DigestUtils.md5DigestAsHex(cmd.getPassword().getBytes());
//    String salt = RandomStringUtils.randomAlphanumeric(6);
//    String newPass = DigestUtils.md5DigestAsHex((password + salt).getBytes());
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // 加密
    String encodedPassword = passwordEncoder.encode(cmd.getPassword().trim());
    User user = new User(cmd.getName(), encodedPassword, null, UserStates.active.name());
    userRepository.save(user);
  }


}
