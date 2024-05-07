package br.com.alura.conversordemoedas.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ExchangeRateAPI {

    private final String apiKey = "0aa594845c0baa53b871ab0f";
    private final String baseUrl = "https://v6.exchangerate-api.com/v6/";

    public Map<String, Double> obterTaxasDeCambio() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String endpoint = baseUrl + apiKey + "/latest/USD"; // Alterado para obter taxas em relação ao USD
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            String responseBody = response.body();
            return extrairTaxasDeCambio(responseBody);
        } else {
            throw new IOException("Erro ao obter as taxas de câmbio. Código de status: " + response.statusCode());
        }
    }

    private Map<String, Double> extrairTaxasDeCambio(String responseBody) {
        Map<String, Double> taxasDeCambio = new HashMap<>();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        String baseCode = jsonObject.get("base_code").getAsString();
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

        // Iterar sobre as taxas de conversão e adicionar ao mapa
        for (Map.Entry<String, JsonElement> entry : conversionRates.entrySet()) {
            String targetCode = entry.getKey();
            double rate = entry.getValue().getAsDouble();
            taxasDeCambio.put(baseCode + "-" + targetCode, rate);
        }
        return taxasDeCambio;
    }

    public double converterMoedaParaMoeda(double valor, String codigoMoedaOrigem, String codigoMoedaDestino) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String endpoint = baseUrl + apiKey + "/pair/" + codigoMoedaOrigem + "/" + codigoMoedaDestino;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            String responseBody = response.body();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            double conversionRate = jsonObject.get("conversion_rate").getAsDouble();
            return valor * conversionRate;
        } else {
            throw new IOException("Erro ao converter a moeda. Código de status: " + response.statusCode());
        }
    }
}
