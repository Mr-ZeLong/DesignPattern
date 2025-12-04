package com.zelon.designpattern.structural.adaptor;

/*
适配器模式
一、使用场景
1. 替换依赖的外部系统，同时减少对原有系统的改动
2. 统一引入的多个功能相似的外部系统接口，日志系统 slf4j 为了适配多个日志系统，
如 log4j、logback、log4j2，就提供了各种适配器，将日志系统适配为 slf4j 接口
总的来说，适配器模式相当于一种补救措施。


二、实现方式
1. 组合
2. 继承
默认情况下使用组合的方式实现，扩展灵活，耦合度低，适合大多数场景，
但有时 被适配的类接口特别多，且大多数与目标接口重合，则使用继承的方式，只需重写部分不同的接口，降低代码量
 */

// 方式一：通过组合的方式实现
public class Adaptor implements ITarget{
    private Adaptee adaptee;
    // 依赖注入
    public Adaptor(Adaptee adaptee){
        this.adaptee = adaptee;
    }
    @Override
    public void f1() {
        // ...
        adaptee.fa();
    }

    @Override
    public void f2() {
        // ...
        adaptee.fb();
    }

    @Override
    public void fc() {
        adaptee.fc();
    }
}

// 方式二：通过继承的方式实现
class Adaptor2 extends Adaptee implements ITarget{
    @Override
    public void f1() {
        super.fa();
    }

    @Override
    public void f2() {
        super.fb();
    }
    // fc() 如果和Adaptee的fc()方法声明相同，且功能相同，则不同重写，这是两种方式的主要差别
}


interface ITarget{
    void f1();
    void f2();
    void fc();
}

class Adaptee{
    public void fa(){
    }
    public void fb(){
    }
    public void fc(){
    }
}