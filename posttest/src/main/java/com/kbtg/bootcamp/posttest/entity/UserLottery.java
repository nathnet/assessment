package com.kbtg.bootcamp.posttest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "user_lottery")
public class UserLottery {
  
  @Id
  @NonNull
  Integer transactionId;

  @ManyToOne
  @JoinColumn(name = "lottery_id")
  Lottery lottery;

  @ManyToOne
  @JoinColumn(name = "user_id")
  User user;

  Integer lotteryAmount;
}
