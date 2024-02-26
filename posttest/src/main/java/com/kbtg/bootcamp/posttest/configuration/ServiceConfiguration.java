package com.kbtg.bootcamp.posttest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kbtg.bootcamp.posttest.controller.AdminController;
import com.kbtg.bootcamp.posttest.controller.LotteryController;
import com.kbtg.bootcamp.posttest.controller.UserController;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.repository.UserLotteryRepository;
import com.kbtg.bootcamp.posttest.repository.UserRepository;
import com.kbtg.bootcamp.posttest.service.LotteryService;


@Configuration
public class ServiceConfiguration {

  @Autowired
  LotteryRepository lotteryRepository;
  
  @Autowired
  UserRepository userRepository;

  @Autowired
  UserLotteryRepository userLotteryRepository;

  @Bean
  public AdminController adminController() {
    return new AdminController(lotteryService());
  }

  @Bean
  public LotteryController lotteryController() {
    return new LotteryController(lotteryService());
  }

  @Bean
  public UserController userController() {
    return new UserController(lotteryService());
  }

  @Bean
  public LotteryService lotteryService() {
    return new LotteryService(lotteryRepository, userRepository, userLotteryRepository);
  }
}
