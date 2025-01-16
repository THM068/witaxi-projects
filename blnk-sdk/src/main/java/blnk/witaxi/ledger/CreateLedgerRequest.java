package blnk.witaxi.ledger;

import blnk.witaxi.request.BaseRequest;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
@Serdeable
public record CreateLedgerRequest(String name, Ledger_Meta meta_data) implements BaseRequest {

    @Override
    public String path() {
        return "/ledgers";
    }
}
