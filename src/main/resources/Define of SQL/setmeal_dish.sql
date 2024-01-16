create table setmeal_dish
(
    id          bigint         null,
    setmeal_id  varchar(32)    null,
    dish_id     varchar(32)    null,
    name        varchar(32)    null,
    price       decimal(10, 2) null,
    copies      int            null,
    sort        int            null,
    create_time datetime       null,
    update_time datetime       null,
    create_user bigint         null,
    update_user bigint         null,
    is_deleted  int            null
);

INSERT INTO my_take_out.setmeal_dish (id, setmeal_id, dish_id, name, price, copies, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1415580119052894209, '1415580119015145474', '1397862198033297410', '老火靓汤', 49800.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO my_take_out.setmeal_dish (id, setmeal_id, dish_id, name, price, copies, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1415580119061282817, '1415580119015145474', '1413342036832100354', '北冰洋', 500.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO my_take_out.setmeal_dish (id, setmeal_id, dish_id, name, price, copies, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1415580119069671426, '1415580119015145474', '1413385247889891330', '米饭', 200.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
