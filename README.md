# domain-commons

This module provides utility objects for handling domain entities in Java projects. It includes immutable and reusable implementations for pagination, sorting, and result handling, making integration with different frameworks and data sources easier.

## Features

- Pagination objects (`Pagination`)
- Flexible sorting (`Sort`, `Order`)
- Handling of paged results (`Slice`, `Page`)
- Immutable implementations using Java records
- Simple and consistent API for modern Java applications

## Package Structure

All objects are located in the package:

`com.peluware.domain`

## Main Files

- `Pagination.java`: Represents pagination information (page, size).
- `Sort.java` and `Order.java`: Define sorting criteria.
- `Slice.java` and `Page.java`: Model paged results and associated metadata.

## Basic Usage

```java
import com.peluware.domain.DefaultPagination;

// Create a pagination for page 0 and size 20
var pagination = new DefaultPagination(0, 20);
```

## Requirements

- Java 21 or higher
- Maven

## Installation

Add the dependency to your `pom.xml`:

```xml

<dependency>
    <groupId>com.peluware</groupId>
    <artifactId>domain-commons</artifactId>
    <version>1.0.2</version>
</dependency>
```

## License

