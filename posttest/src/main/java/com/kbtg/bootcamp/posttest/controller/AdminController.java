package com.kbtg.bootcamp.posttest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbtg.bootcamp.posttest.api.request.PostLotteryRequest;
import com.kbtg.bootcamp.posttest.api.response.PostLotteryResponse;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;

import lombok.NonNull;


@RestController
@RequestMapping("/admin")
public class AdminController {

  private LotteryService lotteryService;

  public AdminController(@NonNull LotteryService LotteryService) {
    this.lotteryService = LotteryService;
  }

  @PostMapping("/lotteries")
  public ResponseEntity<PostLotteryResponse> createOneLottery(@RequestBody PostLotteryRequest lottery) {
    PostLotteryResponse postLotteryResponse = lotteryService.createOneLottery(lottery);

    return new ResponseEntity<>(postLotteryResponse, HttpStatus.CREATED);
  }


  
}
