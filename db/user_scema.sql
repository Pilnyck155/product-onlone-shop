CREATE TABLE shop_user (
  id SERIAL PRIMARY KEY,
  email VARCHAR (150) UNIQUE NOT NULL,
  password VARCHAR (30) NOT NULL);