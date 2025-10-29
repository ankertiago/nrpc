package com.nrpc.patterns.singleton;

/**
 * 饿汉式单例模式 (Eager Initialization Singleton)
 * 
 * 优点：
 * - 线程安全，在类加载时就创建实例
 * - 实现简单，没有线程同步问题
 * - 性能较好，获取实例时没有同步开销
 * 
 * 缺点：
 * - 不管是否使用都会创建实例，可能造成资源浪费
 * - 如果构造函数中有异常，程序启动时就会失败
 */
public class EagerSingleton {
    
    // 在类加载时就创建实例
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    
    // 私有构造函数，防止外部创建实例
    private EagerSingleton() {
        // 防止反射攻击
        if (INSTANCE != null) {
            throw new RuntimeException("不能通过反射创建单例实例");
        }
        System.out.println("EagerSingleton 实例被创建");
    }
    
    // 公共方法获取单例实例
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
    
    // 示例业务方法
    public void doSomething() {
        System.out.println("EagerSingleton 正在执行业务逻辑");
    }
    
    // 防止序列化破坏单例
    private Object readResolve() {
        return INSTANCE;
    }
}