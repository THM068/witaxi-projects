package blnk.witaxi.transactions;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.math.BigDecimal;
import java.util.Map;

@RecordBuilder
@Serdeable
public record TransactionRequest(
        BigDecimal amount,
        String currency,
        Integer precision,
        String reference,
        String source,
        String destination,
        @JsonInclude(JsonInclude.Include.NON_NULL) String description,
//        @JsonInclude(JsonInclude.Include.NON_NULL) String scheduled_for,
        @JsonInclude(JsonInclude.Include.NON_NULL) Boolean allow_overdraft,
//        @JsonInclude(JsonInclude.Include.NON_NULL) Boolean inflight,
//        @JsonInclude(JsonInclude.Include.NON_NULL) String inflight_expiry_date,
//        @JsonInclude(JsonInclude.Include.NON_NULL) Integer rate,
        @JsonInclude(JsonInclude.Include.NON_NULL) Map<String, String> meta_data) {

}
