package com.zelon.designpattern.behavioral.observer;

import com.zelon.designpattern.structural.proxy.UserVo;

import java.util.ArrayList;
import java.util.List;

/*
第一版的代码如果只有少数的处理事件，且后续不太可能扩展的话，已经满足需求。
但是如果后续处理逻辑变多，有更多的服务需要处理用户注册事件，则需要创建多个处理类。
因此，使用观察者模式，将处理逻辑抽离出来，让用户注册事件处理类统一处理。
 */
public class UserController2 {
    // 依赖注入
    private UserService userService;
    private List<RegisterHandler> registerHandlers = new ArrayList<>();

    public void allAllHandlers(List<RegisterHandler> registerHandlers){
        this.registerHandlers.addAll(registerHandlers);
    }

    public Long register(UserVo user) {
        // 后续即便增加了别的处理事件，也只需要添加处理类即可，不需要修改内部代码
        Long userId = userService.createUser(user);
        for (RegisterHandler registerHandler : registerHandlers) {
            registerHandler.handleRegisterSuccess(userId);
        }
        return userId;
    }
}

interface RegisterHandler{
    void handleRegisterSuccess(long userId);
}

class PromotionRegisterHandler implements RegisterHandler{
    @Override
    public void handleRegisterSuccess(long userId) {
        System.out.println("用户注册成功，发送优惠券");
    }
}

class NotificationRegisterHandler implements RegisterHandler{
    @Override
    public void handleRegisterSuccess(long userId) {
        System.out.println("用户注册成功，发送欢迎信");
    }
}