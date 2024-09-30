* El archivo application.properties especifica lo siguiente:

server.port=8080
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/yms
spring.datasource.username=yms_user
spring.datasource.password=yms_clave
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

* Por lo que para hacer correr el aplicativo se requiere configurar el Docker para Mysql:

docker-compose.yml:
version: '3.1'

services:
  db:
    image: mysql:8.0
    container_name: yms_mysql
    restart: unless-stopped
    environment:
      MYSQL_ROOT_PASSWORD: yms_clave_root
      MYSQL_DATABASE: mysql://${MYSQL_HOST:localhost}:3306/yms
      MYSQL_USER: yms_user
      MYSQL_PASSWORD: yms_clave
    ports:
      - "3306:3306"

* Para la imagen de docker se usa el siguente comando:
docker build -t imagen_parcial

docker run -p 8080:8080 imagen_parcial


* Después de esto, se inicia Mysql con el siguiente comando:
docker-compose up -d

* Debido a que es un Spring Boot con gradle, se ejecuta el siguiente comando para que se corra el aplicativo en el localhost:8080:
./gradlew bootRun

