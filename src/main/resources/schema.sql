create table account (
    id serial primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    user_name varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null
);

create table category (
    id serial primary key,
    name varchar(255) not null,
    description varchar(255) not null,
    account_id int not null,
    foreign key (account_id) references account (id)
);

create table todo(
    id serial primary key,
    title varchar(255) not null,
    description varchar(255) not null,
    done boolean not null,
    start_date date not null,
    category_id int not null,
    foreign key (category_id) references category (id)
);