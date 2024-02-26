package com.kbtg.bootcamp.posttest.api.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PostLotteryRequest {
  
  @NonNull
  @NotBlank(message = "Ticket number cannot be empty.")
  @Size(min = 6, max = 6, message = "Ticker number must be exactly 6 digits.")
  @Pattern(regexp = "[0-9]{6}", message = "Ticket number should be from 0-9 for 6 digits. Example: 123456")
  @JsonProperty("ticket")
  String lotteryId;

  @PositiveOrZero
  @JsonProperty("price")
  BigDecimal price;

  @PositiveOrZero
  @JsonProperty("amount")
  Integer amount;
}
