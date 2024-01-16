create table category
(
    id          bigint      null,
    type        int         null,
    name        varchar(64) null,
    sort        int         null,
    create_time datetime    null,
    update_time datetime    null,
    create_user bigint      null,
    update_user bigint      null
);

INSERT INTO my_take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1397844263642378242, 1, '湘菜', 1, '2021-05-27 09:16:58', '2021-07-15 20:25:23', 1, 1);
INSERT INTO my_take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1397844303408574465, 1, '川菜', 2, '2021-05-27 09:17:07', '2021-06-02 14:27:22', 1, 1);
INSERT INTO my_take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1397844391040167938, 1, '粤菜', 3, '2021-05-27 09:17:28', '2021-07-09 14:37:13', 1, 1);
INSERT INTO my_take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1413341197421846529, 1, '饮品', 11, '2021-07-09 11:36:15', '2021-07-09 14:39:15', 1, 1);
INSERT INTO my_take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1413384954989060097, 1, '主食', 12, '2021-07-09 14:30:07', '2021-07-09 14:39:19', 1, 1);
INSERT INTO my_take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1413386191767674881, 2, '儿童套餐', 6, '2021-07-09 14:35:02', '2021-07-09 14:39:05', 1, 1);
