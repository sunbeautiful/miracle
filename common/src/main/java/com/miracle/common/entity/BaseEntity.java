package com.miracle.common.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author sqq
 * @description
 * @date 2021/12/10 15:14
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

  private static final long serialVersionUID = 8659694161444941818L;
  @Id
  @GenericGenerator(
      name = "uuid2",
      strategy = "uuid2",
      parameters = {
          @org.hibernate.annotations.Parameter(
              name = "uuid_gen_strategy_class",
              value = "org.hibernate.id.uuid.CustomVersionOneStrategy")
      })
  @GeneratedValue(generator = "uuid2")
  @Column
  private String id;
  /**
   * 内部版本
   */
  @Column(name = "inner_version")
  @Version
  private Long innerVersion;

  /**
   * 创建人
   */
  @Column(name = "creator")
  @CreatedBy
  private String creator;

  /**
   * 更新人
   */
  @Column(name = "updater")
  @LastModifiedBy
  private String updater;

  /**
   * 创建时间
   */
  @Column(name = "create_time")
  @CreatedDate
  private Date createTime;

  /**
   * 更新时间
   */
  @Column(name = "update_time")
  @LastModifiedDate
  private Date updateTime;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
