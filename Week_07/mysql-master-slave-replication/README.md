# [Mac] play mysql without ~~Homebrew~~

## download

https://dev.mysql.com/downloads/mysql/

| **macOS 10.15 (x86, 64-bit), Compressed TAR Archive** | 8.0.22 | 160.6M |      |
| ----------------------------------------------------- | ------ | ------ | ---- |
|                                                       |        |        |      |

创建目录并将内容拷贝下载内容

```shell
❯ mkdir -p ~/software/mysql
❯ ll
total 752
-rw-r--r--@  1 _mysql  _mysql  378912 Sep 23 20:37 LICENSE
-rw-r--r--@  1 _mysql  _mysql     687 Sep 23 20:37 README
drwxr-xr-x@ 34 _mysql  _mysql    1088 Sep 25 04:32 bin
drwxr-x---   3 _mysql  _mysql      96 Dec  6 21:04 data
drwxr-xr-x@  5 _mysql  _mysql     160 Sep 25 04:29 docs
drwxr-xr-x@ 16 _mysql  _mysql     512 Sep 25 04:29 include
drwxr-x---   3 _mysql  _mysql      96 Dec  6 21:04 keyring
drwxr-xr-x@ 17 _mysql  _mysql     544 Sep 25 04:33 lib
drwxr-xr-x@  4 _mysql  _mysql     128 Sep 25 04:29 man
drwxr-xr-x@ 35 _mysql  _mysql    1120 Dec  6 19:35 share
drwxr-xr-x@  5 _mysql  _mysql     160 Sep 25 04:29 support-files
```

zsh 配置环境目录 

```shell
export PATH=$PATH:~/software/mysql/bin
```

ref: https://www.cnblogs.com/wkzhao/p/10293127.html

## Demo

```shell
# 查看状态
❯ mysqld_multi --defaults-file=/usr/local/etc/my_multi.cnf report
WARNING: Log file disabled. Maybe directory or file isn't writable?
mysqld_multi log file version 2.16; run: Sun Dec 13 16:17:29 2020
Reporting MySQL servers
MySQL server from group: mysqld3316 is not running
MySQL server from group: mysqld3318 is not running

# 启动3316 3318 也可以单独启动3316（mysqld_multi --defaults-file=/usr/local/etc/my_multi.cnf start 3316）
# todo: 目前无法使用 mysqld_multi --defaults-file=/usr/local/etc/my_multi.cnf stop 停止
# 临时方案： mysqladmin -h127.0.0.1 -P3316 -uroot -p shutdown      mysqladmin -h127.0.0.1 -P3318 -uroot -p shutdown
❯ mysqld_multi --defaults-file=/usr/local/etc/my_multi.cnf start
WARNING: Log file disabled. Maybe directory or file isn't writable?
mysqld_multi log file version 2.16; run: Sun Dec 13 16:17:46 2020

Starting MySQL servers
❯ mysqld_multi --defaults-file=/usr/local/etc/my_multi.cnf report
WARNING: Log file disabled. Maybe directory or file isn't writable?
mysqld_multi log file version 2.16; run: Sun Dec 13 16:17:52 2020
Reporting MySQL servers
MySQL server from group: mysqld3316 is running
MySQL server from group: mysqld3318 is running

❯ mysqladmin -h127.0.0.1 -P3316 -uroot -p shutdown
Enter password:
❯ mysqld_multi --defaults-file=/usr/local/etc/my_multi.cnf report
WARNING: Log file disabled. Maybe directory or file isn't writable?
mysqld_multi log file version 2.16; run: Sun Dec 13 16:20:37 2020
Reporting MySQL servers
MySQL server from group: mysqld3316 is not running
MySQL server from group: mysqld3318 is running

# 可以看到打印时区貌似有问题
# 客户端登录
❯ mysql -uroot -h127.0.0.1 -P3316
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 9
Server version: 8.0.22 MySQL Community Server - GPL

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show variables like '%time_zone%';
+------------------+--------+
| Variable_name    | Value  |
+------------------+--------+
| system_time_zone | CST    |
| time_zone        | SYSTEM |
+------------------+--------+
2 rows in set (0.00 sec)
```

### [修正时区问题](https://blog.csdn.net/Howinfun/article/details/100697764)

