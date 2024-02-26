package com.kbtg.bootcamp.posttest.lottery;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@Entity
@Table(name = "lottery")
public class Lottery {
  
}
