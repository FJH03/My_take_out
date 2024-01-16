create table order_detail
(
    id          bigint         null,
    name        varchar(50)    null,
    image       varchar(100)   null,
    order_id    bigint         null,
    dish_id     bigint         null,
    setmeal_id  bigint         null,
    dish_flavor varchar(50)    null,
    number      int            null,
    amount      decimal(10, 2) null
);

INSERT INTO my_take_out.order_detail (id, name, image, order_id, dish_id, setmeal_id, dish_flavor, number, amount) VALUES (1747173171550912514, '儿童套餐A计划', '0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg', 1747173171550912513, null, 1415580119015145474, null, 1, 40.00);
INSERT INTO my_take_out.order_detail (id, name, image, order_id, dish_id, setmeal_id, dish_flavor, number, amount) VALUES (1747173171613827074, '麻辣水煮鱼', '1fdbfbf3-1d86-4b29-a3fc-46345852f2f8.jpg', 1747173171550912513, 1397854652581064706, null, '不要葱,微辣', 1, 138.00);
