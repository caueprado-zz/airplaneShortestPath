# Rota de Viagem #

Um turista deseja viajar pelo mundo pagando o menor preço possível independentemente do número de conexões necessárias.
Vamos construir um programa que facilite ao nosso turista, escolher a melhor rota para sua viagem.

Para isso precisamos inserir as rotas através de um arquivo de entrada.

## Input Example ##
```csv
GRU,BRC,10
BRC,SCL,5
GRU,CDG,75
GRU,SCL,20
GRU,ORL,56
ORL,CDG,5
SCL,ORL,20
```

## Explicando ## 
Caso desejemos viajar de **GRU** para **CDG** existem as seguintes rotas:

1. GRU - BRC - SCL - ORL - CDG ao custo de **$40**
2. GRU - ORL - CGD ao custo de **$64**
3. GRU - CDG ao custo de **$75**
4. GRU - SCL - ORL - CDG ao custo de **$48**
5. GRU - BRC - CDG ao custo de **$45**

O melhor preço é da rota **4** logo, o output da consulta deve ser **CDG - SCL - ORL - CDG**.

### Execução do programa ###
A inicializacao do teste se dará por linha de comando onde o primeiro argumento é o arquivo com a lista de rotas inicial.

```shell
$ spring-boot:run -Dspring-boot.run.arguments=input-routes.csv
```
- Interface de console deverá receber um input com a rota no formato "DE-PARA" e imprimir a melhor rota e seu respectivo valor.


  Exemplo:
  ```shell
  please enter the connection: GRU-CGD
  best connection: GRU - BRC - SCL - ORL - CDG > $40
  please enter the connection: BRC-CDG
  best connection: BRC - ORL > $30
  ```

- Interface Rest

Exemplo request:

http://localhost:8080/api/route/GRU/CDG

response

```json
{"routes":["GRU","BRC","SCL","ORL","CDG"],"cost":40}
```