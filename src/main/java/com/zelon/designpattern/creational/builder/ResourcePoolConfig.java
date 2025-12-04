package com.zelon.designpattern.creational.builder;

/*
建造者模式
一、使用场景
一般情况下，通过 构造器+set() 创建对象，构造器中包含必要参数（参数具有相关性，需要统一校验），set() 设置非必要参数，
但是有时候会出现的情况，此时就需要用到建造者模式
1. 必要参数过多，就会影响代码的可读性与易用性
2. 要求对象具有不变性，创建对象过后不允许更改参数, 不能通过 set() 更改变量

二、实现方式
1. 构建 Builder 静态内部类 , Builder类 包含创建对象所需要的参数以及对应的 set(),
所有的 set() 方法都要返回 Builder 对象，方便链式调用。
2. 创建 Builder 对象，参数通过 Builder 类的 set() 方法设置, 所有参数设置完后，
通过 build() 方法检验参数，并创建所需对象。

三、与工厂模式的区别？
工厂模式主要是根据配置参数动态创建相似类型（实现相同接口）的对象，
建造者模式主要是根据参数配置创建不同参数、类型相同的对象。
 */
public class ResourcePoolConfig {
    private String name;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;

    public static void main(String[] args) {
        // 建造者模式创建对象
        ResourcePoolConfig config = new Builder()
                .setName("dbconnectionpool")
                .setMaxTotal(16)
                .setMaxIdle(8)
                .setMinIdle(2)
                .build();
    }

    private ResourcePoolConfig(Builder builder){
        this.name = builder.name;
        this.maxTotal = builder.maxTotal;
        this.maxIdle = builder.maxIdle;
        this.minIdle = builder.minIdle;
    }

    public static class Builder{
        // 非必要选项使用静态常量设置默认值
        private static final int DEFAULT_MAX_TOTAL = 8;
        private static final int DEFAULT_MAX_IDLE = 8;
        private static final int DEFAULT_MIN_IDLE = 0;

        private String name;
        private int maxTotal = DEFAULT_MAX_TOTAL;
        private int maxIdle = DEFAULT_MAX_IDLE;
        private int minIdle = DEFAULT_MIN_IDLE;

        public Builder(){}

        public ResourcePoolConfig build() {
            /*
            检验:
            1. 必要变量是否填写
            2. 变量之间是否合乎逻辑
             */
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("name can not be blank or null");
            }

            if (minIdle > maxTotal) {
                throw new IllegalArgumentException("minIdle must be less than maxTotal");
            }
            if (maxIdle > maxTotal) {
                throw new IllegalArgumentException("maxIdle must be less than maxTotal");
            }
            return new ResourcePoolConfig(this);
        }

        public Builder setName(String name){
            if(name == null || name.isBlank()){
                throw new IllegalArgumentException("name can not be blank or null");
            }
            this.name = name;
            return this;
        }

        public Builder setMaxTotal(int maxTotal){
            if(maxTotal <= 0) {
                throw new IllegalArgumentException("maxTotal must be positive");
            }
            this.maxTotal = maxTotal;
            return this;
        }

        public Builder setMaxIdle(int maxIdle){
            if(maxIdle <= 0){
                throw new IllegalArgumentException("maxIdle must be positive");
            }
            this.maxIdle = maxIdle;
            return this;
        }

        public Builder setMinIdle(int minIdle){
            if(minIdle < 0){
                throw new IllegalArgumentException("minIdle must be non-negative");
            }
            this.minIdle = minIdle;
            return this;
        }
    }


}
