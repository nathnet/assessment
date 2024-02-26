package com.kbtg.bootcamp.posttest.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Value;

@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @NonNull
  String userId;

  @Column(name = "name")
  String name;

  @Column(name = "email")
  String email;

  @Column(name = "password")
  String password;

  @OneToMany(mappedBy = "user", targetEntity = UserLottery.class)
  List<Lottery> lotteries;
}
