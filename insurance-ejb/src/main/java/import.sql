insert into Province (id, name, version) values (1, 'Mazowieckie', 0);
insert into Province (id, name, version) values (2, 'Podkarpackie', 0);

insert into City (id, provinceId, name, description, version) values (1, 1, 'Kielce', 'Kielce, Mazowieckie', 0);
insert into City (id, provinceId, name, description, version) values (2, 1, 'Warszawa', 'Warszawa, Mazowieckie', 0);
insert into City (id, provinceId, name, description, version) values (3, 2, 'Gdynia', 'Gdynia, Podkarpackie', 0);
insert into City (id, provinceId, name, description, version) values (4, 2, 'Kraków', 'Kraków, Podkarpackie', 0);

insert into Street (id, cityId, name, description, version) values (1, 1, 'ul. Szczygla', 'ul. Szczygla, Kielce, Mazowieckie', 0);
insert into Street (id, cityId, name, description, version) values (2, 1, 'ul. Warszawska', 'ul. Warszawska, Kielce, Mazowieckie', 0);
insert into Street (id, cityId, name, description, version) values (3, 2, 'ul. Sienkiewicza', 'ul. Sienkiewicza, Warszawa, Mazowieckie', 0);
insert into Street (id, cityId, name, description, version) values (4, 2, 'ul. Sowia', 'ul. Sowia, Warszawa, Mazowieckie', 0);
insert into Street (id, cityId, name, description, version) values (5, 3, 'ul. Krakowska', 'ul. Krakowska, Gdynia, Podkarpackie', 0);
insert into Street (id, cityId, name, description, version) values (6, 3, 'ul. Kielecka', 'ul. Kielecka, Gdynia, Podkarpackie', 0);
insert into Street (id, cityId, name, description, version) values (7, 4, 'ul. Sosnowa', 'ul. Sosnowa, Kraków, Podkarpackie', 0);
insert into Street (id, cityId, name, description, version) values (8, 4, 'ul. Paderewskiego', 'ul. Paderewskiego, Kraków, Podkarpackie', 0);

insert into Product (id, code, name, description) values (1, 'U24', 'Ubezpieczenie standard 24', 'Ubezpieczenie to pozwala na super i hiper i wogole i zeby bylo super');
