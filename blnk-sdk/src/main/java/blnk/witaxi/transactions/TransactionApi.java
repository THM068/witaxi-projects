package blnk.witaxi.transactions;

import io.micronaut.http.HttpResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.util.function.BiConsumer;

public interface TransactionApi {

    Mono<HttpResponse<TransactionResponse>> createTransaction(TransactionRequest transactionRequest);

    Mono<HttpResponse<TransactionResponse>> updateInflight(String transactionId, UpdateInflightRequest updateInflightRequest);

    Mono<HttpResponse<TransactionResponse>> refundTransaction(String transactionId);

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

    default BiConsumer<HttpResponse<TransactionResponse>, SynchronousSink<HttpResponse<TransactionResponse>>> updateInflightHandler() {
        return (input, sink) -> {
            if (input.getStatus().getCode() == 200) {
                System.out.println("Successful update Inflight: " + input.status());
                sink.next(input);
            }
            else {
                System.out.println("bad updateInflight Response: " + input.status());
                sink.error(new RuntimeException("updateInflight Bad Request"));
            }
        };
    }

    default BiConsumer<HttpResponse<TransactionResponse>, SynchronousSink<HttpResponse<TransactionResponse>>> refundTransactionHandler() {
        return (input, sink) -> {
            if (input.getStatus().getCode() == 201) {
                System.out.println("Successful Transaction Refund: " + input.status());
                sink.next(input);
            }
            else {
                System.out.println("bad Refund transaction Response: " + input.status());
                sink.error(new RuntimeException("Refund transaction Bad Request"));
            }
        };
    }
}