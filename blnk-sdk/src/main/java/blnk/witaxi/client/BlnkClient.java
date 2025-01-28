package blnk.witaxi.client;

import blnk.witaxi.balances.BalanceApi;
import blnk.witaxi.balances.CreateBalanceRequest;
import blnk.witaxi.balances.CreateBalanceResponse;
import blnk.witaxi.balances.GetBalanceResponse;
import blnk.witaxi.client.exceptions.BlnkClientException;
import blnk.witaxi.client.exceptions.BlnkClientInternalServerException;
import blnk.witaxi.client.exceptions.BlnkClientNotFoundException;
import blnk.witaxi.exceptions.BlnkError;
import blnk.witaxi.ledger.CreateLedgerRequest;
import blnk.witaxi.ledger.GetLedgerRequest;
import blnk.witaxi.ledger.LedgerApi;
import blnk.witaxi.ledger.LedgerResponse;
import blnk.witaxi.transactions.TransactionApi;
import blnk.witaxi.transactions.TransactionRequest;
import blnk.witaxi.transactions.TransactionResponse;
import blnk.witaxi.transactions.UpdateInflightRequest;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

import static io.micronaut.http.HttpHeaders.ACCEPT;

@Singleton
public class BlnkClient  implements LedgerApi, BalanceApi, TransactionApi {
    private final HttpClient httpClient;

    public BlnkClient(@Client(id = "blnk", errorType = BlnkError.class) HttpClient httpClient) {
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
        return response.handle(getLedgerHandler()).onErrorMap(HttpClientResponseException.class, BlnkClient::mapError);
    }

    @Override
    @SingleResult
    public Mono<HttpResponse<CreateBalanceResponse>> createBalance(CreateBalanceRequest createBalanceRequest) {
        final var uri = UriBuilder.of("/balances").build();
        final HttpRequest<CreateBalanceRequest> req = HttpRequest.POST(uri, createBalanceRequest)
                .header(ACCEPT,  "application/json");
        final Mono<HttpResponse<CreateBalanceResponse>> response = Mono.from(httpClient.exchange(req, Argument.of(CreateBalanceResponse.class)));
        return response
                .handle(createBalanceHandler())
                .onErrorMap(HttpClientResponseException.class, BlnkClient::mapError);
    }

    @Override
    public Mono<HttpResponse<GetBalanceResponse>> getBalance(String balance_id) {
        final var uri = UriBuilder.of(String.format("/balances/%s", balance_id)).build();
        final HttpRequest<?> req = HttpRequest.GET(uri).header(ACCEPT,  "application/json");
        final Mono<HttpResponse<GetBalanceResponse>> response = Mono.from(httpClient.exchange(req, Argument.of(GetBalanceResponse.class)));
        return response.handle(getBalanceHandler())
                .onErrorMap(HttpClientResponseException.class, BlnkClient::mapError);
    }

    @Override
    public Mono<HttpResponse<TransactionResponse>> createTransaction(TransactionRequest transactionRequest) {
        final var uri = UriBuilder.of("/transactions").build();
        final HttpRequest<TransactionRequest> req = HttpRequest.POST(uri, transactionRequest)
                .header(ACCEPT,  "application/json");
        final Mono<HttpResponse<TransactionResponse>> response = Mono.from(httpClient.exchange(req, Argument.of(TransactionResponse.class)));
        return response.handle(createTransactionHandler())
                .onErrorMap(HttpClientResponseException.class, BlnkClient::mapError);
    }

    @Override
    public Mono<HttpResponse<TransactionResponse>> updateInflight(String transactionId, UpdateInflightRequest updateInflightRequest) {
        final var uri = UriBuilder.of(String.format("/transactions/inflight/%s", transactionId)).build();
        final HttpRequest<UpdateInflightRequest> req = HttpRequest.PUT(uri, updateInflightRequest);
        final Mono<HttpResponse<TransactionResponse>> response = Mono.from(httpClient.exchange(req, Argument.of(TransactionResponse.class)));
        return response.handle(updateInflightHandler())
                .onErrorMap(HttpClientResponseException.class, BlnkClient::mapError);

    }

    @Override
    public Mono<HttpResponse<TransactionResponse>> refundTransaction(String transactionId) {
        final var uri = UriBuilder.of(String.format("refund-transaction/%s", transactionId)).build();
        final HttpRequest<?> req = HttpRequest.POST(uri, null);
        final Mono<HttpResponse<TransactionResponse>> response = Mono.from(httpClient.exchange(req, Argument.of(TransactionResponse.class)));
        return response.handle(refundTransactionHandler())
                .onErrorMap(HttpClientResponseException.class, BlnkClient::mapError);
    }

    private static Throwable mapError(HttpClientResponseException e) {
        System.out.println("Error: " + e.getMessage());
        System.out.println("Status: " + e.getStatus());
        switch (e.getStatus()) {
            case BAD_REQUEST:
                return new BlnkClientException("Bad request");
            case UNAUTHORIZED:
                return new BlnkClientException("Unauthorized");
            case NOT_FOUND:
                return new BlnkClientNotFoundException("Not found");
            case INTERNAL_SERVER_ERROR:
                return new BlnkClientInternalServerException("Internal server error");
            default:
                return new BlnkClientException("Unknown error");
        }
    }
}
