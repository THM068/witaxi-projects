package blnk.witaxi.client;

import blnk.witaxi.ledger.CreateLedgerRequest;
import blnk.witaxi.ledger.GetLedgerRequest;
import blnk.witaxi.ledger.LedgerApi;
import blnk.witaxi.ledger.LedgerResponse;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

import static io.micronaut.http.HttpHeaders.ACCEPT;

@Singleton
public class BlnkClient  implements LedgerApi {
    private final HttpClient httpClient;

    public BlnkClient(@Client(id = "blnk") HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    @SingleResult
    public Publisher<LedgerResponse> createLedger(CreateLedgerRequest ledgerRequest)  {
        final var uri = UriBuilder.of("/ledgers").build();
        HttpRequest<CreateLedgerRequest> req = HttpRequest.POST(uri, ledgerRequest)
                .header(ACCEPT,  "application/json");
        final Publisher<LedgerResponse> response = httpClient.retrieve(req, Argument.of(LedgerResponse.class));
        return response;
    }

    @Override
    @SingleResult
    public Publisher<LedgerResponse> getLedger(GetLedgerRequest getLedgerRequest) {
        return null;
    }

}
