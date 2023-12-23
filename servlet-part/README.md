# 一、什么是Servlet？

> Servlet产生的背景

之前学习了Tomcat服务器，它提供了Web程序处理服务端对请求的解析和响应的封装，也就是请求的解析和响应的封装都不需要我们自己动手写程序完成。

比如我们将一个webapp部署到Tomcat中，如果自己写程序来处理请求和返回响应的内容，就需要调用Tomcat提供的API，如果此时更改了Web服务器，我们自己写的请求解析和响应内容就无法使用了。

> Servlet是什么？

先分辨什么是动态资源和静态资源。

+ 静态资源
  + 无需在程序运行时通过代码运行生成的资源，比如:HTML,CSS,JS,IMG,音频和视频等。
+ 动态资源
  + 需要在程序运行时通过代码运行生成的资源，在程序运行之前无法确定的数据，运行时动态生成，例如Servlet，Thymeleaf......

Servlet是运行在服务端的Java小程序，是sun公司提供一套定义动态资源规范；也就是一个接口。

Servlet为不同的JavaWeb服务器规定了响应的规范编程，它屏蔽了Web服务器实现的细节，也就是说换一个服务器，还是可以运行。

> Servlet的工作

+ 用来接收、处理客户请求、响应给浏览器的动态资源。Servlet就是Web应用中的**控制器**
+ 简单来说，帮助程序员处理 响应和请求。

# 二、第一个Servlet程序

## 2.1 功能

> 校验注册时，用户名是否被占用，通过客户端向一个Servlet发送请求，携带username，如果用户名是'halcyon'，则返回NO，如果是其他，响应YES

## 2.2 开发过程

> 步骤1 使用maven创建一个web类型的module

下载插件JBLJavaToWeb。

![img](https://github.com/HalcyonJX/web-learning/blob/main/servlet-part/Servlet-img/Snipaste_2023-12-22_21-10-08.png)

下载插件smart tomcat 方便部署

+ 在pom文件导入servlet依赖

注意版本对应，不然代码写得再对，tomcat也找不到你编写的java代码。查询版本链接[servlet与tomcat版本对应](https://tomcat.apache.org/whichversion.html)

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">  
  <modelVersion>4.0.0</modelVersion>  
  <groupId>org.example</groupId>  
  <artifactId>demo1-servlet</artifactId>  
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <dependencies>
    <dependency>
      <groupId>jakarta.servlet</groupId>
      <artifactId>jakarta.servlet-api</artifactId>
      <version>6.0.0</version>
    </dependency>
  </dependencies>
</project>
```

> 步骤2 开发一个UserServlet

```java
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的参数
        String username = req.getParameter("username");
        if("halcyon".equals(username)){
            //通过响应对象响应信息
            resp.getWriter().write("NO");
        }else{
            resp.getWriter().write("YES");
        }
    }
}
```

+ 自定义一个类，要继承HttpServlet类
+ 重写service方法，该方法主要就是用于处理用户请求的服务方法
+ HttpServletRequest 代表请求对象,是有请求报文经过tomcat转换而来的,通过该对象可以获取请求中的信息
+ HttpServletResponse 代表响应对象,该对象会被tomcat转换为响应的报文,通过该对象可以设置响应中的信息
+ Servlet对象的生命周期(创建,初始化,处理服务,销毁)是由tomcat管理的,无需我们自己new
+ HttpServletRequest HttpServletResponse 两个对象也是有tomcat负责转换,在调用service方法时传入给我们用的

> 步骤3 在web.xml为UserServlet配置请求的映射路径

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
	version="4.0">
	<servlet>
<!--给servlet起一个别名-->
		<servlet-name>userServlet</servlet-name>
		<servlet-class>UserServlet</servlet-class>
	</servlet>
	<servlet-mapping>
<!--关联别名和映射路径-->
		<servlet-name>userServlet</servlet-name>
<!--可以为一个Servlet匹配多个不同的映射路径，但是不同的servlet不能使用相同的url-->
		<url-pattern>/userServlet</url-pattern>
<!--
/ 表示通配所有资源，不包括jsp文件
/* 表示统配所有资源，包括jsp文件
/a/*  匹配所有以a前缀的映射路径
*.action 匹配所有以action为后缀的映射路径
-->
	</servlet-mapping>
</web-app>
```

