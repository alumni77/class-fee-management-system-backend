create DATABASE db_fee;
use db_fee;

create table class
(
    classId   varchar(128)                         not null comment '班级Id'
        primary key,
    className varchar(128) collate utf8_general_ci not null comment '班级名称',
    academy   varchar(128) collate utf8_general_ci not null comment '学院名称'
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci, comment '班级表';

create table classadmin
(
    userId   int auto_increment comment '用户Id' primary key,
    username varchar(128) not null comment '用户名',
    password varchar(128) not null comment '用户密码'
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci, comment '班级管理员用户表';

create table classfee
(
    feeId        int auto_increment comment '班费编号' primary key,
    classId      varchar(128)                        not null comment '班级Id',
    subDate      timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '上交日期',
    subCount     int                                 not null comment '使用人数',
    subAmount    float                               not null comment '收入、支出明细 +收入  -支出',
    remainAmount float                               not null comment '剩余金额',
    constraint classId_1 foreign key (classId) references class (classId)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci,comment '班费表';

create table student
(
    studentId varchar(128)              not null comment '学号' primary key,
    classId   varchar(128)              not null comment '班级Id',
    sname     varchar(128) charset utf8 not null comment '学生姓名',
    gender    varchar(10) charset utf8  not null comment '性别',
    phone     varchar(128) charset utf8 not null comment '电话',
    email     varchar(128) charset utf8 not null comment '邮箱',
    constraint classId_0 foreign key (classId) references class (classId)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci,comment '学生表';

create table feedetail
(
    detailId    int auto_increment comment '明细Id' primary key,
    feeId       int                                 not null comment '班费编号',
    studentId   varchar(128)                        not null comment '学号',
    amount      float                               not null comment '支出/收入金额 +代表收入 -代表支出',
    tranTime    timestamp default CURRENT_TIMESTAMP not null comment '支出/收入时间',
    description varchar(128) charset utf8           not null comment '使用说明',
    constraint feeId foreign key (feeId) references classfee (feeId),
    constraint studentId foreign key (studentId) references student (studentId)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci,comment '班费明细表';

insert into classadmin(userId, username, password)
values (null, 'admin', 'f8baa77c88173e88926dda461c19388a');

insert into class (classId, className, academy)
VALUES ('1000001', '计算机科学与技术001', '数据学院');
insert into class (classId, className, academy)
VALUES ('1000002', '计算机科学与技术002', '数据学院');
insert into class (classId, className, academy)
VALUES ('1000003', '计算机科学与技术003', '数据学院');

insert into student(studentId, classId, sname, gender, phone, email)
VALUES ('33333301', '1000001', '张三', '男', '1234567', '1234567@xx.com');
insert into student(studentId, classId, sname, gender, phone, email)
VALUES ('33333302', '1000001', '李四', '男', '1234568', '1234568@xx.com');
insert into student(studentId, classId, sname, gender, phone, email)
VALUES ('33333303', '1000001', '王五', '女', '1234569', '1234569@xx.com');
insert into student(studentId, classId, sname, gender, phone, email)
VALUES ('44444401', '1000002', '张三', '男', '12345671', '12345671@xx.com');
insert into student(studentId, classId, sname, gender, phone, email)
VALUES ('44444402', '1000003', '张三', '男', '12345672', '12345672@xx.com');

insert into classfee(classId, subCount, subAmount, remainAmount)
VALUES ('1000001', 20, 500, 500);
insert into classfee(classId, subCount, subAmount, remainAmount)
VALUES ('1000002', 30, 600, 600);

insert into feedetail(detailId, feeId, studentId, amount, tranTime, description)
VALUES (null, 1, '33333301', 33, null, '扣分罚钱交班费');



















