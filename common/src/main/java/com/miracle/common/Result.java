package com.miracle.common;


import java.io.Serializable;


public class Result<T> implements Serializable {

  private static final long serialVersionUID = 165165465465461346L;

  private String status;

  private String message;

  private String stackInfo;

  private T data;

  private static final Result SUCCESS = succeedWith(null, null);

  private static final Result FAILED = failedWith(null, null);

  private static final Result WARNING = warningWith(null, null);

  public Result() {
  }

  public Result(String status, String message, String stackInfo, T data) {
    this.status = status;
    this.message = message;
    this.stackInfo = stackInfo;
    this.data = data;
  }

  public static <T> Result<T> succeedWith(T data, String msg) {
    return new Result<T>(ResultStatus.SUCCESS.getStatus(), msg, null,
        data);
  }

  public static <T> Result failedWith(T data, String msg) {
    return new Result<T>(ResultStatus.ERROR.getStatus(), msg, null, data);
  }

  public static <T> Result failedWith(T data, String msg,
      String errorMessage) {
    return new Result<T>(ResultStatus.ERROR.getStatus(), msg, errorMessage,
        data);
  }

  public static <T> Result<T> warningWith(T data, String msg) {
    return new Result<T>(ResultStatus.WARNING.getStatus(), null, msg,
        data);
  }


  public static Result succeed() {
    return SUCCESS;
  }


  public static <T> Result<T> succeed(T data) {
    return succeedWith(data, null);
  }


  public static <T> Result<T> succeed(T data, String message) {
    return succeedWith(data, message);
  }


  public static <T> Result<T> failed() {
    return FAILED;
  }


  public static <T> Result<T> failed(T data) {
    return failedWith(data, null);
  }


  public static <T> Result<T> failed(T data, String message) {
    return failedWith(data, message);
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStackInfo() {
    return stackInfo;
  }

  public void setStackInfo(String stackInfo) {
    this.stackInfo = stackInfo;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
