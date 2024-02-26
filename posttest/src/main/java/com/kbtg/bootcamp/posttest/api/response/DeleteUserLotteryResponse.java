package com.kbtg.bootcamp.posttest.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class DeleteUserLotteryResponse {
  
  @NonNull
  @JsonProperty("ticket")
  String lotteryId;
}
