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

/**
brew install mysql@5.7
echo 'export PATH="/usr/local/opt/mysql@5.7/bin:$PATH"' >> ~/.zshrc
mysql.server start
mysql -uroot
mysql.server stop
 */