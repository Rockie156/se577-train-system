CREATE TABLE user_tbl (
    id serial primary key,
    name text,
    email text,
    external_id int unique
);
