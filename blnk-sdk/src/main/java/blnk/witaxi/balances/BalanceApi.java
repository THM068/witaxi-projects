package blnk.witaxi.balances;

import io.micronaut.http.HttpResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.util.function.BiConsumer;

public interface BalanceApi {

    Mono<HttpResponse<CreateBalanceResponse>> createBalance(CreateBalanceRequest createBalanceRequest);
    Mono<HttpResponse<GetBalanceResponse>> getBalance(String balance_id);

    default BiConsumer<HttpResponse<CreateBalanceResponse>, SynchronousSink<HttpResponse<CreateBalanceResponse>>> createBalanceHandler() {
        return (input, sink) -> {
            if (input.getStatus().getCode() == 201) {
                System.out.println("create balance Response: " + input.status());
                sink.next(input);
            }
            else {
                sink.error(new RuntimeException("Balance Bad Request"));
            }
        };
    }

    default BiConsumer<HttpResponse<GetBalanceResponse>, SynchronousSink<HttpResponse<GetBalanceResponse>>> getBalanceHandler() {
        return (input, sink) -> {
            if (input.getStatus().getCode() == 200) {
                System.out.println("Get balance Response: " + input.status());
                sink.next(input);
            }
            else {
                System.out.println("bad balance Response: " + input.status());
                sink.error(new RuntimeException("Balance Bad Request"));
            }
        };
    }

}
