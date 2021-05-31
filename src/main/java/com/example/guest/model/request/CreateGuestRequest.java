package com.example.guest.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
public class CreateGuestRequest {
  @NotBlank(message = "name should not be blank.")
  private String name;
  @NotBlank(message = "email should not be blank.")
  private String email;
  @NotBlank(message = "phone should not be blank.")
  private String phone;
}
