package com.example.guest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@Data
@AllArgsConstructor
public class GuestDto {
  private BigInteger id;
  private String name;
  private String email;
  private String phone;
}
