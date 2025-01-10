package blnk.witaxi.ledger;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.Map;
@Serdeable
public record LedgerRequest(String name, Ledger_Meta meta_data) {
}
