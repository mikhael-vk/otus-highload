## Запуск контейнеров

для сборки проекта: `..$ docker compose build`  
для запуска проект: `..$ docker compose up -d`  
Проект будет запущен на порту 8080  
Swagger-UI доступен по адресу [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Генерация public - private ключей для jwt

```shell
..$ openssl genrsa -out private_key.pem 2048
..$ openssl pkcs8 -topk8 -inform PEM -outform DER -in private_key.pem -out private_key.der -nocrypt
..$ openssl rsa -in private_key.pem -pubout -outform DER -out public_key.der
```

