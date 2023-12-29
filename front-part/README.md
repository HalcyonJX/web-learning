# 一、前端工程化

## 1.1 什么是前端工程化

> `前端工程化`是使用`软件工程的方法`来`单独`解决`前端`的开发流程中`模块化、组件化、规范化、自动化`的问题,其主要目的为了提高效率和降低成本。 

## 1.2 前端工程化实现技术栈

> 前端工程化实现的技术栈有很多,我们采用ES6+nodejs+npm+Vite+VUE3+router+pinia+axios+Element-plus组合来实现

+ ECMAScript6       VUE3中大量使用ES6语法
+ Nodejs                前端项目运行环境
+ npm                    依赖下载工具
+ vite                      前端项目构建工具
+ VUE3                   优秀的渐进式前端框架
+ router                 通过路由实现页面切换
+ pinia                   通过状态管理实现组件数据传递
+ axios                   ajax异步请求封装技术实现前后端数据交互
+ Element-plus     可以提供丰富的快速构建网页的组件仓库              

# 二、ECMA6Script

## 2.1 es6简介

提高JavaScript的核心语言特性和功能

## 2.2 es6的变量和模板字符串

> ES6 新增了`let`和`const`，用来声明变量,使用的细节上也存在诸多差异

+ let 和var的差别

  1、let 不能重复声明

  2、let有块级作用域，非函数的花括号遇见let会有块级作用域，也就是只能在花括号里面访问。

  3、let不会预解析进行变量提升

  4、let 定义的全局变量不会作为window的属性

  5、let在es6中推荐优先使用

```html
    <script>
        //1.let只有在当前代码块有效
        {
            let a = 1
            var b = 2
        }
        // console.log(a); // a is not defined
        console.log(b); //可以输出
        //2.不能重复声明
        let name = '天真'
        // let name = '无邪'
        //3.不存在变量提升(先声明，再使用)
        console.log(test) //可以，但是值为undefined
        var test = 'test'
        //console.log(test1) //不可以 let声明的变量一定要在声明后使用
        let test1 = 'test1'
        //4.不会成为window的属性
        var a = 100
        console.log(window.a) //100
        let c = 200
        console.log(window.c) //undefined
        //5.循环中推荐使用
        for(let i = 0;i < 10;i++){
            console.log(i)
        }
    </script>
```

+ const和var的差异

  1、新增const和let类似，只是const定义的变量不能修改

  2、并不是变量的值不得改动，而是变量指向的那个内存地址所保存的数据不得改动。

```html
    <script>
        //声明场景语法，建议变量名大写
        const PI = 3.1415926
        //1.常量声明必须有初始化值
        // const A; //报错
        //2.常量值不可以改动
        const A = 'zzz'
        // A = 'xx'  //不可改动
        //3.和let一样，分作用域
        {
            const A = 'zzj'
            console.log(A) //zzj
        }
        //4.对应数组和对象元素修改，不算常量修改，修改值，不修改地址
        const TEAM = ['周杰伦','吴彦祖','古天乐']
        TEAM.push('郭富城')
        // TEAM = [] //报错
        console.log(TEAM)
    </script>
```

> 模板字符串（template string）是增强版的字符串，用反引号（`）标识  

1、字符串中可以出现换行符

2、可以使用 ${xxx} 形式输出变量和拼接变量

```html
    <script>
        //1 多行普通字符串
        let urlStr =         
        '<ul>'+
        '<li>JAVA</li>'+
        '<li>html</li>'+
        '<li>VUE</li>'+
        '</ul>'
        console.log(urlStr)
        //2.多行模板字符串
        let urlStr2 = `
        <ul>
            <li>java</li>
            <li>html</li>
            <li>VUE</li>
        </ul>
        `
        console.log(urlStr2)
        //3.普通字符串拼接
        let name = '张小明'
        let infoStr = name + '年级第一'
        console.log(infoStr)
        //4.模板字符串拼接
        let infoStr2 = `${name}被评为年级第一`
        console.log(infoStr2)
    </script>
