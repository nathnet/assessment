package com.kbtg.bootcamp.posttest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@Entity
@Table(name = "user_lottery")
public class UserLottery {
  
}
