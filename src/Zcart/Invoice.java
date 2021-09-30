package Zcart;
import java.util.List;
public class Invoice {
    private long invoiceNo;
    private double totalAmount;
    private List<Inventory> items;

    public long getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(long invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public List<Inventory> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setItems(List<Inventory> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return
                "invoiceNo:" + invoiceNo +
                ", totalAmount=" + totalAmount +
                ", items=" + items ;
    }
}
