DROP TABLE IF EXISTS lottery;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_ticket;

CREATE TABLE lottery (
  lottery_id char(6) PRIMARY KEY,
  price numeric(15, 2),
  amount_available int
);

CREATE TABLE users (
  user_id char(10) PRIMARY KEY,
  name varchar(255),
  email varchar(255),
  password varchar(255)
);

CREATE TABLE user_ticket(
  transaction_id serial PRIMARY KEY,
  lottery_id char(6) NOT NULL,
  user_id char(10) NOT NULL,
  ticket_amount int,
  FOREIGN KEY (lottery_id) REFERENCES lottery (lottery_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);