```shell
# 修正时区后
❯ mysqld_multi --defaults-file=/usr/local/etc/my_multi.cnf start
WARNING: Log file disabled. Maybe directory or file isn't writable?
mysqld_multi log file version 2.16; run: Sun Dec 13 16:28:02 2020

Starting MySQL servers
❯ mysqld_multi --defaults-file=/usr/local/etc/my_multi.cnf report
WARNING: Log file disabled. Maybe directory or file isn't writable?
mysqld_multi log file version 2.16; run: Sun Dec 13 16:28:17 2020
Reporting MySQL servers
MySQL server from group: mysqld3316 is running
MySQL server from group: mysqld3318 is running
```

### 配置主从

主节点

```shell
# 3316
-- 创建数据库xy:
DROP DATABASE IF EXISTS xydb;
CREATE DATABASE xydb;

-- 创建登录用户xy/口令xy
CREATE USER IF NOT EXISTS 'xy'@'%' IDENTIFIED BY 'xy';
GRANT ALL PRIVILEGES ON xydb.* TO 'xy'@'%' WITH GRANT OPTION;
GRANT REPLICATION SLAVE ON *.* TO 'xy'@'%';
FLUSH PRIVILEGES;

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
| xydb               |
+--------------------+
5 rows in set (0.00 sec)

mysql> use xydb;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> show tables;
+-----------------+
| Tables_in_xydb  |
+-----------------+
| concurrent_trip |
+-----------------+
1 row in set (0.01 sec)

mysql> select * from concurrent_trip;
+----+-----------------------------------------------------------------+----------+-----------+
| id | name                                                            | duration | result    |
+----+-----------------------------------------------------------------+----------+-----------+
| 77 | Simple$$EnhancerBySpringCGLIB$$cd4a316b                         |      674 | 102334155 |
| 78 | Yield$$EnhancerBySpringCGLIB$$27c5a6fc                          |     1174 | 102334155 |
| 79 | Join$$EnhancerBySpringCGLIB$$cc683ad3                           |     1065 | 102334155 |
| 80 | WaitAndNotify$$EnhancerBySpringCGLIB$$dbd19d36                  |     1092 | 102334155 |
| 81 | FutureThreadFactory$$EnhancerBySpringCGLIB$$b1446e2c            |     1056 | 102334155 |
| 82 | LockSupport$$EnhancerBySpringCGLIB$$761a2721                    |     1066 | 102334155 |
| 83 | LockWithCondition$$EnhancerBySpringCGLIB$$e8df6113              |      931 | 102334155 |
| 84 | FutureTask$$EnhancerBySpringCGLIB$$d32c8fcd                     |      946 | 102334155 |
| 85 | BlockingQueue$$EnhancerBySpringCGLIB$$40cf2349                  |      933 | 102334155 |
| 86 | Semaphore$$EnhancerBySpringCGLIB$$6918581                       |      984 | 102334155 |
| 87 | CountDownLatch$$EnhancerBySpringCGLIB$$b9bfa44e                 |      989 | 102334155 |
| 88 | CyclicBarrier$$EnhancerBySpringCGLIB$$bec72d2f                  |      975 | 102334155 |
| 89 | CompletableFuture$$EnhancerBySpringCGLIB$$1c4e1e10              |      865 | 102334155 |
| 90 | CompletableFutureApply$$EnhancerBySpringCGLIB$$70393ac4         |      917 | 102334155 |
| 91 | CompletableFutureAccept$$EnhancerBySpringCGLIB$$6029be48        |      941 | 102334155 |
| 92 | CompletableFutureCombine$$EnhancerBySpringCGLIB$$40cba197       |      927 | 102334155 |
| 93 | CompletableFutureCompose$$EnhancerBySpringCGLIB$$49f4ca70       |      869 | 102334155 |
| 94 | CompletableFutureApplyToEither$$EnhancerBySpringCGLIB$$7bab7184 |        5 | 102334155 |
| 95 | Exchanger$$EnhancerBySpringCGLIB$$7293e9a2                      |      860 | 102334155 |
+----+-----------------------------------------------------------------+----------+-----------+
19 rows in set (0.00 sec)

mysql> show master status\G
*************************** 1. row ***************************
             File: mysql-bin.000013
         Position: 541
     Binlog_Do_DB:
 Binlog_Ignore_DB:
Executed_Gtid_Set:
1 row in set (0.00 sec)

mysql> show slave status\G
Empty set, 1 warning (0.00 sec)

# 这里可以看到 3316 binlog文件中 end_log_pos 541 和 show master status 中的 position 对应上
❯ mysqlbinlog -vv mysql-bin.000013

... ...
GRANT REPLICATION SLAVE ON *.* TO 'xy'@'%'
/*!*/;
# at 374
#201213 17:03:56 server id 3316  end_log_pos 451 CRC32 0xa66d4a1d 	Anonymous_GTID	last_committed=1	sequence_number=2	rbr_only=no	original_committed_timestamp=1607850236896878	immediate_commit_timestamp=1607850236896878	transaction_length=167
# original_commit_timestamp=1607850236896878 (2020-12-13 17:03:56.896878 CST)
# immediate_commit_timestamp=1607850236896878 (2020-12-13 17:03:56.896878 CST)
/*!80001 SET @@session.original_commit_timestamp=1607850236896878*//*!*/;
/*!80014 SET @@session.original_server_version=80022*//*!*/;
/*!80014 SET @@session.immediate_server_version=80022*//*!*/;
SET @@SESSION.GTID_NEXT= 'ANONYMOUS'/*!*/;
# at 451
#201213 17:03:56 server id 3316  end_log_pos 541 CRC32 0x8cefc0ad 	Query	thread_id=8	exec_time=0	error_code=0
SET TIMESTAMP=1607850236/*!*/;
SET @@session.time_zone='SYSTEM'/*!*/;
FLUSH PRIVILEGES
/*!*/;
SET @@SESSION.GTID_NEXT= 'AUTOMATIC' /* added by mysqlbinlog */ /*!*/;
DELIMITER ;
# End of log file
/*!50003 SET COMPLETION_TYPE=@OLD_COMPLETION_TYPE*/;
/*!50530 SET @@SESSION.PSEUDO_SLAVE_MODE=0*/;
```

