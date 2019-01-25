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

我们需要开发一个类加载器例来加载该基础包下的所有类，比如使用了某注解的类，或者实现了某接口的类，在或者继承了某父类的所有子类等。

为此我们需要一个`ClassUtil`工具类，提供与类操作相关的方法，比如获取类加载器，加载类，获取指定包名下的所有类等等。代码如下：

```java
package com.lay.smartframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Description:类操作工具类
 * @Author: lay
 * @Date: Created in 14:48 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
public final class ClassUtil {

    private static final Logger LOGGER= LoggerFactory.getLogger(ClassUtil.class);

    /**
     *
     * @Description: 获取类加载器
     * @param:
     * @return: java.lang.ClassLoader
     * @auther: lay
     * @date: 14:51 2019/1/25
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }


    /**
     *
     * @Description: 加载类
     * @param:
     * @param className
     * @param isInitialized 是否执行类的静态代码块
     * @return: java.lang.Class<?>
     * @auther: lay
     * @date: 14:52 2019/1/25
     */
    public static Class<?>  loadClass(String className,boolean isInitialized){
        Class clz;
        try{
            clz=Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure",e);
            throw new RuntimeException(e);
        }
        return clz;
    }


    /**
     *
     * @Description: 获取指定包下的所有类
     * @param:
     * @param packageName
     * @return: java.util.Set<java.lang.Class<?>>
     * @auther: lay
     * @date: 14:53 2019/1/25
     */
    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(url!=null){
                    String protocol=url.getProtocol();
                    if(protocol.equals("file")){
                        String packagePath = url.getPath().replace("%20", " ");//transfer space
                        addClass(classSet,packagePath,packageName);
                    }else if (protocol.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if(jarURLConnection!=null){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if(jarFile!=null){
                                Enumeration<JarEntry> entries = jarFile.entries();
                                while (entries.hasMoreElements()){
                                    JarEntry jarEntry = entries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.endsWith(".class")){
                                        String className=jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                        doAddClass(classSet,className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("get Class-Set failure",e);
            throw new RuntimeException(e);
        }
        return null;
    }


    /**
     *
     * @Description: 递归遍历添加class
     * @param:
     * @param classSet
     * @param packagePath
     * @param packageName
     * @return: void
     * @auther: lay
     * @date: 16:10 2019/1/25
     */
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(file -> (file.isFile()&&file.getName().endsWith(".class"))||file.isDirectory());
        for (File file : files) {
            String fileName=file.getName();
            if(file.isFile()){
                String className=fileName.substring(0,fileName.lastIndexOf("."));
                if(StringUtil.isNotEmpty(packageName)){
                    className=packageName+"."+className;
                }
                doAddClass(classSet,className);
            }else {
                String subPackagePath=fileName;
                if(StringUtil.isNotEmpty(packagePath)){
                    subPackagePath=packagePath+"/"+subPackagePath;
                }
                String subPackageName=fileName;
                if(StringUtil.isNotEmpty(packageName)){
                    subPackageName=packageName+"."+subPackageName;
                }
                addClass(classSet,subPackagePath,subPackageName);
            }

        }
    }

    /**
     *
     * @Description: 加载类
     * @param:
     * @param classSet
     * @param className
     * @return: void
     * @auther: lay
     * @date: 16:10 2019/1/25
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) {
        //为了提高加载类的性能，这里参数为false
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);

    }
}

```

### 开发注解

这里我们的目标是在控制器类上使用`@Controller`注解，在控制器方法上使用`@Action`注解，在服务类上使用

`@Service`注解，在控制器类中可使用`@Inject`注解将服务类依赖注入进来。因此我们需要自定义这四个注解。

控制器注解

```java
package com.lay.smartframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 控制器注解
 * @Author: lay
 * @Date: Created in 16:38 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

}

```

Action方法

```java
package com.lay.smartframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 方法注解
 * @Author: lay
 * @Date: Created in 16:43 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

    /**
     *
     * @Description: 请求类型与路径
     * @param:
     * @return: java.lang.String
     * @auther: lay
     * @date: 16:45 2019/1/25
     */
    String value();
}

```

