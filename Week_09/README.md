[mysql mac主从配置 及 demo](https://github.com/catofdestruction/JAVA-000/tree/main/Week_07/mysql-master-slave-replication) (补交)

console:

```java
2020-12-18 22:03:21.950 [main] INFO  org.xy.rpc.consumer.RpcClientApplication.main:22 - class org.xy.rpc.consumer.RpcClientApplication
2020-12-18 22:03:22.029 [main] INFO  org.xy.rpc.core.client.RpcJdkProxyFactory.post:65 - 
[request]: {"method":"findUserById","params":[666],"serviceClass":"org.xy.rpc.api.service.UserService"}

2020-12-18 22:03:22.309 [main] INFO  org.xy.rpc.core.client.RpcJdkProxyFactory.post:73 - 
[response]: {"@type":"org.xy.rpc.core.api.RpcResponse","exception":null,"result":{"@type":"org.xy.rpc.api.model.User","id":666,"name":"XY-1608300202304"},"status":true}

2020-12-18 22:03:22.317 [main] INFO  org.xy.rpc.core.client.RpcJdkProxyFactory.post:65 - 
[request]: {"method":"findOrderById","params":[999],"serviceClass":"org.xy.rpc.api.service.OrderService"}

2020-12-18 22:03:22.328 [main] INFO  org.xy.rpc.core.client.RpcJdkProxyFactory.post:73 - 
[response]: {"@type":"org.xy.rpc.core.api.RpcResponse","exception":null,"result":{"@type":"org.xy.rpc.api.model.Order","amount":81.0,"id":999,"serialNumber":"7d514794-1c9b-472d-bb7d-cdc6a70044ae"},"status":true}
```

**dubbo+hmily to be continued...**

