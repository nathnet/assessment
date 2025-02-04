package com.kbtg.bootcamp.posttest.api.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PostLotteryRequest {
  
  @NotBlank(message = "Ticket number cannot be empty.")
  @Pattern(regexp = "[0-9]{6}", message = "Ticket number should be from 0-9 for 6 digits. Example: 123456")
  @JsonProperty("ticket")
  String lotteryId;

  @NotNull(message = "Price cannot be empty.")
  @PositiveOrZero
  @JsonProperty("price")
  BigDecimal price;

  @NotNull(message = "Amount cannot be empty.")
  @PositiveOrZero
  @JsonProperty("amount")
  Integer amount;
}
