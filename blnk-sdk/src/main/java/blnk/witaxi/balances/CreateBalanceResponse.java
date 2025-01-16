package blnk.witaxi.balances;

import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.math.BigDecimal;
import java.util.Map;

@Serdeable
@RecordBuilder
public record CreateBalanceResponse(
        BigDecimal balance,
        BigDecimal inflight_balance,
        BigDecimal credit_balance,
        BigDecimal inflight_credit_balance,
        BigDecimal debit_balance,
        BigDecimal inflight_debit_balance,
        String ledger_id,
        String identity_id,
        String balance_id,
        String currency,
        String created_at,
        Map<String, String> meta_data
) {
}


