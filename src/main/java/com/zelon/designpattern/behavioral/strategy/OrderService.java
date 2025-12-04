package com.zelon.designpattern.behavioral.strategy;

/*
策略模式
一、使用场景
在程序的运行过程中，需要根据参数决定接下来使用哪些算法/业务处理逻辑，
会引入大量的if...else判断逻辑，此时可以使用策略模式将策略的定义、创建、使用分离，
控制代码的复杂度，同时代码易于扩展，符合开闭原则。

二、实现方式
1. 策略定义
2. 策略创建
3. 策略使用
 */

import java.util.HashMap;
import java.util.Map;

public class OrderService {
//    // 没有使用策略模式之前
//    public double discount(Order order){
//        String type = order.getType();
//        double price = 0;
//        if("vip".equals(type)){
//            // todo: 会员价格
//        }else if("normal".equals(type)){
//            // todo: 普通价格
//        }else{
//            //...
//        }
//        return price;
//    }

    // 使用策略模式之后
    public double discount(Order order){
        String type = order.getType();
        DiscountStrategy strategy = DiscountStrategyFactory.getDiscountStrategy(type);
        return strategy.calculate(order);
    }
}
// 定义策略
interface DiscountStrategy{
    double calculate(Order order);
}

class VipDiscountStrategy implements DiscountStrategy{
    @Override
    public double calculate(Order order) {
        return order.getAmount() * 0.9;
    }
}

class NormalDiscountStrategy implements DiscountStrategy{
    @Override
    public double calculate(Order order) {
        return order.getAmount();
    }
}

// 创建策略
// 如果策略是没有状态的，那么可以使用缓存的方式实现策略工厂，
// 否则，少量的策略可以通过简单工厂实现，即便会出现少量的if-else也可以接受，
// 但如果策略状态量多，那么就得使用工厂方法模式
class DiscountStrategyFactory{
    private static final Map<String, DiscountStrategy> cachedStrategies = new HashMap<>();
    static {
        cachedStrategies.put("vip", new VipDiscountStrategy());
        cachedStrategies.put("normal", new NormalDiscountStrategy());
    }
    public static DiscountStrategy getDiscountStrategy(String type){
        return cachedStrategies.get(type);
    }
}

class Order {
    private String type;
    private double amount;
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    // ...
}

