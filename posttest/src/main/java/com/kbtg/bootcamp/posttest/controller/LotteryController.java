package com.kbtg.bootcamp.posttest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbtg.bootcamp.posttest.api.response.GetLotteryResponse;
import com.kbtg.bootcamp.posttest.service.LotteryService;

import lombok.NonNull;

@RestController
@RequestMapping("/lotteries")
public class LotteryController {

  private final LotteryService lotteryService;

  public LotteryController(@NonNull LotteryService lotteryService) {
    this.lotteryService = lotteryService;
  }

  @GetMapping("")
  public ResponseEntity<GetLotteryResponse> getLotteries() {
    GetLotteryResponse getLotteryResponse = lotteryService.getAllLotteries();
    return new ResponseEntity<>(getLotteryResponse, HttpStatus.OK);
  }
}
