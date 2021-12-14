package com.miracle.user.dto;

import java.util.Date;

/**
 * @Author sqq
 * @Date 2021-12-10 16:48:25
 */
public class UserDto {


  private String id;

  /**
   * 姓名
   */
  private String name;

  /**
   * 密码
   */
  private String password;

  /**
   * 盐值
   */
  private String salt;

  /**
   * 状态
   */
  private String state;

  /**
   * 内部版本
   */
  private Long innerVersion;

  /**
   * 创建人
   */
  private String creator;

  /**
   * 更新人
   */
  private String updater;

  /**
   * 创建时间
   */
  private Date createTime;

  /**
   * 更新时间
   */
  private Date updateTime;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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

  public Long getInnerVersion() {
    return innerVersion;
  }

  public void setInnerVersion(Long innerVersion) {
    this.innerVersion = innerVersion;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public String getUpdater() {
    return updater;
  }

  public void setUpdater(String updater) {
    this.updater = updater;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}
