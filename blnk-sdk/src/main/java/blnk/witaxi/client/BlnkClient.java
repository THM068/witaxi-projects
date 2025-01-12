package blnk.witaxi.client;

import blnk.witaxi.balances.BalanceApi;
import blnk.witaxi.balances.CreateBalanceRequest;
import blnk.witaxi.balances.CreateBalanceResponse;
import blnk.witaxi.balances.GetBalanceResponse;
import blnk.witaxi.ledger.CreateLedgerRequest;
import blnk.witaxi.ledger.GetLedgerRequest;
import blnk.witaxi.ledger.LedgerApi;
import blnk.witaxi.ledger.LedgerResponse;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

import static io.micronaut.http.HttpHeaders.ACCEPT;

@Singleton
public class BlnkClient  implements LedgerApi, BalanceApi {
    private final HttpClient httpClient;

    public BlnkClient(@Client(id = "blnk") HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    @SingleResult
    public Mono<HttpResponse<LedgerResponse>> createLedger(CreateLedgerRequest ledgerRequest)  {
        final var uri = UriBuilder.of("/ledgers").build();
        final HttpRequest<CreateLedgerRequest> req = HttpRequest.POST(uri, ledgerRequest)
                .header(ACCEPT,  "application/json");
        final Mono<HttpResponse<LedgerResponse>> response = Mono.from(httpClient.exchange(req, Argument.of(LedgerResponse.class)));
        return response.handle(createLedgerHandler());
    }

    @Override
    @SingleResult
    public Mono<HttpResponse<LedgerResponse>> getLedger(GetLedgerRequest getLedgerRequest) {
        final var uri = UriBuilder.of(String.format("/ledgers/%s", getLedgerRequest.ledger_id())).build();
        final HttpRequest<?> req = HttpRequest.GET(uri).header(ACCEPT,  "application/json");
        final Mono<HttpResponse<LedgerResponse>> response = Mono.from(httpClient.exchange(req, Argument.of(LedgerResponse.class)));
        return response.handle(getLedgerHandler());
    }

    @Override
    @SingleResult
    public Mono<HttpResponse<CreateBalanceResponse>> createBalance(CreateBalanceRequest createBalanceRequest) {
        final var uri = UriBuilder.of("/balances").build();
        final HttpRequest<CreateBalanceRequest> req = HttpRequest.POST(uri, createBalanceRequest)
                .header(ACCEPT,  "application/json");
        final Mono<HttpResponse<CreateBalanceResponse>> response = Mono.from(httpClient.exchange(req, Argument.of(CreateBalanceResponse.class)));
        return response.handle(createBalanceHandler());
    }

    @Override
    public Mono<HttpResponse<GetBalanceResponse>> getBalance(String balance_id) {
        final var uri = UriBuilder.of(String.format("/balances/%s", balance_id)).build();
        final HttpRequest<?> req = HttpRequest.GET(uri).header(ACCEPT,  "application/json");
        final Mono<HttpResponse<GetBalanceResponse>> response = Mono.from(httpClient.exchange(req, Argument.of(GetBalanceResponse.class)));
        return response.handle(getBalanceHandler());
    }
}
