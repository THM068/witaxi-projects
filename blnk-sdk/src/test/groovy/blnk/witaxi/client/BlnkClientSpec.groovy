package blnk.witaxi.client

import blnk.witaxi.balances.CreateBalanceRequest
import blnk.witaxi.ledger.GetLedgerRequest
import blnk.witaxi.ledger.CreateLedgerRequest
import blnk.witaxi.ledger.LedgerResponse
import blnk.witaxi.ledger.Ledger_Meta
import groovy.transform.CompileStatic
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import net.datafaker.Faker
import reactor.test.StepVerifier
import spock.lang.Specification

@MicronautTest(environments = ["test"])
class BlnkClientSpec extends Specification {

    @Inject
    BlnkClient blnkClient
    private final Faker faker = new Faker();

    def "BlnkClient can create a Ledger using stepverifier"() {
        given:

            String ledgerName = String.format("Ledger for %s",faker.name().fullName())
            String projectOwner = faker.name().firstName()
            Ledger_Meta ledger_meta = new Ledger_Meta(projectOwner)
            CreateLedgerRequest ledgerRequest = new CreateLedgerRequest(ledgerName, ledger_meta)
        expect:
            StepVerifier.create(blnkClient.createLedger(ledgerRequest))
            .expectNextMatches(ledgerResponse -> {
                LedgerResponse result = ledgerResponse.body()
                result.ledger_id() != null  &&
                result.name() == ledgerName &&
                result.meta_data().project_owner() == ledger_meta.project_owner() &&
                result.created_at() != null
            }).verifyComplete()
    }

    def "creating a ledger and balance transaction"() {
        given:
            String ledgerName = String.format("Ledger for %s",faker.name().fullName())
            String projectOwner = faker.name().firstName()
            Ledger_Meta ledger_meta = new Ledger_Meta(projectOwner)
            CreateLedgerRequest ledgerRequest = new CreateLedgerRequest(ledgerName, ledger_meta)
        when:
            def ledgerResponse = blnkClient.createLedger(ledgerRequest).block()
        then:
            ledgerResponse != null
            LedgerResponse createResultLedger = ledgerResponse.body()
            createResultLedger.ledger_id() != null
            createResultLedger.name() == ledgerName
            createResultLedger.meta_data().project_owner() == ledger_meta.project_owner()

        when:
            def getLedger = blnkClient.getLedger(new GetLedgerRequest(createResultLedger.ledger_id())).block()
        then:
            def getLedgerResult = getLedger.body()
            getLedgerResult.ledger_id() == createResultLedger.ledger_id()
            getLedgerResult.name() == ledgerName
            getLedgerResult.meta_data().project_owner() == createResultLedger.meta_data().project_owner()
            getLedgerResult.created_at() != createResultLedger.created_at()

        //create a balance
        when:
            CreateBalanceRequest balanceRequest = new CreateBalanceRequest(createResultLedger.ledger_id(), "USD", null, ["first_name": "Alice", "account_number": "1234567890"])
            def balanceResponse = blnkClient.createBalance(balanceRequest).block()
        then:
            balanceResponse != null
            var createBalanceResponseResult = balanceResponse.body()
            createBalanceResponseResult.balance_id() != null
            createBalanceResponseResult.ledger_id() == createResultLedger.ledger_id()
            createBalanceResponseResult.currency() == "USD"
            createBalanceResponseResult.inflight_balance() == 0
            createBalanceResponseResult.credit_balance() == 0
            createBalanceResponseResult.inflight_credit_balance() == 0
            createBalanceResponseResult.balance_id() != null
            createBalanceResponseResult.identity_id() == ""
            createBalanceResponseResult.meta_data() == ["first_name": "Alice", "account_number": "1234567890"]
        //Get a balance
        when:
           def getbalance = blnkClient.getBalance(createBalanceResponseResult.balance_id()).block()
        then:
            var getBalanceResponseResult = getbalance.body()
            getBalanceResponseResult.balance_id() != null
            getBalanceResponseResult.ledger_id() == createResultLedger.ledger_id()
            getBalanceResponseResult.currency() == "USD"
            getBalanceResponseResult.inflight_balance() == 0
            getBalanceResponseResult.credit_balance() == 0
            getBalanceResponseResult.inflight_credit_balance() == 0
            getBalanceResponseResult.balance_id() != null
            getBalanceResponseResult.identity_id() == ""
            getBalanceResponseResult.meta_data() == ["first_name": "Alice", "account_number": "1234567890"]
    }
}
