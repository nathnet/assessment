package com.kbtg.bootcamp.posttest.service;

import com.kbtg.bootcamp.posttest.api.request.PostLotteryRequest;
import com.kbtg.bootcamp.posttest.api.response.PostLotteryResponse;

public class LotteryService {

  public LotteryService() {}

  public PostLotteryResponse createOneLottery(PostLotteryRequest request) {
    return PostLotteryResponse.builder().lotteryId("test").build();
  }
}
