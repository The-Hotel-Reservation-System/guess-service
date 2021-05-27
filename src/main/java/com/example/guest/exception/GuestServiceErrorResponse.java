package com.example.guest.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GuestServiceErrorResponse {
  UNHANDLED_EXCEPTION(HttpStatus.BAD_REQUEST, "1000", "Unhandled exception."),
  GUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "1001", "Guest is not found."),
  INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, "1002", "");

  GuestServiceErrorResponse(HttpStatus httpStatus, String errorCode, String errorMessage) {
    this.httpStatus = httpStatus;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  private HttpStatus httpStatus;
  private String errorCode;
  private String errorMessage;

}
