create table setmeal
(
    id          bigint         null,
    category_id bigint         null,
    name        varchar(64)    null,
    price       decimal(10, 2) null,
    status      int            null,
    code        varchar(32)    null,
    description varchar(512)   null,
    image       varchar(255)   null,
    create_time datetime       null,
    update_time datetime       null,
    create_user bigint         null,
    update_user bigint         null,
    is_deleted  int            null
);

INSERT INTO my_take_out.setmeal (id, category_id, name, price, status, code, description, image, create_time, update_time, create_user, update_user, is_deleted) VALUES (1415580119015145474, 1413386191767674881, '儿童套餐A计划', 4000.00, 1, '', '', '0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg', '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
