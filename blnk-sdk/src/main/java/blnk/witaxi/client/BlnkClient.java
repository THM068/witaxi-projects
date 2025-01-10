package blnk.witaxi.client;

import blnk.witaxi.ledger.LedgerApi;
import blnk.witaxi.ledger.LedgerRequest;
import blnk.witaxi.ledger.LedgerResponse;
import io.micronaut.context.annotation.Value;
import io.micronaut.serde.ObjectMapper;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Singleton
public class BlnkClient  implements LedgerApi {
    private final String baseUrl;
    private final ObjectMapper objectMapper;

    public BlnkClient(@Value("${blnk.base.url}") String baseUrl, ObjectMapper objectMapper) {
        this.baseUrl = baseUrl;
        this.objectMapper = objectMapper;
    }

    @Override
    public LedgerResponse createLedger(LedgerRequest ledgerRequest) throws IOException, InterruptedException {
        String requestBody = objectMapper.writeValueAsString(ledgerRequest);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/ledgers", this.baseUrl)))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8)) // Set the body
                .build();
        final HttpResponse<String> response = httpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), LedgerResponse.class);
    }

    private HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }
}
