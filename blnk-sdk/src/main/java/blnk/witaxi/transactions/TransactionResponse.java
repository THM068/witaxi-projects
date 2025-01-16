package blnk.witaxi.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.math.BigDecimal;
import java.util.Map;

@Serdeable
@RecordBuilder
public record TransactionResponse(
    BigDecimal amount,
    Integer rate,
    Integer precision,
    @JsonProperty("precise_amount") long preciseAmount,
    @JsonProperty("transaction_id") String transactionId,
    @JsonProperty("parent_transaction") String parentTransaction,
    String source,
    String destination,
    String reference,
    String currency,
    String description,
    String status,
    String hash,
    @JsonProperty("allow_overdraft") boolean allowOverdraft,
    Boolean inflight,
    @JsonProperty("created_at") String createdAt,
    @JsonProperty("scheduled_for") String scheduledFor,
    @JsonProperty("inflight_expiry_date") String inflightExpiryDate,
    @JsonProperty("meta_data") Map<String, String> metaData)
   {}