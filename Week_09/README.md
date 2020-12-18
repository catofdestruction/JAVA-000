[mysql mac主从配置 及 demo](https://github.com/catofdestruction/JAVA-000/tree/main/Week_07/mysql-master-slave-replication) (补交)

console:

```java
2020-12-18 21:55:12.671 [main] INFO  org.xy.rpc.consumer.RpcClientApplication.main:22 - class org.xy.rpc.consumer.RpcClientApplication
2020-12-18 21:55:12.748 [main] INFO  org.xy.rpc.core.client.RpcJdkProxyFactory.post:65 - 
[request]: {"method":"findUserById","params":[1],"serviceClass":"org.xy.rpc.api.service.UserService"}

2020-12-18 21:55:13.159 [main] INFO  org.xy.rpc.core.client.RpcJdkProxyFactory.post:73 - 
[response]: {"@type":"org.xy.rpc.core.api.RpcResponse","exception":null,"result":{"@type":"org.xy.rpc.api.model.User","id":1,"name":"XY-1608299713154"},"status":true}

2020-12-18 21:55:13.168 [main] INFO  org.xy.rpc.consumer.RpcClientApplication.main:26 - user User(id=1, name=XY-1608299713154)
```

**dubbo+hmily to be continued...**