```

## 2.3 es6的解构表达式

> ES6 的解构赋值是一种方便的语法，可以快速将数组或对象中的值拆分并赋值给变量。解构赋值的语法使用花括号 `{}` 表示对象，方括号 `[]` 表示数组。通过解构赋值，函数更方便进行参数接受等！

> **数组解构赋值**

+ 可以通过数组解构将数组中的值赋值给变量，语法为：

```javascript
        let [a,b,c] = [1,2,3] //按顺序进行初始化
        console.log(a)
        console.log(b)
        console.log(c)
```

+ 该语句将数组 \[1, 2, 3] 中的第一个值赋值给 a 变量，第二个值赋值给 b 变量，第三个值赋值给 c 变量。
  可以使用默认值为变量提供备选值，在数组中缺失对应位置的值时使用该默认值。例如：

```javascript
        let [a,b,c,d=4] = [1,2,3]
        console.log(d)
```

> **对象解构赋值**

+ 可以通过对象解构将对象中的值赋值给变量，语法为：

```javascript
        let{a,b} = {a:1,b:2}
        console.log(a)
        console.log(b)
```

+ 该语句将对象 {a: 1, b: 2} 中的 a 属性值赋值给 a 变量，b 属性值赋值给 b 变量。
  可以为标识符分配不同的变量名称，使用 : 操作符指定新的变量名。例如：

```javascript
        let {a:x , b :y} = {a:2,b:3}
        console.log(x)
        console.log(y)
```

> **函数参数解构赋值**

+ 解构赋值也可以用于函数参数。例如：

```javascript
        function add([x,y]){
            return x + y
        }
        console.log(add([20,30]))
```

+ 该函数接受一个数组作为参数，将其中的第一个值赋给 x，第二个值赋给 y，然后返回它们的和。

+ ES6 解构赋值让变量的初始化更加简单和便捷。通过解构赋值，我们可以访问到对象中的属性，并将其赋值给对应的变量，从而提高代码的可读性和可维护性。

## 2.4 es6的箭头函数

> ES6 允许使用“箭头” 义函数。语法类似Java中的Lambda表达式

### 2.4.1 声明和特点

```html
    <script>
        //1.函数声明
        let fn1 = function(){}
        let fn2 = ()=>{} //箭头函数
        let fn3 = x =>{} //单参数可以胜率()，多参数无参不可以！
        let fn4 = x => console.log(x) //只有一行方法体可以省略{}
        let fn5 = x => x + 1 //当函数体只有一句返回值时，可以省略{}和return语句
        //2.使用特点 箭头函数this关键字
        //在 JavaScript 中，this 关键字通常用来引用函数所在的对象，
        //或者在函数本身作为构造函数时，来引用新对象的实例。
        //但是在箭头函数中，this 的含义与常规函数定义中的含义不同，
        //并且是由箭头函数定义时的上下文来决定的，而不是由函数调用时的上下文来决定的。
        //箭头函数没有自己的this，this指向的是外层上下文环境的this
        let person = {
            name:'张三',
            showName:function(){
                console.log(this) //这里的this是person
                console.log(this.name)
            },
            viewName:()=>{
                console.log(this) // 这里的this是window
                console.log(this.name)
            }
        }
        person.showName()
        person.viewName()

        //this的应用
        function Counter(){
            this.count = 0;
            setInterval(() => { 
                //这里的this是上一层作用域中的this，即Counter实例化对象
                this.count++
                console.log(this.count)
            },1000)
        }
        let counter = new Counter()
    </script>
