INSERT INTO vets VALUES (1, 'Predio15', 'Buffet');
INSERT INTO vets VALUES (2, 'Predio40', 'Fancy');
INSERT INTO vets VALUES (3, 'Silva', 'LaMinuta');
INSERT INTO vets VALUES (4, 'Adm', 'LaCarte');
INSERT INTO vets VALUES (5, 'Outros', 'Outros');
INSERT INTO vets VALUES (6, 'Predio12', 'Bandejao');

INSERT INTO restaurants VALUES (1, 'Predio15', '- Puc');
INSERT INTO restaurants VALUES (2, 'Predio40', '- Puc');
INSERT INTO restaurants VALUES (3, 'Silva', '- Bento Gonçalves');
INSERT INTO restaurants VALUES (4, 'Adm', ' - Puc');
INSERT INTO restaurants VALUES (5, 'Outside', '- Porto ALegre');
INSERT INTO restaurants VALUES (6, 'Subway', '- Tecnopuc');

INSERT INTO specialties VALUES (1, 'Buffet');
INSERT INTO specialties VALUES (2, 'Fancy');
INSERT INTO specialties VALUES (3, 'LaMinuta');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO owners VALUES (1, 'George', 'Franklin', 'henrique', 'teste123', '110 W. Liberty St.', 'Madison', '6085551023');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', 'henrique', 'teste123', '638 Cardinal Ave.', 'Sun Prairie', '6085551749');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', 'henrique', 'teste123', '2693 Commerce St.', 'McFarland', '6085558763');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', 'henrique', 'teste123', '563 Friendly St.', 'Windsor', '6085553198');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', 'henrique', 'teste123', '2387 S. Fair Way', 'Madison', '6085552765');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', 'henrique', 'teste123', '105 N. Lake St.', 'Monona', '6085552654');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', 'henrique', 'teste123', '1450 Oak Blvd.', 'Monona', '6085555387');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', 'henrique', 'teste123', '345 Maple St.', 'Madison', '6085557683');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', 'henrique', 'teste123', '2749 Blackhawk Trail', 'Madison', '6085559435');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', 'henrique', 'teste123', '2335 Independence La.', 'Waunakee', '6085555487');

INSERT INTO pets VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets VALUES (13, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits VALUES (1, 7, '2016-01-10', 'rabies shot');
INSERT INTO visits VALUES (2, 8, '2016-01-11', 'rabies shot');
INSERT INTO visits VALUES (3, 8, '2016-01-11', 'rabies shot');
INSERT INTO visits VALUES (4, 8, '2016-01-12', 'neutered');
INSERT INTO visits VALUES (5, 7, '2016-01-13', 'spayed');
INSERT INTO visits VALUES (6, 7, '2016-01-14', 'spayed');
INSERT INTO visits VALUES (7, 7, '2016-01-15', 'spayed');
INSERT INTO visits VALUES (8, 8, '2016-01-16', 'neutered');
INSERT INTO visits VALUES (9, 7, '2016-01-01', 'rabies shot');
INSERT INTO visits VALUES (10, 7, '2016-01-01', 'rabies shot');

INSERT INTO userSelection VALUES (3, 3, 3, '2013-01-03', 'theBest');
INSERT INTO userSelection VALUES (4, 8, 1, '2016-01-12', 'neutered');
INSERT INTO userSelection VALUES (5, 7, 2, '2016-01-13', 'spayed');
INSERT INTO userSelection VALUES (6, 7, 3, '2016-01-14', 'spayed');
INSERT INTO userSelection VALUES (7, 4, 2, '2016-01-14', 'spayed');
INSERT INTO userSelection VALUES (8, 7, 2, '2016-01-15', 'spayed');
INSERT INTO userSelection VALUES (9, 3, 2, '2016-01-15', 'spayed');
INSERT INTO userSelection VALUES (10, 4, 3, '2016-01-15', 'spayed');
INSERT INTO userSelection VALUES (11, 8, 4, '2016-01-16', 'neutered');
INSERT INTO userSelection VALUES (12, 7, 3, '2016-01-01', 'rabies shot');
INSERT INTO userSelection VALUES (13, 7, 2, '2016-01-01', 'rabies shot');
