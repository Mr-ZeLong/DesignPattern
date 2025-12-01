package singleton;


import java.util.concurrent.atomic.AtomicInteger;

/*
单例模式
一、注意点：
1. 私有化构造函数
2. 是否延迟加载
3. 是否多线程安全
4. getInstance()性能

二、实现方式：
1. 饿汉式：没有线程安全问题，性能高，适用于
使用频繁（没有资源浪费问题）、初始化时间长（避免了延迟加载带来的超时响应问题）的单例对象。
2. 懒汉式：双重检测模式线程安全且性能较高, 但是实现会稍微复杂，需要加锁,
同时要注意使用volatile避免半初始化问题。
 */
// 饿汉式
public class IdGenerator {
    private AtomicInteger id = new AtomicInteger(0);
    private static IdGenerator instance = new IdGenerator();
    private IdGenerator(){}

    public static IdGenerator getInstance(){
        return instance;
    }

    public int getId(){
        return id.incrementAndGet();
    }
}

// 懒汉式
class IdGenerator2{
    private static volatile IdGenerator2 instance;
    private AtomicInteger id = new AtomicInteger(0);
    private IdGenerator2(){}

//    public static synchronized IdGenerator2 getInstance(){
//        // 这种方式虽然线程安全，但是读写都加锁，性能非常差，一半不用
//        if(instance == null) {
//            instance = new IdGenerator2();
//        }
//        return instance;
//    }

    public static IdGenerator2 getInstance(){
        // 只有刚初始化的时候需要加锁，后续都不需要加锁，读性能高
        if(instance == null){
            synchronized (IdGenerator2.class){
                if(instance == null) {
                    instance = new IdGenerator2();
                }
            }
        }
        return instance;
    }

}
