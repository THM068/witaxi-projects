package blnk.witaxi.ledger;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public interface LedgerApi {

    Publisher<LedgerResponse> createLedger(CreateLedgerRequest ledgerRequest);

    Publisher<LedgerResponse> getLedger(GetLedgerRequest getLedgerRequest);

}
