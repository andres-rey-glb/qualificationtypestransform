QualificationType Transformation Microservice
=============================================

The service is implemented in Java using Spring and Spring Cloud
The implementation is listening to a rabbitmq queue to make a
transformation and persist the QualificationType object from Gemfire to Oracle DB.


Technologies
------------

- Spring Data
- Spring Boot
- Spring Cloud
- RabbitMQ


How To Compile
--------------

The microservice can be compiled with:

```
gradle clean build
```

How To Run
----------

The microservice can be started with:

```
gradle bootRun
```



How To Test
----------

The microservice can be tested with:

```
gradle test
```



