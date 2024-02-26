package com.kbtg.bootcamp.posttest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kbtg.bootcamp.posttest.api.response.GetUserLotteryResponse;
import com.kbtg.bootcamp.posttest.api.response.PostUserLotteryResponse;
import com.kbtg.bootcamp.posttest.service.LotteryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.NonNull;

@Valid
@RestController
@RequestMapping("/users")
public class UserController {
  
  private final LotteryService lotteryService;

  public UserController(@NonNull LotteryService lotteryService) {
    this.lotteryService = lotteryService;
  }

  @GetMapping("/{userId}/lotteries")
  public ResponseEntity<GetUserLotteryResponse> getAllLotteriesForUserId(
    @PathVariable
    @NotBlank(message = "userId must not be empty.")
    @Pattern(regexp = "[0-9]{10}", message = "UserId should be from 0-9 for 10 digits. Example: 1234567890")
    String userId
  ) {
    GetUserLotteryResponse getUserLotteryResponse = lotteryService.getAllLotteriesForUserId(userId);

    return new ResponseEntity<>(getUserLotteryResponse, HttpStatus.OK);
  }
  

  @PostMapping("/{userId}/lotteries/{lotteryId}")
  public ResponseEntity<PostUserLotteryResponse> buyLotteryForUserId(
      @PathVariable
      @NotBlank(message = "userId must not be empty.")
      @Pattern(regexp = "[0-9]{10}", message = "UserId should be from 0-9 for 10 digits. Example: 1234567890")
      String userId,
      
      @PathVariable
      @NotBlank(message = "lotteryId must not be empty.")
      @Pattern(regexp = "[0-9]{6}", message = "Ticket number should be from 0-9 for 6 digits. Example: 123456")
      String lotteryId
  ) {

    PostUserLotteryResponse postUserLotteryResponse = lotteryService.purchaseOneLotteryForUserId(userId, lotteryId);
      
    return new ResponseEntity<>(postUserLotteryResponse, HttpStatus.OK);
  }
  
  @DeleteMapping("/{userId}/lotteries/{lotteryId}")
  public String sellLotteryForUserId(@RequestParam String param) {
    return new String();
  }
}
