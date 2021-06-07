package com.example.guest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "guest", schema = "public")
public class Guest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;
  private String name;
  private String email;
  private String phone;
  private Instant createdDate;
}
