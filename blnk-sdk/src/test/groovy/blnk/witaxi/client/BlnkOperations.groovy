package blnk.witaxi.client

import blnk.witaxi.ledger.CreateLedgerRequest
import blnk.witaxi.ledger.LedgerResponse
import blnk.witaxi.ledger.Ledger_Meta
import io.micronaut.http.HttpResponse

trait BlnkOperations {

     LedgerResponse createLedger() {
        String ledgerName = String.format("Ledger for %s", faker.name().fullName())
        String projectOwner = faker.name().firstName()
        Ledger_Meta ledger_meta = new Ledger_Meta(projectOwner)
        CreateLedgerRequest ledgerRequest = new CreateLedgerRequest(ledgerName, ledger_meta)
        HttpResponse<LedgerResponse> ledgerResponse = blnkClient.createLedger(ledgerRequest).block()
        return ledgerResponse.body()
    }

}