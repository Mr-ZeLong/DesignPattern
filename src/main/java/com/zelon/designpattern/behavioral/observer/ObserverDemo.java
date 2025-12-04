package com.zelon.designpattern.behavioral.observer;

import java.util.HashSet;
import java.util.Set;

/*
观察者模式
一、使用场景
当一个对象的改变会影响多个对象，并且这些对象是动态的，则使用观察者模式，
类似邮件、微信公众号，当主题发布新消息时，会通知所有订阅者。
使用模板模式可以将观察者和被观察者的行为进行解耦，提升代码的扩展性。

二、实现方式
1. 同步阻塞
2. 异步调用
 */
// 这是一个同步阻塞的实现方式，消息队列则是典型的异步调用方式
public class ObserverDemo {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserverA());
        subject.registerObserver(new ConcreteObserverB());
        subject.notifyObservers(new Message());
    }
}

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Message message);
}

class ConcreteSubject implements Subject{
    private Set<Observer> observers = new HashSet<>();
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Message message) {
        // todo: 通知所有观察者
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

interface Observer{
    void update(Message message);
}

class ConcreteObserverA implements Observer{
    @Override
    public void update(Message message) {
        // todo: 处理消息
    }
}

class ConcreteObserverB implements Observer{
    @Override
    public void update(Message message) {
        // todo: 处理消息
    }
}
class Message {

}