package blnk.witaxi.ledger;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Ledger_Meta(String project_owner) {
}
