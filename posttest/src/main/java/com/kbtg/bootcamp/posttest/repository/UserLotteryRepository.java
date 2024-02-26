package com.kbtg.bootcamp.posttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kbtg.bootcamp.posttest.entity.UserLottery;

@Repository
public interface UserLotteryRepository extends JpaRepository<UserLottery, Long> {
  
}
