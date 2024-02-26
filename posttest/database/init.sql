DROP TABLE IF EXISTS lottery;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_lottery;

CREATE TABLE lottery (
  lottery_id varchar(6) PRIMARY KEY,
  price numeric(15, 2),
  amount_available int
);

CREATE TABLE users (
  user_id varchar(10) PRIMARY KEY,
  name varchar(255),
  email varchar(255),
  password varchar(255)
);

CREATE TABLE user_lottery (
  transaction_id serial PRIMARY KEY,
  lottery_id varchar(6) NOT NULL,
  user_id varchar(10) NOT NULL,
  lottery_amount int,
  FOREIGN KEY (lottery_id) REFERENCES lottery (lottery_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);