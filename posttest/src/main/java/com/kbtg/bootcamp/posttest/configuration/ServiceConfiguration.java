package com.kbtg.bootcamp.posttest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.service.LotteryService;


@Configuration
public class ServiceConfiguration {

  @Autowired
  LotteryRepository lotteryRepository;

  @Bean
  public LotteryService lotteryService() {
    return new LotteryService(lotteryRepository);
  }
}
