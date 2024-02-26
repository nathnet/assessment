package com.kbtg.bootcamp.posttest.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class GetLotteryResponse {
  
  @NonNull
  @JsonProperty("tickets")
  List<String> lotteries;
}
