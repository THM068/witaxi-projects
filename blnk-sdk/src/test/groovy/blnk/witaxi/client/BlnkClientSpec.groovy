package blnk.witaxi.client
import blnk.witaxi.ledger.GetLedgerRequest
import blnk.witaxi.ledger.CreateLedgerRequest
import blnk.witaxi.ledger.Ledger_Meta
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

    def "BlnkClient can create a Ledger"() {
        given:

            String ledgerName = String.format("Ledger for %s",faker.name().fullName())
            String projectOwner = faker.name().firstName()
            Ledger_Meta ledger_meta = new Ledger_Meta(projectOwner)
            CreateLedgerRequest ledgerRequest = new CreateLedgerRequest(ledgerName, ledger_meta)
        expect:
            StepVerifier.create(blnkClient.createLedger(ledgerRequest))
        .expectNextMatches(ledgerResponse -> {
            ledgerResponse.ledger_id() != null  &&
            ledgerResponse.name() == ledgerName &&
            ledgerResponse.meta_data().project_owner() == ledger_meta.project_owner() &&
            ledgerResponse.created_at() != null
        }).verifyComplete()


//            ledgerResponse != null
//            ledgerResponse.ledger_id() != null
//            ledgerResponse.name() == ledgerName
//            ledgerResponse.meta_data().project_owner() == ledger_meta.project_owner()
//            ledgerResponse.created_at() != null

//        when:
//            def getLedger = blnkClient.getLedger(new GetLedgerRequest(ledgerResponse.ledger_id()))
//        then:
//            getLedger.ledger_id() == ledgerResponse.ledger_id()
//            getLedger.name() == ledgerName
//            getLedger.meta_data().project_owner() == ledgerResponse.meta_data().project_owner()
//            getLedger.created_at() != ledgerResponse.created_at()
    }
}
