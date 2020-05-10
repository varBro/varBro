DROP TABLE IF EXISTS madziarzy;

CREATE TABLE madziarzy (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL
);

INSERT INTO madziarzy (first_name, last_name) VALUES
  ('Paweł', 'Wąs'),
  ('Darek', 'Stopka'),
  ('Mariusz', 'Przysiężniuk'),
  ('Maciek', 'Sopata'),
  ('Wojtek', 'Sekta'),
  ('Karol', 'Zborowski');