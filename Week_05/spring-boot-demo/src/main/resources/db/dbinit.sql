
-- 创建数据库xy:
DROP DATABASE IF EXISTS xydb;
CREATE DATABASE xydb;

-- 创建登录用户xy/口令xy
CREATE USER IF NOT EXISTS 'xy'@'%' IDENTIFIED BY 'xy';
GRANT ALL PRIVILEGES ON xydb.* TO 'xy'@'%' WITH GRANT OPTION;
GRANT REPLICATION SLAVE ON *.* TO 'xy'@'%';
FLUSH PRIVILEGES;

-- 创建表concurrent_trip:
USE xydb;
CREATE TABLE concurrent_trip (
  id BIGINT AUTO_INCREMENT NOT NULL,
  name VARCHAR(81) NOT NULL,
  duration INT NOT NULL,
  result INT NOT NULL,
  PRIMARY KEY(id)
) Engine=INNODB DEFAULT CHARSET=UTF8;

/**
brew install mysql@5.7
echo 'export PATH="/usr/local/opt/mysql@5.7/bin:$PATH"' >> ~/.zshrc
mysql.server start
mysql -uroot
mysql.server stop
 */