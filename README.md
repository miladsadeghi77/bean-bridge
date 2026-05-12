# Mono Mapper

Mono Mapper is a lightweight and extensible Java object mapping framework designed to simplify object-to-object transformations with clean architecture and strategy-based mapping.

It supports multiple mapping scenarios including:

- Bean → Bean
- Bean → Record
- Record → Bean
- Record → Record

The framework is designed with flexibility, scalability, and clean code principles in mind, making it easy to extend and integrate into modern Java applications.

Repository: https://github.com/miladsadeghi77/Mono-Mapper

---

# Features

- Strategy-based architecture
- Support for Java Records
- Reflection-powered mapping
- Extensible mapper system
- Generic type-safe API
- Clean and modular structure
- Easy integration into any Java project

---

# Supported Mapping Types

| Source Type | Target Type |
|---|---|
| Bean | Bean |
| Bean | Record |
| Record | Bean |
| Record | Record |

---

# Project Structure

```text
mono-mapper
├── core
│   ├── mapper
│   ├── strategy
│   ├── reflection
│   └── exception
├── examples
└── tests
```

---

# Installation

## Maven

```xml
<dependency>
    <groupId>com.mono.mapper</groupId>
    <artifactId>mono-mapper</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Gradle

```gradle
implementation 'com.mono.mapper:mono-mapper:1.0.0'
```

---

# Quick Start

## Example Classes

```java
public class UserEntity {

    private String firstName;
    private String lastName;

    // getters and setters
}
```

```java
public record UserDto(
        String firstName,
        String lastName
) {}
```

---

## Basic Mapping

```java
MonoMapper mapper = new MonoMapper();

UserEntity entity = new UserEntity();
entity.setFirstName("Milad");
entity.setLastName("Sadeghi");

UserDto dto = mapper.map(entity, UserDto.class);

System.out.println(dto.firstName());
```

---

# Architecture

Mono Mapper uses a strategy pattern internally.

Each mapping type has its own implementation strategy:

```text
MapperStrategy
 ├── BeanToBeanStrategy
 ├── BeanToRecordStrategy
 ├── RecordToBeanStrategy
 └── RecordToRecordStrategy
```

The framework automatically detects the appropriate strategy at runtime.

---

# Example Strategy Registration

```java
Map<MappingType, MapperStrategy<?, ?>> strategies = new HashMap<>();

strategies.put(MappingType.BEAN_TO_BEAN, new BeanToBeanStrategy());
strategies.put(MappingType.BEAN_TO_RECORD, new BeanToRecordStrategy());
strategies.put(MappingType.RECORD_TO_BEAN, new RecordToBeanStrategy());
strategies.put(MappingType.RECORD_TO_RECORD, new RecordToRecordStrategy());
```

---

# Validation Support

Mono Mapper can be extended with custom validations such as:

- `@NotNull`
- `@Pattern`
- Required field validation
- Custom constraints

Example:

```java
@Pattern(regexp = "^09\\d{9}$")
private String phoneNumber;
```

---

# Future Improvements

- Nested object mapping
- Collection mapping
- Custom field converters
- Annotation-based configuration
- Compile-time optimization
- Spring Boot starter
- Caching reflection metadata
- Performance benchmarks

---

# Why Mono Mapper?

Many developers use mapping frameworks to reduce boilerplate code and simplify DTO transformations. Mono Mapper focuses on:

- Simplicity
- Transparency
- Extensibility
- Clean architecture
- Java Record support

Unlike heavy or magic-based mappers, Mono Mapper aims to stay understandable and easy to debug.

---

# Contributing

Contributions, issues, and feature requests are welcome.

1. Fork the repository
2. Create your feature branch

```bash
git checkout -b feature/amazing-feature
```

3. Commit your changes

```bash
git commit -m "Add amazing feature"
```

4. Push to the branch

```bash
git push origin feature/amazing-feature
```

5. Open a Pull Request

---

# License

This project is licensed under the MIT License.

---

# Author

Created by **Milad Sadeghi**

GitHub: https://github.com/miladsadeghi77
