create table shopping_cart
(
    id          bigint         null,
    name        varchar(50)    null,
    image       varchar(100)   null,
    user_id     bigint         null,
    dish_id     bigint         null,
    setmeal_id  bigint         null,
    dish_flavor varchar(50)    null,
    number      int            null,
    amount      decimal(10, 2) null,
    create_time datetime       null
);

