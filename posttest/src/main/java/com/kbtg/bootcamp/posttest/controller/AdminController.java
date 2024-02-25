package com.kbtg.bootcamp.posttest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/admin")
public class AdminController {
  public AdminController() {}

  @PostMapping("/lotteries")
  public String createOneLottery() {
      //TODO: process POST request
      return "test";
  }
  
}
