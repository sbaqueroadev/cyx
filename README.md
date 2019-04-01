# Test

This project shows the implementation of a RESTful application developed with Java (Spring) and React.js

## Getting Started

After running the application go to

```
http://localhost:8081/Cyxtera/
```

To look the Data Rest services go to:

```
http://localhost:8081/Cyxtera/api
```

### Prerequisites

1.	Download mongo:

```
https://www.mongodb.com/download-center#production
```

2.	Install Mongo:

```
https://docs.mongodb.com/manual/installation/#mongodb-community-edition
```

3.	Start mongo: See link above ( run mongod in terminal)

4.	Download and install java 8

```
http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
```

### Installing

Run in terminal

create a database in mongo:

* first enter to mongo terminal service:

```
mongo
```

* then create the database:

```
use cyxtera
```

```
mvn spring-boot:run
```

## Running the tests

just run in terminal

```
mvn test
```

## Authors

* **Sergio Baquero Ariza**

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
