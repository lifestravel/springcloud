SpringCloud是分布式微服务架构的一站式解决方法，是多种微服务架构落地技术的集合体，俗称微服务全家桶。SpringCloud 和 springCloud Alibaba 目前是最主流的微服务框架组合。

**SpringCloud和SpringBoot版本对应关系查看:https://start.spring.io/actuator/info**


#    Eureka：
## 什么是服务治理：
SpringCloud封装了Netflix公司开发的Eureka模块来实现服务治理
在传统的rpc远程调用框架中，管理每个服务与服务之间依赖关系比较复杂，管理比较复杂，所以需要使用服务治理，管理服务于服务之间依赖关系，可以实现服务调用、负载均衡、容错等，实现服务发现与注册。
# 什么是服务注册与发现：
Eureka采用了CS的设计结构，Eureka Server服务注册功能的服务器，它是服务注册中心。而系统中的其他微服务，使用Eureka的客户端连接到Eureka Server并维持心跳连接。这样系统的维护人员就可以通过Eureka Server来监控系统中各个微服务是否正常运行。这点和zookeeper很相似

在服务注册与发现中，有一个注册中心。当服务器启却时候，会把当前自己服务器的信息比如服务地址適讯地址等以别名方式注册到注册中心上。另一方（消费者服务提供者），以该别名的方式去注册中心上获取到实际的服务適讯地址然后再实现本地RPC调用RPC远程调用框架核心设计思想：在于注册中心，因为便用注册中心管理每个服务与服务之间的一个依赖关系（服务治理概念)。在任何rpc远程框架中，都会有一个注册中心（存放服务地址相关信息（接口地址））

