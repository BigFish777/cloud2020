# cloud2020


<hr>



java底层基础：JVM/JUC/JMM/GC/Nginx高级配置

## 微服务架构体系图

![image-20200603095629637](C:\Users\qsmm\Desktop\makedown\图片\image-20200603095629637.png)



### 什么是springcloud微服务？

分布式服务架构的一站式解决方案，是多种微服务架构的落地技术的集合，俗称为服务全家桶。

落地技术：

![image-20200603100112737](C:\Users\qsmm\Desktop\makedown\图片\image-20200603100112737.png)

![image-20200603100452796](C:\Users\qsmm\Desktop\makedown\图片\image-20200603100452796.png)



要学习的技术：

![image-20200603100556680](C:\Users\qsmm\Desktop\makedown\图片\image-20200603100556680.png)



SpringBoot 2.x + springCloud H.x

![image-20200603101640337](C:\Users\qsmm\Desktop\makedown\图片\image-20200603101640337.png)



![image-20200603102822780](C:\Users\qsmm\Desktop\makedown\图片\image-20200603102822780.png)



## 零基础创建微服务项目

1、创建一个maven工程作为父工程，jdk选择 1.8，选择蓝色的site构建

![image-20200603110341210](C:\Users\qsmm\Desktop\makedown\图片\image-20200603110341210.png)

2、删除掉多余的东西只留下一个pom文件即可，然后进行环境的设定，字符集，编码全部设为utf-8。过滤掉多余的文件，*.idea、*.iml

![image-20200603110534731](C:\Users\qsmm\Desktop\makedown\图片\image-20200603110534731.png)

![image-20200603110625741](C:\Users\qsmm\Desktop\makedown\图片\image-20200603110625741.png)

![image-20200603110929815](C:\Users\qsmm\Desktop\makedown\图片\image-20200603110929815.png)



3.导入我们需要的jar包，pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <!--父工程-->
  <groupId>com.atguigucloud</groupId>
  <artifactId>cloud2020</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>pom</packaging>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>12</maven.compiler.source>
    <maven.compiler.target>12</maven.compiler.target>
    <junit.version>4.12</junit.version>
    <lombok.version>1.18.10</lombok.version>
    <log4j.version>1.2.17</log4j.version>
    <mysql.version>8.0.18</mysql.version>
    <druid.version>1.1.16</druid.version>
    <mybatis.spring.boot.version>2.1.1</mybatis.spring.boot.version>
  </properties>

  <!--父工程使用 dependencyManagement 限定jar包的版本 ，子项目 dependencies 如果不指定版本号则使用父工程的版本-->
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-project-info-reports-plugin</artifactId>
        <version>3.0.0</version>
      </dependency>
      <!--spring boot 2.2.2-->
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>2.2.2.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--spring cloud Hoxton.SR1-->
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>Hoxton.SR1</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--spring cloud alibaba 2.1.0.RELEASE-->
      <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-alibaba-dependencies</artifactId>
        <version>2.1.0.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>

      <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>${mybatis.spring.boot.version}</version>
      </dependency>
      <!--junit-->
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
      </dependency>
      <!--log4j-->
      <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>${log4j.version}</version>
      </dependency>

      <!--mysql-->
      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>${mysql.version}</version>
        <scope>runtime</scope>
      </dependency>
      <!-- druid-->
      <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>${druid.version}</version>
      </dependency>
    </dependencies>

  </dependencyManagement>

  <build>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-site-plugin</artifactId>
          <version>3.7.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-project-info-reports-plugin</artifactId>
          <version>3.0.0</version>
        </plugin>
      </plugins>
    </pluginManagement>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-site-plugin</artifactId>
        <configuration>
          <locales>en,fr</locales>
        </configuration>
      </plugin>
    </plugins>
  </build>

</project>
```

### 0、服务模块的创建

**步骤：**

1. 创建module

2. 修改pom.xml

3. 写yml文件

4. 创建启动类

5. 写业务类

6. 以上步骤也就是创建一个springboot的项目，并进行项目配置

   

### 1、创建公共模块

![image-20200603160445909](C:\Users\qsmm\Desktop\makedown\图片\image-20200603160445909.png)

创建我们要使用的实体类，并insert 到maven中

```java
package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    //404 not_font
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}


/***************************************优雅的分隔符*****************************************/


package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor//全参构造
@NoArgsConstructor//无参构造
public class Payment implements Serializable {
    private Long id;
    private String serial;
}

```





### 2、创建订单模块

**项目结构：**

![image-20200603160252475](C:\Users\qsmm\Desktop\makedown\图片\image-20200603160252475.png)

**pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.atguigucloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-payment8001</artifactId>

    <dependencies>

        <!-- ⭐⭐⭐⭐⭐引入我们自己的公共模块⭐⭐⭐⭐⭐ -->
        <dependency>
            <groupId>com.atguigucloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <!--如果没写版本,从父层面找,找到了就直接用,全局统一-->
        </dependency>
        <!--mysql-connector-java-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!--jdbc-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>

</project>
```



**application.yml**

```yaml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://39.105.27.58/spring_cloud?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT
    username: root
    password: lovehxp521..

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.atguigu.springcloud.entities
```



订单系统的测试类controller：`PaymentController.java`

```java
package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

import javax.annotation.Resource;
@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果："+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功",result);
        }
        return new CommonResult(444,"插入数据库失败");
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果："+payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功",payment);
        }
        return new CommonResult(444,"查询数据为空",null);
    }

}
/*
	小知识：@RequestBody用来接收json数据
	而httpEntity接受json数据
*/

```

通过postman进行测试，测试成功！

![image-20200603155155600](C:\Users\qsmm\Desktop\makedown\图片\image-20200603155155600.png)

![image-20200603155241663](C:\Users\qsmm\Desktop\makedown\图片\image-20200603155241663.png)



具体项目代码不在列举。

### 3、创建订单消费者模块

项目结构

![image-20200603155448058](C:\Users\qsmm\Desktop\makedown\图片\image-20200603155448058.png)



这个模块比较简单只需要创建Controller层就可以。

步骤一样，配置pom.xml,application.xml

**pom.xml,我们用不到dao层相关的jar包不去引用它**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.atguigucloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-order80</artifactId>
    <dependencies>

        <!-- ⭐⭐⭐⭐⭐引入我们自己的公共模块⭐⭐⭐⭐⭐ -->
        <dependency>
            <groupId>com.atguigucloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>

        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>

</project>
```



**appilcation.yml**

```yaml
server:
  port: 80
```

但是根据版本的不同，因为父工程引入了jdbc 和 druid数据源，子工程如果不配置数据源信息会变异报错，我们在启动类中屏蔽数据源的扫描配置。

**RunApp80.java**

```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//屏蔽数据源的扫描配置
public class RunApp80 {
    public static void main(String[] args) {
        SpringApplication.run(RunApp80.class,args);
    }
}
```

在消费者模块中我们要用到，RestTemplate 工具类，为了方便我们把它交给Spring进行管理，创建applicationConfig.java配置类

```java
package com.atguigu.springcloud.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration//别忘了 @Configuration 注解
public class ApplicationConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

我们看Controller层

