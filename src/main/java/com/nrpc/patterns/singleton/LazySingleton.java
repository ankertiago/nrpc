package com.nrpc.patterns.singleton;

/**
 * 懒汉式单例模式 (Lazy Initialization Singleton)
 * 
 * 优点：
 * - 延迟加载，只有在需要时才创建实例
 * - 节省资源，避免不必要的实例创建
 * 
 * 缺点：
 * - 需要考虑线程安全问题
 * - 性能较差，每次获取实例都需要同步
 */
public class LazySingleton {
    
    private static LazySingleton instance;
    
    // 私有构造函数
    private LazySingleton() {
        // 防止反射攻击
        if (instance != null) {
            throw new RuntimeException("不能通过反射创建单例实例");
        }
        System.out.println("LazySingleton 实例被创建");
    }
    
    // 线程安全的懒加载方法
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
    
    // 示例业务方法
    public void doSomething() {
        System.out.println("LazySingleton 正在执行业务逻辑");
    }
    
    // 防止序列化破坏单例
    private Object readResolve() {
        return instance;
    }
}