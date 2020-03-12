CREATE TABLE order_tbl
(
    id    serial primary key,
    email text
);

CREATE TABLE order_item_tbl
(
    id               serial primary key,
    trip_id          int     not null references trip (id),
    start_station_id char(3) not null references stop (id),
    end_station_id   char(3) not null references stop (id),
    sequence         int     not null
);

CREATE TABLE order_x_item_tbl
(
    id            serial primary key,
    order_id      int references order_tbl (id),
    order_item_id int references order_item_tbl (id)
);