```

### 2.4.2 实践和应用场景

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        #xdd{
            display: inline-block;
            width: 200px;
            height: 200px;
            background-color: red;
        }
    </style>
</head>
<body>
    <div id="xdd"></div>
    <script>
        let xdd = document.getElementById("xdd")
        //方案1
        xdd.onclick = function(){
            console.log(this)
            let _this = this //this 是xdd
            //开启定时器
            setTimeout(function(){
                console.log(this)
                //变粉色
                _this.style.backgroundColor = 'pink'
            },2000)
        }
        //方案2
        xdd.onclick = function(){
            console.log(this)
            //开启定时器
            setTimeout(()=>{
                console.log(this) //使用setTimeout()方法所在环境时的this对象
                this.style.backgroundColor='pink'
            },2000)
        }
    </script>
</body>
</html>
```

### 2.4.3 rest和spread

> rest参数，在形参上使用 和JAVA中的可变参数几乎一样
>
> spread参数,在实参上使用rest

```html
    <script>
        //1.参数列表中多个普通参数 普通函数和箭头函数中都支持
        let fun1 = function(a,b,c,d=10){console.log(a,b,c,d)}
        let fun2 = (a,b,c,d=10) => {console.log(a,b,c,d)}
        fun1(1,2,3)
        fun2(20,30,20,100)
        //2...作为参数列表，称之为rest参数 普通函数和箭头函数中都支持，因为箭头函数中无法使用arguments，rest是一种解决方案
        let fun3 = function(...args){console.log(args)}
        let fun4 = (...args) => {console.log(args)}
        fun3(1,2,34,5,32,2141,34)
        fun4(13,23,421,45,21,221,21523,21456,434,21)
        //只能有一个rest参数，放在最后
        
        let arr = [1,2,3]
        let fun5 = (a,b,c) => {
            console.log(a,b,c)
        }
        //调用方法时，对arr进行转换
        fun5(...arr)
        //应用场景1 合并数组
        let arr2 = [4,5,6]
        let arr3 = [...arr,...arr2]
        console.log(arr3)
        //应用场景2 合并对象属性
        let p1 = {name:"藏三"}
        let p2 = {age:10}
        let p3 = {gender:"boy"}
        let person = {...p1,...p2,...p3}
        console.log(person)
    </script>
```

## 2.5 es6的对象创建和拷贝

### 2.5.1 对象创建的语法糖

> ES6中新增了对象创建的语法糖,支持了class extends constructor等关键字,让ES6的语法和面向对象的语法更加接近

```html
    <script>
        class Person{
            //属性
            #n;
            age;
            get name(){
                return this.n;
            }
            set name(n){
                this.n = n;
            }
            //实例方法
            eat(food){
                console.log(this.age+"岁的"+this.n+"用筷子吃"+food)
            }
            //静态方法
            static sum(a,b){
                return a+b
            }
            //构造器
            constructor(name,age){
                this.n = name;
                this.age = age;
            }
        }
        let person = new Person("张三",10)
        //访问对象属性
        //调用对象方法
        console.log(person.name)
        console.log(person.n)
        person.name = "小明"
        person.eat("火锅")
        console.log(Person.sum(100,200))

        class Student extends Person{
            grade;
            score;
            study(){}
            constructor(name,age){
                super(name,age)
            }
        }
        let stu = new Student("学生小李",19)
        stu.eat("面条")
        stu.grade = 2
        console.log(stu.grade)
    </script>
```

### 2.5.2 对象的深拷贝和浅拷贝

> 对象的拷贝,快速获得一个和已有对象相同的对象的方式

```javascript
        // 浅拷贝
        let arr = ['java','c','python']
        let person = {
            name:'张三',
            language:arr
        }
        //浅拷贝，person2和person指向相同的内存
        let person2 = person
        person2.name = "小帅"
        console.log(person.name)
        // 深拷贝
        //通过JSON和字符串的转换形成一个新的对象
        let person3 = JSON.parse(JSON.stringify(person))
        person3.name="zzj"
        console.log(person.name)
        console.log(person3.name)
```

## 2.6 es6的模块化处理

### 2.6.1模块化介绍

> 模块化是一种组织和管理前端代码的方式，将代码拆分成小的模块单元，使得代码更易于维护、扩展和复用。它包括了定义、导出、导入以及管理模块的方法和规范。前端模块化的主要优势如下：

