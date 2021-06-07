package com.example.guest.exception;

import lombok.Getter;

public class GuestServiceException extends RuntimeException {
  @Getter
  final GuestServiceErrorResponse response;

  public GuestServiceException(GuestServiceErrorResponse response) {
    this.response = response;
  }

}
