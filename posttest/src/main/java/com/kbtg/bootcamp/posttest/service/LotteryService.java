package com.kbtg.bootcamp.posttest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbtg.bootcamp.posttest.api.request.PostLotteryRequest;
import com.kbtg.bootcamp.posttest.api.response.GetLotteryResponse;
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

  public GetLotteryResponse getAllLotteries() {
    List<String> allLotteryIds = lotteryRepository.findAll()
        .stream()
        .map(Lottery::getLotteryId)
        .collect(Collectors.toList());

    return GetLotteryResponse.builder().lotteries(allLotteryIds).build();
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
