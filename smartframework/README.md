# Smart Framework 

java web框架

## 开发环境

### 创建项目框架

首先，创建smart-framework的maven项目

```xml
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.lay</groupId>
    <artifactId>smart-framework</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
```

然后需要为该项目添加相关依赖。

```xml
<!-- Servlet-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>
        <!--  JSP-->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
            <scope>provided</scope>
        </dependency>
        <!-- JSTL-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
            <scope>runtime</scope>
        </dependency>

        <!--Junit-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
```

在框架中大量使用日志输出，最流行的的日志关机就是Log4j了，但是它只是日志的一种具体实现，如果将来需要使用其他更好的日志，代码中很多日志输出的地方都需要修改。为了解决这个问题，我们使用一个名为SLF4J的日志框架，它实际上是日志框架的接口，而Log4J只是日志框架的一种实现而已。只需添加以下依赖，九能同时引入SLF4J和LOG4J两个依赖，因为，Maven依赖具有传递性。

```xml
		<!--SLF4J-->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.25</version>
        </dependency>
```

因为需要用到数据库，这里添加一个MySQL的java驱动对应的依赖

```XML
		<!--MySQL-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
            <scope>runtime</scope>
        </dependency>
```

由于在Controller的方法返回值重视可以返回JSON数据的，因此我们还需要一款JSON序列化工具。

```xml
   <!-- Jackson-->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.8.1</version>
        </dependency>
```

还有常用的工具类

```xml
<!--Apache Commons Lang-->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.4</version>
        </dependency>

        <!--Apache Commons Collections -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-collections4</artifactId>
            <version>4.0</version>
        </dependency>
```

对于JDBC类库而言，我们选择轻量级的DbUtils

```xml
<!--Apache Commons DbUtils-->
        <dependency>
            <groupId>commons-dbutils</groupId>
            <artifactId>commons-dbutils</artifactId>
            <version>1.6</version>
        </dependency>
```

最后是框架中需要用到的数据库连接池

```xml
  <!--DBCP2-->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-dbcp2</artifactId>
            <version>2.2.0</version>
        </dependency>
```

### 创建实例项目

我们还需要创建一个使用该框架的项目chapter3，然后在maven中依赖smart-framework即可。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.lay.javaweb</groupId>
    <artifactId>chapter3</artifactId>
    <version>1.0.0</version>
    <packaging>war</packaging>

    <dependencies>
        <!--Smart Framework-->
        <dependency>
            <groupId>com.lay</groupId>
            <artifactId>smart-framework</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Compile 统一源代码编译输出的JDK版本-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.7.0</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.18.1</version>
                <!--  maven打包时跳过测试单元-->
                <configuration>
                    <skipTests>true</skipTests>
                </configuration>
            </plugin>
            <!--Tomcat-->
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <port>8080</port>
                    <path>/${project.artifactId}</path>
                    <charset>utf-8</charset>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

开发环境准备完毕后，我们就可以实现具体的细节了。既然是一个框架，那么首先要考虑的问题就是配置，需要让配置尽可能的减少，这样开发者的学习成本才会更低。

### 定义框架配置项

在chapter3项目中的`/src/main/resources`目录下，创建要给名为`smart.properties`的文件，内容如下

```properties
smart.framework.jdbc.driver=com.mysql.jdbc.Driver
smart.framework.jdbc.url=jdbc:mysql://192.168.3.253:3306/java_web?useUnicode=true&characterEncoding=utf8&useSSL=false
smart.framework.jdbc.username=developer
smart.framework.jdbc.password=1q@W3e$R


smart.framework.app.base_package=com.lay.javaweb.chapter3
#Jsp文件基础路径
smart.framework.app.jsp_path=/WEB-INF/view/
#静态资源文件的基础路径（JS,CSS,IMG）
smart.framework.app.asset_path=/asset/
```

前面几个配置项是mysql的配置，后面几个解释下

- smart.framework.app.base_package ：#chapter3项目基础包名
- smart.framework.app.jsp_path：#Jsp文件基础路径
- smart.framework.app.asset_path：#静态资源文件的基础路径（JS,CSS,IMG）

### 加载配置项

既然配置文件有了，我们就需要根据配置项的名称来获取配置项的内容。因此我们在smart-framework项目中创建一个ConfigHelper的助手类，让它来读取`smart.properties`配置文件。

