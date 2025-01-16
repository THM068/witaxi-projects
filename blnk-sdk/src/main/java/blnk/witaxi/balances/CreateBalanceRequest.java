package blnk.witaxi.balances;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.Map;

@RecordBuilder
@Serdeable
public record CreateBalanceRequest(String ledger_id, String currency,  @JsonInclude(JsonInclude.Include.NON_NULL) String identity_id, @JsonInclude(JsonInclude.Include.NON_NULL)Map<String, String> meta_data) {
}
