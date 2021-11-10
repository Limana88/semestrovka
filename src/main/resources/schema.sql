create table account (
                         id serial primary key ,
                         first_name varchar,
                         last_name varchar,
                         email varchar,
                         hash_password varchar,
                         uuid uuid
);

create table hotel (
                       id serial primary key ,
                       name varchar,
                       locality varchar,
                       street varchar,
                       building_number varchar,
                       image varchar,
                       number_rooms integer,
                       number_free_rooms integer
);

create table reservation (
                             id serial primary key ,
                             user_id integer,
                             hotel_id integer,
                             foreign key (user_id) references account(id) on delete cascade ,
                             foreign key (hotel_id) references hotel(id) on delete cascade ,
                             check_in_date timestamp,
                             check_out_date timestamp,
                             check ( check_out_date > reservation.check_in_date ),
                             reservation_status text check ( reservation_status in ('active', 'inactive'))
);