首先，我们需要创建一个名为`ConfigConstant`的常量类，让它来维护配置文件中相关的配置项名称

```java
package com.lay.smartframework;

/**
 * @Description: 提供相关配置项常量
 * @Author: lay
 * @Date: Created in 11:26 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
public interface ConfigConstant {
    String CONFIG_FILE="smart.properties";

    String JDBC_DRIVER="smart.framework.jdbc.driver";
    String JDBC_URL="smart.framework.jdbc.url";
    String JDBC_USERNAME="smart.framework.jdbc.username";
    String JDBC_PASSWORD="smart.framework.jdbc.password";

    String APP_BASE_PACKAGE="smart.framework.app.base_package";
    String APP_JSP_PATH="smart.framework.app.jsp_path";
    String APP_ASSET_PATH="smart.framework.app.asset_path";
}

```

为了获取文件，我们需要编写一个获取配置文件的工具类`PropsUtil`，完成一些读取文件的操作

```java
package com.lay.smartframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @Description:属性文件工具类
 * @Author: lay
 * @Date: Created in 16:01 2019/1/23
 * @Modified By:IntelliJ IDEA
 */
public class PropsUtil {

    private static final Logger LOGGER= LoggerFactory.getLogger(PropsUtil.class);

    //加载属性文件
    public static Properties loadProps(String fileName){
        Properties props=null;
        InputStream is=null;

        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            //is = PropsUtil.class.getClassLoader().getResourceAsStream(fileName);
            //ResourceBundle bundle = ResourceBundle.getBundle("config");
            if(is==null){
                throw new FileNotFoundException(fileName+" file is not found");
            }
            props=new Properties();
            props.load(is);
        } catch (IOException e) {
            LOGGER.error("load properties file failure",e);
        } finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure",e);
                }
            }
        }
        return props;
    }
    
    //获取字符型属性
    public static String getString(Properties props,String key){
        return getString(props,key,"");
    }


    //获取字符型属性（可指定默认值）
    public static String getString(Properties props, String key, String defaultValue) {
        String value=defaultValue;
        if(props.contains(key)){
            value=props.getProperty(key);
        }
        return value;
    }

    //获取数值型属性
    public static int getInt(Properties props,String key){
        return getInt(props,key,0);
    }

    //获取数值型属性（可指定默认值）
    public static int getInt(Properties props, String key, int defaultValue) {
        int value=defaultValue;
        if(props.contains(key)){
            value= Integer.parseInt(props.getProperty(key));
        }
        return value;
    }

    //获取布尔型属性
    public static boolean getBoolean(Properties props,String key){
        return getBoolean(props,key,false);
    }

    //获取布尔型属性 (可指定默认值)
    public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean value=defaultValue;
        if(props.contains(key)){
            value= Boolean.parseBoolean(props.getProperty(key));
        }
        return value;
    }
}

```

然后，我们只需要借助`PropsUtil`就能轻松的实现`ConfigHelper`，其实就是定义一些静态方法，让它们分别获取`smart.properties`配置文件中的配置项。

```java
package com.lay.smartframework.helper;

import com.lay.smartframework.ConfigConstant;
import com.lay.smartframework.util.PropsUtil;

import java.util.Properties;

/**
 * @Description: 属性文件助手类
 * @Author: lay
 * @Date: Created in 11:31 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
public class ConfigHelper {
    private static final Properties CONFIG_PROPS= PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    //获取JDBC驱动
    public static String getJdbcDriver(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
    }

    //获取JDBC URL
    public static String getJdbcUrl(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_URL);
    }

    //获取JDBC用户名
    public static String getJdbcUsername(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_USERNAME);
    }

    //获取JDBC密码
    public static String getJdbcPassword(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_PASSWORD);
    }

    //获取应用基础包名
    public static String getAppBasePackage(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_BASE_PACKAGE);
    }


    //获取应用JSP路径
    public static String getAppJspPath(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_JSP_PATH,"/WEB-INF/view/");
    }

    //获取应用静态资源路径
    public static String getAppAssetPath(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_ASSET_PATH,"/asset/");
    }

}

```

注意，在获取Jsp和静态资源的路径时候，我们配置了默认值。也就是说，在smart.properties配置文件中这两个配置项是可选的。如果不是特殊要求，可以修改这两个配置。换句话说，这两个配置项会以smart.properties配置文件中所配置的值为优先值。

### 开发一个类加载器

