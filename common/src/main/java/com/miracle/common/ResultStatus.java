package com.miracle.common;


public enum ResultStatus {
  /**
   * http返回状态
   */
  ERROR("error"),
  WARNING("warning"),
  SUCCESS("success");

  String status;

  ResultStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
