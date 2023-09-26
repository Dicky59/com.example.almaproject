CREATE TABLE customer
(
  id SERIAL NOT NULL,
  name varchar NOT NULL,
  email varchar,
  homepage varchar,
  businessid varchar,
  streetaddress varchar,
  phone varchar


);
INSERT INTO customer(name, email, homepage, businessid, streetaddress, phone)
VALUES ('Customer #1', 'info@customer1.fi', 'www.customer1.com', '121757-9', 'Oikokatu 3, 00170 Helsinki', '040 666 7721');

INSERT INTO customer(name, email, homepage, businessid, streetaddress, phone)
VALUES ('Customer #2', 'info@customer2.fi', 'www.customer2.com', '121337-7', 'Oikokatu 9, 00170 Helsinki', '040 666 5521');