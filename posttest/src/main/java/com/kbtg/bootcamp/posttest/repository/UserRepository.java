package com.kbtg.bootcamp.posttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kbtg.bootcamp.posttest.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  
}
