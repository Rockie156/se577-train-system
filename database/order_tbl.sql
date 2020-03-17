CREATE TABLE order_tbl
(
    id    serial primary key,
    email text
);

CREATE TABLE order_itinerary_tbl
(
    id       serial primary key,
    order_id int not null references order_tbl (id)
);

CREATE TABLE order_segment_tbl
(
    id               serial primary key,
    itinerary_id     int     not null references order_itinerary_tbl (id),
    trip_id          int     not null references trip (id),
    start_station_id char(3) not null references stop (id),
    end_station_id   char(3) not null references stop (id),
    sequence         int     not null
);