![https://img-blog.csdnimg.cn/img_convert/8da648937c52bff1a3690b6520c3f540.png](https://img-blog.csdnimg.cn/img_convert/8da648937c52bff1a3690b6520c3f540.png)
> 官方停更不停用，以后可能用的越来越少。
  Eureka 是 Netflix 开发的，一个基于 REST 服务的，服务注册与发现的组件，以实现中间层服务器的负载平衡和故障转移。
  Eureka 分为 Eureka Server 和 Eureka Client及服务端和客户端。Eureka Server为注册中心，是服务端，而服务提供者和消费者即为客户端，消费者也可以是服务者，服务者也可以是消费者。同时Eureka Server在启动时默认会注册自己，成为一个服务，所以Eureka Server也是一个客户端，这是搭建Eureka集群的基础。
  Eureka Client：一个Java客户端，用于简化与 Eureka Server 的交互（通常就是微服务中的客户端和服务端）。通过注册中心进行访问。是一个Java客户端，用于简化Eureka Server的交互，客户端同时也具备一个内置的、使用轮询（roundrobin）负载算氵去的负载均衡器
  在应用启动后，将会向Eureka Server发送心跳（默认周期为30秒）。如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除（默认90秒）
  Eureka Server：提供服务注册服务，各个微服务节，通过配置启动后，会在Eureka Serverc中进行注册，这样Eureka Server中的服务注册表中将会存储所有可用服务节点信息，服务节点的信息可以在界面中直观看到。
  服务在Eureka上注册，然后每隔30秒发送心跳来更新它们的租约。如果客户端不能多次续订租约，那么它将在大约90秒内从服务器注册表中剔除。注册信息和更新被复制到集群中的所有eureka节点。来自任何区域的客户端都可以查找注册表信息（每30秒发生一次）来定位它们的服务（可能在任何区域）并进行远程调用
  服务提供者向注册中心注册服务，并每隔30秒发送一次心跳，就如同人还活着存在的信号一样，如果Eureka在90秒后还未收到服务提供者发来的心跳时，那么它就会认定该服务已经死亡就会注销这个服务。这里注销并不是立即注销，而是会在60秒以后对在这个之间段内“死亡”的服务集中注销，如果立即注销，势必会对Eureka造成极大的负担。这些时间参数都可以人为配置。
  Eureka还有自我保护机制，如果在15分钟内超过85%的节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，所以不会再接收心跳，也不会删除服务。
  客户端消费者会向注册中心拉取服务列表，因为一个服务器的承载量是有限的，所以同一个服务会部署在多个服务器上，每个服务器上的服务都会去注册中心注册服务，他们会有相同的服务名称但有不同的实例id，所以拉取的是服务列表。我们最终通过负载均衡来获取一个服务，这样可以均衡各个服务器上的服务。
![](https://img-blog.csdnimg.cn/20190816202635152.png)
###Eureka集群
1先启动eureka注册中心
 2启动服务提供者payment支付服务
 3支付服务启动后会把自身信息化 服务以别名方式注册进eureka
 4消费者order服务在要调用接囗时，使用服务别名去注册中心取实际的RPC远程调用地址
 5消费者获得调用地址后，底层实际是利用HttpClient技术实现呈调用
 6消费者获得服务地址后会存jvm内存中，默认每间隔30s更新一次服务调用地址
 >Eureka Server在设计的时候就考虑了高可用设计，在Eureka服务治理设计中，所有节点既是服务的提供方，也是服务的消费方，服务注册中心也不例外。
  Eureka Server的高可用实际上就是将自己做为服务向其他服务注册中心注册自己，这样就可以形成一组互相注册的服务注册中心，以实现服务清单的互相同步，达到高可用的效果。
  Eureka Server的同步遵循着一个非常简单的原则：只要有一条边将节点连接，就可以进行信息传播与同步。可以采用两两注册的方式实现集群中节点完全对等的效果，实现最高可用性集群，任何一台注册中心故障都不会影响服务的注册与发现。
  问题：微服务RPC远程服务调用最核心的是什么：
  高可用，试想你的注册中心只有一个。onlyone,它出故障了那就呵呵了，会导致整个为服务环境不可用，所以要搭建Eureka注册中心集群，实现负载均衡+故障容错
  Eureka 集群的原理：相互注册，互相守望。每台Eureka服务器都有集群里其他Eureka服务器地址的信息
  单机构建集群需要修改host文件区分端口
  
###Eureka自我保护机制
某时刻某一个微服务不可用了，Eureka不会立即清理，依旧会对该微服务的信息进行保存。属于CAP里的AP分支
保护模式主要用于一组客户和Eureka Server之间存在网络分区场景下保护。一旦进入保护模式，Eureka Server将会尝试保护其服务注册表中的信息，不再删除服务注册表中固定信息，也就是不会注销任何微服务。
如果在Eureka Server的首页看到以下这段提示，则说明Eureka讲入了保护模式：
> EMERGENCY!EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY’RE NOT.
RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE

为什么会产生Eureka自我保护机制？

为了防止Eureka Client可以正常运行但是与Eureka Server网络不通情况下，Eureka Server不会立刻将Eureka Client服务剔除

什么是自我保护模式？

默认情况下，如果Eureka Server在一定时间内没有接收到某个微服务实例的心跳，Eureka Server将会注销该实例（默认90秒）。但是当网络分区故障发生时、卡顿、拥挤）时，微服务与Eureka Server之间无法正常通信，以上行为可能变得非常危险了—因为微服务本身其实是健康的，此时本不应该注销这个微服务。Eureka通过"自我保护模式"来解决这个问题—当Eureka Server节点在短时间内丢失过多客户端时（可能发生了网络分区故障），那么这个节点就会进入自我保护模式。

自我保护机制：默认情况下Eureka CIient定时向Eureka Server端发送心跳包。

如畀Eureka在server端在一定时间内（默认90秒）没有收到Eureka Client发送心跳包，便会直接从服务注册列表中剔除该服务，但是在短时间（90秒中）内丢矢了大量的服务实例心跳，这时Eureka Server会开启目我保护机制，不令剔除该服务（该现象可能出现如果网络不通但是Eureka Client出现宕机，此时如果别的注册中心如果一定时间内没有收到心跳会将剔除该服务这样就出现了严重失误，因为客户端还能正常发送心跳，只是网络延迟问题，而保护机制是为了解决此问韙而产生的

在自我保护模式中，Eureka Server会保护服务注册表中的信息，不再注册任何服务实例。

它的设计哲学就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。一句话讲解：好死不如赖活着

综上，自我保护模式是一种应对网络异常的安全保护措施。它的架构哲学是宁可同时保留所有微服务（健康的微服务和不健康的微服务都会保留）也不盲目注销任何健康的微服务。使用自我保护模式，可以让Eureka集群更加的健壮、稳定。
###eureka配置项解读：
在注册服务之后，服务提供者会维护一个心跳用来持续高速Eureka Server，“我还在持续提供服务”，否则Eureka Server的剔除任务会将该服务实例从服务列表中排除出去。我们称之为服务续约。
面是服务续约的两个重要属性：

（1）eureka.instance.lease-expiration-duration-in-seconds
leaseExpirationDurationInSeconds，表示eureka server至上一次收到client的心跳之后，等待下一次心跳的超时时间，在这个时间内若没收到下一次心跳，则将移除该instance。
默认为90秒
如果该值太大，则很可能将流量转发过去的时候，该instance已经不存活了。
如果该值设置太小了，则instance则很可能因为临时的网络抖动而被摘除掉。
该值至少应该大于leaseRenewalIntervalInSeconds

（2）eureka.instance.lease-renewal-interval-in-seconds
leaseRenewalIntervalInSeconds，表示eureka client发送心跳给server端的频率。如果在leaseExpirationDurationInSeconds后，server端没有收到client的心跳，则将摘除该instance。除此之外，如果该instance实现了HealthCheckCallback，并决定让自己unavailable的话，则该instance也不会接收到流量。
默认30秒
> *eureka.client.registry-fetch-interval-seconds* :表示eureka client间隔多久去拉取服务注册信息，默认为30秒，对于api-gateway，如果要迅速获取服务注册状态，可以缩小该值，比如5秒
  *eureka.server.enable-self-preservation*
  是否开启自我保护模式，默认为true。
  默认情况下，如果Eureka Server在一定时间内没有接收到某个微服务实例的心跳，Eureka Server将会注销该实例（默认90秒）。但是当网络分区故障发生时，微服务与Eureka Server之间无法正常通信，以上行为可能变得非常危险了——因为微服务本身其实是健康的，此时本不应该注销这个微服务。
  Eureka通过“自我保护模式”来解决这个问题——当Eureka Server节点在短时间内丢失过多客户端时（可能发生了网络分区故障），那么这个节点就会进入自我保护模式。一旦进入该模式，Eureka Server就会保护服务注册表中的信息，不再删除服务注册表中的数据（也就是不会注销任何微服务）。当网络故障恢复后，该Eureka Server节点会自动退出自我保护模式。
  综上，自我保护模式是一种应对网络异常的安全保护措施。它的架构哲学是宁可同时保留所有微服务（健康的微服务和不健康的微服务都会保留），也不盲目注销任何健康的微服务。使用自我保护模式，可以让Eureka集群更加的健壮、稳定。
  *eureka.server.eviction-interval-timer-in-ms*
  eureka server清理无效节点的时间间隔，默认60000毫秒，即60秒

#zookeeper
    zookeeper是一个分布式协调工具，可以实现注册中心功能
    关闭Linux服务器防火墙后动zookeeper服务器
    zookeeper服务器取代Eureka服务器，zk作为服务注册中心
    
##zookeeper依赖
```xml
 <!--springcloud 整合 zookeeper 组件-->
        <!--如果 服务端安装的zookeeper是低版本的可以会与jar包中的版本冲突，由zk-discovery和zk之间的jar包冲突的问题。需要手动排除依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <!--zk发现-->
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
        </dependency>
```
> 
>我们在zk上注册的node是临时节点,当我们的服务一定时间内没有发送心跳，那么zk就会将这个服务的znode删除了。
没有自我保护机制。重新建立连接后znode-id号也会变
>```shell script
># 通过zookeeper客户端zkCki.sh连接zookeeper 
># 查看我们注册的所有服务
>ls /services
># 查看 cloud-provider-service节点
>ls /services/cloud-provider-service
>```
**关于 zookeeper 的集群搭建，目前使用较少，而且在 yml 文件中的配置也是类似，以列表形式写入 zookeeper 的多个地址即可，**

#Consul
consul也是服务注册中心的一个实现，是由go语言写的。官网地址： https://www.consul.io/intro 中文地址： https://www.springcloud.cc/spring-cloud-consul.html
Consul是一套开源的分布式服务发现和配置管理系统。
提供了微服务系统中的服务治理，配置中心，控制总线等功能。这些功能中的每一个都可以根据需要单独使用，也可以一起使用以构建全方位的服务网络。
**提供的功能：**
> 服务发现：提供HTTP和DNS两种发现方式
> 健康监测：支持多种方式，HTTP、TCP、Docker、Shell脚本定制化
  KV存储：Key、Value的存储方式
  多数据中心：Consul支持多数据中心
  可视化Web界面