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