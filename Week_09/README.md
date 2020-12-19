[mysql mac主从配置 及 demo](https://github.com/catofdestruction/JAVA-000/tree/main/Week_07/mysql-master-slave-replication) (补交)

console:

```java
2020-12-19 23:58:20.627 [main] INFO  org.xy.rpc.consumer.RpcClientApplication.main:26 - class org.xy.rpc.consumer.RpcClientApplication
2020-12-19 23:58:20.635 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByRpcJdkProxy:32 - 
========== RpcJdkProxy ==========

2020-12-19 23:58:20.725 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:38 - 
[request]: {"method":"findUserById","params":[666],"serviceClass":"org.xy.rpc.api.service.UserService"}

2020-12-19 23:58:21.247 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:46 - 
[response]: {"@type":"org.xy.rpc.core.api.RpcResponse","exception":null,"result":{"@type":"org.xy.rpc.api.model.User","id":666,"name":"XY-1608393501158"},"status":true}

2020-12-19 23:58:21.262 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByRpcJdkProxy:34 - User(id=666, name=XY-1608393501158)
2020-12-19 23:58:21.268 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:38 - 
[request]: {"method":"findOrderById","params":[999],"serviceClass":"org.xy.rpc.api.service.OrderService"}

2020-12-19 23:58:21.296 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:46 - 
[response]: {"@type":"org.xy.rpc.core.api.RpcResponse","exception":null,"result":{"@type":"org.xy.rpc.api.model.Order","amount":81.0,"id":999,"serialNumber":"28b10caa-cefe-4691-a2fb-61c1acadd4eb"},"status":true}

2020-12-19 23:58:21.303 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByRpcJdkProxy:36 - Order(id=999, serialNumber=28b10caa-cefe-4691-a2fb-61c1acadd4eb, amount=81.0)
2020-12-19 23:58:21.303 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByCglibProxy:40 - 
========== RpcCglibProxy ==========

2020-12-19 23:58:21.382 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:38 - 
[request]: {"method":"findUserById","params":[666],"serviceClass":"org.xy.rpc.api.service.UserService"}

2020-12-19 23:58:21.433 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:46 - 
[response]: {"@type":"org.xy.rpc.core.api.RpcResponse","exception":null,"result":{"@type":"org.xy.rpc.api.model.User","id":666,"name":"XY-1608393501431"},"status":true}

2020-12-19 23:58:21.434 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByCglibProxy:42 - User(id=666, name=XY-1608393501431)
2020-12-19 23:58:21.438 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:38 - 
[request]: {"method":"findOrderById","params":[999],"serviceClass":"org.xy.rpc.api.service.OrderService"}

2020-12-19 23:58:21.445 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:46 - 
[response]: {"@type":"org.xy.rpc.core.api.RpcResponse","exception":null,"result":{"@type":"org.xy.rpc.api.model.Order","amount":81.0,"id":999,"serialNumber":"916d19ed-bd88-4ca3-b07f-1e08332c9468"},"status":true}

2020-12-19 23:58:21.446 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByCglibProxy:44 - Order(id=999, serialNumber=916d19ed-bd88-4ca3-b07f-1e08332c9468, amount=81.0)
```

**dubbo+hmily to be continued...**

