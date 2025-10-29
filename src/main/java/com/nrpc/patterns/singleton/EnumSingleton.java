package com.nrpc.patterns.singleton;

/**
 * 枚举单例模式 (Enum Singleton)
 * 
 * 优点：
 * - 线程安全，JVM 保证枚举实例的唯一性
 * - 防止反射攻击
 * - 防止序列化破坏单例
 * - 实现简洁，代码少
 * - 延迟加载（在第一次使用时才会被加载）
 * 
 * 缺点：
 * - 枚举类型在某些情况下可能不够灵活
 * - 不能继承其他类
 * 
 * 推荐使用：这是实现单例模式的最佳方式
 */
public enum EnumSingleton {
    
    INSTANCE;
    
    // 构造函数（枚举的构造函数默认是私有的）
    EnumSingleton() {
        System.out.println("EnumSingleton 实例被创建");
    }
    
    // 示例业务方法
    public void doSomething() {
        System.out.println("EnumSingleton 正在执行业务逻辑");
    }
    
    // 可以添加其他业务方法
    public String getMessage() {
        return "这是来自枚举单例的消息";
    }
    
    // 静态方法获取实例（可选，因为可以直接使用 EnumSingleton.INSTANCE）
    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}