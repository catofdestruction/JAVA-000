[mysql mac主从配置 及 demo](https://github.com/catofdestruction/JAVA-000/tree/main/Week_07/mysql-master-slave-replication) (补交)

console:

```java
========== RpcJdkProxy ==========

2020-12-20 18:56:33.463 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:38 - 
[request]: {"method":"findUserById","params":[666],"serviceClass":"org.xy.rpc.api.service.UserService"}

2020-12-20 18:56:33.856 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:46 - 
[response]: {"@type":"org.xy.rpc.core.api.RpcResponse","exception":null,"result":{"@type":"org.xy.rpc.api.model.User","id":666,"name":"XY-1608461793835"},"status":true}

2020-12-20 18:56:33.863 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByRpcJdkProxy:55 - User(id=666, name=XY-1608461793835)
2020-12-20 18:56:33.863 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:38 - 
[request]: {"method":"findOrderById","params":[999],"serviceClass":"org.xy.rpc.api.service.OrderService"}

2020-12-20 18:56:33.870 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:46 - 
[response]: {"@type":"org.xy.rpc.core.api.RpcResponse","exception":null,"result":{"@type":"org.xy.rpc.api.model.Order","amount":81.0,"id":999,"serialNumber":"46241985-4142-42e6-8161-2da291924676"},"status":true}

2020-12-20 18:56:33.871 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByRpcJdkProxy:57 - Order(id=999, serialNumber=46241985-4142-42e6-8161-2da291924676, amount=81.0)
2020-12-20 18:56:33.872 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByCglibProxy:61 - 
========== RpcCglibProxy ==========

2020-12-20 18:56:33.876 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:38 - 
[request]: {"method":"findUserById","params":[666],"serviceClass":"org.xy.rpc.api.service.UserService"}

2020-12-20 18:56:33.882 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:46 - 
[response]: {"@type":"org.xy.rpc.core.api.RpcResponse","exception":null,"result":{"@type":"org.xy.rpc.api.model.User","id":666,"name":"XY-1608461793879"},"status":true}

2020-12-20 18:56:33.882 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByCglibProxy:63 - User(id=666, name=XY-1608461793879)
2020-12-20 18:56:33.884 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:38 - 
[request]: {"method":"findOrderById","params":[999],"serviceClass":"org.xy.rpc.api.service.OrderService"}

2020-12-20 18:56:33.889 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:46 - 
[response]: {"@type":"org.xy.rpc.core.api.RpcResponse","exception":null,"result":{"@type":"org.xy.rpc.api.model.Order","amount":81.0,"id":999,"serialNumber":"ef4977b0-f635-4f80-aecd-5287bd0567bb"},"status":true}

2020-12-20 18:56:33.889 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByCglibProxy:65 - Order(id=999, serialNumber=ef4977b0-f635-4f80-aecd-5287bd0567bb, amount=81.0)
2020-12-20 18:56:33.889 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByRpcServiceProxyFactoryBean:69 - 
========== RpcServiceProxyFactoryBean is not a good way ==========

2020-12-20 18:56:33.889 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:38 - 
[request]: {"method":"findUserById","params":[666],"serviceClass":"org.xy.rpc.api.service.UserService"}

2020-12-20 18:56:33.893 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:46 - 
[response]: {"@type":"org.xy.rpc.core.api.RpcResponse","exception":null,"result":{"@type":"org.xy.rpc.api.model.User","id":666,"name":"XY-1608461793892"},"status":true}

2020-12-20 18:56:33.893 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByRpcServiceProxyFactoryBean:70 - User(id=666, name=XY-1608461793892)
2020-12-20 18:56:33.894 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:38 - 
[request]: {"method":"findOrderById","params":[999],"serviceClass":"org.xy.rpc.api.service.OrderService"}

2020-12-20 18:56:33.898 [main] INFO  org.xy.rpc.core.client.TempPostInterface.post:46 - 
[response]: {"@type":"org.xy.rpc.core.api.RpcResponse","exception":null,"result":{"@type":"org.xy.rpc.api.model.Order","amount":81.0,"id":999,"serialNumber":"e1766861-5afb-4976-a711-3305b0b5155a"},"status":true}

2020-12-20 18:56:33.898 [main] WARN  org.xy.rpc.consumer.RpcClientApplication.invokeByRpcServiceProxyFactoryBean:71 - Order(id=999, serialNumber=e1766861-5afb-4976-a711-3305b0b5155a, amount=81.0)
```

**dubbo+hmily to be continued...**

