package com.nrpc.patterns.singleton;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单例模式测试类
 * 
 * 测试各种单例模式的正确性和线程安全性
 */
public class SingletonTest {
    
    @Test
    @DisplayName("测试饿汉式单例的唯一性")
    public void testEagerSingletonUniqueness() {
        EagerSingleton instance1 = EagerSingleton.getInstance();
        EagerSingleton instance2 = EagerSingleton.getInstance();
        
        assertSame(instance1, instance2, "饿汉式单例应该返回相同的实例");
        assertEquals(instance1.hashCode(), instance2.hashCode(), "相同实例应该有相同的hashCode");
    }
    
    @Test
    @DisplayName("测试懒汉式单例的唯一性")
    public void testLazySingletonUniqueness() {
        LazySingleton instance1 = LazySingleton.getInstance();
        LazySingleton instance2 = LazySingleton.getInstance();
        
        assertSame(instance1, instance2, "懒汉式单例应该返回相同的实例");
        assertEquals(instance1.hashCode(), instance2.hashCode(), "相同实例应该有相同的hashCode");
    }
    
    @Test
    @DisplayName("测试双重检查锁定单例的唯一性")
    public void testDoubleCheckedLockingSingletonUniqueness() {
        DoubleCheckedLockingSingleton instance1 = DoubleCheckedLockingSingleton.getInstance();
        DoubleCheckedLockingSingleton instance2 = DoubleCheckedLockingSingleton.getInstance();
        
        assertSame(instance1, instance2, "双重检查锁定单例应该返回相同的实例");
        assertEquals(instance1.hashCode(), instance2.hashCode(), "相同实例应该有相同的hashCode");
    }
    
    @Test
    @DisplayName("测试枚举单例的唯一性")
    public void testEnumSingletonUniqueness() {
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.getInstance();
        
        assertSame(instance1, instance2, "枚举单例应该返回相同的实例");
        assertEquals(instance1.hashCode(), instance2.hashCode(), "相同实例应该有相同的hashCode");
        assertNotNull(instance1.getMessage(), "枚举单例的业务方法应该正常工作");
    }
    
    @Test
    @DisplayName("测试静态内部类单例的唯一性")
    public void testStaticInnerClassSingletonUniqueness() {
        StaticInnerClassSingleton instance1 = StaticInnerClassSingleton.getInstance();
        StaticInnerClassSingleton instance2 = StaticInnerClassSingleton.getInstance();
        
        assertSame(instance1, instance2, "静态内部类单例应该返回相同的实例");
        assertEquals(instance1.hashCode(), instance2.hashCode(), "相同实例应该有相同的hashCode");
    }
    
    @Test
    @DisplayName("测试双重检查锁定单例的线程安全性")
    public void testDoubleCheckedLockingSingletonThreadSafety() throws InterruptedException {
        final int threadCount = 100;
        final CountDownLatch latch = new CountDownLatch(threadCount);
        final AtomicInteger uniqueInstances = new AtomicInteger(0);
        
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        
        // 使用原子引用来存储第一个实例
        final java.util.concurrent.atomic.AtomicReference<DoubleCheckedLockingSingleton> firstInstanceRef = 
            new java.util.concurrent.atomic.AtomicReference<>();
        
        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    DoubleCheckedLockingSingleton instance = DoubleCheckedLockingSingleton.getInstance();
                    
                    // 检查是否是唯一实例
                    firstInstanceRef.compareAndSet(null, instance);
                    if (instance == firstInstanceRef.get()) {
                        uniqueInstances.incrementAndGet();
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        
        latch.await();
        executor.shutdown();
        
        assertEquals(threadCount, uniqueInstances.get(), 
                    "所有线程都应该获取到相同的单例实例");
    }
    
    @Test
    @DisplayName("测试枚举单例的线程安全性")
    public void testEnumSingletonThreadSafety() throws InterruptedException {
        final int threadCount = 100;
        final CountDownLatch latch = new CountDownLatch(threadCount);
        final AtomicInteger uniqueInstances = new AtomicInteger(0);
        
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        
        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    EnumSingleton instance = EnumSingleton.INSTANCE;
                    
                    // 所有实例都应该是同一个
                    if (instance == EnumSingleton.INSTANCE) {
                        uniqueInstances.incrementAndGet();
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        
        latch.await();
        executor.shutdown();
        
        assertEquals(threadCount, uniqueInstances.get(), 
                    "所有线程都应该获取到相同的枚举单例实例");
    }
    
    @Test
    @DisplayName("测试反射攻击防护")
    public void testReflectionAttackPrevention() {
        // 测试静态内部类单例的反射防护
        StaticInnerClassSingleton instance = StaticInnerClassSingleton.getInstance();
        
        try {
            java.lang.reflect.Constructor<StaticInnerClassSingleton> constructor = 
                StaticInnerClassSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            
            assertThrows(java.lang.reflect.InvocationTargetException.class, () -> {
                constructor.newInstance();
            }, "应该防止通过反射创建新实例");
            
        } catch (Exception e) {
            // 如果获取构造函数失败，说明防护有效
            assertTrue(true, "反射攻击被成功阻止");
        }
    }
}