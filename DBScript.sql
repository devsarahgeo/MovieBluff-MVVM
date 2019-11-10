create database moviebluff;
use moviebluff;
create table login(
user_id int AUTO_INCREMENT PRIMARY KEY,
first_name varchar(50),
email varchar(50),
password varchar(50),
picture_path varchar(350)
);

describe login;
