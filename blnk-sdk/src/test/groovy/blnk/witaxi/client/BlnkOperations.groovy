package blnk.witaxi.client

import blnk.witaxi.balances.CreateBalanceRequest
import blnk.witaxi.balances.CreateBalanceResponse
import blnk.witaxi.balances.GetBalanceResponse
import blnk.witaxi.ledger.CreateLedgerRequest
import blnk.witaxi.ledger.LedgerResponse
import blnk.witaxi.ledger.Ledger_Meta
import io.micronaut.http.HttpResponse
import net.datafaker.Faker

trait BlnkOperations {
     final Faker faker = new Faker();
     LedgerResponse createLedger() {
        String ledgerName = String.format("Ledger for %s", faker.name().fullName())
        String projectOwner = faker.name().firstName()
        Ledger_Meta ledger_meta = new Ledger_Meta(projectOwner)
        CreateLedgerRequest ledgerRequest = new CreateLedgerRequest(ledgerName, ledger_meta)
        HttpResponse<LedgerResponse> ledgerResponse = blnkClient.createLedger(ledgerRequest).block()
        return ledgerResponse.body()
    }

    CreateBalanceResponse createBalance(String ledgerId, String firstName = faker.name().firstName(), String accountNumber = "1234567890") {
        CreateBalanceRequest balanceRequest = new CreateBalanceRequest(ledgerId, "USD", null, ["first_name": firstName, "account_number": accountNumber])
        HttpResponse<CreateBalanceResponse> balanceResponse = blnkClient.createBalance(balanceRequest).block()
        return balanceResponse.body()
    }

}