1.  提高代码可维护性：通过将代码拆分为小的模块单元，使得代码结构更为清晰，可读性更高，便于开发者阅读和维护。
2.  提高代码可复用性：通过将重复使用的代码变成可复用的模块，减少代码重复率，降低开发成本。
3.  提高代码可扩展性：通过模块化来实现代码的松耦合，便于更改和替换模块，从而方便地扩展功能。

> 目前，前端模块化有多种规范和实现，包括 CommonJS、AMD 和 ES6 模块化。ES6 模块化是 JavaScript 语言的模块标准，使用 import 和 export 关键字来实现模块的导入和导出。现在，大部分浏览器都已经原生支持 ES6 模块化，因此它成为了最为广泛使用的前端模块化标准. `

+ ES6模块化的几种暴露和导入方式
  1. 分别导出
  2. 统一导出
  3. 默认导出
+ `ES6中无论以何种方式导出,导出的都是一个对象,导出的内容都可以理解为是向这个对象中添加属性或者方法`

### 2.6.2 分别导出

+ module.js 向外分别暴露成员

```javascript
//1.分别暴露
//模块想对外导出，添加export关键字即可

//导出一个变量
export const PI = 3.14
//导出一个函数
export function sum(a,b){
    return a+b
}
//导出一个类
export class Person{
    constructor(name,age){
        this.name = name;
        this.age = age;
    }
    sayHello(){
        console.log(`Hello,my name is ${this.name},I'm ${this.age} years old`)
    }
}
```

+ app.js 导入module.js中的成员

```javascript
/*
    *代表module.js 中所有的成员
    m1代表所有成员所属的对象
*/
import * as m1 from './module.js'
//使用暴露的属性
console.log(m1.PI)
//调用暴露的方法
console.log(m1.sum(100,299))
//调用暴露的类
let person = new m1.Person('hsp',50)
person.sayHello()
```

+ index.html作为程序启动的入口  导入 app.js 

```html
    <!-- 导入JS文件，添加type='module'属性，否则不支持es6的模块化 -->
    <script src="./app.js" type="module"></script>
```

### 2.6.3 统一导出

+ module.js向外统一导出成员

```javascript
//2.统一暴露
// 模块想对外导出,export统一暴露想暴露的内容!
// 定义一个常量
const PI = 3.14
// 定义一个函数
function sum(a, b) {
  return a + b;
}
// 定义一个类
class Person {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }
  sayHello() {
    console.log(`Hello, my name is ${this.name}, I'm ${this.age} years old.`);
  }
}
// 统一对外导出(暴露)
export {
	PI,
    sum,
    Person
}
```

+ app.js导入module.js中的成员

```javascript
/* 
    {}中导入要使用的来自于module.js中的成员
    {}中导入的名称要和module.js中导出的一致,也可以在此处起别名
    {}中如果定义了别名,那么在当前模块中就只能使用别名
    {}中导入成员的顺序可以不是暴露的顺序
    一个模块中可以同时有多个import
    多个import可以导入多个不同的模块,也可以是同一个模块
*/
//import {PI ,Person ,sum }  from './module.js'
//import {PI as pi,Person as People,sum as add}  from './module.js'
import {PI ,Person ,sum,PI as pi,Person as People,sum as add}  from './module.js'
// 使用暴露的属性
console.log(PI)
console.log(pi)
// 调用暴露的方法
let result1 =sum(10,20)
console.log(result1)
let result2 =add(10,20)
console.log(result2)
// 使用暴露的Person类
let person1 =new Person('张三',10)
person1.sayHello()
let person2 =new People('李四',11)
person2.sayHello()
```

### 2.6.4 默认导出

+ modules混合向外导出

```javascript
// 3默认和混合暴露
/* 
    默认暴露语法  export default sum
    默认暴露相当于是在暴露的对象中增加了一个名字为default的属性
    三种暴露方式可以在一个module中混合使用

*/
export const PI = 3.14
// 导出一个函数
function sum(a, b) {
  return a + b;
}
// 导出一个类
class Person {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }
  sayHello() {
    console.log(`Hello, my name is ${this.name}, I'm ${this.age} years old.`);
  }
}

