package com.kbtg.bootcamp.posttest.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kbtg.bootcamp.posttest.api.request.PostLotteryRequest;
import com.kbtg.bootcamp.posttest.api.response.PostLotteryResponse;
import com.kbtg.bootcamp.posttest.entity.Lottery;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;

import lombok.NonNull;

@Service
public class LotteryService {

  private final LotteryRepository lotteryRepository;

  public LotteryService(@NonNull final LotteryRepository lotteryRepository) {
    this.lotteryRepository = lotteryRepository;
  }

  public PostLotteryResponse createOneLottery(final PostLotteryRequest request) {
    String lotteryId = request.getLotteryId();
    Optional<Lottery> optionalLottery = lotteryRepository.findById(lotteryId);

    int totalAmount = request.getAmount();

    if (optionalLottery.isPresent()) {
        Lottery existingLottery = optionalLottery.get();
        totalAmount += existingLottery.getAmountAvailable();
    }

    Lottery lottery = Lottery.builder()
        .lotteryId(lotteryId)
        .price(request.getPrice())
        .amountAvailable(totalAmount)
        .build();

    lotteryRepository.save(lottery);

    return PostLotteryResponse.builder().lotteryId(lotteryId).build();
  }
}
