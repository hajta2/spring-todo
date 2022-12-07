insert into account (first_name, last_name, email, user_name, password) values ('John', 'Doe', 'john.doe@gmail.com', 'user', '$2a$12$5TuzTcyYSBsWL7CwKY8cCezUJCGaQl/loHJ/aNlJTkZr4Ykxl/Jzq', 'ROLE_USER');
insert into account (first_name, last_name, email, user_name, password) values ('Admin', 'Admin', 'admin@admin.com', 'admin', '$2a$12$pSN4hr5ettz4/NtF80WateE1f/sD6Er1pKETi5nbTLhbmoKhyO/yS', 'ROLE_ADMIN');

insert into category (name, description, account_id) values ('Work', 'Work related tasks', 1);
insert into category (name, description, account_id) values ('Home', 'Home related tasks', 1);

insert into todo (title, description, done, start_date, category_id) values ('Learn Spring Boot', 'Learn Spring Boot', false, '2022-09-01', 1);
insert into todo (title, description, done, start_date, category_id) values ('Learn Angular', 'Learn Angular', false, '2022-12-01', 1);
insert into todo (title, description, done, start_date, category_id) values ('Wash Dishes', 'Wash Dishes', false, '2022-12-03', 1);