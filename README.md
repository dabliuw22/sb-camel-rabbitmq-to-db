# RabbitMQ to DB - Spring Boot and Apache Camel

En este proyecto crearemos un consumidor para RabbitMQ con *Apache Camel* y realizaremos inserciones en H2 DB.

1. Descargar e instalar RabbitMQ.

2. Habilitar plugin *rabbitmq_management*:
```
$ rabbitmq-plugins enable rabbitmq_management
``` 
3. Abrir rabbitmq management: *http://localhost:15672*.

4. Crear exchange *myExchange* de tipo *topic*.

5. Crear queue *myQueue* con auto-delete deshabilitado.

6. Agregar un mensaje a la queue *myQueue*:
```
{"id": 1, "name": "One", "username": "one"}
```
7. Correr el Proyecto y verificar que el registro fue insertado en la DB, para eso utilizaremos la consola de H2:
	* *http://localhost:8080/h2-console*