create table orders
(
    id              bigint         null,
    number          varchar(50)    null,
    status          int            null,
    user_id         bigint         null,
    address_book_id bigint         null,
    order_time      datetime       null,
    checkout_time   datetime       null,
    pay_method      int            null,
    amount          decimal(10, 2) null,
    remark          varchar(100)   null,
    phone           varchar(255)   null,
    address         varchar(255)   null,
    user_name       varchar(255)   null,
    consignee       varchar(255)   null
);

INSERT INTO my_take_out.orders (id, number, status, user_id, address_book_id, order_time, checkout_time, pay_method, amount, remark, phone, address, user_name, consignee) VALUES (1747173171550912513, '1747173171550912513', 2, 1738402096889384961, 1747173138608848898, '2024-01-16 16:25:20', '2024-01-16 16:25:20', 1, 178.00, '', '15903551284', '无', null, '冯');
