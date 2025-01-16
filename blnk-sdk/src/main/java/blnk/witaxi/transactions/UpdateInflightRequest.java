package blnk.witaxi.transactions;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record UpdateInflightRequest(String status) {
}
