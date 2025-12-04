package com.zelon.designpattern.structural.proxy;
/*
代理模式
一、使用场景
1. 以非耦合的方式给业务系统增加一些非功能性的需求：监控、日志、鉴权、事务、限流等
2. 隐藏具体操作的实现细节，例如 PRC 远程调用

二、实现方式
1. 静态代理：
① 实现被代理类的接口（首选）；
② 继承被代理类（次选）
两种方式都要重写被代理类的方法，然后在方法中附加增强功能。

2. 动态代理，Java中根据
静态代理如果一个类需要实现多个增强功能，则需要创建多个代理类，每次也都要重写相同的逻辑，增加了开发和维护成本。
因此，Java 提供了动态代理机制，不需要事先创建好每一个代理类，而是通过反射机制，在运行的时候
动态创建代理类，然后在系统中使用代理类替换掉原始类。
*/

// 这种方式会将业务功能和非业务功能耦合在一起，不推荐使用
public class UserController implements IUserController{
    // 依赖注入
    private MetricsCollector metricsCollector;
    @Override
    public UserVo login(String telephone, String password) {
        // todo: 使用 metricsCollector 统计用户登录耗时
        // todo: 登录操作
        return null;
    }

    @Override
    public UserVo register(String telephone, String password) {
        // todo: 使用 metricsCollector 统计用户注册耗时
        // todo: 注册操作
        return null;
    }
}
// 静态代理方式一：实现接口（如果没有就自己加一个接口，让被代理类和代理类都实现相同的接口）
class UserControllerProxy implements IUserController{
    // 依赖注入
    private MetricsCollector metricsCollector;
    private IUserController userController;
    @Override
    public UserVo login(String telephone, String password) {
        // todo: 使用 metricsCollector 统计用户登录耗时
        // todo: 登录操作
        return null;
    }

    @Override
    public UserVo register(String telephone, String password) {
        // todo: 使用 metricsCollector 统计用户登录耗时
        // todo: 登录操作
        return null;
    }
}

// 静态代理方式二：继承被代理类（如果被代理类是外部类，我们没法添加接口，则使用继承的方式）
class UserControllerProxy2 extends UserController{
    // 依赖注入
    private MetricsCollector metricsCollector;
    @Override
    public UserVo login(String telephone, String password) {
        // todo: 使用 metricsCollector 统计用户登录耗时
        return super.login(telephone, password);
    }
    @Override
    public UserVo register(String telephone, String password) {
        // todo: 使用 metricsCollector 统计用户登录耗时
        return super.register(telephone, password);
    }
}