// 导出默认
export default sum
// 统一导出
export {
   Person
}
```

+ app.js 的default和其他导入写法混用

```javascript
/* 
    *代表module.js中的所有成员
    m1代表所有成员所属的对象
*/
import * as m1 from './module.js'
import {default as add} from './module.js' // 用的少
import add2 from './module.js' // 等效于 import {default as add2} from './module.js'

// 调用暴露的方法
let result =m1.default(10,20)
console.log(result)
let result2 =add(10,20)
console.log(result2)
let result3 =add2(10,20)
console.log(result3)

// 引入其他方式暴露的内容
import {PI,Person} from './module.js'
// 使用暴露的Person类
let person =new Person('张三',10)
person.sayHello()
// 使用暴露的属性
console.log(PI)
```

# 三、前端工程化环境搭建

## 3.1 nodejs的简介和安装

### 3.1.1 什么是nodejs

> Node.js 是一个基于 Chrome V8 引擎的 JavaScript 运行时环境，可以使 JavaScript 运行在服务器端。使用 Node.js，可以方便地开发服务器端应用程序，如 Web 应用、API、后端服务，还可以通过 Node.js 构建命令行工具等。相比于传统的服务器端语言（如 PHP、Java、Python 等），Node.js 具有以下特点：

-   单线程，但是采用了事件驱动、异步 I/O 模型，可以处理高并发请求。
-   轻量级，使用 C++ 编写的 V8 引擎让 Node.js 的运行速度很快。
-   模块化，Node.js 内置了大量模块，同时也可以通过第三方模块扩展功能。
-   跨平台，可以在 Windows、Linux、Mac 等多种平台下运行。

> Node.js 的核心是其管理事件和异步 I/O 的能力。Node.js 的异步 I/O 使其能够处理大量并发请求，并且能够避免在等待 I/O 资源时造成的阻塞。此外，Node.js 还拥有高性能网络库和文件系统库，可用于搭建 WebSocket 服务器、上传文件等。`在 Node.js 中，我们可以使用 JavaScript 来编写服务器端程序，这也使得前端开发人员可以利用自己已经熟悉的技能来开发服务器端程序，同时也让 JavaScript 成为一种全栈语言。`Node.js 受到了广泛的应用，包括了大型企业级应用、云计算、物联网、游戏开发等领域。常用的 Node.js 框架包括 Express、Koa、Egg.js 等，它们能够显著提高开发效率和代码质量。

### 3.1.2 如何安装nodejs

1.  打开官网https://nodejs.org/en下载对应操作系统的 LTS 版本。
2.  双击安装包进行安装，安装过程中遵循默认选项即可(或者参照https://www.runoob.com/nodejs/nodejs-install-setup.html )。安装完成后，可以在命令行终端输入 `node -v` 和 `npm -v` 查看 Node.js 和 npm 的版本号。

3. 定义一个app.js文件，cmd到该文件所在目录，然后再dos上通过`node app.js`命令即可运行

```javascript
function sum(a,b){
    return a+b;
}
function main(){
    console.log(sum(10,20))
}
main()
```

## 3.2 npm配置和使用

### 3.2.1 npm介绍

> NPM全称Node Package Manager，是Node.js包管理工具，是全球最大的模块生态系统，里面所有的模块都是开源免费的；也是Node.js的包管理工具，相当于后端的Maven 。

### 3.2.2 npm安装和配置

> 1.安装

+ 安装node，自动安装npm包管理工具！


> 2.配置依赖下载使用阿里镜像

+ npm 安装依赖包时默认使用的是官方源，由于国内网络环境的原因，有时会出现下载速度过慢的情况。为了解决这个问题，可以配置使用阿里镜像来加速 npm 的下载速度，具体操作如下：
+ 打开命令行终端，执行以下命令，配置使用阿里镜像：
+ 原来的 registry.npm.taobao.org 已替换为 registry.npmmirror.com

``` shell
npm config set registry https://registry.npmmirror.com
```

+ 确认配置已生效，可以使用以下命令查看当前 registry 的配置：如果输出结果为 `https://registry.npmmirror.com`，说明配置已成功生效。

