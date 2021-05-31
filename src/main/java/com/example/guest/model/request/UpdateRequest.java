package com.example.guest.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UpdateRequest {
  private String name;
  private String phone;
}
