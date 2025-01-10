package blnk.witaxi.client

import blnk.witaxi.ledger.LedgerRequest
import blnk.witaxi.ledger.LedgerResponse
import blnk.witaxi.ledger.Ledger_Meta
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import net.datafaker.Faker
import spock.lang.Specification

import java.net.http.HttpClient

@MicronautTest(environments = ["test"])
class BlnkClientSpec extends Specification {

    @Inject
    BlnkClient blnkClient
    private final Faker faker = new Faker();

    def "BlnkClient can create a Ledger"() {
        given:

            String ledgerName = String.format("Ledger for %s",faker.name().fullName())
            String projectOwner = faker.name().firstName()
            Ledger_Meta ledger_meta = new Ledger_Meta(projectOwner)
            LedgerRequest ledgerRequest = new LedgerRequest(ledgerName, ledger_meta)
        when:
            def ledgerResponse = blnkClient.createLedger(ledgerRequest)
        then:

            ledgerResponse != null
            ledgerResponse.ledger_id() != null
            ledgerResponse.name() == ledgerName
            ledgerResponse.meta_data().project_owner() == ledger_meta.project_owner()
            ledgerResponse.created_at() != null
    }
}
