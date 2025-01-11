package blnk.witaxi.ledger;

import io.micronaut.http.HttpResponse;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.util.function.BiConsumer;

public interface LedgerApi {

    Mono<HttpResponse<LedgerResponse>> createLedger(CreateLedgerRequest ledgerRequest);

    Mono<HttpResponse<LedgerResponse>> getLedger(GetLedgerRequest getLedgerRequest);

    default BiConsumer<HttpResponse<LedgerResponse>, SynchronousSink<HttpResponse<LedgerResponse>>> createLedgerHandler() {
        return (input, sink) -> {
            if (input.getStatus().getCode() >= 400) {
                sink.error(new RuntimeException("Ledger Bad Request"));
            }
            else {
                System.out.println("Ledger Response: " + input.status());
                sink.next(input);
            }
        };
    }

    default BiConsumer<HttpResponse<LedgerResponse>, SynchronousSink<HttpResponse<LedgerResponse>>> getLedgerHandler() {
        return (input, sink) -> {
            if (input.getStatus().getCode() >= 400) {
                sink.error(new RuntimeException("Ledger Bad Request"));
            }
            else {
                System.out.println("Ledger Response: " + input.status());
                sink.next(input);
            }
        };
    }



}
