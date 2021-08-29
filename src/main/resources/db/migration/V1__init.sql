create table `system_user` (id bigint not null auto_increment primary key, chat_id bigint not null, username varchar(255) not null, first_name varchar(255), last_name varchar(255), status varchar(20));
create table `system_role` (id bigint not null auto_increment primary key, `name` varchar(50) not null unique);
create table `system_user_role` (id_user bigint not null, id_role bigint not null);
alter table system_user_role add foreign key (id_user) references system_user(id);
alter table system_user_role add foreign key (id_role) references system_role(id);
insert into system_role (`name`) values ('GOD'), ('ADMIN'), ('SUPPORT');
