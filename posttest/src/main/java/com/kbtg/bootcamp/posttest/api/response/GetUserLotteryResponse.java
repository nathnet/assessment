package com.kbtg.bootcamp.posttest.api.response;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class GetUserLotteryResponse {
  
  @NonNull
  @JsonProperty("tickets")
  List<String> lotteries;

  @NonNull
  @JsonProperty("count")
  Integer count;

  @NonNull
  @JsonProperty("cost")
  BigDecimal totalCost;
}
