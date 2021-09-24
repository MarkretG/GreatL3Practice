package zCoin;
public class RCTransaction {
    private String transactionType;
    private double amount;
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "RCTransaction{" +
                "transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
