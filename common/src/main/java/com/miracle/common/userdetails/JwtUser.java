package com.miracle.common.userdetails;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author sqq
 * @description
 * @date 2021/12/16 14:29
 */
public class JwtUser implements UserDetails {

  private static final long serialVersionUID = 3955554655886715447L;

  private String name;

  private String password;

  /**
   * 权限（角色）列表
   */
  Collection<? extends GrantedAuthority> authorities;

  public JwtUser(String name, String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.name = name;
    this.password = password;
    this.authorities = authorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAuthorities(
      Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }
}
