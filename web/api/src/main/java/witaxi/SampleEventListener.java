package witaxi;

import blnk.witaxi.client.BlnkClient;
import blnk.witaxi.ledger.Ledger_Meta;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

import java.io.IOException;

@Singleton
public class SampleEventListener {

    private final BlnkClient blnkClient;

    public SampleEventListener(BlnkClient blnkClient) {
        this.blnkClient = blnkClient;
    }

    @EventListener
    public void onStartupEvent(StartupEvent event) throws IOException, InterruptedException {
        System.out.println("________________________");
        Ledger_Meta ledger_meta = new Ledger_Meta("projectOwner");
        blnk.witaxi.ledger.CreateLedgerRequest ledgerRequest = new blnk.witaxi.ledger.CreateLedgerRequest("ledgerName", ledger_meta);

        blnkClient.createLedger(ledgerRequest);
    }
}