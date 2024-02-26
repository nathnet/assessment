package com.kbtg.bootcamp.posttest.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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
