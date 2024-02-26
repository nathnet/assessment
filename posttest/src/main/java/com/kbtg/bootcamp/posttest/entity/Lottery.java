package com.kbtg.bootcamp.posttest.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@Entity
@Table(name = "lottery")
public class Lottery {
  
  @Id
  @NonNull
  String lotteryId;

  @Column(name = "price")
  @NonNull
  BigDecimal price;

  @Column(name = "amount_available")
  @NonNull
  Integer amountAvailable;
}
