create table address_book
(
    id            bigint       null,
    user_id       bigint       null,
    consignee     varchar(50)  null,
    sex           tinyint      null,
    phone         varchar(11)  null,
    province_code varchar(12)  null,
    province_name varchar(32)  null,
    city_code     varchar(12)  null,
    city_name     varchar(32)  null,
    district_code varchar(12)  null,
    district_name varchar(32)  null,
    detail        varchar(200) null,
    label         varchar(100) null,
    is_default    tinyint      null,
    create_time   datetime     null,
    update_time   datetime     null,
    create_user   bigint       null,
    update_user   bigint       null,
    is_deleted    int          null
);

INSERT INTO my_take_out.address_book (id, user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default, create_time, update_time, create_user, update_user, is_deleted) VALUES (1747173138608848898, 1738402096889384961, '冯', 1, '15903551284', null, null, null, null, null, null, '无', '公司', 1, '2024-01-16 16:25:12', '2024-01-16 16:25:13', 1738402096889384961, 1738402096889384961, null);
