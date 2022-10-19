create table post (
	id serial PRIMARY KEY,
	name TEXT,
	text VARCHAR(8000),
	link TEXT,
	created TIMESTAMP,
	UNIQUE(link)
);