CREATE TABLE Product (
  id SERIAL PRIMARY KEY,
  name VARCHAR (40) NOT NULL,
  price INTEGER NOT NULL,
  creation_date DATE NOT NULL);

  INSERT INTO Product (name, price, creation_date) VALUES ('milk', 18, '2021-04-08');
  INSERT INTO Product (name, price, creation_date) VALUES ('bread', 14, '2022-03-11');
  INSERT INTO Product (name, price, creation_date) VALUES ('honey', 21, '2021-07-24');