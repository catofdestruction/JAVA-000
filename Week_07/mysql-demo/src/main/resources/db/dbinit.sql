-- 创建数据库jddb:
DROP DATABASE IF EXISTS jddb;
CREATE DATABASE jddb;


-- 创建登录用户jd/口令jd
CREATE USER IF NOT EXISTS 'jd'@'%' IDENTIFIED BY 'jd';
GRANT ALL PRIVILEGES ON jddb.* TO 'jd'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

-- 创建表:
USE jddb;

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

create table t_order_item
(
    id                   bigint auto_increment
        primary key,
    order_id             bigint                              not null,
    order_sn             varchar(64)                         not null,
    product_id           bigint                              not null,
    product_price        decimal(10, 2)                      not null,
    product_quantity     int(10)                             not null,
    product_total_amount decimal(10, 2)                      not null,
    is_deleted           bit                                 not null,
    created_by           varchar(30)                         null,
    last_modified_by     varchar(30)                         null,
    created_date         timestamp default CURRENT_TIMESTAMP not null,
    last_modified_date   timestamp default CURRENT_TIMESTAMP not null,
    constraint UK_25kiqjvhra3qu4t60vxvh2u7p
        unique (product_id),
    constraint UK_ikue62vow9wl5x6d4h5n67wsm
        unique (order_sn),
    constraint UK_tr4wwliho4mtheslf3eugb9km
        unique (order_id)
);

/**
brew install mysql@5.7
echo 'export PATH="/usr/local/opt/mysql@5.7/bin:$PATH"' >> ~/.zshrc
mysql.server start
mysql -uroot
mysql.server stop
 */