```java
package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

import javax.annotation.Resource;
import java.lang.reflect.Parameter;

@Slf4j //lombok 日志注解
@RestController
public class OrderController {
//    定义服务层的地址前缀
    public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Parameter> create(Payment payment){
        log.info("consumer:添加<"+payment+">成功!");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Parameter> getPayment(@PathVariable("id") Long id){
        log.info("consumer:查询<Payment>成功!");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

}
```



创建完毕Controller我们测试一下

首先是根据id查询

![image-20200603162435668](C:\Users\qsmm\Desktop\makedown\图片\image-20200603162435668.png)

然后是添加

![image-20200603162502217](C:\Users\qsmm\Desktop\makedown\图片\image-20200603162502217.png)

看下数据库

![image-20200603162546461](C:\Users\qsmm\Desktop\makedown\图片\image-20200603162546461.png)





spring-cloud API:

https://cloud.spring.io/spring-cloud-static/Hoxton.SR1/reference/htmlsingle/

## spring-cloud 开始

### 一、服务注册中心

1. Eurka
2. Zookeeper
3. Consul
4. Nacos



![image-20200604115819079](C:\Users\qsmm\Desktop\makedown\图片\image-20200604115819079.png)

![image-20200604120052932](C:\Users\qsmm\Desktop\makedown\图片\image-20200604120052932.png)

#### 1、Eureka

![image-20200604120108172](C:\Users\qsmm\Desktop\makedown\图片\image-20200604120108172.png)

##### (1)、`Eureka `基础知识

1. 什么是服务治理

   Spring Cloud封装了Netflix 公司开发的Eureka模块来实现服务治理

   在传统的rpc远程调用框架中，管理每个服务与服务之间依赖关系比较复，管理比较复杂，所以需要使用服务治理,管理服务于服务之间依赖关系，可以实现服务调用、负载均衡、容错等，实现服务发现与注册。

##### (2)、单机`Eureka `构建步骤

1. 创建注册服务中心模块

   **步骤：**

   - 创建module

   ![image-20200604145216592](C:\Users\qsmm\Desktop\makedown\图片\image-20200604145216592.png)

   - 修改pom.xml

   ```xml
    <dependencies>
           <!-- eureka-server -->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
           </dependency>
           <!-- 引用自己定义的api通用包，可以使用Payment支付Entity -->
           <dependency>
               <groupId>com.atguigucloud</groupId>
               <artifactId>cloud-api-commons</artifactId>
               <version>${project.version}</version>
           </dependency>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
           </dependency>
           <!--监控-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-actuator</artifactId>
           </dependency>
           <!-- 一般通用配置 -->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-devtools</artifactId>
               <scope>runtime</scope>
               <optional>true</optional>
           </dependency>
           <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
               <optional>true</optional>
           </dependency>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-test</artifactId>
               <scope>test</scope>
           </dependency>
       </dependencies>
   ```

   

   - 写yml文件

     ```yaml
     server:
       port: 7001
     eureka:
       instance:
         hostname: localhost #eureka服务端的实例名称
       client:
         #表示自己就是注册中心，我的职责是维护服务实例，并不需要去检索服务
         fetch-registry: false
         #false表示不想注册中心注册自己
         register-with-eureka: false
         service-url:
           # 设置与eureka server交互的地址检查服务和注册服务都需要这个以来的地址
             defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
     ```

     

   - 创建启动类 要加服务注册中心注解：@EnableEurekaServer

     ```java
     @SpringBootApplication
     @EnableEurekaServer //表示为服务注册中心
     public class EurekaRun7001 {
         public static void main(String[] args) {
             SpringApplication.run(EurekaRun7001.class,args);
         }
     }
     ```

 2. 修改 **订单模块**：`cloud-provider-payment8001`  和 **订单消费模块:** `cloud-consumer-order80`

    

    - 添加 `eureka-client` jar包

    ```xml
            <!-- eureka-client -->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
            </dependency>
    ```

    - 修改 yml

    ```yaml
    # cloud-payment-service
    spring:
      application:
        name: cloud-payment-service # 微服务的名字
    eureka:
      client:
        #需要去检索服务
        fetch-registry: true
        #false表示不想注册中心注册自己
        register-with-eureka: true
        service-url:
          # 设置与eureka server交互的地址检查服务和注册服务都需要这个以来的地址
          defaultZone: http://localhost:7001/eureka/
    
    # cloud-consumer-order
    spring:
      application:
        name: cloud-consumer-order
    eureka:
      client:
        #是否开启检索服务
        fetch-registry: true
        #是否在注册中心注册自己
        register-with-eureka: true
        service-url:
          # 设置与eureka server交互的地址检查服务和注册服务都需要这个以来的地址
          defaultZone: http://localhost:7001/eureka/
    ```

    

    - 主启动类添加注解

    ```java
    @SpringBootApplication
    @EnableEurekaClient //表示这是一个服务提供着
    public class Run8001 {
        public static void main(String[] args) {
            SpringApplication.run(Run8001.class,args);
        }
    }
    ```

    

##### (3)、集群`Eureka `构建步骤     **相互注册，相互守望**

集群`Eureka `构建很简单，我们需要先搭建两三台和单机版一样的注册中心模块，只需要修改一下yml配置文件即可

首先我们修改一下本地的host文件

```xml
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
127.0.0.1 eureka7003.com
```



三个服务中心全部修改一下：**application.yml**

```yaml
server:
  port: 7001
eureka:
  instance:
    hostname: eureka7001.com #eureka服务端的实例名称 eureka7002.com、eureka7003.com
  client:
    #表示自己就是注册中心，我的职责是维护服务实例，并不需要去检索服务
    fetch-registry: false
    #是否在注册中心注册自己
    register-with-eureka: false
    service-url:
      # 最重要的地方就是把自己注册给其他的注册中心
      defaultZone: http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/

```



- `actuator`微服务信息完善

- 服务与发现`Discovery` 

  - Discovery相当于把`eureka `控制台中的信息全部获得，从而进行操作

  - 第一步启动类添加 `@EnableDiscoveryClient` 注解

    ```java
    @SpringBootApplication
    @EnableEurekaClient //表示这是一个服务提供着
    @EnableDiscoveryClient //服务与发现
    public class Run8001 {
        public static void main(String[] args) {
            SpringApplication.run(Run8001.class,args);
        }
    }
    ```

  - 第二步我们创建一个测试接口进行获得打印输出

  ```java
  @Resource
  private DiscoveryClient discoveryClient;
  
  @GetMapping("/payment/discovery")
  public Object discovery(){
      //获得所有的服务Id
      List<String> services = discoveryClient.getServices();
      for (String serviceId:services
          ) {
          log.info(">>>>>>>服务id:"+serviceId+"<<<<<<<");
          //根据服务id获得该服务下的实现，因为若果是集群会有多个实现
          List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
          for (ServiceInstance in:instances
              ) {
              //拿到每个服务实现 获得服务的
              log.info(in.getServiceId() + "\t" + in.getHost() + "\t" + in.getPort() + "\t" + in.getUri());
              /*
                   *   >>>>>>>服务id:cloud-consumer-order<<<<<<<
                   *      CLOUD-CONSUMER-ORDER
                   *          192.168.101.80	80	http://192.168.101.80:80
                   *  >>>>>>>服务id:cloud-payment-service<<<<<<<
                   *      CLOUD-PAYMENT-SERVICE
                   *          192.168.101.80	8002	http://192.168.101.80:8002
                   *          192.168.101.80	8001	http://192.168.101.80:8001
                   *          
                   */
          }
      }
      return this.discoveryClient;
  }
  ```

  

