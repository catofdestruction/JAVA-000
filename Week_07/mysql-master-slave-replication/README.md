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

