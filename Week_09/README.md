[mysql mac主从配置 及 demo](https://github.com/catofdestruction/JAVA-000/tree/main/Week_07/mysql-master-slave-replication) (补交)

console:

```java
2020-12-18 20:28:33.089 [main] INFO  org.xy.rpc.consumer.RpcClientApplication.main:22 - class org.xy.rpc.consumer.RpcClientApplication
req json: {"method":"findUserById","params":[1],"serviceClass":"org.xy.rpc.api.service.UserService"}
resp json: {"result":"{\"@type\":\"org.xy.rpc.api.model.User\",\"id\":1,\"name\":\"XY-1608294513774\"}","status":true,"exception":null}
2020-12-18 20:28:33.883 [main] INFO  org.xy.rpc.consumer.RpcClientApplication.main:26 - user User(id=1, name=XY-1608294513774)
```