- `eureka `自我保护

  - 解释：当有一个微服务不可用了，Eureka不会立即清除掉，依然会对该微服务的信息进行保存
  - 它属于CAP里面AP分支



##### (4)、订单服务模块的集群搭建（eureka ）

首先创建两个订单服务模块，创建两个一样的即可，端口号不同，因为上面我们应创建完毕一个，不在列举

重要步骤：

- 修改端口号 和其他的配置

```yaml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service # 微服务的名字
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://39.105.27.58/spring_cloud?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT
    username: root
    password: lovehxp521..

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.atguigu.springcloud.entities

eureka:
  client:
    #表示自己就是注册中心，我的职责是维护服务实例，并不需要去检索服务
    fetch-registry: true
    #是否在注册中心注册自己
    register-with-eureka: true
    service-url:
      # 设置与eureka server交互的地址检查服务和注册服务都需要这个以来的地址
      # defaultZone: http://localhost:7001/eureka/
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
  instance:
    instance-id: payment8001 #修改 eureka的Status 名字
    prefer-ip-address: true #开启访问路径的ip显示
```



- 修改controller，给Controller的接口添加一个标记方便我们测试是否集群搭建成功

```java
    @Value("${server.port}")
    private String serverPort;

 @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果："+payment);
        if (payment!=null){
            //输出一下端口号，以便于查看集群的情况
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }
        return new CommonResult(444,"查询数据为空",null);
    }
```

- 启动测试，是否可以使用

![image-20200604162115410](C:\Users\qsmm\Desktop\makedown\图片\image-20200604162115410.png)



![image-20200604162132567](C:\Users\qsmm\Desktop\makedown\图片\image-20200604162132567.png)

- 然后我们需改订单服务的消费者 `cloud-consumer-order80`
  - 首先修改访问的地址，我们之前是写死的，我们要把这里的地址改为，注册中心的 Application 唯一name![image-20200604162419228](C:\Users\qsmm\Desktop\makedown\图片\image-20200604162419228.png)

![image-20200604162536621](C:\Users\qsmm\Desktop\makedown\图片\image-20200604162536621.png)

```java
@Slf4j
@RestController
public class OrderController {
//    定义服务层的地址前缀
    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Parameter> create(Payment payment){
        log.info("consumer:添加<"+payment+">成功!");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Parameter> getPayment(@PathVariable("id") Long id){
        log.info("consumer:查询<Payment>成功!");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
}
```

- 然后修改ApplicationConfig配置文件中的 RestTemplate对象，加一个负载均衡的注解 @LoadBalanced

  ```java
  @Configuration
  public class ApplicationConfig {
      @Bean
      @LoadBalanced
      public RestTemplate restTemplate(){
          return new RestTemplate();
      }
  }
  ```

- 这样就完成了！！启动项目测试！

  ![image-20200604162911272](C:\Users\qsmm\Desktop\makedown\图片\image-20200604162911272.png)

![image-20200604162939950](C:\Users\qsmm\Desktop\makedown\图片\image-20200604162939950.png)

这里我们并没有指定那个端口的订单服务，他是以轮训的方式进行访问。





#### 3、`zookeeper`



![image-20200605095613526](C:\Users\qsmm\Desktop\makedown\图片\image-20200605095613526.png)



##### (1)、Zookeeper的单机版 安装和操作（docker）

1、zookeeper下载最新版本年

```
docker pull zookeeper
```

2、检查是否下载成功

```
docker images
```

