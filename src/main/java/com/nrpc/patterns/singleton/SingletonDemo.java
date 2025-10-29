package com.nrpc.patterns.singleton;

/**
 * 单例模式演示类
 * 
 * 这个类展示了如何使用不同类型的单例模式
 * 以及它们的特点和使用场景
 */
public class SingletonDemo {
    
    public static void main(String[] args) {
        System.out.println("=== 单例模式演示 ===\n");
        
        // 1. 饿汉式单例演示
        System.out.println("1. 饿汉式单例模式:");
        demonstrateEagerSingleton();
        
        // 2. 懒汉式单例演示
        System.out.println("\n2. 懒汉式单例模式:");
        demonstrateLazySingleton();
        
        // 3. 双重检查锁定单例演示
        System.out.println("\n3. 双重检查锁定单例模式:");
        demonstrateDoubleCheckedLockingSingleton();
        
        // 4. 枚举单例演示
        System.out.println("\n4. 枚举单例模式:");
        demonstrateEnumSingleton();
        
        // 5. 静态内部类单例演示
        System.out.println("\n5. 静态内部类单例模式:");
        demonstrateStaticInnerClassSingleton();
        
        // 6. 多线程测试
        System.out.println("\n6. 多线程安全性测试:");
        testThreadSafety();
    }
    
    private static void demonstrateEagerSingleton() {
        EagerSingleton singleton1 = EagerSingleton.getInstance();
        EagerSingleton singleton2 = EagerSingleton.getInstance();
        
        System.out.println("实例1和实例2是否相同: " + (singleton1 == singleton2));
        System.out.println("实例1 hashCode: " + singleton1.hashCode());
        System.out.println("实例2 hashCode: " + singleton2.hashCode());
        singleton1.doSomething();
    }
    
    private static void demonstrateLazySingleton() {
        LazySingleton singleton1 = LazySingleton.getInstance();
        LazySingleton singleton2 = LazySingleton.getInstance();
        
        System.out.println("实例1和实例2是否相同: " + (singleton1 == singleton2));
        System.out.println("实例1 hashCode: " + singleton1.hashCode());
        System.out.println("实例2 hashCode: " + singleton2.hashCode());
        singleton1.doSomething();
    }
    
    private static void demonstrateDoubleCheckedLockingSingleton() {
        DoubleCheckedLockingSingleton singleton1 = DoubleCheckedLockingSingleton.getInstance();
        DoubleCheckedLockingSingleton singleton2 = DoubleCheckedLockingSingleton.getInstance();
        
        System.out.println("实例1和实例2是否相同: " + (singleton1 == singleton2));
        System.out.println("实例1 hashCode: " + singleton1.hashCode());
        System.out.println("实例2 hashCode: " + singleton2.hashCode());
        singleton1.doSomething();
    }
    
    private static void demonstrateEnumSingleton() {
        EnumSingleton singleton1 = EnumSingleton.INSTANCE;
        EnumSingleton singleton2 = EnumSingleton.getInstance();
        
        System.out.println("实例1和实例2是否相同: " + (singleton1 == singleton2));
        System.out.println("实例1 hashCode: " + singleton1.hashCode());
        System.out.println("实例2 hashCode: " + singleton2.hashCode());
        System.out.println("枚举单例消息: " + singleton1.getMessage());
        singleton1.doSomething();
    }
    
    private static void demonstrateStaticInnerClassSingleton() {
        StaticInnerClassSingleton singleton1 = StaticInnerClassSingleton.getInstance();
        StaticInnerClassSingleton singleton2 = StaticInnerClassSingleton.getInstance();
        
        System.out.println("实例1和实例2是否相同: " + (singleton1 == singleton2));
        System.out.println("实例1 hashCode: " + singleton1.hashCode());
        System.out.println("实例2 hashCode: " + singleton2.hashCode());
        singleton1.doSomething();
    }
    
    private static void testThreadSafety() {
        System.out.println("正在测试多线程环境下的单例安全性...");
        
        // 测试双重检查锁定单例的线程安全性
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                DoubleCheckedLockingSingleton instance = DoubleCheckedLockingSingleton.getInstance();
                System.out.println("线程 " + Thread.currentThread().getName() + 
                                 " 获取到实例: " + instance.hashCode());
            }, "Thread-" + i).start();
        }
        
        // 等待一下让线程执行完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}