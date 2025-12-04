package com.zelon.designpattern.structural.adaptor;

// 原来的系统使用 A 类
interface IA {
    void fa();
}

class A implements IA {
    @Override
    public void fa() {
        System.out.println("fa");
    }
}

class Demo {
    private IA a;
    public Demo(IA a){
        this.a = a;
    }
    public void demo(){
        a.fa();
    }
}

// 现在需要更换为外部系统 B 类，但是 与原有系统不兼容
class B {
    public void fb(){
        System.out.println("fb");
    }
}

// 使用适配器类，使得 B 类兼容原有的系统
public class BAdaptor implements IA {
    private B b;
    public BAdaptor(B b){
        this.b = b;
    }
    @Override
    public void fa() {
        b.fb();
    }
}