```shell
npm config get registry
```

+ 如果需要恢复默认的官方源，可以执行以下命令：

```javascript
npm config set registry https://registry.npmjs.org/
```

> 3.配置全局依赖下载后存储位置

+ 在 Windows 系统上，npm 的全局依赖默认安装在 `<用户目录>\AppData\Roaming\npm` 目录下。

+ 如果需要修改全局依赖的安装路径，可以按照以下步骤操作：

  1. 创建一个新的全局依赖存储目录，例如 `D:\GlobalNodeModules`。

  2. 打开命令行终端，执行以下命令来配置新的全局依赖存储路径：

     ``` shell
     npm config set prefix "D:\GlobalNodeModules"
     ```

  3. 确认配置已生效，可以使用以下命令查看当前的全局依赖存储路径：

     ``` shell
     npm config get prefix
     ```

> 4.升级npm版本

+ cmd 输入npm -v 查看版本

+ 如果node中自带的npm版本过低！则需要升级至9.6.6！

```shell
npm install -g npm@9.6.6
```

### 3.2.3 npm 常用命令

> 1.项目初始化

+ npm init
  + 进入一个vscode创建好的项目中, 执行 npm init 命令后，npm 会引导您在命令行界面上回答一些问题,例如项目名称、版本号、作者、许可证等信息，并最终生成一个package.json 文件。package.json信息会包含项目基本信息！类似maven的pom.xml
+ npm init -y
  + 执行，-y yes的意思，所有信息使用当前文件夹的默认值！不用挨个填写！

> 2.安装依赖  (查看所有依赖地址  https://www.npmjs.com )

+ npm install 包名 或者 npm install 包名@版本号
  + 安装包或者指定版本的依赖包(安装到当前项目中)
+ npm install -g 包名
  + 安装全局依赖包(安装到d:/GlobalNodeModules)则可以在任何项目中使用它，而无需在每个项目中独立安装该包。
+ npm install
  + 安装package.json中的所有记录的依赖

> 3.升级依赖

+ npm update 包名
  + 将依赖升级到最新版本

> 4.卸载依赖

+ npm uninstall 包名

> 5.查看依赖

+ npm ls
  + 查看项目依赖

+ npm list -g
  + 查看全局依赖

> 6.运行命令

+ npm run 命令是在执行 npm 脚本时使用的命令。npm 脚本是一组在 package.json 文件中定义的可执行命令。npm 脚本可用于启动应用程序，运行测试，生成文档等，还可以自定义命令以及配置需要运行的脚本。

+ 在 package.json 文件中，scripts 字段是一个对象，其中包含一组键值对，键是要运行的脚本的名称，值是要执行的命令。例如，以下是一个简单的 package.json 文件：

```json
{
	"name": "my-app",
  	"version": "1.0.0",
    "scripts": {
        "start": "node index.js",
        "test": "jest",
        "build": "webpack"
    },
    "dependencies": {
        "express": "^4.17.1",
        "jest": "^27.1.0",
        "webpack": "^5.39.0"
    }
}
```

+ scripts 对象包含 start、test 和 build 三个脚本。当您运行 npm run start 时，将运行 node index.js，并启动应用程序。同样，运行 npm run test 时，将运行 Jest 测试套件，而 npm run build 将运行 webpack 命令以生成最终的构建输出。
+ 总之，npm run 命令为您提供了一种在 package.json 文件中定义和管理一组指令的方法，可以在项目中快速且灵活地运行各种操作。





















































