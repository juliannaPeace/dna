# Processamento de Sequência de DNA (Teste MELI)

A api consiste em processar uma cadeia de DNA e determinar se o DNA informado é de um Simion ou Humano. 

● Só serão aceitas as letras ATGC <br/>
● Você saberá se um DNA pertence a um símio, se encontrar 2 ou mais sequências de quatro letras iguais em qualquer direção, horizontal, vertical ou nas diagonais. <br/>
● Só serão permitidas matrizes quadráticas como payload de entrada para processamento <br/>

### Para mais informações sobre os endpoints disponíveis consulte a documentação através da URL : <br/>
http://35.177.191.253:8080/swagger-ui.html

### Para rodar a aplicação de forma manual: (branch main)

rode o comando:

```sh
$ docker-compose up -d
```

Em seguida gere o build da imagem:

```sh
$ ./mvnw clean install package
```
Navegue até a pasta target e rode o comando:

```sh
$ java -jar dna-0.0.1-SNAPSHOT.jar
```

### Para rodar os testes execute o comando:

```sh
$ ./mvnw test
```

### Para rodar a aplicação usando imagem docker: (branch staging)

Primeiro crie um arquivo chamado docker-compose.override.yml, no mesmo diretório em que se encontra o docker-compose e insira nele as variáveis de ambiente para acesso ao banco de dados

```sh
version: '3.4'
services:
  postgres-compose:
    environment:
      POSTGRES_USER: "POSTGRES_USER"
      POSTGRES_PASSWORD: "POSTGRES_PASSWORD"
      POSTGRES_DB: "POSTGRES_DB"
  api-meli:
    environment:
      JDBC_URL: "jdbc:postgresql://postgres-compose:5432/dna"
      JDBC_USER_NAME: "JDBC_USER_NAME"
      JDBC_PASSWORD: "JDBC_PASSWORD"
```
Em seguida rode o comando:

```sh
$ docker-compose up -d
```

**Tecnologias utilizadas**
- Java 11
- Spring
- Docker
- Swagger
- Lombok
- Maven
- Postgresql
- Flyway
