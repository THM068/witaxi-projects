package blnk.witaxi.transactions;

public enum TransactionStatus {
    COMMIT("commit"),VOID("void");
    private String status;

    TransactionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        System.out.println("Status: " + status);
        return status;
    }
}
