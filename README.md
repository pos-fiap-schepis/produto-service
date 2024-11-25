# produto-service

O Produto Service é um serviço responsável por gerenciar produtos em um sistema de fast food. Ele permite criar, atualizar, listar e excluir prodotos.


### Requisitos
Java 17
Maven 3.6+
Docker
Docker Compose

## Configuração

### Banco de Dados
O serviço utiliza um banco de dados mongodb. 
A configuração do banco de dados está definida no arquivo docker-compose.yaml.

### Docker Compose
Para iniciar o serviço e o banco de dados, execute o comando:
```sh
docker-compose up
```

### Maven
Para compilar e executar o serviço localmente, execute os seguintes comandos:
```sh
mvn clean install
mvn spring-boot:run

## Testes

### Testes Unitários
Para executar os testes unitários, utilize o comando:
```sh
mvn test
```

### Testes BDD com Cucumber
Para executar os testes BDD com Cucumber, utilize o comando:
```sh
mvn test
```