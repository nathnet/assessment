package com.kbtg.bootcamp.posttest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kbtg.bootcamp.posttest.entity.User;
import com.kbtg.bootcamp.posttest.entity.UserLottery;

@Repository
public interface UserLotteryRepository extends JpaRepository<UserLottery, Integer> {
  Optional<List<UserLottery>> findAllByUser(User user);
}
