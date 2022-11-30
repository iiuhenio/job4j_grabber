CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, "name") values (1, 'Samsung');
INSERT INTO company (id, "name") values (2, 'Sony');
INSERT INTO company (id, "name") values (3, 'Toshiba');
INSERT INTO company (id, "name") values (4, 'LG');
INSERT INTO company (id, "name") values (5, 'Hitachi');
INSERT INTO company (id, "name") values (6, 'Bosch');

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO person (id, "name", company_id) values (1, 'Anton', 1);
INSERT INTO person (id, "name", company_id) values (2, 'Ivan', 1);
INSERT INTO person (id, "name", company_id) values (3, 'Vlad', 2);
INSERT INTO person (id, "name", company_id) values (4, 'Semen', 2);
INSERT INTO person (id, "name", company_id) values (5, 'Andrey', 3);
INSERT INTO person (id, "name", company_id) values (6, 'Aleksey', 3);
INSERT INTO person (id, "name", company_id) values (7, 'Stepan', 3);
INSERT INTO person (id, "name", company_id) values (8, 'Iliya', 4);
INSERT INTO person (id, "name", company_id) values (9, 'Evgeni', 4);
INSERT INTO person (id, "name", company_id) values (10, 'Vladimir', 5);
INSERT INTO person (id, "name", company_id) values (11, 'Svetlana', 5);
INSERT INTO person (id, "name", company_id) values (12, 'Mariya', 5);
INSERT INTO person (id, "name", company_id) values (13, 'Anna', 6);
INSERT INTO person (id, "name", company_id) values (14, 'Ekaterina', 6);
INSERT INTO person (id, "name", company_id) values (15, 'Viktoriya', 6);

SELECT * FROM person JOIN company ON company.id = person.company_id WHERE company.id != 5;

select company.name, count(person.company_id) maximum
from person
join company on person.company_id=company.id
group by company.name
having COUNT(person.id) = (
 	select max(m.max_id)
 	from (
 		select company_id, count(id) max_id
 		from person
 		group by company_id
 	) m);
