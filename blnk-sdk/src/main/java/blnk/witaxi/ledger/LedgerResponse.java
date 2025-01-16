package blnk.witaxi.ledger;


import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

@Serdeable
@RecordBuilder
public record LedgerResponse(String ledger_id, String name, String created_at, Ledger_Meta meta_data) {


}
