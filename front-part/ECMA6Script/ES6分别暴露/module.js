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