从节点

```shell
mysql> show slave status;
Empty set, 1 warning (0.01 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
4 rows in set (0.01 sec)    
    
mysql> CHANGE MASTER TO
    ->     MASTER_HOST='127.0.0.1',
    ->     MASTER_PORT = 3316,
    ->     MASTER_USER='xy',
    ->     MASTER_PASSWORD='xy',
    ->     MASTER_LOG_FILE='mysql-bin.000013',
    ->     MASTER_LOG_POS=541;
Query OK, 0 rows affected, 1 warning (0.03 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
4 rows in set (0.00 sec)

mysql> show slave status\G
*************************** 1. row ***************************
               Slave_IO_State:
                  Master_Host: 127.0.0.1
                  Master_User: xy
                  Master_Port: 3316
                Connect_Retry: 60
              Master_Log_File: mysql-bin.000013
          Read_Master_Log_Pos: 541
               Relay_Log_File: XY-relay-bin.000001
                Relay_Log_Pos: 4
        Relay_Master_Log_File: mysql-bin.000013
             Slave_IO_Running: No
            Slave_SQL_Running: No
              Replicate_Do_DB:
          Replicate_Ignore_DB:
           Replicate_Do_Table:
       Replicate_Ignore_Table:
      Replicate_Wild_Do_Table:
  Replicate_Wild_Ignore_Table:
                   Last_Errno: 0
                   Last_Error:
                 Skip_Counter: 0
          Exec_Master_Log_Pos: 541
              Relay_Log_Space: 156
              Until_Condition: None
               Until_Log_File:
                Until_Log_Pos: 0
           Master_SSL_Allowed: No
           Master_SSL_CA_File:
           Master_SSL_CA_Path:
              Master_SSL_Cert:
            Master_SSL_Cipher:
               Master_SSL_Key:
        Seconds_Behind_Master: NULL
Master_SSL_Verify_Server_Cert: No
                Last_IO_Errno: 0
                Last_IO_Error:
               Last_SQL_Errno: 0
               Last_SQL_Error:
  Replicate_Ignore_Server_Ids:
             Master_Server_Id: 0
                  Master_UUID:
             Master_Info_File: mysql.slave_master_info
                    SQL_Delay: 0
          SQL_Remaining_Delay: NULL
      Slave_SQL_Running_State:
           Master_Retry_Count: 86400
                  Master_Bind:
      Last_IO_Error_Timestamp:
     Last_SQL_Error_Timestamp:
               Master_SSL_Crl:
           Master_SSL_Crlpath:
           Retrieved_Gtid_Set:
            Executed_Gtid_Set:
                Auto_Position: 0
         Replicate_Rewrite_DB:
                 Channel_Name:
           Master_TLS_Version:
       Master_public_key_path:
        Get_master_public_key: 0
            Network_Namespace:
1 row in set, 1 warning (0.01 sec)

mysql> start slave;
Query OK, 0 rows affected, 1 warning (0.00 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
4 rows in set (0.00 sec)

mysql> CHANGE MASTER TO
    MASTER_HOST='127.0.0.1',  
    MASTER_PORT = 3316,
    MASTER_USER='xy',      
    MASTER_PASSWORD='xy',   
    MASTER_LOG_FILE='mysql-bin.000013',
    MASTER_LOG_POS=541;
```

