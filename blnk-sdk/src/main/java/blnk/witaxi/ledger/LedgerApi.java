package blnk.witaxi.ledger;

import java.io.IOException;

public interface LedgerApi {
    LedgerResponse createLedger(CreateLedgerRequest ledgerRequest) throws IOException, InterruptedException;

    LedgerResponse getLedger(GetLedgerRequest getLedgerRequest) throws IOException, InterruptedException;

}
