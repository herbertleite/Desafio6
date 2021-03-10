# Projeto de API REST  

![](https://github.com/herbertleite/desafio6/blob/master/DiagramaDeClasses.png)

## O projeto consiste em um pseudo ecommerce de games mobile utilizando Java.

## Funcionalidades

- Buscar um usuario por id
- Buscar todos usuarios
- Fazer checkout de usuario
- Adicionar produto no carrinho
- Excluir produto do carrinho
- Listar produtos do carrinho por preço
- Listar produtos do carrinho por score
- Listar produtos do carrinho por ordem alfabetica
- Procurar um produto por nome
- Procurar um produto por preço

## Tecnologias utilizadas e seu papel
● Java 11 - Linguagem utilizada, escolhi por já estar trabalhando com essa versão.
● Maven - É responsável por gerenciar dependências, controlar versão de artefatos, gerar
relatórios de produtividade, garantir execução de testes, manter nível de qualidade do código
dentre outras, escolhi o maven para gerenciar as dependências do projeto pela facilidade e
praticidade.
● Spring boot - O Spring Boot é uma ferramenta que visa facilitar o processo de configuração e
publicação de aplicações que utilizem o ecossistema Spring, escolhi o spring Boot pela
facilidade e produtividade que ele me fornece.
● Spring Boot Starter - E o coração de uma aplicação Spring. Sua função principal é combinar
as várias dependências de um projeto Spring Boot em uma única dependência, retirando-se a
necessidade de configuração de múltiplas dependências no Maven, escolhi para ter o projeto
rodando o mais rápido possível e sem complicação.
● Spring-data-JPA - Tem por objetivo facilitar nosso trabalho com persistência de dados de uma
forma geral, e com ele podemos usar hibernate, eclipse-link entre outros.
● Spring-devtools - A Spring Developer Tools usa cache para melhorar a performance.
● Spring-starter-test - Realizar alguns testes.
● POSTGRESQL - Banco de dados utilizado na aplicação, escolhi pela escalabilidade e flexibilidade


## Executar a aplicação

Primeiro é necessário iniciar seu banco de dados POSTGRESQL. É necessário criar as tabelas do banco. A API faz isso para você se na primeira execução você utilizar a seguinte propriedade spring.jpa.hibernate.ddl-auto=update a base é denominada 'supera' e o banco por padrão é criado desde que o POSTGRES tenha sido inicializado:
O Script do banco de dados encontrasse no resources
### application.properties

```json
spring.jpa.properties.jdbc.lob.non_contextual_creation=true
spring.datasource.url=jdbc:postgresql://localhost:5432/supera
spring.datasource.username=postgres  <-- Usuario do banco de dados
spring.datasource.password=admin   <-- Senha do banco de dados
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=org.postgresql.Driver
```
## Funcionalidade do projeto
Para primeira utilização e necessario inserir o script que se encontra em Resources/bancodados.sql no query tool do banco de dados. Após a inserção do script já terá um usuário no sistema para adicionar um produto no carrinho, para isso utilize o endpoint http://localhost:8081/api/Cart/1
## OBSERVAÇÂO: Caso o sistema não encontre o usuário ele não retornará 500 porque o erro de NullPointException é tratado gerando um 404 objetoNaoEncontradoException.


Pacotes do projeto
● Controller (Tem como objetivo direcionar o fluxo da aplicação mapeando e direcionado as
ações recebida pela camada da apresentação para os respectivos serviços da aplicação)
● Exception (Tratamento adequado das exceções)
● Model (As entidades)
● Repository (Usa a camada de persistência para gravar e recuperar os dados necessários para
persistir e recuperar os objetos no bando de dados)
● Service (Onde trabalhamos as regras de negócio, manipulando as informações vindas via
request e retornam a response para o controller devolver para a aplicação)

## Erros
404: NotFound

## Endpoints

## User

### Get
### http://localhost:8081/api/User
### Busca todos os usuario
### Response
```
[
    {
        "id": 1,
        "name": "Joaozinho"
    }
]
```
### Get
### http://localhost:8081/api/User/id
### Busca um usuario por id
### Response
```
{
    "id": 1,
    "name": "Joaozinho"
}
```

### Get
### http://localhost:8081/api/User/Checkout/id
### Faz checkin do usuário
### Response
```
{
    "subtotal": 197.88,
    "total": 207.88,
    "frete": 10.00
}
```

## Cart

### Post
### http://localhost:8081/api/Cart/id
### Adiciona um produto no carrinho
### Request
```
{
    "id": 201,
    "name": "Call Of Duty Infinite Warfare",
    "price": 49.99,
    "score": 80,
    "image": "call-of-duty-infinite-warfare.png"
}
```
### Response
```
{
    "id": 1,
    "products": [
        {
            "id": 312,
            "name": "Super Mario Odyssey",
            "price": 197.88,
            "score": 100,
            "image": "super-mario-odyssey.png"
        },
        {
            "id": 201,
            "name": "Call Of Duty Infinite Warfare",
            "price": 49.99,
            "score": 80,
            "image": "call-of-duty-infinite-warfare.png"
        }
    ]
}
```
### Delete
### http://localhost:8081/api/Cart/id
### Exclui um produto do carrinho
### Request
```
{
     "id": 201,
     "name": "Call Of Duty Infinite Warfare",
     "price": 49.99,
     "score": 80,
     "image": "call-of-duty-infinite-warfare.png"
}
```
### Response
```
{
    "id": 1,
    "products": [
        {
            "id": 312,
            "name": "Super Mario Odyssey",
            "price": 197.88,
            "score": 100,
            "image": "super-mario-odyssey.png"
        }
    ]
}
```

### Get
### http://localhost:8081/api/Cart/ByPreco/id
### Busca produtos do carrinho por preço
### Response
```
    [
    {
        "id": 201,
        "name": "Call Of Duty Infinite Warfare",
        "price": 49.99,
        "score": 80,
        "image": "call-of-duty-infinite-warfare.png"
    },
    {
        "id": 312,
        "name": "Super Mario Odyssey",
        "price": 197.88,
        "score": 100,
        "image": "super-mario-odyssey.png"
    }
]
```

### Get
### http://localhost:8081/api/Cart/ByScore/id
### Busca produtos do carrinho por score
### Response
```
[
    {
        "id": 201,
        "name": "Call Of Duty Infinite Warfare",
        "price": 49.99,
        "score": 80,
        "image": "call-of-duty-infinite-warfare.png"
    },
    {
        "id": 312,
        "name": "Super Mario Odyssey",
        "price": 197.88,
        "score": 100,
        "image": "super-mario-odyssey.png"
    }
]
```
### Get
### http://localhost:8081/api/Cart/ByName/id
### Busca produtos do carrinho por nome
### Response
```
[
    {
        "id": 201,
        "name": "Call Of Duty Infinite Warfare",
        "price": 49.99,
        "score": 80,
        "image": "call-of-duty-infinite-warfare.png"
    },
    {
        "id": 312,
        "name": "Super Mario Odyssey",
        "price": 197.88,
        "score": 100,
        "image": "super-mario-odyssey.png"
    }
]
```






## Product

### Get
### http://localhost:8081/api/Product/ByName?nome=Duty
### Procura produtos da loja por nome
### Response
```
[
    {
        "id": 201,
        "name": "Call Of Duty Infinite Warfare",
        "price": 49.99,
        "score": 80,
        "image": "call-of-duty-infinite-warfare.png"
    },
    {
        "id": 99,
        "name": "Call Of Duty WWII",
        "price": 249.99,
        "score": 205,
        "image": "call-of-duty-wwii.png"
    }
]
```


### Get
### http://localhost:8081/api/Product/ByPrice?precoInicio=190&precoFim=10000
### Procura produtos da loja por faixa de preço
### Response
```
[
    {
        "id": 312,
        "name": "Super Mario Odyssey",
        "price": 197.88,
        "score": 100,
        "image": "super-mario-odyssey.png"
    },
    {
        "id": 99,
        "name": "Call Of Duty WWII",
        "price": 249.99,
        "score": 205,
        "image": "call-of-duty-wwii.png"
    },
    {
        "id": 420,
        "name": "FIFA 18",
        "price": 195.39,
        "score": 325,
        "image": "fifa-18.png"
    }
]
```
