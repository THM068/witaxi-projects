package blnk.witaxi.ledger;

import io.micronaut.serde.annotation.Serdeable;
@Serdeable
public record LedgerRequest(String name, Ledger_Meta meta_data) {
}
