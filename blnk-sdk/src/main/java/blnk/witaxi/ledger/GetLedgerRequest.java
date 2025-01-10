package blnk.witaxi.ledger;

public record GetLedgerRequest(String ledger_id) implements blnk.witaxi.request.BaseRequest {

    @Override
    public String path() {
        return String.format("/ledgers/%s", ledger_id);
    }
}
