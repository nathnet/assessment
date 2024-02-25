DROP TABLE IF EXISTS lottery;
DROP TABLE IF EXISTS user_ticket;

CREATE TABLE lottery (
  lottery_id char(6) PRIMARY KEY,
  price numeric(, 2),
  amount_available int,
);

CREATE TABLE user_ticket(
  FOREIGN KEY (lottery_id) char(6) REFERENCES lottery (lottery_id),
  FOREIGN KEY (user_id) varchar(255) REFERENCES user (user_id),
  amount int,
);

CREATE TABLE user (
  pk serial PRIMARY KEY,
  user_id uuid NOT NULL,
  name varchar(255),
  email varchar(255),
  password varchar(255),
);