[MySQL 8.0 Public Key Retrieval is not allowed 错误的解决方法](https://blog.csdn.net/u013360850/article/details/80373604)

因为541 已经创建了库表 从库并不能复制数据

### 清除主库库表重新配置

主节点

```shell
mysql> drop schema xydb;
Query OK, 0 rows affected (0.02 sec)

mysql>
mysql>
mysql>
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
4 rows in set (0.00 sec)

mysql>
mysql>
mysql>
mysql>
mysql> show master status;
+------------------+----------+--------------+------------------+-------------------+
| File             | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+------------------+----------+--------------+------------------+-------------------+
| mysql-bin.000013 |    11757 |              |                  |                   |
+------------------+----------+--------------+------------------+-------------------+
1 row in set (0.00 sec)

mysql> 
```

从节点

```shell
mysql> CHANGE MASTER TO
    ->     MASTER_HOST='127.0.0.1',
    ->     MASTER_PORT = 3316,
    ->     MASTER_USER='xy',
    ->     MASTER_PASSWORD='xy',
    ->     MASTER_LOG_FILE='mysql-bin.000013',
    ->     MASTER_LOG_POS=11757;
Query OK, 0 rows affected, 1 warning (0.05 sec)

mysql>
mysql>
mysql>
mysql>
mysql> start slave;
Query OK, 0 rows affected, 1 warning (0.01 sec)

# Slave_IO_Running: Yes   Slave_SQL_Running: Yes    Slave_SQL_Running_State: Slave has read all relay log; waiting for more updates
mysql> show slave status\G
*************************** 1. row ***************************
               Slave_IO_State: Waiting for master to send event
                  Master_Host: 127.0.0.1
                  Master_User: xy
                  Master_Port: 3316
                Connect_Retry: 60
              Master_Log_File: mysql-bin.000013
          Read_Master_Log_Pos: 11757
               Relay_Log_File: XY-relay-bin.000002
                Relay_Log_Pos: 324
        Relay_Master_Log_File: mysql-bin.000013
             Slave_IO_Running: Yes
            Slave_SQL_Running: Yes
              Replicate_Do_DB:
          Replicate_Ignore_DB:
           Replicate_Do_Table:
       Replicate_Ignore_Table:
      Replicate_Wild_Do_Table:
  Replicate_Wild_Ignore_Table:
                   Last_Errno: 0
                   Last_Error:
                 Skip_Counter: 0
          Exec_Master_Log_Pos: 11757
              Relay_Log_Space: 530
              Until_Condition: None
               Until_Log_File:
                Until_Log_Pos: 0
           Master_SSL_Allowed: No
           Master_SSL_CA_File:
           Master_SSL_CA_Path:
              Master_SSL_Cert:
            Master_SSL_Cipher:
               Master_SSL_Key:
        Seconds_Behind_Master: 0
Master_SSL_Verify_Server_Cert: No
                Last_IO_Errno: 0
                Last_IO_Error:
               Last_SQL_Errno: 0
               Last_SQL_Error:
  Replicate_Ignore_Server_Ids:
             Master_Server_Id: 3316
                  Master_UUID: 4e9a7102-37c0-11eb-bec5-09c6c7938afa
             Master_Info_File: mysql.slave_master_info
                    SQL_Delay: 0
          SQL_Remaining_Delay: NULL
      Slave_SQL_Running_State: Slave has read all relay log; waiting for more updates
           Master_Retry_Count: 86400
                  Master_Bind:
      Last_IO_Error_Timestamp:
     Last_SQL_Error_Timestamp:
               Master_SSL_Crl:
           Master_SSL_Crlpath:
           Retrieved_Gtid_Set:
            Executed_Gtid_Set:
                Auto_Position: 0
         Replicate_Rewrite_DB:
                 Channel_Name:
           Master_TLS_Version:
       Master_public_key_path:
        Get_master_public_key: 0
            Network_Namespace:
1 row in set, 1 warning (0.01 sec)

mysql>
```

主库创建库表

```shell
mysql> show master status;
+------------------+----------+--------------+------------------+-------------------+
| File             | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+------------------+----------+--------------+------------------+-------------------+
| mysql-bin.000013 |    11757 |              |                  |                   |
+------------------+----------+--------------+------------------+-------------------+
1 row in set (0.00 sec)

mysql> CREATE SCHEMA xydb;
Query OK, 1 row affected (0.01 sec)

mysql> use xydb;
Database changed
mysql> CREATE TABLE concurrent_trip (
    ->   id BIGINT AUTO_INCREMENT NOT NULL,
    ->   name VARCHAR(81) NOT NULL,
    ->   duration INT NOT NULL,
    ->   result INT NOT NULL,
    ->   PRIMARY KEY(id)
    -> ) Engine=INNODB DEFAULT CHARSET=UTF8;
Query OK, 0 rows affected, 1 warning (0.02 sec)

mysql> show master status;
+------------------+----------+--------------+------------------+-------------------+
| File             | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+------------------+----------+--------------+------------------+-------------------+
| mysql-bin.000013 |    12306 |              |                  |                   |
+------------------+----------+--------------+------------------+-------------------+
1 row in set (0.01 sec)

mysql>
```

查看从库

```shell
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
| xydb               |
+--------------------+
5 rows in set (0.01 sec)

mysql> show slave status\G
*************************** 1. row ***************************
               Slave_IO_State: Waiting for master to send event
                  Master_Host: 127.0.0.1
                  Master_User: xy
                  Master_Port: 3316
                Connect_Retry: 60
              Master_Log_File: mysql-bin.000013
          Read_Master_Log_Pos: 12306
               Relay_Log_File: XY-relay-bin.000002
                Relay_Log_Pos: 873
        Relay_Master_Log_File: mysql-bin.000013
             Slave_IO_Running: Yes
            Slave_SQL_Running: Yes
              Replicate_Do_DB:
          Replicate_Ignore_DB:
           Replicate_Do_Table:
       Replicate_Ignore_Table:
      Replicate_Wild_Do_Table:
  Replicate_Wild_Ignore_Table:
                   Last_Errno: 0
                   Last_Error:
                 Skip_Counter: 0
          Exec_Master_Log_Pos: 12306
              Relay_Log_Space: 1079
              Until_Condition: None
               Until_Log_File:
                Until_Log_Pos: 0
           Master_SSL_Allowed: No
           Master_SSL_CA_File:
           Master_SSL_CA_Path:
              Master_SSL_Cert:
            Master_SSL_Cipher:
               Master_SSL_Key:
        Seconds_Behind_Master: 0
Master_SSL_Verify_Server_Cert: No
                Last_IO_Errno: 0
                Last_IO_Error:
               Last_SQL_Errno: 0
               Last_SQL_Error:
  Replicate_Ignore_Server_Ids:
             Master_Server_Id: 3316
                  Master_UUID: 4e9a7102-37c0-11eb-bec5-09c6c7938afa
             Master_Info_File: mysql.slave_master_info
                    SQL_Delay: 0
          SQL_Remaining_Delay: NULL
      Slave_SQL_Running_State: Slave has read all relay log; waiting for more updates
           Master_Retry_Count: 86400
                  Master_Bind:
      Last_IO_Error_Timestamp:
     Last_SQL_Error_Timestamp:
               Master_SSL_Crl:
           Master_SSL_Crlpath:
           Retrieved_Gtid_Set:
            Executed_Gtid_Set:
                Auto_Position: 0
         Replicate_Rewrite_DB:
                 Channel_Name:
           Master_TLS_Version:
       Master_public_key_path:
        Get_master_public_key: 0
            Network_Namespace:
1 row in set, 1 warning (0.02 sec)

mysql>
```

主库插入数据并查看

```shell
mysql> select * from concurrent_trip;
+----+-----------------------------------------------------------------+----------+-----------+
| id | name                                                            | duration | result    |
+----+-----------------------------------------------------------------+----------+-----------+
|  1 | Simple$$EnhancerBySpringCGLIB$$e952977                          |      610 | 102334155 |
|  2 | Yield$$EnhancerBySpringCGLIB$$69109f08                          |      890 | 102334155 |
|  3 | Join$$EnhancerBySpringCGLIB$$db332df                            |      843 | 102334155 |
|  4 | WaitAndNotify$$EnhancerBySpringCGLIB$$1d1c9542                  |      913 | 102334155 |
|  5 | FutureThreadFactory$$EnhancerBySpringCGLIB$$f28f6638            |     1002 | 102334155 |
|  6 | LockSupport$$EnhancerBySpringCGLIB$$b7651f2d                    |     1140 | 102334155 |
|  7 | LockWithCondition$$EnhancerBySpringCGLIB$$2a2a591f              |      926 | 102334155 |
|  8 | FutureTask$$EnhancerBySpringCGLIB$$147787d9                     |      886 | 102334155 |
|  9 | BlockingQueue$$EnhancerBySpringCGLIB$$821a1b55                  |      863 | 102334155 |
| 10 | Semaphore$$EnhancerBySpringCGLIB$$47dc7d8d                      |      839 | 102334155 |
| 11 | CountDownLatch$$EnhancerBySpringCGLIB$$fb0a9c5a                 |      834 | 102334155 |
| 12 | CyclicBarrier$$EnhancerBySpringCGLIB$$12253b                    |     1116 | 102334155 |
| 13 | CompletableFuture$$EnhancerBySpringCGLIB$$5d99161c              |      904 | 102334155 |
| 14 | CompletableFutureApply$$EnhancerBySpringCGLIB$$b18432d0         |      949 | 102334155 |
| 15 | CompletableFutureAccept$$EnhancerBySpringCGLIB$$a174b654        |      897 | 102334155 |
| 16 | CompletableFutureCombine$$EnhancerBySpringCGLIB$$821699a3       |      901 | 102334155 |
| 17 | CompletableFutureCompose$$EnhancerBySpringCGLIB$$8b3fc27c       |      901 | 102334155 |
| 18 | CompletableFutureApplyToEither$$EnhancerBySpringCGLIB$$bcf66990 |        4 | 102334155 |
| 19 | Exchanger$$EnhancerBySpringCGLIB$$b3dee1ae                      |     1044 | 102334155 |
+----+-----------------------------------------------------------------+----------+-----------+
19 rows in set (0.00 sec)

mysql> show master status;
+------------------+----------+--------------+------------------+-------------------+
| File             | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+------------------+----------+--------------+------------------+-------------------+
| mysql-bin.000013 |    15715 |              |                  |                   |
+------------------+----------+--------------+------------------+-------------------+
1 row in set (0.00 sec)

mysql>
```

从库查看数据

```shell
mysql> use xydb
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> select * from concurrent_trip;
+----+-----------------------------------------------------------------+----------+-----------+
| id | name                                                            | duration | result    |
+----+-----------------------------------------------------------------+----------+-----------+
|  1 | Simple$$EnhancerBySpringCGLIB$$e952977                          |      610 | 102334155 |
|  2 | Yield$$EnhancerBySpringCGLIB$$69109f08                          |      890 | 102334155 |
|  3 | Join$$EnhancerBySpringCGLIB$$db332df                            |      843 | 102334155 |
|  4 | WaitAndNotify$$EnhancerBySpringCGLIB$$1d1c9542                  |      913 | 102334155 |
|  5 | FutureThreadFactory$$EnhancerBySpringCGLIB$$f28f6638            |     1002 | 102334155 |
|  6 | LockSupport$$EnhancerBySpringCGLIB$$b7651f2d                    |     1140 | 102334155 |
|  7 | LockWithCondition$$EnhancerBySpringCGLIB$$2a2a591f              |      926 | 102334155 |
|  8 | FutureTask$$EnhancerBySpringCGLIB$$147787d9                     |      886 | 102334155 |
|  9 | BlockingQueue$$EnhancerBySpringCGLIB$$821a1b55                  |      863 | 102334155 |
| 10 | Semaphore$$EnhancerBySpringCGLIB$$47dc7d8d                      |      839 | 102334155 |
| 11 | CountDownLatch$$EnhancerBySpringCGLIB$$fb0a9c5a                 |      834 | 102334155 |
| 12 | CyclicBarrier$$EnhancerBySpringCGLIB$$12253b                    |     1116 | 102334155 |
| 13 | CompletableFuture$$EnhancerBySpringCGLIB$$5d99161c              |      904 | 102334155 |
| 14 | CompletableFutureApply$$EnhancerBySpringCGLIB$$b18432d0         |      949 | 102334155 |
| 15 | CompletableFutureAccept$$EnhancerBySpringCGLIB$$a174b654        |      897 | 102334155 |
| 16 | CompletableFutureCombine$$EnhancerBySpringCGLIB$$821699a3       |      901 | 102334155 |
| 17 | CompletableFutureCompose$$EnhancerBySpringCGLIB$$8b3fc27c       |      901 | 102334155 |
| 18 | CompletableFutureApplyToEither$$EnhancerBySpringCGLIB$$bcf66990 |        4 | 102334155 |
| 19 | Exchanger$$EnhancerBySpringCGLIB$$b3dee1ae                      |     1044 | 102334155 |
+----+-----------------------------------------------------------------+----------+-----------+
19 rows in set (0.00 sec)

mysql> show slave status\G
*************************** 1. row ***************************
               Slave_IO_State: Waiting for master to send event
                  Master_Host: 127.0.0.1
                  Master_User: xy
                  Master_Port: 3316
                Connect_Retry: 60
              Master_Log_File: mysql-bin.000013
          Read_Master_Log_Pos: 15715
               Relay_Log_File: XY-relay-bin.000002
                Relay_Log_Pos: 4282
        Relay_Master_Log_File: mysql-bin.000013
             Slave_IO_Running: Yes
            Slave_SQL_Running: Yes
              Replicate_Do_DB:
          Replicate_Ignore_DB:
           Replicate_Do_Table:
       Replicate_Ignore_Table:
      Replicate_Wild_Do_Table:
  Replicate_Wild_Ignore_Table:
                   Last_Errno: 0
                   Last_Error:
                 Skip_Counter: 0
          Exec_Master_Log_Pos: 15715
              Relay_Log_Space: 4488
              Until_Condition: None
               Until_Log_File:
                Until_Log_Pos: 0
           Master_SSL_Allowed: No
           Master_SSL_CA_File:
           Master_SSL_CA_Path:
              Master_SSL_Cert:
            Master_SSL_Cipher:
               Master_SSL_Key:
        Seconds_Behind_Master: 0
Master_SSL_Verify_Server_Cert: No
                Last_IO_Errno: 0
                Last_IO_Error:
               Last_SQL_Errno: 0
               Last_SQL_Error:
  Replicate_Ignore_Server_Ids:
             Master_Server_Id: 3316
                  Master_UUID: 4e9a7102-37c0-11eb-bec5-09c6c7938afa
             Master_Info_File: mysql.slave_master_info
                    SQL_Delay: 0
          SQL_Remaining_Delay: NULL
      Slave_SQL_Running_State: Slave has read all relay log; waiting for more updates
           Master_Retry_Count: 86400
                  Master_Bind:
      Last_IO_Error_Timestamp:
     Last_SQL_Error_Timestamp:
               Master_SSL_Crl:
           Master_SSL_Crlpath:
           Retrieved_Gtid_Set:
            Executed_Gtid_Set:
                Auto_Position: 0
         Replicate_Rewrite_DB:
                 Channel_Name:
           Master_TLS_Version:
       Master_public_key_path:
        Get_master_public_key: 0
            Network_Namespace:
1 row in set, 1 warning (0.01 sec)

mysql>
```



[MYSQL database replication Error MY-002061](https://dba.stackexchange.com/questions/218207/mysql-database-replication-error-my-002061)

主从复制报错2061解决方案

```sql
CHANGE MASTER TO GET_MASTER_PUBLIC_KEY=1;
```

CHANGE MASTER TO
    MASTER_HOST='127.0.0.1',  
    MASTER_PORT = 3316,
    MASTER_USER='xy',      
    MASTER_PASSWORD='xy',   
    MASTER_LOG_FILE='mysql-bin.000015',
    MASTER_LOG_POS=1078;