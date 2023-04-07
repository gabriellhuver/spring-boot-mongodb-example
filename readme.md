# Microserviço Spring Boot com conexão ao MongoDB e testes automatizados

[![Build Status](https://github.com/gabriellhuver/spring-boot-mongodb-example/actions/workflows/maven.yml/badge.svg)](https://github.com/gabriellhuver/spring-boot-mongodb-example/actions/workflows/maven.yml)

Este é um exemplo de microserviço Spring Boot que utiliza o MongoDB para armazenar e recuperar informações, e inclui testes automatizados para garantir o seu funcionamento correto.

## Pré-requisitos

- JDK 20
- Maven
- MongoDB

## Configuração do MongoDB

Este exemplo utiliza uma instância local do MongoDB. Você deve criar um banco de dados chamado `exampledb` e uma coleção chamada `examplecollection`.

## Execução do serviço

Para executar o serviço, basta executar o comando `mvn spring-boot:run` na raiz do projeto.

## Testes automatizados

Os testes automatizados são executados automaticamente durante o processo de build, ou podem ser executados manualmente através do comando `mvn test`.

A cobertura de testes é de 100% das classes do projeto, garantindo que todas as funcionalidades estão funcionando corretamente.

## Funcionalidades do serviço

O serviço permite a criação, leitura, atualização e exclusão de informações armazenadas no MongoDB.

## Documentação da API

A documentação da API está disponível na rota `/swagger-ui.html` após a execução do serviço. Lá você poderá encontrar informações sobre os endpoints disponíveis e como utilizá-los.

## Conclusão

Este exemplo mostra como utilizar o MongoDB em um microserviço Spring Boot, e inclui testes automatizados para garantir a sua correta funcionalidade.
