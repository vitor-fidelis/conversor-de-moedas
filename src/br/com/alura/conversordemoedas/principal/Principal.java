package br.com.alura.conversordemoedas.principal;

import br.com.alura.conversordemoedas.service.ExchangeRateAPI;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Principal {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        ExchangeRateAPI exchangeRateAPI = new ExchangeRateAPI();

        while (true) {
            try {
                exibirMenu();
                int opcaoMenu = SCANNER.nextInt();
                SCANNER.nextLine(); // Consumir o caractere "\n"

                switch (opcaoMenu) {
                    case 1:
                        converterMoeda(exchangeRateAPI);
                        break;
                    case 2:
                        listarTaxasDeCambio(exchangeRateAPI);
                        System.out.println();
                        break;
                    case 3:
                        sair(); // Chamando o método sair()
                        break;
                    default:
                        System.err.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Digite um número inteiro.");
                SCANNER.nextLine(); // Limpar entrada incorreta
            } catch (IOException | InterruptedException e) {
                System.err.println("Erro ao acessar a API. Tente novamente mais tarde.");
                e.printStackTrace();
            }
        }
    }

    private static void sair() {
        System.out.println("Obrigado e volte sempre que precisar fazer os cálculos aqui encontrados!!!");
        System.exit(0);
    }

    private static void exibirMenu() {
        System.out.println("\n\n### Conversor de Moedas ###\n");
        System.out.println("1. Converter moeda");
        System.out.println("2. Listar todas as taxas de câmbio");
        System.out.println("3. Sair");
        System.out.print("Digite sua opção: ");
    }

    private static void converterMoeda(ExchangeRateAPI exchangeRateAPI) throws IOException, InterruptedException {
        System.out.print("Digite o valor na moeda de origem: ");
        double valor = SCANNER.nextDouble();
        SCANNER.nextLine(); // Consumir o caractere "\n"

        System.out.print("Digite o código da moeda de origem: ");
        String codigoMoedaOrigem = SCANNER.nextLine().toUpperCase();

        System.out.print("Digite o código da moeda de destino: ");
        String codigoMoedaDestino = SCANNER.nextLine().toUpperCase();

        double valorConvertido = exchangeRateAPI.converterMoedaParaMoeda(valor, codigoMoedaOrigem, codigoMoedaDestino);
        System.out.printf("Valor convertido: %.2f %s\n", valorConvertido, codigoMoedaDestino);
    }

    private static void listarTaxasDeCambio(ExchangeRateAPI exchangeRateAPI) {
        try {
            Map<String, Double> taxasDeCambio = exchangeRateAPI.obterTaxasDeCambio();
            System.out.println("\nTaxas de câmbio disponíveis:");
            taxasDeCambio.forEach((moeda, taxa) -> System.out.printf("%s: %.4f\n", moeda, taxa));
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao acessar a API. Tente novamente mais tarde.");
            e.printStackTrace();
        }
    }
}
