DROP TABLE IF EXISTS beerStyle;
CREATE TABLE beerStyle (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  temperatura_minima INT NOT NULL,
  temperatura_maxima INT NOT NULL
);

INSERT INTO
  beerStyle(nome,temperatura_minima,temperatura_maxima)
values
  ('Weissbier',-1,-3),
  ('Pilsens',-2,4),
  ('Weizenbier',-4,6),
  ('Red ale',-5,5),
  ('India pale ale',-6,7),
  ('IPA',-7,10),
  ('Dunkel',-8,2),
  ('Imperial Stouts',-10,13),
  ('Brown ale',0,14);
