package com.zelon.designpattern.behavioral.responsibilitychain;

/*
责任链模式
一、使用场景
当我们需要灵活地对一个数据进行多步骤的处理时，如果直接用 if-else 写死，代码的扩展性差，违反开闭原则，
随着处理步骤增多，整个类会变得臃肿，此时就可以使用责任链模式，将所有的处理步骤转化为一条处理链路，
当需要添加/删除处理步骤时，不需要修改原来的类.
Spring Interceptor、Dubbo Filter 就是使用责任链模式实现的。框架使用者不需要修改源代码就可以
扩展新的拦截器/过滤器。

二、实现方式
1. 继承 + 链表存储处理器
2. 接口 + 数组/列表存储处理器
注意：GOF中责任链模式是只要有一个处理器处理了，就不往下处理，但是实际开发中，存在一些场景需要全链路处理。
 */

public class HandlerChain {
    private Handler head;
    private Handler tail;
    public void addHandler(Handler handler){
        if(head == null) {
            head = handler;
            tail = handler;
            return;
        }
        tail.setSuccessor(handler);
        tail = handler;
    }

    public void handle(){
        if(head != null){
            head.handle();
        }
    }

    public static void main(String[] args) {
        HandlerChain handlerChain = new HandlerChain();
        handlerChain.addHandler(new HandlerA());
        handlerChain.addHandler(new HandlerB());
    }
}

abstract class Handler{
    protected Handler successor;

    public void setSuccessor(Handler successor){
        this.successor = successor;
    }

    // 设计成模板方法的模式，子类就不需要每次自己手动调用下一个处理器了
    // 而且手动调用容易忘记，导致 bug ，这里直接把步骤写死
    public final void handle(){
        if(!doHandle()){
            successor.handle();
        }
    }

    abstract boolean doHandle();
}

class HandlerA extends Handler{
    @Override
    boolean doHandle() {
        boolean isHandled = false;
        // 处理逻辑...
        return isHandled;
    }
}

class HandlerB extends Handler{
    @Override
    boolean doHandle() {
        boolean isHandled = false;
        // 处理逻辑...
        return isHandled;
    }
}