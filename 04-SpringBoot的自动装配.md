# SpringBoot自动装配原理

[toc]

# 1.SpringBoot自动装配简介

Spring Boot 自动装配（Auto-Configuration）是 Spring Boot 框架的核心特性之一，它通过`约定优于配置`的理念，根据类路径中的 jar 包、类以及其他条件，自动配置 Spring 应用程序上下文中的 Bean。

对比传统的Spring配置：


| 特性       | 传统 Spring             | Spring Boot 自动装配 |
| ---------- | ----------------------- | -------------------- |
| 配置方式   | 手动 XML 或 Java Config | 自动 + 按需配置      |
| 依赖管理   | 手动管理版本            | 起步依赖（Starter）  |
| 配置复杂度 | 高                      | 低                   |
| 开发效率   | 较低                    | 极高                 |

核心设计理念：

```mermaid
graph LR
    A[应用启动] --> B{扫描类路径}
    B --> C[检测依赖]
    C --> D{判断条件}
    D -->|条件满足| E[自动配置 Bean]
    D -->|条件不满足| F[跳过配置]
    E --> G[注册到容器]
    F --> H[等待其他配置]
    G --> I[应用就绪]
    H --> I
```

# 2.自动装配的核心原理

Spring Boot 应用启动时，通过以下步骤触发自动装配：

1. @SpringBootApplication 注解组合了 @EnableAutoConfiguration
2. AutoConfigurationImportSelector 读取 spring.factories
3. 条件注解 判断是否满足配置条件
4. Bean 定义注册 到 Spring 容器

核心机制:

```mermaid
flowchart 
    subgraph 核心机制
        EnableAutoConfiguration["@EnableAutoConfiguration"] --> AutoConfigurationImportSelector[AutoConfigurationImportSelector]
        AutoConfigurationImportSelector["AutoConfigurationImportSelector"] --> SpringFactories[spring.factories]
        SpringFactories --> Conditional["条件注解 @Conditional"]
    end
    subgraph 执行流程
        D[启动类] --> EnableAutoConfiguration
        SpringFactories --> E[加载配置类]
        Conditional --> F[条件判断]
        F --> G[注册 Bean]
    end
```

完整流程图:

```mermaid
sequenceDiagram
    participant App as 启动类
    participant SA as SpringApplication
    participant ACIS as AutoConfigurationImportSelector
    participant SF as SpringFactoriesLoader
    participant CC as ConfigurationClassParser
    participant Container as Spring容器
  
    App->>SA: run()
    SA->>SA: refreshContext()
    SA->>SA: invokeBeanFactoryPostProcessors()
    SA->>ACIS: selectImports()
    ACIS->>SF: loadFactoryNames()
    SF-->>ACIS: 返回配置类列表
    ACIS->>ACIS: 过滤(exclude/duplicate)
    ACIS->>CC: 解析配置类
    CC->>CC: 处理@Conditional
    CC->>Container: 注册Bean定义
    Container-->>App: 上下文刷新完成   
```

# 3.源码解读

