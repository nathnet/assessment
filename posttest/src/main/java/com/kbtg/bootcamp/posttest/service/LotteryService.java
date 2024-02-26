package com.kbtg.bootcamp.posttest.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbtg.bootcamp.posttest.api.request.PostLotteryRequest;
import com.kbtg.bootcamp.posttest.api.response.DeleteUserLotteryResponse;
import com.kbtg.bootcamp.posttest.api.response.GetLotteryResponse;
import com.kbtg.bootcamp.posttest.api.response.GetUserLotteryResponse;
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

  public GetUserLotteryResponse getAllLotteriesForUserId(String userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    User user = optionalUser
        .orElseThrow(() -> new BadRequestException(String.format("Your userID %s is incorrect", userId)));

    Optional<List<UserLottery>> optionalUserLotteryList = userLotteryRepository.findAllByUser(user);
    List<UserLottery> userLotteryList = optionalUserLotteryList.orElse(new ArrayList<>());

    List<String> lotteries = new ArrayList<>();
    int userLotteryCount = 0;
    BigDecimal totalCost = new BigDecimal(0);
    for (UserLottery userLottery : userLotteryList) {
      String lotteryId = userLottery.getLottery().getLotteryId();
      int lotteryAmount = userLottery.getLotteryAmount();

      for (int count = 0; count < lotteryAmount; count++) {
        lotteries.add(lotteryId);
      }

      userLotteryCount += lotteryAmount;
      totalCost = totalCost.add(userLottery.getPriceAtPurchase());
    }

    return GetUserLotteryResponse.builder()
        .lotteries(lotteries)
        .count(userLotteryCount)
        .totalCost(totalCost)
        .build();
  }

  public PostUserLotteryResponse purchaseOneLotteryForUserId(String userId, String lotteryId) {
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
        .priceAtPurchase(lottery.getPrice())
        .build();
    UserLottery savedUserLottery = userLotteryRepository.save(userLottery);

    return PostUserLotteryResponse.builder().transactionId(savedUserLottery.getTransactionId()).build();
  }

  public DeleteUserLotteryResponse sellLotteryForUserId(String userId, String lotteryId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    User user = optionalUser
        .orElseThrow(() -> new BadRequestException(String.format("Your userID %s is incorrect", userId)));

    Optional<List<UserLottery>> optionalUserLotteryList = userLotteryRepository.findAllByUser(user);
    List<UserLottery> userLotteryList = optionalUserLotteryList.orElse(new ArrayList<>());

    List<UserLottery> lotteriesToBeSold = userLotteryList.stream()
        .filter(userLottery -> lotteryId.equals(userLottery.getLottery().getLotteryId()))
        .collect(Collectors.toList());

    if (lotteriesToBeSold.size() == 0) {
      throw new BadRequestException(String.format("Sale canceled. You do not own any lottery with id %s.", lotteryId));
    }

    userLotteryRepository.deleteAll(lotteriesToBeSold);

    return DeleteUserLotteryResponse.builder().lotteryId(lotteryId).build();
  }
}
