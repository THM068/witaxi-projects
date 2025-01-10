package blnk.witaxi.ledger;


import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record LedgerResponse(String ledger_id, String name, String created_at, Ledger_Meta meta_data) {


}
