create table if not exists post (
	id serial PRIMARY KEY,
	name TEXT,
	text VARCHAR(8000),
	link TEXT unique not null,
	created TIMESTAMP,
	UNIQUE(link)
);