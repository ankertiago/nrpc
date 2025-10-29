package com.nrpc.patterns.singleton;

/**
 * 双重检查锁定单例模式 (Double-Checked Locking Singleton)
 * 
 * 优点：
 * - 延迟加载，只有在需要时才创建实例
 * - 线程安全且性能较好
 * - 只在第一次创建时需要同步，之后直接返回实例
 * 
 * 缺点：
 * - 实现相对复杂
 * - 需要使用 volatile 关键字确保可见性
 */
public class DoubleCheckedLockingSingleton {
    
    // 使用 volatile 确保多线程环境下的可见性
    private static volatile DoubleCheckedLockingSingleton instance;
    
    // 私有构造函数
    private DoubleCheckedLockingSingleton() {
        // 防止反射攻击
        if (instance != null) {
            throw new RuntimeException("不能通过反射创建单例实例");
        }
        System.out.println("DoubleCheckedLockingSingleton 实例被创建");
    }
    
    // 双重检查锁定方法
    public static DoubleCheckedLockingSingleton getInstance() {
        // 第一次检查，避免不必要的同步
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                // 第二次检查，确保线程安全
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
    
    // 示例业务方法
    public void doSomething() {
        System.out.println("DoubleCheckedLockingSingleton 正在执行业务逻辑");
    }
    
    // 防止序列化破坏单例
    private Object readResolve() {
        return instance;
    }
}