## 第四课时 Spring Boot 项目构建

## 1、了解Spring Boot

### 1.1、进入Spring Boot 官网

https://spring.io/projects/spring-boot

### 1.2、Spring Boot的宗旨

​	Spring Boot的宗旨并非要重写Spring或是代替Spring，而是希望通过设计大量的自动化配置等方式来简化Spring原有样化的配置，使得开发者可以快速构建应用。

Spring Boot 通过一些列Starter POMs的定义，整合各项功能不需要再Maven的pom.xml维护错综复杂的依赖关系。

## 2、创建Spring Boot项目

### 2.1 手动创建Spring Boot 项目

​	打开开发工具IDEA,选择File -> New -> project

​	![1544360262012](images\5C1544360262012.png)

如图，选择maven选择Next

![1544360621547](images\5C1544360621547.png)

输入我们定义的GroupId `cn.org.july.springboot` ;输入ArtifactId `spring-hello`;点击下一步（Next）

![1544360702876](images\5C1544360702876.png)

选择项目保存路径，和项目名称。这个根据个人不同情况进行定义。点击Finish

![1544360881953](images\5C1544360881953.png)

完成后项目结构图如上。让我们完善项目结构：

​	![1544361344731](images\5C1544361344731.png)

#### Spring Boot 结构基本三大块

​	**src/main/java**:程序主入口`HelloApplication` ,可以直接通过该类来启动Spring Boot应用。

​	**src/main/resources**: 配置目录。该目录主要用来存放配置信息。比如应用名称，服务端口，数据库连接等。由于我们引入WEB模块，因此产生了stataic 和 templates。前者存放静态资源如图片，CSS,Js等。后者用于存放web模板文件。这里我们主要演示Restful API，所以这两个目录不会使用到。

​	**src/test/** :  单元测试目录。编写`HelloApplicationTests` 通过Junit4 实现。可以直接运行Spring Boot 单元测试。

让我们根据官网，完善pom.xml。

![1544362729131](images\1544362729131.png)

在pom.xml中引入 	`spring-boot-starter-parent` 。

```xml
<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.1.RELEASE</version>
</parent>
```

修改后pom.xml内容：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>cn.org.july.springboot</groupId>
    <artifactId>spring-hello</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!--1、引入SpringBoot parent -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.1.RELEASE</version>
    </parent>

</project>
```

使用mvaen命令查看工程结构 `mvn dependency:tree`.

![1544363083256](images\5C1544363083256.png)

#### 引入SpringBoot WEB模块

![1544363167267](images\5C1544363167267.png)

在pom.xml中新增statrser POMs 中 starter-web

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
</dependencies>
```

修改后完整的pom.xml文件内容如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>cn.org.july.springboot</groupId>
    <artifactId>spring-hello</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!--1、引入SpringBoot parent -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.1.RELEASE</version>
    </parent>

    <dependencies>
        <!-- 2、 引入WEB模块  -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

</project>
```

再次查看项目依赖结构：

![1544363698456](images\5C1544363698456.png)

使用maven命令查看项目结构 `mvn dependency:tree` 。

![1544364002908](images\5C1544364002908.png)

通过上面结构我们可以看到 `spring-boot-starter-web`间接引入了 json,tomcat,hibernate-validator,spring-web,spring-webmvc等模块。

#### 实现RESTful API

​	在Spring Boot 中创建一个 RESTful API 的实现代码同Spring MVC应用一样，只是不需要像Spring MVC那样先做很多配置，而是像下面直接编写Controller 内容：

新建package，命名为 `cn.org.july.web`,可以根据自己的实际情况进行修改。

新建HelloController类，内容如下：

```java
@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String helle(){
        return "hello word";
    }
}
```

编写项目启动类`HelloApplication` 内容如下：

```java
@SpringBootApplication
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }
}
```

启动Spring boot 应用，通过浏览器访问http://localhost:8080/hello

我们可以在浏览器看到返回了预期效果"Hello World"

### 3、小练习：使用RESTful API返回List集合信息。



3.1、新增UserController，内置List<User> 对象。对象内容包含张三，李四，王五。年龄 12，14，20。通过访问users获取数据。

3.2、实现：

​	创建UserController 内容如下:

```java
package cn.org.july.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private static List<User> userList = new ArrayList<>();

    static {
          User zhangsan = new User();
          zhangsan.setAge(12);
          zhangsan.setName("张三");

          User lisi = new User();
          lisi.setName("李四");
          lisi.setAge(14);

          User ww = new User();
          ww.setAge(20);
          ww.setName("王五");

          userList.add(zhangsan);
          userList.add(lisi);
          userList.add(ww);
    }

    @RequestMapping(value = "/users")
    @ResponseBody
    public List<User> getUsers(){
        return userList;
    }


}

class User{
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

通过浏览器访问：http://localhost:8080/users

![1544366036863](images\5C1544366036863.png)



