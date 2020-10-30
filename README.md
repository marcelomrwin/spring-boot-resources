### Executando redis local
```
docker run --rm --name redis -p 6379:6379 -d redis:5
```

### Executando mysql local
```
docker run --rm --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=peoples -d mysql:latest
```

### Executando RabbitMQ local
```
docker run --rm -d --hostname rabbitmq --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```
### Executando a aplicação
```
mvn clean spring-boot:run
```

Acesso a swagger-ui http://localhost:8080/swagger-ui/

Existem dois modelos para serviços, People e Student.

O People utiliza base de dados MySQL

O Student utiliza base de dados redis

Os serviços providos pelo Controller AMQ realiza persistência nas bases e publica mensagem no AMQ.

A aplicação é tanto produtora de mensagem AMQ quanto consumidora.
