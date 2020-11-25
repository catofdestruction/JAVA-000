
-- 创建数据库jddb:
DROP DATABASE IF EXISTS jddb;
CREATE DATABASE jddb;``

-- 创建登录用户jd/口令jd
CREATE USER IF NOT EXISTS 'jd'@'%' IDENTIFIED BY 'jd';
GRANT ALL PRIVILEGES ON jddb.* TO 'jd'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

-- 创建表:
USE jddb;


/**
brew install mysql@5.7
echo 'export PATH="/usr/local/opt/mysql@5.7/bin:$PATH"' >> ~/.zshrc
mysql.server start
mysql -uroot
mysql.server stop
 */