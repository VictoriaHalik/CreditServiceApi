create table credits (id int8 not null, finish_date timestamp, month_sum int8, paid_off boolean not null, period_months int4 not null, start_date timestamp, sum_left int8, sum_paid int8, sum_pay int8, sum_take int8 not null, user_id int8 not null, primary key (id))
create table users (user_id int8 not null, age int4 not null, email_address varchar(255), first_name varchar(25), last_name varchar(25), password varchar(255), primary key (user_id))
alter table if exists credits add constraint FKdqrqgej9w17hf6mpkwcf75l1p foreign key (user_id) references users on delete cascade