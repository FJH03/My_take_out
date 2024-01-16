create table operate_log
(
    id            bigint auto_increment
        primary key,
    operate_user  bigint        null,
    operate_time  datetime      null,
    class_name    varchar(100)  null,
    method_name   varchar(100)  null,
    method_params varchar(1000) null,
    return_value  varchar(2000) null,
    cost_time     bigint        null
);

