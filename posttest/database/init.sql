DROP TABLE IF EXISTS lottery;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_lottery;

CREATE TABLE lottery (
  lottery_id varchar(6) CHECK (LENGTH(lottery_id) = 6) PRIMARY KEY,
  price numeric(15, 2) CHECK (price >= 0),
  amount_available int CHECK (amount_available >= 0)
);

CREATE TABLE users (
  user_id varchar(10) CHECK (LENGTH(user_id) = 10) PRIMARY KEY,
  name varchar(255),
  email varchar(255),
  password varchar(255)
);

CREATE TABLE user_lottery (
  transaction_id serial PRIMARY KEY,
  lottery_id varchar(6) CHECK (LENGTH(lottery_id) = 6) NOT NULL,
  user_id varchar(10) CHECK (LENGTH(user_id) = 10) NOT NULL,
  lottery_amount int CHECK (lottery_amount > 0),
  price_at_purchase numeric(15, 2) CHECK (price_at_purchase >= 0),
  FOREIGN KEY (lottery_id) REFERENCES lottery (lottery_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);