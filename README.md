# NRPC - Singleton Pattern Implementation

这是一个全面的Java单例模式实现指南，包含5种不同的单例模式实现方式以及详细的最佳实践说明。

## 项目概述

本项目提供了以下内容：

1. **5种不同的单例模式实现**
   - 饿汉式单例 (EagerSingleton)
   - 懒汉式单例 (LazySingleton)  
   - 双重检查锁定单例 (DoubleCheckedLockingSingleton)
   - 枚举单例 (EnumSingleton) ⭐ 推荐
   - 静态内部类单例 (StaticInnerClassSingleton) ⭐ 推荐

2. **完整的测试用例**
   - 单例唯一性验证
   - 线程安全性测试
   - 反射攻击防护测试

3. **详细的文档和最佳实践**
   - 每种模式的优缺点分析
   - 适用场景说明
   - 性能对比

## 快速开始

### 编译和运行

```bash
# 使用Maven编译项目
mvn clean compile

# 运行演示程序
mvn exec:java

# 运行测试
mvn test
```

### 直接使用Java命令

```bash
# 编译
javac -d target/classes src/main/java/com/nrpc/patterns/singleton/*.java

# 运行演示
java -cp target/classes com.nrpc.patterns.singleton.SingletonDemo
```

## 单例模式说明

### 推荐使用方式

1. **枚举单例** - 最佳选择，安全且简洁
2. **静态内部类单例** - 高性能且支持延迟加载
3. **双重检查锁定单例** - 适用于需要自定义初始化逻辑的场景

### 性能特点

| 实现方式 | 线程安全 | 延迟加载 | 性能 | 推荐度 |
|---------|---------|---------|------|-------|
| 饿汉式 | ✅ | ❌ | 高 | ⭐⭐⭐ |
| 懒汉式 | ✅ | ✅ | 低 | ⭐⭐ |
| 双重检查锁定 | ✅ | ✅ | 高 | ⭐⭐⭐⭐ |
| 枚举 | ✅ | ✅ | 高 | ⭐⭐⭐⭐⭐ |
| 静态内部类 | ✅ | ✅ | 高 | ⭐⭐⭐⭐⭐ |

## 安全性考虑

所有实现都包含了以下安全防护措施：

- **反射攻击防护** - 在构造函数中防止重复实例化
- **序列化攻击防护** - 使用readResolve()方法确保反序列化时的单例性
- **线程安全** - 确保多线程环境下的正确性

## 详细文档

查看 [SINGLETON_GUIDE.md](SINGLETON_GUIDE.md) 了解更详细的实现说明和最佳实践。

## 项目结构

```
src/
├── main/java/com/nrpc/patterns/singleton/
│   ├── EagerSingleton.java                    # 饿汉式单例
│   ├── LazySingleton.java                     # 懒汉式单例
│   ├── DoubleCheckedLockingSingleton.java     # 双重检查锁定单例
│   ├── EnumSingleton.java                     # 枚举单例
│   ├── StaticInnerClassSingleton.java         # 静态内部类单例
│   └── SingletonDemo.java                     # 演示程序
└── test/java/com/nrpc/patterns/singleton/
    └── SingletonTest.java                      # 测试用例
```

## 运行示例输出

```
=== 单例模式演示 ===

1. 饿汉式单例模式:
EagerSingleton 实例被创建
实例1和实例2是否相同: true
实例1 hashCode: 349885916
实例2 hashCode: 349885916
EagerSingleton 正在执行业务逻辑

2. 懒汉式单例模式:
LazySingleton 实例被创建
实例1和实例2是否相同: true
...
```

## 技术栈

- Java 11+
- Maven 3.6+
- JUnit 5 (测试框架)

## 贡献

欢迎提交问题和改进建议！
