学习笔记

```mysql
create table t_user
(
    id            bigint auto_increment
        primary key,
    account       varchar(30)                         not null,
    password      varchar(20)                         not null,
    nick_name     varchar(30)                         null,
    gender        tinyint(1)                          not null,
    real_name     varchar(10)                         not null,
    phone         varchar(11)                         not null,
    identity_card varchar(19)                         not null,
    register_time timestamp default CURRENT_TIMESTAMP not null,
    birthday      datetime                            null,
    level         tinyint(1)                          not null,
    constraint UK_kh3p75uuyexsq7d6wb0gysukh
        unique (account),
    constraint UK_llibb0visvghi4tveb4nd6ugb
        unique (nick_name),
    constraint UK_m5bu5erj83eubjsa1nyms0t89
        unique (phone)
);
```

```mysql
create table t_order
(
    id                 bigint auto_increment
        primary key,
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
    last_modified_date timestamp default CURRENT_TIMESTAMP not null,
    constraint UK_66dgp6abglwgdd678paheb1nv
        unique (delivery_sn),
    constraint UK_9gb07lvp22e93xt5givstp2s
        unique (order_sn)
);
```

```mysql
create table t_product
(
    id                 bigint auto_increment
        primary key,
    product_sn         varchar(64)                         not null,
    brand_id           bigint                              null,
    category_id        bigint                              null,
    name               varchar(64)                         not null,
    description        varchar(255)                        not null,
    price              decimal(10, 2)                      not null,
    weight             decimal(10, 2)                      not null,
    image_url          varchar(255)                        null,
    is_deleted         bit                                 not null,
    created_date       timestamp default CURRENT_TIMESTAMP not null,
    last_modified_date timestamp default CURRENT_TIMESTAMP not null,
    constraint UK_ctthk2vsd6kc2v4lebsrnqawx
        unique (product_sn)
);
```

