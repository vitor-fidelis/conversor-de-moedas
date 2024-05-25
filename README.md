# Conversor de Moedas
![badge conclusao](Badge-Conversor.png)


Este é um projeto de um Conversor de Moedas desenvolvido em Java como parte do desafio proposto pela ONE (Oracle Next Education) na formação de Java para backend developer. O aplicativo permite converter entre diferentes moedas com taxas de câmbio em tempo real obtidas de uma API externa.

## Funcionalidades

- Conversão entre várias moedas, incluindo USD, EUR, GBP, entre outras.
- Taxas de câmbio em tempo real obtidas de uma API.
- Interface de linha de comando (CLI) simples e fácil de usar.

## Como Usar

1. Clone o repositório para o seu ambiente local.
2. Abra o projeto em sua IDE preferida.
3. Execute o aplicativo `Principal.java`.
4. Siga as instruções na tela para converter entre diferentes moedas.

## Exemplo de Uso

```java
// Exemplo de conversão de USD para EUR
double valorEmUSD = 100.0;
String codigoMoedaOrigem = "USD";
String codigoMoedaDestino = "EUR";
double valorConvertido = exchangeRateAPI.converterMoedaParaMoeda(valorEmUSD, codigoMoedaOrigem, codigoMoedaDestino);
System.out.println("Valor convertido: " + valorConvertido + " " + codigoMoedaDestino);
```

## Pré-requisitos

- Java 8 ou superior.
- Conexão com a Internet para obter as taxas de câmbio em tempo real.

## Contribuindo

Se você quiser contribuir com este projeto, por favor, faça um fork e abra uma pull request. Ficaríamos felizes em revisar suas contribuições!

## Licença

Este projeto é licenciado sob a [MIT License](LICENSE).

---

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Perfil-0077B5?logo=linkedin)]([INSIRA_O_SEU_LINKEDIN](https://www.linkedin.com/in/vitorgcfidelis/))

```