服务类注解

```java
package com.lay.smartframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 服务类注解
 * @Author: lay
 * @Date: Created in 16:46 2019/1/25
 * @Modified By:IntelliJ IDEA
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}

```

依赖注入注解

```java
package com.lay.smartframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 依赖注入注解
 * @Author: lay
 * @Date: Created in 16:47 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}

```

因为我们在`smart.properties`配置文件中指定了`smart.framework.app.base_package`。它是整个应用的基础包，以通过ClassUtil加载的类都需要基于该基础包名。所以有必要提供一个`ClassHelper`助手类。此外，我们可以将带有`@Controller`和`@Service`注解所产生的对象理解为有Smart框架所管理的Bean。所以有必要在`ClassHelper`中增加一个获取应用包名下所有bean类的方法。代码如下

```java
package com.lay.smartframework.helper;

import com.lay.smartframework.annotation.Controller;
import com.lay.smartframework.annotation.Service;
import com.lay.smartframework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 类操作助手类
 * @Author: lay
 * @Date: Created in 14:47 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
public final class ClassHelper {

    //定义类集合（用于存放锁加载的类）
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage=ConfigHelper.getAppBasePackage();
        CLASS_SET= ClassUtil.getClassSet(basePackage);
    }

    /**
     *
     * @Description: 获取应用包下所有类
     * @param:
     * @return: java.util.Set<java.lang.Class<?>>
     * @auther: lay
     * @date: 16:52 2019/1/25
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }
    /**
     *
     * @Description: 获取应用包下所有Service类
     * @param:
     * @return: java.util.Set<java.lang.Class<?>>
     * @auther: lay
     * @date: 17:03 2019/1/25
     */
    public static Set<Class<?>> getServiceClassSet(){
        return getClassSet(Service.class);
    }


    /**
     *
     * @Description: 获取应用包下所有Controller类
     * @param:
     * @return: java.util.Set<java.lang.Class<?>>
     * @auther: lay
     * @date: 17:04 2019/1/25
     */
    public static Set<Class<?>> getControllerClassSet(){
        return getClassSet(Controller.class);
    }

    /**
     *
     * @Description: 获取应用包下所有Bean类（包括service,controller）
     * @param:
     * @return: java.util.Set<java.lang.Class<?>>
     * @auther: lay
     * @date: 17:07 2019/1/25
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet=new HashSet<>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }
    /**
     *
     * @Description: 根据注解类型获取应用包下相应类
     * @param:
     * @param clz
     * @return: java.util.Set<java.lang.Class<?>>
     * @auther: lay
     * @date: 17:01 2019/1/25
     */
    public static Set<Class<?>> getClassSet(Class<? extends Annotation> clz){
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        for (Class<?> c : CLASS_SET) {
            if(c.isAnnotationPresent(clz)){
                classSet.add(c);
            }
        }
        return classSet;
    }

}

```

### 实现Bean容器

使用`ClassHelper`类可以获取所加载的类，但无法通过类来实例化对象。因此，需要提供一个反射工具类ReflectionUtil，让它封装Java反射相关的API，对外提供更好的工具方法。

```java
package com.lay.smartframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: 反射工具类
 * @Author: lay
 * @Date: Created in 17:09 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
public final class ReflectionUtil {

    private static final Logger LOGGER= LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     *
     * @Description: 创建实例
     * @param:
     * @param clz
     * @return: java.lang.Object
     * @auther: lay
     * @date: 17:13 2019/1/25
     */
    public static Object newInstance(Class<?> clz){
        Object instance;
        try {
            instance=clz.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     *
     * @Description: 调用方法
     * @param:
     * @param obj
     * @param method
     * @param args
     * @return: java.lang.Object
     * @auther: lay
     * @date: 17:14 2019/1/25
     */
    public static Object invokeMethod(Object obj, Method method,Object...args){
        Object result;
        try {
            method.setAccessible(true);
            result=method.invoke(obj,args);
        } catch (Exception e) {
            LOGGER.error("invoke method failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     *
     * @Description: 设置成员变量的值
     * @param:
     * @param obj
     * @param field
     * @param value
     * @return: void
     * @auther: lay
     * @date: 17:17 2019/1/25
     */
    public static void setField(Object obj, Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);
        } catch (IllegalAccessException e) {
            LOGGER.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }

}

```