![img](https://img2018.cnblogs.com/blog/333678/202001/333678-20200120220046153-1477904928.png)

3、zookeeper安装

将2181端口映射出来

```
docker run -d --name zookeeper -p 2181:2181 -v /root/zookeeper/data:/data   zookeeper:latest
```

4、验证

```
docker ps
```

![img](https://img2018.cnblogs.com/blog/333678/202001/333678-20200120220207935-1083313474.png)

 

**docker 下的zookeeper常用操作**

**1、查看容器开启状态**

![img](https:////upload-images.jianshu.io/upload_images/17342150-c2bb5223938e2e11.png?imageMogr2/auto-orient/strip|imageView2/2/w/455/format/webp)

**2、常用命令**

2.1、首先使用命令进入服务器（进入zookeeper的文件根目录）: docker exec -it zookeeper bash

![img](https:////upload-images.jianshu.io/upload_images/17342150-343d4e3b2fb848d7.png?imageMogr2/auto-orient/strip|imageView2/2/w/447/format/webp)

2.2、使用命令 ./bin/zkServer.sh status 来查看节点的状态

![img](https:////upload-images.jianshu.io/upload_images/17342150-8e10494bdbb9b8f5.png?imageMogr2/auto-orient/strip|imageView2/2/w/777/format/webp)

2.3、使用zkCli.sh开启客户端

![img](https:////upload-images.jianshu.io/upload_images/17342150-699b17734d9cfa7b.png?imageMogr2/auto-orient/strip|imageView2/2/w/368/format/webp)

2.4、使用create -e /node1 node1.1 创建临时节点，当客户端关闭时候，该节点会随之删除。不加参数－e创建永久节点。

![img](https:////upload-images.jianshu.io/upload_images/17342150-a7cf2e360aec83ab.png?imageMogr2/auto-orient/strip|imageView2/2/w/536/format/webp)

 get /node:获取节点值

 ls /node：列出节点

 delete /node 删除节点

 stat：查看节点信息

setAct path acl：用于设置节点访问权限

getAcl path ：查看节点的权限信息



![img](https:////upload-images.jianshu.io/upload_images/17342150-9ba167badcb89c20.png?imageMogr2/auto-orient/strip|imageView2/2/w/484/format/webp)

3、ACL

> ACL全称是Access Control List，访问控制列表，zookeeper中ACL由三部分组成，即Scheme:id:permission，其中：    
> scheme是验证过程中使用的检验策略；id是权限被赋予的对象，比如ip或某个用户；permission为可以操作的权限，有5个值：crdwa，分别表示create、read、delete、write、admin,具体含义在[zookeeper ACL](https://links.jianshu.com/go?to=https%3A%2F%2Fblog.csdn.net%2Fzkp_java%2Farticle%2Fdetails%2F82635101%23t14)中已经描述过。

通过*setAcl path acl*命令可以设置节点的访问权限，path是节点路径，acl是要设置的权限(crdwa)。通过*getAcl path*可以查看节点的权限信息。需要注意的是节点的acl不具有继承关系。

3.1 scheme 

权限检验策略(scheme)有五种类型：world、auth、digest、IP、super.

格式：world:anyone:permission
world:anyone:crdwa标记为所有用户都可以create read delete write 节点。

- auth：  
  -   auth:username:password:crd 表示给认证通过的用户设置crd权限。
  -   可以通过addauth digest <username>:<password>命令添加用户。

![img](https:////upload-images.jianshu.io/upload_images/17342150-a62e350dbeb6facd.png?imageMogr2/auto-orient/strip|imageView2/2/w/515/format/webp)

- digest：
  -   digest:id:permission  digest格式中的id需要使用sha1进行加密；这个命令的作用和auth差不多。
- ip：
  -   ip:id:permission ：表示指定某个地址可以有权限。
- super：
  - super检验策略主要供运维人员维护节点使用。



##### (2)、zookeeper的使用

###### 服务提供者

1、项目结构

![image-20200615114116304](C:\Users\qsmm\Desktop\makedown\图片\image-20200615114116304.png)



2、引入pom

```xml
    <dependencies>
        <!-- zookeeper-client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
        </dependency>

        <!--引入我们自己的公共模块-->
        <dependency>
            <groupId>com.atguigucloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <!--如果没写版本,从父层面找,找到了就直接用,全局统一-->
        </dependency>
        <!--mysql-connector-java-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!--jdbc-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>
```



3、编写 yaml

```yaml
server:
  port: 8003
  tomcat:
    uri-encoding: UTF-8
spring:
  application:
    name: cloud-payment-service # 微服务的名字
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://39.105.27.58/spring_cloud?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT
    username: root
    password: lovehxp521..
  # 注册于服务模块配置 zookeeper
  cloud:
    zookeeper:
      connect-string: 39.105.27.58:2181
  http:
    encoding:
      force: true
      charset: utf-8
      enabled: true
# mybatis 配置
mybatis:
  # mapper xml文件位置
  mapper-locations: classpath:mapper/*.xml
  # 开启实体类包扫描
  type-aliases-package: com.atguigu.springcloud.entities
```



4、创建启动类

```java
@SpringBootApplication
@EnableDiscoveryClient //让注册中心能够发现，扫描到改服务
public class Run8003 {
    public static void main(String[] args) {
        SpringApplication.run(Run8003.class,args);
    }
}
```



5、创建测试controller

```java
@Slf4j
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/uuid")
    public CommonResult getPaymentById(){
        log.info("进入/payment/uuid");
        return new CommonResult(200,"查询成功,serverPort:"+serverPort, UUID.randomUUID().toString());
    }
}
```



###### 服务消费者

1、项目结构

![image-20200615114747192](C:\Users\qsmm\Desktop\makedown\图片\image-20200615114747192.png)

2、引入pom

```xml
    <dependencies>
        <!-- eureka-client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
        </dependency>

        <dependency>
            <groupId>com.atguigucloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>

        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>
```



3、编写 yaml

```yaml
server:
  port: 80
spring:
  application:
    name: cloud-consumer-order
  cloud:
    zookeeper:
      connect-string: 39.105.27.58:2181
```



4、创建启动类

```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//屏蔽数据源的扫描配置
@EnableDiscoveryClient
public class RunZk80 {
    public static void main(String[] args) {
        SpringApplication.run(RunZk80.class,args);
    }
}
```

5、创建配置文件

```java
@Configuration
public class ApplicationConfig {
    @Bean
    @LoadBalanced//开启Ribbon 负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```



6、创建测试controller

```java
@Slf4j
@RestController
public class OrderController {
//    定义服务层的地址前缀
    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/get")
    public CommonResult<Parameter> getPayment(){
        log.info("consumer:查询<Payment>成功!");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/uuid",CommonResult.class);
    }
}
```





#### 4、`consul`

中文文档：https://www.springcloud.cc/spring-cloud-consul.html

1. ##### Linux环境下使用docker安装单机版的 `consul`

   1、拉取镜像

   ```java
   docker search consul
   
   docker pull consul
   ```

   

   2、启动镜像

   ```java
   docker run -d --name myconsulserver --net=host -e'CONSUL_LOCAL_CONFIG={"skip_leave_on_interrupt": true}' consul agent -server -bind=127.0.0.1 -bootstrap-expect=1 -client 0.0.0.0 -ui
   ```

   

   ```html
   –net=host docker参数, 使得docker容器越过了netnamespace的隔离，免去手动指定端口映射的步骤
   
   -server consul支持以server或client的模式运行, server是服务发现模块的核心, client主要用于转发请求
   
   -advertise 将本机私有IP传递到consul
   
   -bootstrap-expect 指定consul将等待几个节点连通，成为一个完整的集群
   
   -retry-join 指定要加入的consul节点地址，失败会重试, 可多次指定不同的地址
   
   -client consul绑定在哪个client地址上，这个地址提供HTTP、DNS、RPC等服务，默认是127.0.0.1
   
   -bind 该地址用来在集群内部的通讯，集群内的所有节点到地址都必须是可达的，默认是0.0.0.0
   
   -allow_stale 设置为true, 表明可以从consul集群的任一server节点获取dns信息, false则表明每次请求都会经过consul server leader
   
   --name DOCKER容器的名称
   
   -client 0.0.0.0 表示任何地址可以访问。
   
   -ui  提供图形化的界面。
   ```

   


   3、验证是否成功

   地址：http://10.1.54.162:8500/ui


![image-20200610115822829](C:\Users\qsmm\Desktop\makedown\图片\image-20200610115822829.png)

2. Windows环境下安装启动`consul`

   1. 下载：https://www.consul.io/downloads.html
   2. 解压：consul.exe
   3. 配置环境变量
   4. ​	打开环境变量，在path上面添加consul.exe的安装路径，比如我的在consul.exe在D:\consul中，那就配置成D:\consul就可以了，例如下图：

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200412234436592.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyNDQ5OTYz,size_16,color_FFFFFF,t_70)

   5. 启动`consul`

   打开cmd窗口，输入:consul agent -dev

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200412234620298.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyNDQ5OTYz,size_16,color_FFFFFF,t_70)

   6. 访问测试 在地址栏输入`http://localhost:8500`，可以看到下面的页面，如果看不到，就在cmd窗口里面点击一下Enter回车键就可以了，如下图：

      ![在这里插入图片描述](https://img-blog.csdnimg.cn/2020041223501071.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyNDQ5OTYz,size_16,color_FFFFFF,t_70)

      

3. ##### 服务提供者

   1. 创建 `colud-provider-payment8005`服务端

   ![image-20200611094426926](C:\Users\qsmm\Desktop\makedown\图片\image-20200611094426926.png)

   2. 配置	`pom.xml`导入jar包

   ```xml
   <dependencies>
       <!-- consul-client -->
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-starter-consul-discovery</artifactId>
       </dependency>
   
       <!--consul的健康检查依赖此包，如果不添加会出现checkfailing-->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-actuator</artifactId>
       </dependency>
   
   
       <!--引入我们自己的公共模块-->
       <dependency>
           <groupId>com.atguigucloud</groupId>
           <artifactId>cloud-api-commons</artifactId>
           <version>${project.version}</version>
       </dependency>
       <!--监控-->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-actuator</artifactId>
       </dependency>
       
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
       </dependency>
       
       <dependency>
           <groupId>org.mybatis.spring.boot</groupId>
           <artifactId>mybatis-spring-boot-starter</artifactId>
       </dependency>
       
       <dependency>
           <groupId>com.alibaba</groupId>
           <artifactId>druid-spring-boot-starter</artifactId>
           <!--如果没写版本,从父层面找,找到了就直接用,全局统一-->
       </dependency>
       <!--mysql-connector-java-->
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
       </dependency>
       <!--jdbc-->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-jdbc</artifactId>
       </dependency>
       <!--热部署-->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-devtools</artifactId>
           <scope>runtime</scope>
           <optional>true</optional>
       </dependency>
       <dependency>
           <groupId>org.projectlombok</groupId>
           <artifactId>lombok</artifactId>
           <optional>true</optional>
       </dependency>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-test</artifactId>
           <scope>test</scope>
       </dependency>
   
   </dependencies>
   
   <!-- 注意： 这里必须要添加， 否者各种依赖有问题 -->
   <repositories>
       <repository>
           <id>spring-milestones</id>
           <name>Spring Milestones</name>
           <url>https://repo.spring.io/libs-milestone</url>
           <snapshots>
               <enabled>false</enabled>
           </snapshots>
       </repository>
   </repositories>
   ```

   

   3. 配置`application.yml`

   ```yaml
   server:
     port: 8005
     tomcat:
       uri-encoding: UTF-8
   spring:
     application:
       name: cloud-payment-service # 微服务的名字
     datasource:
       type: com.alibaba.druid.pool.DruidDataSource
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://39.105.27.58/spring_cloud?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT
       username: root
       password: lovehxp521..
     #微服务的配置
     cloud:
       consul:
         host: localhost
         port: 8500
         discovery:
           service-name: ${spring.application.name}
   
   management:
     endpoint:
       health:
         show-details: always
   
     http:
       encoding:
         force: true
         charset: utf-8
         enabled: true
   
   # mybatis 配置
   mybatis:
     mapper-locations: classpath:mapper/*.xml
     type-aliases-package: com.atguigu.springcloud.entities
   ```

   4. 创建启动类

   ```java
   @SpringBootApplication
   @EnableDiscoveryClient
   public class RunConsul8005 {
   
       public static void main(String[] args) {
           SpringApplication.run(RunConsul8005.class,args);
       }
   }
   ```

   5. 创建测试Controller类

   ```java
   @Slf4j
   @RestController
   public class PaymentController {
   
       @Value("${server.port}")
       private String serverPort;
   
       @GetMapping("/payment/uuid")
       public CommonResult<String> getPaymentById(){
           log.info("进入/payment/uuid");
           return new CommonResult(200,"查询成功,ConsulServerPort:"+serverPort, UUID.randomUUID().toString());
       }
   }
   ```

   

   

4. ##### 服务消费者

   1、创建 `cloud-consumer-consul-order80` 消费端

   

   ![image-20200613112010029](C:\Users\qsmm\Desktop\makedown\图片\image-20200613112010029.png)

   2、配置	`pom.xml`导入jar包

   ```xml
       <dependencies>
           <!-- eureka-client -->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-consul-discovery</artifactId>
           </dependency>
   
           <!-- 这个包是用做健康度监控的，必须引入 不然报错No instances available for  -->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-actuator</artifactId>
           </dependency>
   
           <dependency>
               <groupId>com.atguigucloud</groupId>
               <artifactId>cloud-api-commons</artifactId>
               <version>${project.version}</version>
           </dependency>
   
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
           </dependency>
           <!--监控-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-actuator</artifactId>
           </dependency>
           <dependency>
               <groupId>org.mybatis.spring.boot</groupId>
               <artifactId>mybatis-spring-boot-starter</artifactId>
           </dependency>
   
           <!--热部署-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-devtools</artifactId>
               <scope>runtime</scope>
               <optional>true</optional>
           </dependency>
           <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
               <optional>true</optional>
           </dependency>
   
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-test</artifactId>
               <scope>test</scope>
           </dependency>
   
       </dependencies>
   
       <!-- 注意： 这里必须要添加， 否者各种依赖有问题 -->
       <repositories>
           <repository>
               <id>spring-milestones</id>
               <name>Spring Milestones</name>
               <url>https://repo.spring.io/libs-milestone</url>
               <snapshots>
                   <enabled>false</enabled>
               </snapshots>
           </repository>
       </repositories>
   ```

   

   3、配置`application.yml`

   ```yaml
   server:
     port: 80
   
   spring:
     application:
       name: cloud-consumer-order
     cloud:
       consul:
         host: localhost
         port: 8500
         discovery:
           service-name: ${spring.application.name}
   ```

   

   4、创建启动类

   ```java
   @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//屏蔽数据源的扫描配置
   @EnableDiscoveryClient
   public class RunConsul80 {
       public static void main(String[] args) {
           SpringApplication.run(RunConsul80.class,args);
       }
   }
   ```

   5、创建配置文件

   ```java
   @Configuration
   public class ApplicationConfig {
       @Bean
       @LoadBalanced//负载均衡的注解(英：加载平衡的)
       public RestTemplate restTemplate(){
           return new RestTemplate();
       }
   }
   ```

   

   6、创建测试Controller类

   ```java
   @Slf4j
   @RestController
   public class OrderController {
   //    定义服务层的地址前缀
       //public static final String PAYMENT_URL = "http://localhost:8001";
       public static final String PAYMENT_URL = "http://cloud-payment-service";
       @Resource
       private RestTemplate restTemplate;
   
       @GetMapping("/consumer/payment/get")
       public CommonResult<Parameter> getPayment(){
           log.info("consumer:查询<Payment>成功!");
           return restTemplate.getForObject(PAYMENT_URL+"/payment/uuid",CommonResult.class);
       }
   
       @GetMapping("/consumer/payment/getForEntity")
       public CommonResult<Parameter> getPayment2(){
           log.info("consumer:查询<getPayment2>成功!");
           ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/uuid", CommonResult.class);
           if (forEntity.getStatusCode().is2xxSuccessful()){
              return forEntity.getBody();
           }else {
               return new CommonResult(444,"操作失败");
           }
       }
   
   
   
   }
   ```

   

### 二、服务调用

1. Ribbon
2. LoadBalancer



#### 1、Ribbon负载均衡

默认使用轮训

```java
@Configuration
public class ApplicationConfig {
    @Bean
    @LoadBalanced//负载均衡的注解(默认使用轮训)
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

#### 2、Ribbon负载规则替换

![image-20200613144339462](C:\Users\qsmm\Desktop\makedown\图片\image-20200613144339462.png)

1、创建一个Ribbon的配置类，而且这个类不能被spring扫描到

![image-20200613163326477](C:\Users\qsmm\Desktop\makedown\图片\image-20200613163326477.png)

```java
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        return new RandomRule();//创建 随机 的实现类
    }
}
```

2、我们需要在主启动类上加上 @RibbonClient 注解,并且指定服务名称，@RibbonClients 也可以指定多个

```java
/**
* @RibbonClients(
        @RibbonClient(value = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class),
        @RibbonClient(value = "CLOUD-TEST-SERVICE",configuration = MySelfRule2.class)
        )
**/

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//屏蔽数据源的扫描配置
@EnableDiscoveryClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
public class RunConsul80 {
    public static void main(String[] args) {
        SpringApplication.run(RunConsul80.class,args);
    }
}
```



#### 3、手写一个轮询的实现机制

![image-20200613164253583](C:\Users\qsmm\Desktop\makedown\图片\image-20200613164253583.png)



1、创建一个自定义的loadBalancer接口

```java
public interface LoadBalancer {

    /**
     * 获得consul集群数量上的服务总数
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
```



2、创建实现类

```java
@Component
public class MyLB implements LoadBalancer {

    //原子类 Integer 防止多线程问题
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //获得当前是第几次访问
    public final int getAndIncrement(){
        int currnet;
        int next;
        do {
            currnet = this.atomicInteger.get();
            //当current大于等于Integer最大值时，赋值为0 反之 current +1
            next = currnet >= Integer.MAX_VALUE ? 0:currnet+1;
        }while (!this.atomicInteger.compareAndSet(currnet,next));
        //compareAndSet 判断第一个参数和第二个参数是否和参数二相等，不相等就吧第二个的值赋值给第一个
        System.out.println("********************** 第几次访问"+next+" next");
        return next;
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        //求获得下标
        int index = getAndIncrement() % serviceInstances.size();
        //通过下标 返回指定的服务
        return serviceInstances.get(index);
    }
}
```



3、实现测试

```java
@Slf4j
@RestController
public class OrderController {
//    定义服务层的地址前缀
    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("/consumer/get/lb")
    public String getlb(){
        //discoveryClient.getInstances("服务名") 获得健康的服务列表
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if (instances==null || instances.size()<=0){
            return null;
        }
        //调用我们自己写的轮训方法 获得到具体的服务  并获得该服务的url地址进行访问
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/uuid",String.class);
    }


}
```



### 三、服务调用2

1. Feign(已经停用)

2. OpenFeign

#### 1、OpenFeign是什么？

**是一个声明式的web客户端,只需要创建一个接口,添加注解即可完成微服务之间的调用**



![](E:/学习项目/Student/springcloud/图片/Feign的2.png)

==就是A要调用B,Feign就是在A中创建一个一模一样的B对外提供服务的的接口,我们调用这个接口,就可以服务到B==



#### **2、Feign与OpenFeign区别**

![](C:\Users\qsmm\Pictures\Saved Pictures\feign.png)

#### 3、OpenFeign服务调用

```java
之前的服务间调用,我们使用的是ribbon+RestTemplate
		现在改为使用OpenFeign
```

**创建一个新的消费模块，使用openfeign进行访问**

1、创建项目

![image-20200613181650176](C:\Users\qsmm\Desktop\makedown\图片\image-20200613181650176.png)



2、导入pom

```xml
<dependencies>

        <!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

        <!-- eureka-client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <dependency>
            <groupId>com.atguigucloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>

        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>
```



2、创建yml

```yaml
server:
  port: 80
spring:
  application:
    name: cloud-consumer-order
eureka:
  client:
    #是否开启检索服务 注意检索服务必须开启，不然找不到发服务列表
    fetch-registry: true
    #是否在注册中心注册自己
    register-with-eureka: true
    service-url:
      # 设置与eureka server交互的地址检查服务和注册服务都需要这个以来的地址
      # defaultZone: http://localhost:7001/eureka/
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
  instance:
    instance-id: consumerPayment80
    prefer-ip-address: true
```



3、创建启动类

```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//屏蔽数据源的扫描配置
@EnableFeignClients
public class RunOpenFeignApp80 {

    public static void main(String[] args) {
        SpringApplication.run(RunOpenFeignApp80.class,args);
    }
}

```

4、创建service接口

```java
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //@FeignClient value = "服务地址"
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);
}
```



5、创建Controller测试类

```java
@RestController
public class PaymentFeignController {

    @Resource
    PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/paymentFeign/get/{id}")
    public CommonResult<Parameter> getPayment(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

}
```

6、测试结果

![image-20200613182148220](C:\Users\qsmm\Desktop\makedown\图片\image-20200613182148220.png)



#### 4、OpenFeign超时控制

- 超时控制介绍·
  **默认Feign客户端只等待一秒钟,但是服务霭处理需要超过1秒，导致Feign客户端不想等待了,直接返回报错。为了避免这样的情况,有时候我们需要设置Feign客户端的超时控制。**
- 解决方法
  ym 文件中开启配置
  application.yml

```yaml
# 设置feign客户端超时时间（OpenFeign默认支持Ribbon）
ribbon:
  # 指的是建立连接后从服务器读取到可用资源所用时间
  ReadTimeout: 5000
  # 指的是建立连接所用的时间，适用于网络状况正常的情况下，两端连接所用的时间
  ConnectTimeout: 5000
```

- 栗子🌰：默认Feign客户端只等待一秒钟，如果我们故意让他超时。

生产者故意设的访问时间超过 1 秒钟

```java
@GetMapping("/payment/timeout")
    public CommonResult getPaymentTimeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult<String>(200,"成功",null);
    }
```

消费者去访问时会报 `超时异常`：

![image-20200615101328786](C:\Users\qsmm\Desktop\makedown\图片\image-20200615101328786.png)



#### 5、openfeign日志增强

- Feign提供了日志打印功能，我们可以通过配置来调整日志级别，从而了解Feign中Http请求的细节。说白了就是对Feign接口的调用情况进行监控和输出

日志级别
1：NONE:默认的，不显示任何日志;
2：BASIC:仅记录请求方法、URL、 响应状态码及执行时间;
3：HEADERS:除了BASIC中定义的信息之外,还有请求和响应的头信息;
4：FULL:除了HEADERS中定义的信息之外，还有请求和响应的正文及元数据。

- 怎么使用日志？
  第一步：FeignConfig.java

```java
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
} 
```

​		第二步：在yml配置文件中开启 日志

```yaml
# feign 开启日志增强
logging:
  level:
    com.atguigu.springcloud.service.PaymentFeignService: debug
```



查看打印结果：

![image-20200615105747678](C:\Users\qsmm\Desktop\makedown\图片\image-20200615105747678.png)



### 四、服务降级

1. Hystrix（豪猪哥）
2. resilience4j
3. sentinel

#### 1、Hystrix

![image-20200615115946343](C:\Users\qsmm\Desktop\makedown\图片\image-20200615115946343.png)

![](C:\Users\qsmm\Desktop\makedown\图片\Hystrix的2.png)



#### 2、hystrix中的重要概念:

##### (1)、服务降级

> 比如当某个服务繁忙,不能让客户端的请求一直等待,应该立刻返回给客户端一个备选方案

##### (2)、服务熔断

> 当某个服务出现问题,卡死了,不能让用户一直等待,需要关闭所有对此服务的访问，然后调用服务降级

##### (3)、服务限流

> 限流,比如秒杀场景,不能访问用户瞬间都访问服务器,限制一次只可以有多少请求



#### 3、服务降级的使用：

（1）引入pom

```xml
<!-- hystrix 服务降级 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```

（2）在启动类中添加注解 开启`@EnableCircuitBreaker`

```java
@SpringBootApplication
@EnableEurekaClient //表示这是一个服务提供着
@EnableDiscoveryClient //服务与发现
@EnableCircuitBreaker //服务降级
public class Run8007 {
    public static void main(String[] args) {
        SpringApplication.run(Run8007.class,args);
    }
}
```

（3）在方法上添加`@HystrixCommand` 注解，当方法执行时出现超时，或者异常都会执行降级

```java
 @HystrixCommand(fallbackMethod = "getPaymentTimeoutReserve",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000") //value = "3000" 运行超时上限  fallbackMethod 执行降级时执行的方法
    })
    public CommonResult getPaymentTimeout(){
        log.info("进入PaymentTimeout");
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int i = 1/0;
        return new CommonResult<String>(200,Thread.currentThread().getName()+"payment_timeout",null);
    }
```



（4）在需要降级的类中创建一个备胎方法

```java
/**
 * 用来降级的备胎方法
 * @return
 */
public CommonResult getPaymentTimeoutReserve(){
    log.info("进入备胎服务---成功降级！");
    return new CommonResult<String>(200,Thread.currentThread().getName()+"--备胎启动.start---(┬＿┬)",null);
}
```

（5）当方法出错或者超时时

![image-20200615165250161](C:\Users\qsmm\Desktop\makedown\图片\image-20200615165250161.png)



每个方法都加上一个降级方法不太聪明，会使得代码污染很严重，太臃肿，太麻烦。

第一种方法（适合任何时候）：所以我们应该使用全局的备胎来处理降级操作，必要的方法在加上属于自己的兜底方法即可。

1、首先还是创建处理的方法

```java
  /**
     * 用来降级的备胎方法
     */
    public CommonResult getPaymentTimeoutReserve(){
        log.info("进入全局备胎服务---成功降级！傻逼");
        return new CommonResult<String>(200,Thread.currentThread().getName()+"--全局备胎启动.start---(┬＿┬)",null);
    }
```

2、类名添加注解和指定默认的备胎方法

```java
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "getPaymentTimeoutReserve")
public class OrderFeignHystrixController {
    ...
}
```



3、在每个方法上添加上注解 @HystrixCommand ，当他没有指定属于他的备胎方法时，会使用全局的

```java
@HystrixCommand
@GetMapping("/consumer/payment/timeout")
public CommonResult getPaymentTimeout(){
    int i = 1/0;
    return paymentFeignHystrixService.getPaymentTimeout();
}
```



4、测试结果

![image-20200616110324984](C:\Users\qsmm\Desktop\makedown\图片\image-20200616110324984.png)

第二种方法（在使用feign接口调用）：优雅的配置 服务降级

首先创建一个类`PaymentFallbackService`实现`feign接口` ,在这个实现类中可以一一对应的服务降级

```java
@Component
public class PaymentFallbackService implements PaymentFeignHystrixService {
    @Override
    public CommonResult getPaymentById(Long id) {
        return new CommonResult(200,"getPaymentById:(┬＿┬)备胎别抛弃了，变成千斤顶了。");
    }

    @Override
    public CommonResult getPaymentTimeout() {
        return new CommonResult(200,"getPaymentTimeout:(┬＿┬)备胎别抛弃了，变成千斤顶了。");
    }

    @Override
    public CommonResult getPaymentTimeoutbt() {
        return new CommonResult(200,"getPaymentTimeoutbt:(┬＿┬)备胎别抛弃了，变成千斤顶了。");
    }
}

```



在接口中只需要在 注解 ` @FeignClient` 设置 `fallback` 为当前实现类`PaymentFallbackService.class`

```java
@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE",fallback = PaymentFallbackService.class)
public interface PaymentFeignHystrixService {
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    public CommonResult getPaymentTimeout();

    @GetMapping("/payment/timeout_bt")
    public CommonResult getPaymentTimeoutbt();
}
```





#### 4、服务熔断的使用

![image-20200616151326749](C:\Users\qsmm\Desktop\makedown\图片\image-20200616151326749.png)

当失败率达到60时就会进行服务降级，从而进入半开状态，进行接口的保护。一定时间后会再次尝试是否请求成功，若果成功则回复关闭状态

```java
/****服务熔断****/
@HystrixCommand(fallbackMethod = "PaymentCircuitBreaker_fallback",commandProperties = {
        @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少，跳闸
})
public String PaymentCircuitBreaker(@PathVariable("id") Long id){
    log.info("进入 PaymentCircuitBreaker");
    if (id<0){
        throw new RuntimeException("id 不能为负数！");
    }
    return Thread.currentThread().getName()+"流水号："+ IdUtil.simpleUUID();
}

public String PaymentCircuitBreaker_fallback(@PathVariable("id") Long id){
    return "id 不能为负数，请稍后重试(┬＿┬)！";
}
```



小总结：

实现步骤：

![image-20200616151442028](C:\Users\qsmm\Desktop\makedown\图片\image-20200616151442028.png)



![image-20200616151530584](C:\Users\qsmm\Desktop\makedown\图片\image-20200616151530584.png)



### 五、服务网关

![image-20200624111510365](C:\Users\qsmm\Desktop\makedown\图片\image-20200624111510365.png)

![image-20200624104807497](C:\Users\qsmm\Desktop\makedown\图片\image-20200624104807497.png)

#### 1、Zuul(不再使用)

模型：

![image-20200624105856592](C:\Users\qsmm\Desktop\makedown\图片\image-20200624105856592.png)

![image-20200624105913310](C:\Users\qsmm\Desktop\makedown\图片\image-20200624105913310.png)

#### 2、Zuul2(新的正在开发，不建议使用，公司内部发生分歧)

#### 3、gateway

![image-20200624104846063](C:\Users\qsmm\Desktop\makedown\图片\image-20200624104846063.png)



**为什么使用`gateway`？**

![image-20200624105157690](C:\Users\qsmm\Desktop\makedown\图片\image-20200624105157690.png)

**gateway的特性？**

![image-20200624105314088](C:\Users\qsmm\Desktop\makedown\图片\image-20200624105314088.png)

**SpringCloud GateWay 与 Zuul的区别：**

![image-20200624105832682](C:\Users\qsmm\Desktop\makedown\图片\image-20200624105832682.png)



**模型：**

![image-20200624105802893](C:\Users\qsmm\Desktop\makedown\图片\image-20200624105802893.png)



#### （1）gateway的三大核心概念

 Route（路由）

> 路由是构建网关的基本模块，他由ID、目标URL、一系列的断言和过滤器组成，如果断言为true则匹配该路由

Predicate（断言）

> ![image-20200624111545189](C:\Users\qsmm\Desktop\makedown\图片\image-20200624111545189.png)

Filter（过滤）

> 指的是Spring框架中GateWayFilter的实例，使用过滤器，可以在请求被路由前和后多请求进行修改。

总体

![image-20200624111901373](C:\Users\qsmm\Desktop\makedown\图片\image-20200624111901373.png)





![image-20200624111935060](C:\Users\qsmm\Desktop\makedown\图片\image-20200624111935060.png)

![image-20200624112038363](C:\Users\qsmm\Desktop\makedown\图片\image-20200624112038363.png)

#### （2）gateway的使用

1. 创建新的gateway模块

![image-20200624140423796](C:\Users\qsmm\Desktop\makedown\图片\image-20200624140423796.png)

2. 修改修改pom

   ```xml
       <dependencies>
           <!--gateway 2.2.1-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-gateway</artifactId>
           </dependency>
   
           <!-- eureka-client -->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
           </dependency>
   
           <dependency>
               <groupId>com.atguigucloud</groupId>
               <artifactId>cloud-api-commons</artifactId>
               <version>${project.version}</version>
           </dependency>
   
           <dependency>
               <groupId>org.mybatis.spring.boot</groupId>
               <artifactId>mybatis-spring-boot-starter</artifactId>
           </dependency>
   
           <!--热部署-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-devtools</artifactId>
               <scope>runtime</scope>
               <optional>true</optional>
           </dependency>
           <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
               <optional>true</optional>
           </dependency>
   
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-test</artifactId>
               <scope>test</scope>
           </dependency>
       </dependencies>
   ```

   

3. 创建yml

   ```yaml
   server:
     port: 9527
   
   spring:
     application:
       name: cloud-gateway
     cloud:
       gateway:
         discovery:
           locator:
             enabled: true #开启动态路由
         routes: #配置路由
           - id: payment_routh # 路由的id，没有固定的要求，建议配合服务名
             # uri: http://localhost:8001 # 匹配后提供服务的路由地址
             uri: lb://CLOUD-PAYMENT-SERVICE # 匹配后提供服务的动态路由地址
             predicates:
               - Path=/payment/get/** # 断言路径匹配的进行你路由
           - id: payment_routh2 # 路由的id，没有固定的要求，建议配合服务名
             # uri: http://localhost:8001 # 匹配后提供服务的路由地址
             uri: lb://CLOUD-PAYMENT-SERVICE # 匹配后提供服务的动态路由地址
             predicates: # 官网：https://cloud.spring.io/spring-cloud-gateway/2.2.x/reference/html/#the-after-route-predicate-factory
               - Path=/payment/lb/** # 断言路径匹配的进行你路由
               #- After=2020-06-24T13:42:24.946+08:00[Asia/Shanghai]
               #- Before=2020-06-24T13:50:24.946+08:00[Asia/Shanghai]
               - Cookie=username,lisi # cmd下面curl测试 : curl http://localhost:9527/payment/lb --cookie "username=lisi"
   eureka:
     client:
       #是否开启检索服务
       fetch-registry: true
       #是否在注册中心注册自己
       register-with-eureka: true
       service-url:
         # 设置与eureka server交互的地址检查服务和注册服务都需要这个以来的地址
         # defaultZone: http://localhost:7001/eureka/
         defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
     instance:
       instance-id: cloud-gateway-service
       prefer-ip-address: true
   ```

   

4. 创建主启动类

   ```java
   @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//屏蔽数据源的扫描配置
   @EnableEurekaClient
   public class RunGateWay9527 {
       public static void main(String[] args) {
           SpringApplication.run(RunGateWay9527.class,args);
       }
   }
   ```



#### （3）全局过滤器 [Global Filters](https://cloud.spring.io/spring-cloud-gateway/2.2.x/reference/html/#global-filters)

创建自定义的全局过滤器实现 `GlobalFilter, Ordered`

```java
/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/24
 * \* Time: 14:24
 * \* 判断请求中是否携带token（这里可以做oath验证）
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getPath().toString();
        log.info("path");
        if (path.equals("/get/token")){
            log.info("获取token，放行！");
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst("Authorization");
        if (token == null){
            log.error("用户token为空，非法用户");
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return response.setComplete();
        }
        log.info(token);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
```



### 六、服务配置

- Config

- Nacos

##### 1、spring-cloud的分布式配置中心(spring-cloud config)概述：

![image-20200624151547655](C:\Users\qsmm\Desktop\makedown\图片\image-20200624151547655.png)

##### 2、Config 整合github

1. 首先在GitHub上创建一个新的仓库[springcloud-config](https://github.com/BigFish777/springcloud-config),并创建配置文件

![image-20200624163100372](C:\Users\qsmm\Desktop\makedown\图片\image-20200624163100372.png)



2. 在本地项目文件夹下，拉取GitHub仓库

   > git clone git@github.com:BigFish777/springcloud-config.git
   >
   > git add .
   >
   > git commit -m"init yml"
   >
   >  git push origin master

3. 创建服务端模块

（1）创建项目

![](C:\Users\qsmm\Desktop\makedown\图片\image-20200624163436360.png)

（2）导入pom

```xml
    <dependencies>
        <!-- config-server-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
        <!-- eureka-client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>com.atguigucloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```



（3）修改yml

```yaml
server:
  port: 3344

spring:
  application:
    name: cloud-config-center
  cloud:
    config:
      server:
        git:
          uri: https://github.com/BigFish777/springcloud-config.git
          search-paths:
            # 搜索目录
            - springcloud-config
      # 读取分支
      label: master
# 服务注册到eureka
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
```



（4）创建启动类

```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//屏蔽数据源的扫描配置
@EnableConfigServer
public class RunConfig3344 {
    public static void main(String[] args) {
        SpringApplication.run(RunConfig3344.class,args);
    }
}
```

##### 3、创建客户端模块

（1）创建客户端模块

![image-20200624175503361](C:\Users\qsmm\Desktop\makedown\图片\image-20200624175503361.png)

（2）导入pom

```xml
<dependencies>

    <!-- config-client-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>

    <!-- eureka-client -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>

    <dependency>
        <groupId>com.atguigucloud</groupId>
        <artifactId>cloud-api-commons</artifactId>
        <version>${project.version}</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!--监控-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
    </dependency>

    <!--热部署-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

</dependencies>
```

（3）修改yml bootstrap.yml

​		

```yaml
server:
  port: 3355

spring:
  application:
    name: cloud-config-client
  cloud:
    # config
    config:
      label: master #分支名称
      name: config #配置文件名称
      profile: dev  # 读取后缀名
      uri: http://localhost:3344/ # 配置中心地址
# 服务注册到eureka
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/

```

（4）创建启动类

```java
@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//屏蔽数据源的扫描配置
public class RunConfig3355 {
    public static void main(String[] args) {
        SpringApplication.run(RunConfig3355.class,args);
    }
}
```

（5）创建测试controller类,读取github里面的配置文件参数

```java
@RestController
public class ConfigController {

    @Value("${config.info}")
    private String StrInfo;

    @GetMapping("/get/str")
    public String getStrInfo(){
        return StrInfo;
    }
}
```



问题？

运维每次修改配置文件都需要重启微服务，不合实际。



##### 动态刷新之——手动挡

修改`yml`文件

```yaml
# 暴露监端点
management:
  server:
    port: 3355
  endpoints:
    web:
      exposure:
        include: "*" # 打开全部的请求端点 refresh,health,info 。。。
      base-path: / #默认是 /actuator
```

在controller类中添加注解 `@RefreshScope`

```java
@RestController
@RefreshScope`
public class ConfigController {

    @Value("${config.info}")
    private String StrInfo;

    @GetMapping("/get/str")
    public String getStrInfo(){
        return StrInfo;
    }
}
```

修改完成后需要手动访问 地址进行刷新。

> curl -X POST "http://localhost:3355/refresh"

### 五、服务总线

消息总线(spring-cloud Bus)

- Bus
- Nacos

















# @springcloud 中的注解

## 注册与发现

#### `@EnableDiscoveryClient` 和 `@EnableEurekaClient` 

##### 注解作用: 将一个微服务注册到Eureka Server、Zookeeper（或其他服务发现组件，例如Zookeeper、Consul等），Eureka 2.0闭源之后，Consul慢慢会成为主流。

添加Eureka Client（或其他服务发现组件的Client）依赖：

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

写配置：

```yaml
spring:
  application:
    name: microservice-provider-user
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```



​	添加 Zookeeper 依赖：

```xml
<!-- zookeeper-client -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
</dependency>
```

从Spring Cloud Edgware开始，@EnableDiscoveryClient 或@EnableEurekaClient 可省略。只需加上相关依赖，并进行相应配置，即可将微服务注册到服务发现组件上。

@EnableDiscoveryClient和@EnableEurekaClient共同点就是：都是能够让注册中心能够发现，扫描到改服务。

不同点：@EnableEurekaClient只适用于Eureka作为注册中心，@EnableDiscoveryClient 可以是其他注册中心。



nameserver 100.100.2.138
nameserver 100.100.2.136



```java
docker run -d --name myconsulserver --net=host -e'CONSUL_LOCAL_CONFIG={"skip_leave_on_interrupt": true}' consul agent -server -bind=127.0.0.1 -bootstrap-expect=1 -client 0.0.0.0 -ui

```











