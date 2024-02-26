package com.kbtg.bootcamp.posttest.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_lottery")
public class UserLottery {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer transactionId;

  @ManyToOne
  @JoinColumn(name = "lottery_id")
  Lottery lottery;

  @ManyToOne
  @JoinColumn(name = "user_id")
  User user;

  @Column(name = "lottery_amount")
  Integer lotteryAmount;

  @Column(name = "price_at_purchase")
  BigDecimal priceAtPurchase;
}
