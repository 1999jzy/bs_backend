create database bs;
use bs;

create table User(
	uid integer primary key auto_increment,
    username varchar(64) not null unique,
    password varchar(64) not null unique,
    email varchar(64) not null unique,
    name varchar(64) null,
    face varchar(255) null
);

create table Book(
	bookid integer primary key auto_increment,
    bookname varchar(64) not null,
    priceori decimal(5,2) not null,
    pricenow decimal(5,2) not null,
    category varchar(64) not null,
    content varchar(255) null,
    pic varchar(255) null,
    bookUrl varchar(255) null,
    username varchar(64) not null,
    state integer not null,
    time timestamp null
);

create table Ord(
	orderid integer primary key auto_increment,
    bookid integer not null,
    bookname varchar(64) not null,
    ordertime timestamp not null,
    buyer varchar(64) not null,
    seller varchar(64) not null,
    ordertype integer not null,
    address varchar(255) null,
    state integer not null,
    bcom integer not null,
    scom integer not null
);

create table chatlist(
	chatid integer primary key auto_increment,
    user varchar(64) not null,
    anothoer_user varchar(64) not null
);

create table chatmsg(
	msgid integer primary key auto_increment,
    chatid integer not null,
    sender varchar(64) not null,
    content varchar(255) not null,
    time timestamp null
)