1. @SpringBootApplication 组合注解

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration // 关键注解
@ComponentScan(excludeFilters = {@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {}
```

2. @EnableAutoConfiguration 详解

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class) // 关键Import
public @interface EnableAutoConfiguration {
	String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";
	Class<?>[] exclude() default {};
	String[] excludeName() default {};
}
```

## 3.1.AutoConfigurationImportSelector解读

+ selectImports方法

```java
	@Override
	public String[] selectImports(AnnotationMetadata annotationMetadata) {
        // 1. 检查是否启用自动配置
		if (!isEnabled(annotationMetadata)) {
			return NO_IMPORTS;
		}
      
        // 2. 获取自动配置条目，加载spring.factories以及对应的Configuration配置类
		AutoConfigurationMetadata autoConfigurationMetadata 
                = AutoConfigurationMetadataLoader.loadMetadata(this.beanClassLoader);
		AutoConfigurationEntry autoConfigurationEntry 
                = getAutoConfigurationEntry(autoConfigurationMetadata, annotationMetadata);

        // 3. 返回配置类数组
		return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
	}
```

+ getAutoConfigurationEntry方法

```java
protected AutoConfigurationEntry getAutoConfigurationEntry(AutoConfigurationMetadata autoConfigurationMetadata,
        AnnotationMetadata annotationMetadata) {
    if (!isEnabled(annotationMetadata)) {
        return EMPTY_ENTRY;
    }
    AnnotationAttributes attributes = getAttributes(annotationMetadata);
  
    // 1. 获取所有候选配置类，会通过SpringFactoriesLoader去加载spring.factories
    List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);
  
    // 2.去重和排除
    configurations = removeDuplicates(configurations);
    Set<String> exclusions = getExclusions(annotationMetadata, attributes);
    checkExcludedClasses(configurations, exclusions);
    configurations.removeAll(exclusions);
  
    // 3.过滤（条件判断）
    configurations = filter(configurations, autoConfigurationMetadata);
  
    // 4.触发事件
    fireAutoConfigurationImportEvents(configurations, exclusions);
    return new AutoConfigurationEntry(configurations, exclusions);
}
```

条件和判断：

```mermaid
flowchart LR
    A[候选配置类列表] --> B{遍历配置类}
    B --> C["读取@Conditional注解"]
    C --> D{条件匹配?}
    D -->|是| E[保留配置类]
    D -->|否| F[排除配置类]
    E --> G{还有下一个?}
    F --> G
    G -->|是| B
    G -->|否| H[返回最终列表]
```

# 4.条件注解体系


**Spring Boot 提供了丰富的条件注解，用于控制自动配置的生效条件：**

## 4.1 类路径条件


| **注解**                          | **说明**                          | **示例**                                          |
| --------------------------------- | --------------------------------- | ------------------------------------------------- |
| **@ConditionalOnClass**           | **类路径存在指定类**              | `@ConditionalOnClass(DataSource.class)`           |
| **@ConditionalOnMissingClass**    | **类路径不存在指定类**            | `@ConditionalOnMissingClass(Servlet.class)`       |
| **@ConditionalOnSingleCandidate** | **容器中只有一个指定类型的 Bean** | `@ConditionalOnSingleCandidate(DataSource.class)` |

## 4.2 Bean 条件


| **注解**                      | **说明**                  | **示例**                                          |
| ----------------------------- | ------------------------- | ------------------------------------------------- |
| **@ConditionalOnBean**        | **容器中存在指定 Bean**   | `@ConditionalOnBean(name="dataSource")`           |
| **@ConditionalOnMissingBean** | **容器中不存在指定 Bean** | `@ConditionalOnMissingBean(UserService.class)`    |
| **@ConditionalOnExpression**  | **SpEL 表达式结果**       | `@ConditionalOnExpression("${app.enabled:true}")` |

## 4.3 配置属性条件


| **注解**                   | **说明**                   | **示例**                                                |
| -------------------------- | -------------------------- | ------------------------------------------------------- |
| **@ConditionalOnProperty** | **配置属性存在且满足条件** | `@ConditionalOnProperty(prefix="app", name="enabled")`  |
| **@ConditionalOnResource** | **类路径存在指定资源**     | `@ConditionalOnResource("classpath:config.properties")` |

## 4.4 Web 应用条件


| **注解**                            | **说明**          | **示例**                                            |
| ----------------------------------- | ----------------- | --------------------------------------------------- |
| **@ConditionalOnWebApplication**    | **是 Web 应用**   | `@ConditionalOnWebApplication(type = Type.SERVLET)` |
| **@ConditionalOnNotWebApplication** | **不是 Web 应用** | `@ConditionalOnNotWebApplication`                   |

## 4.5 其他条件


| **注解**                        | **说明**           |
| ------------------------------- | ------------------ |
| **@ConditionalOnJava**          | **Java 版本条件**  |
| **@ConditionalOnWarDeployment** | **War 包部署条件** |
| **@ConditionalOnCloudPlatform** | **云平台条件**     |

# 5.SPI 机制与 spring.factories
Java SPI 机制：
```mermaid
graph LR
    A[Service接口] --> B[实现类1]
    A --> C[实现类2]
    A --> D[实现类3]
    
    B --> E[META-INF/services]
    C --> E
    D --> E
    
    E --> F[ServiceLoader]
    F --> G[加载所有实现]
```
Spring Boot 的扩展 SPI:

Spring Boot 在 Java SPI 基础上进行了扩展，使用 spring.factories 文件
```text
META-INF/spring.factories
META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports (Spring Boot 2.7+)
```
不同版本方式不同，作用还是一样的。
