use quiz_website;

SET FOREIGN_KEY_CHECKS = 1;
drop table if exists mail;
drop table if exists challenges;
drop table if exists answer;
drop table if exists passed_quiz;
drop table if exists friends;
drop table if exists question;
drop table if exists quiz;

drop table if exists user;

CREATE TABLE user
(
    id       int         NOT NULL AUTO_INCREMENT,
    name     VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    CONSTRAINT unique_username UNIQUE (username),
    PRIMARY KEY (id)
);


create table friends
(
    sender_id   int         NOT NULL,
    receiver_id int         NOT NULL,
    status      varchar(10) NOT NULL,
    FOREIGN KEY (sender_id) references user (id),
    foreign key (receiver_id) references user (id)
);

create table quiz
(
    id            int         NOT NULL AUTO_INCREMENT,
    name          varchar(50) not null,
    description   varchar(100),
    creator_id    int         not null,
    creation_date long,
    primary key (id),
    foreign key (creator_id) references user (id)
);

CREATE TABLE question
(
    id                   int          NOT NULL AUTO_INCREMENT,
    quiz_id              int          NOT NULL,
    question             VARCHAR(200) NOT NULL,
    type                 varchar(50)  not null,
    choices              varchar(200) not null,
    correct_answer       varchar(200) not null,
    correct_answer_index int          not null,
    picture_url          varchar(1000),
    PRIMARY KEY (id),
    FOREIGN KEY (quiz_id) references quiz (id)
);
CREATE TABLE passed_quiz
(
    id         int not null AUTO_INCREMENT,
    user_id    int NOT NULL,
    quiz_id    int NOT NULL,
    score      int not null,
    start_date long,
    end_date   long,
    duration   long,
    primary key (id),
    FOREIGN KEY (quiz_id) references quiz (id),
    FOREIGN KEY (user_id) references user (id)
);

CREATE TABLE answer
(
    id             int NOT NULL AUTO_INCREMENT,
    question_id    int NOT NULL,
    user_answer    VARCHAR(1000),
    passed_quiz_id int not null,
    PRIMARY KEY (id),
    FOREIGN KEY (question_id) references question (id),
    foreign key (passed_quiz_id) references passed_quiz (id)
);



CREATE TABLE mail
(
    id          int          NOT NULL AUTO_INCREMENT,
    sender_id   int          NOT NULL,
    receiver_id int          NOT NULL,
    context     VARCHAR(300) NOT NULL,
    sent_date   long,
    foreign key (sender_id) references user (id),
    foreign key (receiver_id) references user (id),
    PRIMARY KEY (id)
);


create table challenges
(
    id           int NOT NULL AUTO_INCREMENT,
    sender_id    int NOT NULL,
    receiver_id  int NOT NULL,
    quiz_id      int NOT NULL,
    sender_score int NOT NULL,
    FOREIGN KEY (sender_id) references user (id),
    FOREIGN KEY (receiver_id) references user (id),
    PRIMARY KEY (id)
);



insert into user (name, username, password)
values ('amiko', 'amalzet@freeuni.edu.ge', 'a');
insert into user (name, username, password)
values ('davita', 'dsolo17@freeuni.edu.ge', 'a');
insert into user (name, username, password)
values ('lado', 'lniko17@freeuni.edu.ge', 'a');
insert into user (name, username, password)
values ('mari', 'mormo17@freeuni.edu.ge', 'a');


insert into friends (sender_id, receiver_id, status)
values (1, 2, 'APPROVED');
insert into friends (sender_id, receiver_id, status)
values (2, 3, 'APPROVED');
insert into friends (sender_id, receiver_id, status)
values (1, 4, 'APPROVED');
insert into friends (sender_id, receiver_id, status)
values (2, 4, 'APPROVED');
insert into friends (sender_id, receiver_id, status)
values (3, 4, 'APPROVED');