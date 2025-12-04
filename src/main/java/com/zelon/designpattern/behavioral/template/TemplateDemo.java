package com.zelon.designpattern.behavioral.template;

/*
模板方法模式
一、使用场景
一个方法中的业务逻辑有固定的步骤，而其中某些步骤的具体实现可能不同，可以留给用户自行扩展
此时就可以使用模板方法模式，解决代码复用和框架拓展问题。
Java中的 AbstractList 中的 allAll() 方法就是一个模板方法, 里面规定了添加所有元素的步骤：
必须通过一个个add()的方式来添加，add() 要求子类自己实现

二、实现方式
模板方法设置固定的实现步骤，不能重写，各个步骤设置为抽象方法，要求子类重写。
在实际的应用场景中，可能不会完全按照上面的写法，尤其在 Java中只能实现单继承，继承的类只能有一个，
有时候模板方法还需要继承其他的类，此时就无法通过继承的方式实现模板方法。

因此，在很多框架中，使用的是将可变的具体步骤以回调函数的方式注入，用组合来扩展模板方法中的具体步骤。
Spring 中提供的各种 template（JDBC Template、RedisTemplate）就用到了模板方法的思想（复用+扩展）
例如 一般情况下，使用JDBC需要加载驱动，创建连接，创建Statement，执行SQL，处理结果，关闭Statement，关闭连接，
很多操作是重复且与业务无关的，JDBCTemplate 就将 JDBC 进行封装，其中提前定义了各种步骤，
我们只需要编写与业务有关的代码（SQL + 查询结果与PO对象的映射关系）
 */
public abstract class TemplateDemo {
    public final void templateMethod() {
        // 模板方法不能重写，必须按照既定的步骤执行，但是具体的步骤可以由之类自行扩展
        primitiveOperation1();
        primitiveOperation2();
    }
    // 具体实现步骤子类必须重写
    protected abstract void primitiveOperation1();
    protected abstract void primitiveOperation2();
}

class ConcreteClassA extends TemplateDemo {
    @Override
    protected void primitiveOperation1() {
        // todo: 子类实现具体的步骤1
    }

    @Override
    protected void primitiveOperation2() {
        // todo: 子类实现具体的步骤2
    }
}

class ConcreteClassB extends TemplateDemo {
    @Override
    protected void primitiveOperation1() {
        // todo: 子类实现具体的步骤1
    }

    @Override
    protected void primitiveOperation2() {
        // todo: 子类实现具体的步骤2
    }
}