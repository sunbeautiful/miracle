package com.miracle.user.domain;

import com.miracle.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @Author sqq
 * @Date 2021-12-10 15:05:16
 */
@Entity
@DynamicUpdate
@Table(name = "uc_user")
public class User extends BaseEntity {

  private static final long serialVersionUID = 6774401548881397981L;

  /**
   * 姓名
   */
  @Column(name = "name")
  private String name;

  /**
   * 密码
   */
  @Column(name = "password")
  private String password;

  /**
   * 盐值
   */
  @Column(name = "salt")
  private String salt;

  /**
   * 状态
   */
  @Column(name = "state")
  private String state;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
