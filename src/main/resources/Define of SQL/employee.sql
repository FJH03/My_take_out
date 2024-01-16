create table employee
(
    id          bigint      null,
    name        varchar(32) null,
    username    varchar(32) null,
    password    varchar(64) null,
    phone       varchar(11) null,
    sex         varchar(2)  null,
    id_number   varchar(18) null,
    status      int         null,
    create_time datetime    null,
    update_time datetime    null,
    create_user bigint      null,
    update_user bigint      null
);

INSERT INTO my_take_out.employee (id, name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) VALUES (1, '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', 1, '2021-05-06 17:20:07', '2023-12-08 13:30:31', 1, 1);
INSERT INTO my_take_out.employee (id, name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) VALUES (1732737175644205058, '冯佳和', '2113042621', 'e10adc3949ba59abbe56e057f20f883e', '18334501074', '1', '140428200210130010', 1, '2023-12-07 20:21:50', '2023-12-19 16:13:43', 1, 1);