+ Servlet并不是文件系统中实际存在的文件或者目录,所以为了能够请求到该资源,我们需要为其配置映射路径
+ servlet的请求映射路径配置在web.xml中
+ servlet-name作为servlet的别名,可以自己随意定义,见名知意就好
+ url-pattern标签用于定义Servlet的请求映射路径
+ 一个servlet可以对应多个不同的url-pattern
+ 多个servlet不能使用相同的url-pattern
+ url-pattern中可以使用一些通配写法
  + /        表示通配所有资源,不包括jsp文件
  + /*      表示通配所有资源,包括jsp文件
  + /a/*     匹配所有以a前缀的映射路径
  + *.action 匹配所有以action为后缀的映射路径

> 步骤4 开发一个form表单,向servlet发送一个get请求并携带username参数

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="userServlet">
    请输入用户名:<input type="text" name="username" /> <br>
    <input type="submit" value="校验">
</form>
</body>
</html>
```

> 运行项目

![sad](https://github.com/HalcyonJX/web-learning/blob/main/servlet-part/Servlet-img/Snipaste_2023-12-23_00-50-54.png)

![dsada](https://github.com/HalcyonJX/web-learning/blob/main/servlet-part/Servlet-img/Snipaste_2023-12-23_00-51-09.png)

# 三、Servlet注解方式配置

刚才我们的第一个Servlet项目采用的是xml方式配置，可以看到需要在xml文件当中写类别名和关联路径，这样才能让tomcat服务器访问到我们的Java代码。这种方式比较麻烦，不会经常使用。

## 3.1 @WebServlet注解使用

```java
@WebServlet(
        name = "userServlet",
value = "/userServlet"
//        value = "/user",
//        urlPatterns =
//                {"/userServlet1","/userServlet2","/userServlet"},
//        initParams = {@WebInitParam(name = "encoding",value = "UTF-8")},
//        loadOnStartup = 6
)
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的键值对参数
        String username = req.getParameter("username");
        if("halcyon".equals(username)){
            //通过响应对象响应信息
            resp.getWriter().write("NO");
        }else {
            resp.getWriter().write("YES");
        }
    }
}
```

# 四、Servlet生命周期

## 4.1 生命周期简介

> 什么是servlet的生命周期？

+ 应用程序中的对象不仅在空间上有层次结构的关系，在时间上也会因为处于程序运行过程中的不同阶段而表现出不同状态和不同行为，这就是对象的生命周期。如同Java new一个类对象时所做的事。
+ 简单来说，就是Servlet对象在容器中从创建到销毁的过程。

>Servlet容器：

+ Servlet对象就是Servlet容器创建的，生命周期方法都是由容器（目前我们是Tomcat）调用的。

> Servlet主要的生命周期执行特点：

| 生命周期 | 对应方法                 | 执行时机               | 执行次数 |
| -------- | ------------------------ | ---------------------- | -------- |
| 构造对象 | 构造器                   | 第一次请求或者容器启动 | 1        |
| 初始化   | init()                   | 构造完毕后             | 1        |
| 处理服务 | service、doget、doput... | 每次请求               | 多次     |
| 销毁     | destory()                | 容器关闭               | 1        |

## 4.2 生命周期测试

> 开发servlet代码：

```java
public class ServletLife extends HttpServlet {
    public ServletLife(){
        System.out.println("构造器启动~~~");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("初始化启动~~");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service方法");
    }

    @Override
    public void destroy() {
        System.out.println("销毁方法");
    }
}
```

> 配置Servlet

```xml
<servlet>
	<servlet-name>servletLife</servlet-name>
	<servlet-class>ServletLife</servlet-class>
<!--load-on-startup 如果配置的是正整数则表示在容器启动时就要实例化Servlet，数字表示的是实例化的顺序-->
	<load-on-startup>1</load-on-startup>
</servlet>
	<servlet-mapping>
		<servlet-name>servletLife</servlet-name>
		<url-pattern>/servletLife</url-pattern>
	</servlet-mapping>
```

## 4.3 生命周期总结

1. 通过生命周期测试我们发现Servlet对象在容器中是单例的
2. 容器是可以处理并发的用户请求的,每个请求在容器中都会开启一个线程
3. 多个线程可能会使用相同的Servlet对象,所以在Servlet中,我们不要轻易定义一些容易经常发生修改的成员变量
4. load-on-startup中定义的正整数表示实例化顺序,如果数字重复了,容器会自行解决实例化顺序问题,但是应该避免重复
5. Tomcat容器中,已经定义了一些随系统启动实例化的servlet,我们自定义的servlet的load-on-startup尽量不要占用数字1-5

# 五、Servlet继承结构

## 5.1 Servlet接口

> 接口及方法说明

+ Servlet 规范接口,所有的Servlet必须实现 
  + public void init(ServletConfig config) throws ServletException;   
    + 初始化方法,容器在构造servlet对象后,自动调用的方法,容器负责实例化一个ServletConfig对象,并在调用该方法时传入
    + ServletConfig对象可以为Servlet 提供初始化参数
  + public ServletConfig getServletConfig();
    + 获取ServletConfig对象的方法,后续可以通过该对象获取Servlet初始化参数
  + public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException;
    + 处理请求并做出响应的服务方法,每次请求产生时由容器调用
    + 容器创建一个ServletRequest对象和ServletResponse对象,容器在调用service方法时,传入这两个对象
  + public String getServletInfo();
    + 获取ServletInfo信息的方法
  + public void destroy();
    + Servlet实例在销毁之前调用的方法

## 5.2 GenericServlet 抽象类

> 源码解释

+ GenericServlet 抽象类是对Servlet接口一些固定功能的粗糙实现,以及对service方法的再次抽象声明,并定义了一些其他相关功能方法
  + private transient ServletConfig config; 
    + 初始化配置对象作为属性
  + public GenericServlet() { } 
    + 构造器,为了满足继承而准备
  + public void destroy() { } 
    + 销毁方法的平庸实现
  + public String getInitParameter(String name) 
    + 获取初始参数的快捷方法
  + public Enumeration<String> getInitParameterNames() 
    + 返回所有初始化参数名的方法
  + public ServletConfig getServletConfig()
    +  获取初始Servlet初始配置对象ServletConfig的方法
  + public ServletContext getServletContext()
    +  获取上下文对象ServletContext的方法
  + public String getServletInfo() 
    + 获取Servlet信息的平庸实现
  + public void init(ServletConfig config) throws ServletException() 
    + 初始化方法的实现,并在此调用了init的重载方法
  + public void init() throws ServletException 
    + 重载init方法,为了让我们自己定义初始化功能的方法
  + public void log(String msg) 
  + public void log(String message, Throwable t)
    +  打印日志的方法及重载
  + public abstract void service(ServletRequest req, ServletResponse res) throws ServletException, IOException; 
    + 服务方法再次声明
  + public String getServletName() 
    + 获取ServletName的方法

## 5.3 HttpServlet 抽象类

> 解释

+ abstract class HttpServlet extends GenericServlet  HttpServlet抽象类,除了基本的实现以外,增加了更多的基础功能
  + private static final String METHOD_DELETE = "DELETE";
  + private static final String METHOD_HEAD = "HEAD";
  + private static final String METHOD_GET = "GET";
  + private static final String METHOD_OPTIONS = "OPTIONS";
  + private static final String METHOD_POST = "POST";
  + private static final String METHOD_PUT = "PUT";
  + private static final String METHOD_TRACE = "TRACE";
    + 上述属性用于定义常见请求方式名常量值
  + public HttpServlet() {}
    + 构造器,用于处理继承
  + public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
    + 对服务方法的实现
    + 在该方法中,将请求和响应对象转换成对应HTTP协议的HttpServletRequest HttpServletResponse对象
    + 调用重载的service方法
  + public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    + 重载的service方法,被重写的service方法所调用
    + 在该方法中,通过请求方式判断,调用具体的do***方法完成请求的处理
  + protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  + protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  + protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  + protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  + protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  + protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  + protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    + 对应不同请求方式的处理方法
    + 除了doOptions和doTrace方法,其他的do*** 方法都在故意响应错误信息

## 5.4 自定义Servlet

+ 自定义Servlet中,必须要对处理请求的方法进行重写
  + 要么重写service方法
  + 要么重写doGet/doPost方法

# 六、ServletConfig和ServletContext

## 6.1 ServletConfig的使用

> ServletConfig是什么

+ 为Servlet提供初始配置参数的一种对象，每个Servlet都有自己独立唯一的ServletConfig对象
+ 容器会为每个Servlet实例化一个ServletConfig对象，并通过Servlet生命周期的init方法传入给Servlet作为属性。

> ServletConfig是一个接口，定义了如下API

```java
public interface ServletConfig {
    String getServletName();

    ServletContext getServletContext();

    String getInitParameter(String var1);

    Enumeration<String> getInitParameterNames();
}
```

| 方法名                  | 作用                                                         |
| ----------------------- | ------------------------------------------------------------ |
| getServletName()        | 获取\<servlet-name>HelloServlet\</servlet-name>定义的Servlet名称 |
| getServletContext()     | 获取ServletContext对象                                       |
| getInitParameter()      | 获取配置Servlet时设置的『初始化参数』，根据名字获取值        |
| getInitParameterNames() | 获取所有初始化参数名组成的Enumeration对象                    |

> ServletConfig怎么用，测试代码如下

+ 定义ServletA

```java
public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig servletConfig = this.getServletConfig();
        //根据数据名获得单个参数
        String value = servletConfig.getInitParameter("param3");
        System.out.println("param3:"+value);
        //获取所有参数名
        Enumeration<String> parameterNames = servletConfig.getInitParameterNames();
        while(parameterNames.hasMoreElements()){
            String nextElement = parameterNames.nextElement();
            System.out.println(nextElement+":"+servletConfig.getInitParameter(nextElement));
        }
    }
}
```

+ ServletB

```java
public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig servletConfig = this.getServletConfig();
        //根据数据名获得单个参数
        String value = servletConfig.getInitParameter("param3");
        System.out.println("param3:"+value);
        //获取所有参数名
        Enumeration<String> parameterNames = servletConfig.getInitParameterNames();
        while(parameterNames.hasMoreElements()){
            String nextElement = parameterNames.nextElement();
            System.out.println(nextElement+":"+servletConfig.getInitParameter(nextElement));
        }
    }
}
```

+ 配置Servlet

```xml
	<servlet>
		<servlet-name>ServletA</servlet-name>
		<servlet-class>ServletA</servlet-class>
		<!--配置servletA的参数-->
		<init-param>
			<param-name>param1</param-name>
			<param-value>value1</param-value>
		</init-param>
		<init-param>
			<param-name>param2</param-name>
			<param-value>value2</param-value>
		</init-param>
	</servlet>
	<servlet-mapping>
		<servlet-name>ServletA</servlet-name>
		<url-pattern>/servletA</url-pattern>
	</servlet-mapping>
	<servlet>
		<servlet-name>ServletB</servlet-name>
		<servlet-class>ServletB</servlet-class>
		<init-param>
			<param-name>param3</param-name>
			<param-value>value3</param-value>
		</init-param>
		<init-param>
			<param-name>param4</param-name>
			<param-value>value4</param-value>
		</init-param>
	</servlet>
	<servlet-mapping>
		<servlet-name>ServletB</servlet-name>
		<url-pattern>/servletB</url-pattern>
	</servlet-mapping>
```

+ 请求调试

![](https://github.com/HalcyonJX/web-learning/blob/main/servlet-part/Servlet-img/Snipaste_2023-12-23_13-08-00.png)

## 6.2 ServletContext的使用

> ServletContext是什么

+ ServletContext对象又称呼为上下文对象，或者叫应用域对象。
+ 容器会为每个app创建一个独立的唯一的ServletContext对象
+ ServletContext对象为所有的Servlet所共享
+ ServletContext可以为所有的Servlet提供初始配置参数

![](https://github.com/HalcyonJX/web-learning/blob/main/servlet-part/Servlet-img/Snipaste_2023-12-23_13-10-36.png)

> ServletContext怎么用

+ 配置ServletContext参数

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
	version="4.0">
<context-param>
	<param-name>paramA</param-name>
	<param-value>valueA</param-value>
</context-param>
	<context-param>
		<param-name>paramB</param-name>
		<param-value>valueB</param-value>
	</context-param>
</web-app>
```

+ 获取ServletContext并获取参数

```java
@WebServlet("/servlet")
public class Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        String valueA = servletContext.getInitParameter("paramA");
        System.out.println("value:"+valueA);
        //获取所有的参数名
        Enumeration<String> parameterNames = servletContext.getInitParameterNames();
        while (parameterNames.hasMoreElements()){
            String nextElement = parameterNames.nextElement();
            System.out.println(nextElement+":"+servletContext.getInitParameter(nextElement));
        }
    }
}
```

## 6.3 ServletContext其他重要API

> 获取资源的真实路径

``` java
String realPath = servletContext.getRealPath("资源在web目录中的路径");
```

+ 例如我们的目标是需要获取项目中某个静态资源的路径，不是工程目录中的路径，而是**部署目录中的路径**；我们如果直接拷贝其在我们电脑中的完整路径的话其实是有问题的，因为如果该项目以后部署到公司服务器上的话，路径肯定是会发生改变的，所以我们需要使用代码动态获取资源的真实路径.  只要使用了servletContext动态获取资源的真实路径，**那么无论项目的部署路径发生什么变化，都会动态获取项目运行时候的实际路径**，所以就不会发生由于写死真实路径而导致项目部署位置改变引发的路径错误问题

> 获取项目的上下文路径

``` java
String contextPath = servletContext.getContextPath();
```

+ 项目的部署名称,也叫项目的上下文路径,在部署进入tomcat时所使用的路径,该路径是可能发生变化的,通过该API动态获取项目真实的上下文路径,可以**帮助我们解决一些后端页面渲染技术或者请求转发和响应重定向中的路径问题**

>  域对象的相关API

+ 域对象: 一些用于存储数据和传递数据的对象,传递数据不同的范围,我们称之为不同的域,不同的域对象代表不同的域,共享数据的范围也不同
+ ServletContext代表应用,所以ServletContext域也叫作应用域,是webapp中最大的域,可以在本应用内实现数据的共享和传递
+ webapp中的三大域对象,分别是应用域,会话域,请求域
+ 三大域对象都具有的API如下

| API                                         | 功能解释            |
| ------------------------------------------- | ------------------- |
| void setAttribute(String key,Object value); | 向域中存储/修改数据 |
| Object getAttribute(String key);            | 获得域中的数据      |
| void removeAttribute(String key);           | 移除域中的数据      |

# 七、HttpServletRequest

> 简介

+ HttpServletRequest是一个接口，其父接口是ServletReques
+ HttpServletRequest是Tomcat将请求报文转换封装而来的对象，在Tomcat调用service方法时传入
+ 客户端发来的请求

> 常见API

+ 获取请求行信息相关(方式,请求的url,协议及版本)

| API                           | 功能解释                       |
| ----------------------------- | ------------------------------ |
| StringBuffer getRequestURL(); | 获取客户端请求的url            |
| String getRequestURI();       | 获取客户端请求项目中的具体资源 |
| int getServerPort();          | 获取客户端发送请求时的端口     |
| int getLocalPort();           | 获取本应用在所在容器的端口     |
| int getRemotePort();          | 获取客户端程序的端口           |
| String getScheme();           | 获取请求协议                   |
| String getProtocol();         | 获取请求协议及版本号           |
| String getMethod();           | 获取请求方式                   |

+ 获得请求头信息相关

| API                                   | 功能解释               |
| ------------------------------------- | ---------------------- |
| String getHeader(String headerName);  | 根据头名称获取请求头   |
| Enumeration<String> getHeaderNames(); | 获取所有的请求头名字   |
| String getContentType();              | 获取content-type请求头 |

+ 获得请求参数相关

| API                                                     | 功能解释                             |
| ------------------------------------------------------- | ------------------------------------ |
| String getParameter(String parameterName);              | 根据请求参数名获取请求单个参数值     |
| String[] getParameterValues(String parameterName);      | 根据请求参数名获取请求多个参数值数组 |
| Enumeration<String> getParameterNames();                | 获取所有请求参数名                   |
| Map<String, String[]> getParameterMap();                | 获取所有请求参数的键值对集合         |
| BufferedReader getReader() throws IOException;          | 获取读取请求体的字符输入流           |
| ServletInputStream getInputStream() throws IOException; | 获取读取请求体的字节输入流           |
| int getContentLength();                                 | 获得请求体长度的字节数               |

+ 其他API

| API                                          | 功能解释                    |
| -------------------------------------------- | --------------------------- |
| String getServletPath();                     | 获取请求的Servlet的映射路径 |
| ServletContext getServletContext();          | 获取ServletContext对象      |
| Cookie[] getCookies();                       | 获取请求中的所有cookie      |
| HttpSession getSession();                    | 获取Session对象             |
| void setCharacterEncoding(String encoding) ; | 设置请求体字符集            |

# 八、HttpServletResponse

> 简介

+ HttpServletResponse是一个接口,其父接口是ServletResponse
+ HttpServletResponse是Tomcat预先创建的,在Tomcat调用service方法时传入
+ HttpServletResponse代表对客户端的响应,该对象会被转换成响应的报文发送给客户端,通过该对象我们可以设置响应信息

> 常见API

+ 设置响应行相关

| API                        | 功能解释       |
| -------------------------- | -------------- |
| void setStatus(int  code); | 设置响应状态码 |


+ 设置响应头相关

| API                                                    | 功能解释                                         |
| ------------------------------------------------------ | ------------------------------------------------ |
| void setHeader(String headerName, String headerValue); | 设置/修改响应头键值对                            |
| void setContentType(String contentType);               | 设置content-type响应头及响应字符集(设置MIME类型) |

+ 设置响应体相关

| API                                                       | 功能解释                                                |
| --------------------------------------------------------- | ------------------------------------------------------- |
| PrintWriter getWriter() throws IOException;               | 获得向响应体放入信息的字符输出流                        |
| ServletOutputStream getOutputStream() throws IOException; | 获得向响应体放入信息的字节输出流                        |
| void setContentLength(int length);                        | 设置响应体的字节长度,其实就是在设置content-length响应头 |

+ 其他API

| API                                                          | 功能解释                                            |
| ------------------------------------------------------------ | --------------------------------------------------- |
| void sendError(int code, String message) throws IOException; | 向客户端响应错误信息的方法,需要指定响应码和响应信息 |
| void addCookie(Cookie cookie);                               | 向响应体中增加cookie                                |
| void setCharacterEncoding(String encoding);                  | 设置响应体字符集                                    |

> MIME类型

+ MIME类型,可以理解为文档类型,用户表示传递的数据是属于什么类型的文档
+ 浏览器可以根据MIME类型决定该用什么样的方式解析接收到的响应体数据
+ 可以这样理解: 前后端交互数据时,告诉对方发给对方的是 html/css/js/图片/声音/视频/... ...
+ tomcat/conf/web.xml中配置了常见文件的拓展名和MIMIE类型的对应关系
+ 常见的MIME类型举例如下

| 文件拓展名                  | MIME类型               |
| --------------------------- | ---------------------- |
| .html                       | text/html              |
| .css                        | text/css               |
| .js                         | application/javascript |
| .png /.jpeg/.jpg/... ...    | image/jpeg             |
| .mp3/.mpe/.mpeg/ ... ...    | audio/mpeg             |
| .mp4                        | video/mp4              |
| .m1v/.m1v/.m2v/.mpe/... ... | video/mpeg             |

# 九、请求转发和响应重定向

## 9.1 概述

> 什么是请求转发和响应重定向

+ 请求转发和响应重定向是web应用中间件访问项目资源的两种手段，也是Servlet控制页面跳转的两种手段
+ 请求转发通过HttpServletRequest实现，响应重定向通过HttpServletResponse实现。
+ 请求转发生活举例: 张三找李四借钱,李四没有,李四找王五,让王五借给张三
+ 响应重定向生活举例:张三找李四借钱,李四没有,李四让张三去找王五,张三自己再去找王五借钱

## 9.2 请求转发

> 逻辑图

![](https://github.com/HalcyonJX/web-learning/blob/main/servlet-part/Servlet-img/Snipaste_2023-12-23_13-33-50.png)

> 请求转发特点(背诵)

+ 请求转发通过HttpServletRequest对象获取请求转发器实现
+ 请求转发是服务器内部的行为,对客户端是屏蔽的
+ 客户端只发送了一次请求,客户端地址栏不变
+ 服务端只产生了一对请求和响应对象,这一对请求和响应对象会继续传递给下一个资源
+ 因为全程只有一个HttpServletRequset对象,所以请求参数可以传递,请求域中的数据也可以传递
+ 请求转发可以转发给其他Servlet动态资源,也可以转发给一些静态资源以实现页面跳转
+ 请求转发可以转发给WEB-INF下受保护的资源
+ 请求转发不能转发到本项目以外的外部资源

> 请求转发测试代码

![](https://github.com/HalcyonJX/web-learning/blob/main/servlet-part/Servlet-img/Snipaste_2023-12-23_13-39-34.png)

+ ServletA

```java
@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求转发器
        //转发给 servletB
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("servletB");
        //转发给一个视图资源
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("welcome.html");
        //转发 WEB-INF下的资源
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/view1.html");
        //转发外部资源   报错 404
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("https://www.qq.com");
        //获取请求参数
        String username = req.getParameter("username");
        System.out.println(username);
        //向请求域中添加数据
        req.setAttribute("reqKey","requestMsg");
        //做出转发动作
        requestDispatcher.forward(req,resp);
    }
}
```

+ ServletB

```java
@WebServlet("/servletB")
public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        System.out.println(username);
        //获取请求域中的数据
        String reqMsg = (String) req.getAttribute("reqKey");
        System.out.println(reqMsg);
        //做出响应
        resp.getWriter().write("ServletB response");
    }
}
```

+ 打开浏览器输入url测试

```url
http://localhost:8080/demo05-servlet/servletA?username=halcyon
```

## 9.3 响应重定向

> 响应重定向运行逻辑图

![](https://github.com/HalcyonJX/web-learning/blob/main/servlet-part/Servlet-img/Snipaste_2023-12-23_13-53-30.png)

> 响应重定向特点(背诵)

+ 响应重定向通过HttpServletResponse对象的sendRedirect方法实现
+ 响应重定向是服务端通过302响应码和路径,告诉客户端自己去找其他资源,是在服务端提示下的,客户端的行为
+ 客户端至少发送了两次请求,客户端地址栏是要变化的
+ 服务端产生了多对请求和响应对象,且请求和响应对象不会传递给下一个资源
+ 因为全程产生了多个HttpServletRequset对象,所以请求参数不可以传递,请求域中的数据也不可以传递
+ 重定向可以是其他Servlet动态资源,也可以是一些静态资源以实现页面跳转
+ 重定向不可以到给WEB-INF下受保护的资源
+ 重定向可以到本项目以外的外部资源

> 响应重定向测试代码

![](https://github.com/HalcyonJX/web-learning/blob/main/servlet-part/Servlet-img/Snipaste_2023-12-23_14-09-15.png)

+ ServletA

```java
@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        System.out.println(username);
        //向请求域中添加数据
        req.setAttribute("reqKey","reqMsg");
        //响应重定向
        //重定向到servlet动态资源
//        resp.sendRedirect("servletB");
        //重定向到视图静态资源
//        resp.sendRedirect("welcome.html");
        //重定向到WEB-INF下的资源   不行  404
//        resp.sendRedirect("WEB-INF/views/view1.html");
        //重定向到外部资源
        resp.sendRedirect("https://www.qq.com");
    }
}
```

+ ServletB

```java
@WebServlet("/servletB")
public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        System.out.println(username);
        //获取请求域中的数据
        String reqMsg = (String) req.getAttribute("reqKey");
        System.out.println(reqMsg);
        //做出响应
        resp.getWriter().write("servletB response");
    }
}
```

+ 测试路径

```java
http://localhost:8080/demo05-servlet/servletA?username=halcyon
```

# 十、MVC架构模式



>  MVC（Model View Controller）是软件工程中的一种**`软件架构模式`**，它把软件系统分为**`模型`**、**`视图`**和**`控制器`**三个基本部分。用一种业务逻辑、数据、界面显示分离的方法组织代码，将业务逻辑聚集到一个部件里面，在改进和个性化定制界面及用户交互的同时，不需要重新编写业务逻辑。

+ **M**：Model 模型层,具体功能如下
  1. 存放和数据库对象的实体类以及一些用于存储非数据库表完整相关的VO对象
  2. 存放一些对数据进行逻辑运算操作的的一些业务处理代码

+ **V**：View 视图层,具体功能如下
  1. 存放一些视图文件相关的代码 html css js等
  2. 在前后端分离的项目中,后端已经没有视图文件,该层次已经衍化成独立的前端项目

+ **C**：Controller 控制层,具体功能如下
  1. 接收客户端请求,获得请求数据
   2. 将准备好的数据响应给客户端

> MVC模式下,项目中的常见包

+ M:
  1. 实体类包(pojo /entity /bean) 专门存放和数据库对应的实体类和一些VO对象
  2. 数据库访问包(dao/mapper)  专门存放对数据库不同表格CURD方法封装的一些类
  3. 服务包(service)                       专门存放对数据进行业务逻辑运算的一些类

+ C:
  1. 控制层包(controller)

+ V:
  1. web目录下的视图资源 html css js img 等
  2. 前端工程化后,在后端项目中已经不存在了



+ 非前后端分离的MVC

![](https://github.com/HalcyonJX/web-learning/blob/main/servlet-part/Servlet-img/Snipaste_2023-12-23_14-19-12.png)

+ 前后端分离的MVC

![](https://github.com/HalcyonJX/web-learning/blob/main/servlet-part/Servlet-img/Snipaste_2023-12-23_14-19-18.png)













