我们需要获取所有被Smart管理的bean类，此时需要调用ClassHelper类的getBeanClassSe()方法，随后需要循环调用RefectionUtil类的newInstance方法，根据类来实例化对象，最后将每次创建的对象存放在一个静态的Map中。我们需要随时获取该map，还需要通过map的key（类名）去获取所对应的value（bean对象）。BeanHelper代码如下：

```java
package com.lay.smartframework.helper;

import com.lay.smartframework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description: Bean助手类
 * @Author: lay
 * @Date: Created in 17:20 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
public final class BeanHelper {

    //定义 Bean 映射（用于存放 Bean 类与 Bean 实例的映射关系）
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet=ClassHelper.getBeanClassSet();
        for (Class<?>  beanClass: beanClassSet) {
            Object obj= ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass,obj);
        }
    }

    /**
     *
     * @Description: 获取 Bean 映射
     * @param:
     * @return: java.util.Map<java.lang.Class<?>,java.lang.Object>
     * @auther: lay
     * @date: 17:26 2019/1/25
     */
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     *
     * @Description: 获取 Bean 实例
     * @param:
     * @param cls
     * @return: T
     * @auther: lay
     * @date: 17:27 2019/1/25
     */
    public static <T> T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class:{}"+cls);
        }
        return (T)BEAN_MAP.get(cls);
    }
}

```

现在BeanHelper相当于一个bean容器了，因为在bean map中存放了bean类与bean实例的映射关系，我们只需要通过调用getBean方法，传入一个bean类，就能获取bean的实例。

### 实现依赖注入的功能

我们在Controller中定义Servic成员变量，然后在Controller的Action方法调用Service成员变量的方法。那么如何实例化Service成员变量呢。

为此我们定义的@Inject注解就是来实现Service的实例化。

我们通过BeanHelper获取所有的bean map结构，里面记录了类与对象的映射关系，然后遍历这个映射关系，分别取出bean类与bean实例。进而通过反射获取类中的所有成员遍历。继续遍历这些成员变量，在寻源中判断当前成员变量是否带有@Inject注解，若带有该注解，则从bean map中根据bean类取出bean实例，最后通过RefecltionUtil#setField方法来修改当前成员变量的值。

我们把如上逻辑写一个IocHelper类，让它来完成这件事。

```java
package com.lay.smartframework.helper;

import com.lay.smartframework.annotation.Inject;
import com.lay.smartframework.util.CollectionUtil;
import com.lay.smartframework.util.ReflectionUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Description: 依赖注入助手类
 * @Author: lay
 * @Date: Created in 17:31 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
public final class IocHelper {
    static {
        //获取所有的 Bean 类与 bean 实例之间的映射关系（简称 Bean Map）
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            //遍历 beanMap
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                //从 beanMap 中获取 bean类与 bean的实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                //获取 Bean 类中定义的所有成员变量（ bean filed）
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtils.isNotEmpty(beanFields)){
                    //是遍历 beanFields
                    for (Field field : beanFields) {
                        //判断变量是否带有 @Inject 注解
                        if(field.isAnnotationPresent(Inject.class)){
                            //在bean map中获取 field对应的实例
                            Class<?> beanTypeClass = field.getType();
                            Object beanFieldInstance = beanMap.get(beanTypeClass);
                            if(beanFieldInstance!=null){
                                //通过反射初始化 beanField的值
                                ReflectionUtil.setField(beanInstance,field,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}

```

只需要在IocHelper的静态块中实现相关逻辑，就能完成Ioc容器的初始化工作。那么，这个静态块在什么时候加载呢？其实当IocHelper这个类被加载的时候，就会加载它的静态块，后面我们需要找一个统一的地方来加载这个IocHelper。

值得注意的是，此时在Ioc框架所管理的对象都是单例的，由于Ioc框架底层还是从BeanHelper中获取bean Map的，而bean map中的对象都是实现创建好并放入这个bean容器的，所有的对象都是单例的。

