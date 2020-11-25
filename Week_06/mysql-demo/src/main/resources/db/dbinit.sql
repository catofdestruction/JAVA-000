-- 创建数据库jddb:
DROP DATABASE IF EXISTS jddb;
CREATE DATABASE jddb;


-- 创建登录用户jd/口令jd
CREATE USER IF NOT EXISTS 'jd'@'%' IDENTIFIED BY 'jd';
GRANT ALL PRIVILEGES ON jddb.* TO 'jd'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

-- 创建表:
USE jddb;

-- TODO: to be optimized
-- auto-generated definition
create table t_user
(
    id            bigint auto_increment primary key,
    account       varchar(30) not null,
    identity_card varchar(18) not null,
    name          varchar(30) not null,
    password      varchar(20) not null,
    phone         varchar(11) not null
);

-- auto-generated definition
create table t_order
(
    id                 bigint auto_increment primary key,
    created_date       bigint       not null,
    last_modified_date bigint       not null,
    seller_id          bigint       not null,
    status             varchar(255) not null,
    total_price        varchar(20)  not null,
    buyer_id           bigint       not null,
    constraint FKk26ipfwqtjq9995jrn29murlt
        foreign key (buyer_id) references t_user (id)
);

-- auto-generated definition
create table t_merchandise
(
    id       bigint auto_increment primary key,
    name     varchar(30) not null,
    price    varchar(10) not null,
    weight   varchar(10) null,
    order_id bigint      not null,
    constraint FK3it2cp3fx6v6cqo4bp3fnqjuc
        foreign key (order_id) references t_order (id)
);


/**
brew install mysql@5.7
echo 'export PATH="/usr/local/opt/mysql@5.7/bin:$PATH"' >> ~/.zshrc
mysql.server start
mysql -uroot
mysql.server stop
 */