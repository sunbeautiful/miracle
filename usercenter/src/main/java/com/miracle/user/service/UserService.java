package com.miracle.user.service;

import com.miracle.common.constants.UserStates;
import com.miracle.user.domain.User;
import com.miracle.user.dto.UserDto;
import com.miracle.user.repository.UserRepository;
import java.util.Collections;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * @author sqq
 * @description
 * @date 2021/12/14 10:12
 */
@Service
public class UserService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
    String password = DigestUtils.md5DigestAsHex(cmd.getPassword().getBytes());
    String salt = RandomStringUtils.randomAlphanumeric(6);
    String newPass = DigestUtils.md5DigestAsHex((password + salt).getBytes());
    User user = new User(cmd.getName(), newPass, salt, UserStates.active.name());
    userRepository.save(user);
  }


}
