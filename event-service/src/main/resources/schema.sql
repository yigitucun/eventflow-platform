DROP TABLE IF EXISTS event_staff;
DROP TABLE IF EXISTS events;

CREATE TABLE events (
    id SERIAL primary key ,
    title varchar(255) not null ,
    description text,
    event_date timestamp not null ,
    location varchar(255) not null ,
    price decimal(10,2) not null ,
    capacity int not null ,
    sold_tickets int not null default 0,
    status varchar(20) DEFAULT 'ACTIVE',
    created_at timestamp default now(),
    duration int not null default 60
);

CREATE TABLE event_staff(
    id serial primary key ,
    event_id int not null ,
    user_id int not null ,
    role varchar(50) not null ,
    unique(event_id,user_id),
    FOREIGN KEY (event_id) references events(id) ON DELETE CASCADE
);

