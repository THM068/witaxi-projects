package blnk.witaxi.ledger;

import io.micronaut.http.annotation.Post;

import java.io.IOException;

public interface LedgerApi {
    LedgerResponse createLedger(LedgerRequest ledgerRequest) throws IOException, InterruptedException;

}
