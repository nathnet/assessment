package com.kbtg.bootcamp.posttest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbtg.bootcamp.posttest.api.request.PostLotteryRequest;
import com.kbtg.bootcamp.posttest.api.response.GetLotteryResponse;
import com.kbtg.bootcamp.posttest.api.response.PostLotteryResponse;
import com.kbtg.bootcamp.posttest.api.response.PostUserLotteryResponse;
import com.kbtg.bootcamp.posttest.entity.Lottery;
import com.kbtg.bootcamp.posttest.entity.User;
import com.kbtg.bootcamp.posttest.entity.UserLottery;
import com.kbtg.bootcamp.posttest.exception.BadRequestException;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.repository.UserLotteryRepository;
import com.kbtg.bootcamp.posttest.repository.UserRepository;

import lombok.NonNull;

@Service
public class LotteryService {

  private final LotteryRepository lotteryRepository;
  private final UserRepository userRepository;
  private final UserLotteryRepository userLotteryRepository;

  public LotteryService(@NonNull final LotteryRepository lotteryRepository,
      @NonNull final UserRepository userRepository,
      @NonNull final UserLotteryRepository userLotteryRepository) {
    this.lotteryRepository = lotteryRepository;
    this.userRepository = userRepository;
    this.userLotteryRepository = userLotteryRepository;
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

  public PostUserLotteryResponse purchaseOneLottery(String userId, String lotteryId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    Optional<Lottery> optionalLottery = lotteryRepository.findById(lotteryId);

    User user = optionalUser
        .orElseThrow(() -> new BadRequestException(String.format("Your userID %s is incorrect", userId)));
    Lottery lottery = optionalLottery
        .orElseThrow(() -> new BadRequestException(String.format("The lottery you want to purchase with id %s does not exist", lotteryId)));

    UserLottery userLottery = UserLottery.builder()
        .user(user)
        .lottery(lottery)
        .lotteryAmount(1) // magic!
        .build();
    UserLottery savedUserLottery = userLotteryRepository.save(userLottery);

    return PostUserLotteryResponse.builder().transactionId(savedUserLottery.getTransactionId()).build();
  }
}
