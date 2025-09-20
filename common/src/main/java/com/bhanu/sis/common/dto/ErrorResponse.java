package com.bhanu.sis.common.dto;
import java.time.Instant;
public class ErrorResponse {
  public Instant timestamp = Instant.now();
  public String path;
  public String message;
  public String code;
  public ErrorResponse(String path, String message, String code){
    this.path=path; this.message=message; this.code=code;
  }
}
