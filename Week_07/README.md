学习笔记

-- TODO: to be optimized

```mysql
create table t_user
(
    id            bigint auto_increment primary key,
    account       varchar(30)                         not null,
    password      varchar(20)                         not null,
    nick_name     varchar(30)                         null,
    gender        tinyint(1)                          not null,
    real_name     varchar(10)                         not null,
    phone         varchar(11)                         not null,
    identity_card varchar(19)                         not null,
    register_time timestamp default CURRENT_TIMESTAMP not null,
    birthday      datetime                            null,
    level         tinyint(1)                          not null
);
```

```mysql
create table t_order
(
    id                 bigint auto_increment primary key,
    order_sn           varchar(64)                         not null,
    user_id            bigint(19)                          not null,
    user_name          varchar(10)                         not null,
    total_amount       decimal(10, 2)                      not null,
    freight_amount     decimal(10, 2)                      not null,
    pay_amount         decimal(10, 2)                      not null,
    gender             tinyint(1)                          not null,
    pay_type           tinyint(1)                          not null,
    status             tinyint(1)                          not null,
    address_id         bigint(19)                          not null,
    delivery_sn        varchar(64)                         null,
    remark             varchar(255)                        null,
    is_deleted         bit                                 not null,
    created_date       timestamp default CURRENT_TIMESTAMP not null,
    last_modified_date timestamp default CURRENT_TIMESTAMP not null
);
```

```mysql
-- auto-generated definition by idea
create table t_merchandise
(
    id       bigint auto_increment primary key,
    name     varchar(30) not null,
    price    varchar(10) not null,
    weight   varchar(10) null,
    order_id bigint      not null,
    constraint FK3it2cp3fx6v6cqo4bp3fnqjuc foreign key (order_id) references t_order (id)
);
```

