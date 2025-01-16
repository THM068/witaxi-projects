package blnk.witaxi.transactions;

import io.micronaut.http.HttpResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.util.function.BiConsumer;

public interface TransactionApi {

    Mono<HttpResponse<TransactionResponse>> createTransaction(TransactionRequest transactionRequest);

    default BiConsumer<HttpResponse<TransactionResponse>, SynchronousSink<HttpResponse<TransactionResponse>>> createTransactionHandler() {
        return (input, sink) -> {
            if (input.getStatus().getCode() == 201) {
                System.out.println("Successful Transaction: " + input.status());
                sink.next(input);
            }
            else {
                System.out.println("bad transaction Response: " + input.status());
                sink.error(new RuntimeException("transaction Bad Request"));
            }
        };
    }
}