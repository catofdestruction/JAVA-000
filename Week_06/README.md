学习笔记

-- TODO: to be optimized

```mysql
-- auto-generated definition by idea
create table t_user
(
    id            bigint auto_increment primary key,
    account       varchar(30) not null,
    identity_card varchar(18) not null,
    name          varchar(30) not null,
    password      varchar(20) not null,
    phone         varchar(11) not null
);
```

```mysql
-- auto-generated definition by idea
create table t_order
(
    id                 bigint auto_increment primary key,
    created_date       bigint       not null,
    last_modified_date bigint       not null,
    seller_id          bigint       not null,
    status             varchar(255) not null,
    total_price        varchar(20)  not null,
    buyer_id           bigint       not null,
    constraint FKk26ipfwqtjq9995jrn29murlt foreign key (buyer_id) references t_user (id)
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

