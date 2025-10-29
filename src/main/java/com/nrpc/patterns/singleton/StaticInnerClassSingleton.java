package com.nrpc.patterns.singleton;

/**
 * 静态内部类单例模式 (Static Inner Class Singleton)
 * 
 * 优点：
 * - 延迟加载，只有在调用 getInstance() 时才会加载内部类
 * - 线程安全，JVM 保证类加载过程的线程安全
 * - 性能好，没有同步开销
 * - 实现简洁
 * 
 * 缺点：
 * - 相对复杂一些
 * 
 * 推荐使用：这是实现单例模式的推荐方式之一
 */
public class StaticInnerClassSingleton {
    
    // 私有构造函数
    private StaticInnerClassSingleton() {
        // 防止反射攻击
        if (SingletonHolder.INSTANCE != null) {
            throw new RuntimeException("不能通过反射创建单例实例");
        }
        System.out.println("StaticInnerClassSingleton 实例被创建");
    }
    
    // 静态内部类，只有在第一次使用时才会被加载
    private static class SingletonHolder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }
    
    // 获取单例实例
    public static StaticInnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    // 示例业务方法
    public void doSomething() {
        System.out.println("StaticInnerClassSingleton 正在执行业务逻辑");
    }
    
    // 防止序列化破坏单例
    private Object readResolve() {
        return SingletonHolder.INSTANCE;
    }
}