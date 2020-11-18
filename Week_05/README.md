# Console output



```shell
2020-11-18 23:56:39.191 [main] INFO  org.xy.spring.SpringBootDemoApplication.main:27 - Application start ...

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.6.RELEASE)

2020-11-18 23:56:39.577 [main] INFO  org.xy.spring.SpringBootDemoApplication.logStartupProfileInfo:651 - No active profile set, falling back to default profiles: default
2020-11-18 23:56:40.634 [main] INFO  org.xy.spring.SpringBootDemoApplication.logStarted:61 - Started SpringBootDemoApplication in 1.364 seconds (JVM running for 2.124)
2020-11-18 23:56:40.636 [main] INFO  org.xy.spring.SpringBootDemoApplication.main:29 - Application started ...
2020-11-18 23:56:40.638 [main] INFO  org.xy.spring.source.RunReturnablesAopByXml.showSpringBootApplicationThreads:26 - 
================================================================== 3 threads ==================================================================

java.lang.ThreadGroup[name=main,maxpri=10]
    Thread[main,5,main]
    Thread[Monitor Ctrl-Break,5,main]
    Thread[logback-2,5,main]
2020-11-18 23:56:40.642 [main] INFO  org.xy.spring.source.RunReturnablesAopByXml.showSpringBootApplicationThreads:28 - 
================================================================== 10 threads ==================================================================

java.lang.ThreadGroup[name=system,maxpri=10]
    Thread[Reference Handler,10,system]
    Thread[Finalizer,8,system]
    Thread[Signal Dispatcher,9,system]
    Thread[RMI TCP Accept-0,5,system]
    Thread[Attach Listener,9,system]
    Thread[RMI Scheduler(0),5,system]
    java.lang.ThreadGroup[name=main,maxpri=10]
        Thread[main,5,main]
        Thread[Monitor Ctrl-Break,5,main]
        Thread[logback-2,5,main]
    java.lang.ThreadGroup[name=RMI Runtime,maxpri=10]
        Thread[RMI TCP Connection(1)-127.0.0.1,5,RMI Runtime]
2020-11-18 23:56:40.651 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (0) Simple$$EnhancerBySpringCGLIB$$98f380f8 ==================================================================

2020-11-18 23:56:40.652 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[Simple in Thread[main,5,main]] start ...

2020-11-18 23:56:41.551 [Simple] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [Simple in Thread[Simple,5,main]] compute fibo40 result: 102334155 cost: 892 ms 
==========================================================================================

2020-11-18 23:56:41.552 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[Simple in Thread[main,5,main]] end ...

2020-11-18 23:56:41.554 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (1) Yield$$EnhancerBySpringCGLIB$$f36ef689 ==================================================================

2020-11-18 23:56:41.555 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[Yield in Thread[main,5,main]] start ...

2020-11-18 23:56:42.562 [Yield] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [Yield in Thread[Yield,5,main]] compute fibo40 result: 102334155 cost: 1003 ms 
==========================================================================================

2020-11-18 23:56:42.563 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[Yield in Thread[main,5,main]] end ...

2020-11-18 23:56:42.563 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (2) Join$$EnhancerBySpringCGLIB$$98118a60 ==================================================================

2020-11-18 23:56:42.564 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[Join in Thread[main,5,main]] start ...

2020-11-18 23:56:43.503 [Join] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [Join in Thread[Join,5,main]] compute fibo40 result: 102334155 cost: 936 ms 
==========================================================================================

2020-11-18 23:56:43.504 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[Join in Thread[main,5,main]] end ...

2020-11-18 23:56:43.505 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (3) WaitAndNotify$$EnhancerBySpringCGLIB$$a77aecc3 ==================================================================

2020-11-18 23:56:43.505 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[WaitAndNotify in Thread[main,5,main]] start ...

2020-11-18 23:56:44.601 [WaitAndNotify] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [WaitAndNotify in Thread[WaitAndNotify,5,main]] compute fibo40 result: 102334155 cost: 1091 ms 
==========================================================================================

2020-11-18 23:56:44.602 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[WaitAndNotify in Thread[main,5,main]] end ...

2020-11-18 23:56:44.603 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (4) FutureThreadFactory$$EnhancerBySpringCGLIB$$7cedbdb9 ==================================================================

2020-11-18 23:56:44.603 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[FutureThreadFactory in Thread[main,5,main]] start ...

2020-11-18 23:56:45.695 [FutureThreadFactory-0] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [FutureThreadFactory in Thread[FutureThreadFactory-0,5,main]] compute fibo40 result: 102334155 cost: 1088 ms 
==========================================================================================

2020-11-18 23:56:45.696 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[FutureThreadFactory in Thread[main,5,main]] end ...

2020-11-18 23:56:45.697 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (5) LockSupport$$EnhancerBySpringCGLIB$$41c376ae ==================================================================

2020-11-18 23:56:45.697 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[LockSupport in Thread[main,5,main]] start ...

2020-11-18 23:56:46.569 [LockSupport] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [LockSupport in Thread[LockSupport,5,main]] compute fibo40 result: 102334155 cost: 870 ms 
==========================================================================================

2020-11-18 23:56:46.570 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[LockSupport in Thread[main,5,main]] end ...

2020-11-18 23:56:46.570 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (6) LockWithCondition$$EnhancerBySpringCGLIB$$b488b0a0 ==================================================================

2020-11-18 23:56:46.570 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[LockWithCondition in Thread[main,5,main]] start ...

2020-11-18 23:56:47.431 [LockWithCondition] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [LockWithCondition in Thread[LockWithCondition,5,main]] compute fibo40 result: 102334155 cost: 858 ms 
==========================================================================================

2020-11-18 23:56:47.431 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[LockWithCondition in Thread[main,5,main]] end ...

2020-11-18 23:56:47.432 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (7) FutureTask$$EnhancerBySpringCGLIB$$9ed5df5a ==================================================================

2020-11-18 23:56:47.432 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[FutureTask in Thread[main,5,main]] start ...

2020-11-18 23:56:48.287 [FutureTask] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [FutureTask in Thread[FutureTask,5,main]] compute fibo40 result: 102334155 cost: 852 ms 
==========================================================================================

2020-11-18 23:56:48.287 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[FutureTask in Thread[main,5,main]] end ...

2020-11-18 23:56:48.288 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (8) BlockingQueue$$EnhancerBySpringCGLIB$$c7872d6 ==================================================================

2020-11-18 23:56:48.288 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[BlockingQueue in Thread[main,5,main]] start ...

2020-11-18 23:56:49.152 [BlockingQueue] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [BlockingQueue in Thread[BlockingQueue,5,main]] compute fibo40 result: 102334155 cost: 861 ms 
==========================================================================================

2020-11-18 23:56:49.153 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[BlockingQueue in Thread[main,5,main]] end ...

2020-11-18 23:56:49.154 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (9) Semaphore$$EnhancerBySpringCGLIB$$d23ad50e ==================================================================

2020-11-18 23:56:49.154 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[Semaphore in Thread[main,5,main]] start ...

2020-11-18 23:56:50.056 [Semaphore] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [Semaphore in Thread[Semaphore,5,main]] compute fibo40 result: 102334155 cost: 898 ms 
==========================================================================================

2020-11-18 23:56:50.057 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[Semaphore in Thread[main,5,main]] end ...

2020-11-18 23:56:50.058 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (10) CountDownLatch$$EnhancerBySpringCGLIB$$8568f3db ==================================================================

2020-11-18 23:56:50.058 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[CountDownLatch in Thread[main,5,main]] start ...

2020-11-18 23:56:50.946 [CountDownLatch] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [CountDownLatch in Thread[CountDownLatch,5,main]] compute fibo40 result: 102334155 cost: 885 ms 
==========================================================================================

2020-11-18 23:56:50.947 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[CountDownLatch in Thread[main,5,main]] end ...

2020-11-18 23:56:50.958 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (11) CyclicBarrier$$EnhancerBySpringCGLIB$$8a707cbc ==================================================================

2020-11-18 23:56:50.959 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[CyclicBarrier in Thread[main,5,main]] start ...

2020-11-18 23:56:52.039 [CyclicBarrier] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [CyclicBarrier in Thread[CyclicBarrier,5,main]] compute fibo40 result: 102334155 cost: 1075 ms 
==========================================================================================

2020-11-18 23:56:52.039 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[CyclicBarrier in Thread[main,5,main]] end ...

2020-11-18 23:56:52.061 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (12) CompletableFuture$$EnhancerBySpringCGLIB$$e7f76d9d ==================================================================

2020-11-18 23:56:52.062 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[CompletableFuture in Thread[main,5,main]] start ...

2020-11-18 23:56:53.212 [ForkJoinPool.commonPool-worker-1] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [CompletableFuture in Thread[ForkJoinPool.commonPool-worker-1,5,main]] compute fibo40 result: 102334155 cost: 1141 ms 
==========================================================================================

2020-11-18 23:56:53.212 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[CompletableFuture in Thread[main,5,main]] end ...

2020-11-18 23:56:53.213 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (13) CompletableFutureApply$$EnhancerBySpringCGLIB$$3be28a51 ==================================================================

2020-11-18 23:56:53.213 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[CompletableFutureApply in Thread[main,5,main]] start ...

2020-11-18 23:56:54.452 [ForkJoinPool.commonPool-worker-1] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [CompletableFutureApply in Thread[ForkJoinPool.commonPool-worker-1,5,main]] compute fibo40 result: 102334155 cost: 1236 ms 
==========================================================================================

2020-11-18 23:56:54.452 [ForkJoinPool.commonPool-worker-1] WARN  org.xy.spring.source.Returnable.optimizedFibo40:86 - 
==========================================================================================
 [CompletableFutureApply in Thread[ForkJoinPool.commonPool-worker-1,5,main]] compute fibo40 result: 102334155 cost: 0 ms 
==========================================================================================

2020-11-18 23:56:54.453 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[CompletableFutureApply in Thread[main,5,main]] end ...

2020-11-18 23:56:54.454 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (14) CompletableFutureAccept$$EnhancerBySpringCGLIB$$2bd30dd5 ==================================================================

2020-11-18 23:56:54.455 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[CompletableFutureAccept in Thread[main,5,main]] start ...

2020-11-18 23:56:55.905 [ForkJoinPool.commonPool-worker-1] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [CompletableFutureAccept in Thread[ForkJoinPool.commonPool-worker-1,5,main]] compute fibo40 result: 102334155 cost: 1447 ms 
==========================================================================================

2020-11-18 23:56:55.905 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[CompletableFutureAccept in Thread[main,5,main]] end ...

2020-11-18 23:56:55.907 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (15) CompletableFutureCombine$$EnhancerBySpringCGLIB$$c74f124 ==================================================================

2020-11-18 23:56:55.907 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[CompletableFutureCombine in Thread[main,5,main]] start ...

2020-11-18 23:56:55.911 [ForkJoinPool.commonPool-worker-2] WARN  org.xy.spring.source.Returnable.optimizedFibo40:86 - 
==========================================================================================
 [CompletableFutureCombine in Thread[ForkJoinPool.commonPool-worker-2,5,main]] compute fibo40 result: 102334155 cost: 0 ms 
==========================================================================================

2020-11-18 23:56:56.984 [ForkJoinPool.commonPool-worker-1] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [CompletableFutureCombine in Thread[ForkJoinPool.commonPool-worker-1,5,main]] compute fibo40 result: 102334155 cost: 1073 ms 
==========================================================================================

2020-11-18 23:56:57.047 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[CompletableFutureCombine in Thread[main,5,main]] end ...

2020-11-18 23:56:57.048 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (16) CompletableFutureCompose$$EnhancerBySpringCGLIB$$159e19fd ==================================================================

2020-11-18 23:56:57.049 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[CompletableFutureCompose in Thread[main,5,main]] start ...

2020-11-18 23:56:58.179 [ForkJoinPool.commonPool-worker-1] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [CompletableFutureCompose in Thread[ForkJoinPool.commonPool-worker-1,5,main]] compute fibo40 result: 102334155 cost: 1126 ms 
==========================================================================================

2020-11-18 23:56:58.224 [ForkJoinPool.commonPool-worker-2] WARN  org.xy.spring.source.Returnable.optimizedFibo40:86 - 
==========================================================================================
 [CompletableFutureCompose in Thread[ForkJoinPool.commonPool-worker-2,5,main]] compute fibo40 result: 102334155 cost: 0 ms 
==========================================================================================

2020-11-18 23:56:58.225 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[CompletableFutureCompose in Thread[main,5,main]] end ...

2020-11-18 23:56:58.226 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (17) CompletableFutureApplyToEither$$EnhancerBySpringCGLIB$$4754c111 ==================================================================

2020-11-18 23:56:58.226 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[CompletableFutureApplyToEither in Thread[main,5,main]] start ...

2020-11-18 23:56:58.229 [ForkJoinPool.commonPool-worker-1] WARN  org.xy.spring.source.Returnable.optimizedFibo40:86 - 
==========================================================================================
 [CompletableFutureApplyToEither in Thread[ForkJoinPool.commonPool-worker-1,5,main]] compute fibo40 result: 102334155 cost: 0 ms 
==========================================================================================

2020-11-18 23:56:58.229 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[CompletableFutureApplyToEither in Thread[main,5,main]] end ...

2020-11-18 23:56:58.230 [main] INFO  org.xy.spring.source.ReturnableAop.lambda$around$0:54 - 
================================================================== (18) Exchanger$$EnhancerBySpringCGLIB$$3e3d392f ==================================================================

2020-11-18 23:56:58.230 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:55 - 
[Exchanger in Thread[main,5,main]] start ...

2020-11-18 23:56:59.331 [ForkJoinPool.commonPool-worker-2] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [CompletableFutureApplyToEither in Thread[ForkJoinPool.commonPool-worker-2,5,main]] compute fibo40 result: 102334155 cost: 1102 ms 
==========================================================================================

2020-11-18 23:56:59.339 [Exchanger] WARN  org.xy.spring.source.Returnable.fibo40:73 - 
==========================================================================================
 [Exchanger in Thread[Exchanger,5,main]] compute fibo40 result: 102334155 cost: 1106 ms 
==========================================================================================

2020-11-18 23:56:59.390 [main] WARN  org.xy.spring.source.ReturnableAop.lambda$around$0:61 - 
[Exchanger in Thread[main,5,main]] end ...

2020-11-18 23:56:59.408 [main] INFO  com.zaxxer.hikari.HikariDataSource.<init>:71 - HikariPool-1 - Starting...
2020-11-18 23:56:59.869 [main] INFO  com.zaxxer.hikari.HikariDataSource.<init>:73 - HikariPool-1 - Start completed.
2020-11-18 23:56:59.886 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('Simple$$EnhancerBySpringCGLIB$$98f380f8', 903, 102334155)

2020-11-18 23:56:59.887 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('Yield$$EnhancerBySpringCGLIB$$f36ef689', 1009, 102334155)

2020-11-18 23:56:59.892 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('Join$$EnhancerBySpringCGLIB$$98118a60', 941, 102334155)

2020-11-18 23:56:59.893 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('WaitAndNotify$$EnhancerBySpringCGLIB$$a77aecc3', 1099, 102334155)

2020-11-18 23:56:59.893 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('FutureThreadFactory$$EnhancerBySpringCGLIB$$7cedbdb9', 1093, 102334155)

2020-11-18 23:56:59.894 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('LockSupport$$EnhancerBySpringCGLIB$$41c376ae', 873, 102334155)

2020-11-18 23:56:59.894 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('LockWithCondition$$EnhancerBySpringCGLIB$$b488b0a0', 862, 102334155)

2020-11-18 23:56:59.895 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('FutureTask$$EnhancerBySpringCGLIB$$9ed5df5a', 856, 102334155)

2020-11-18 23:56:59.895 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('BlockingQueue$$EnhancerBySpringCGLIB$$c7872d6', 866, 102334155)

2020-11-18 23:56:59.896 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('Semaphore$$EnhancerBySpringCGLIB$$d23ad50e', 903, 102334155)

2020-11-18 23:56:59.896 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('CountDownLatch$$EnhancerBySpringCGLIB$$8568f3db', 901, 102334155)

2020-11-18 23:56:59.897 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('CyclicBarrier$$EnhancerBySpringCGLIB$$8a707cbc', 1103, 102334155)

2020-11-18 23:56:59.898 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('CompletableFuture$$EnhancerBySpringCGLIB$$e7f76d9d', 1151, 102334155)

2020-11-18 23:56:59.898 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('CompletableFutureApply$$EnhancerBySpringCGLIB$$3be28a51', 1242, 102334155)

2020-11-18 23:56:59.899 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('CompletableFutureAccept$$EnhancerBySpringCGLIB$$2bd30dd5', 1452, 102334155)

2020-11-18 23:56:59.900 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('CompletableFutureCombine$$EnhancerBySpringCGLIB$$c74f124', 1142, 102334155)

2020-11-18 23:56:59.900 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('CompletableFutureCompose$$EnhancerBySpringCGLIB$$159e19fd', 1177, 102334155)

2020-11-18 23:56:59.905 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('CompletableFutureApplyToEither$$EnhancerBySpringCGLIB$$4754c111', 5, 102334155)

2020-11-18 23:56:59.906 [main] INFO  org.xy.spring.jdbc.JdbcUtils.batchInsert:96 - 
> HikariProxyPreparedStatement@1818449913 wrapping com.mysql.jdbc.JDBC42PreparedStatement@6fff253c: INSERT INTO concurrent_trip (name, duration, result) VALUES ('Exchanger$$EnhancerBySpringCGLIB$$3e3d392f', 1161, 102334155)

2020-11-18 23:57:00.013 [main] INFO  org.xy.spring.source.RunReturnablesAopByXml.after:21 - 
================================================================== THE END ==================================================================


Process finished with exit code 0
```