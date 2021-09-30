package Zcart;

import java.util.List;
public class Customer {
    private String email;
    private String password;
    private String name;
    private long mobile;
    private List<String> changePassword;
    private List<Invoice> invoiceList;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public void setChangePassword(List<String> pass) {
        this.changePassword=pass;
    }

    public List<String> getChangePassword() {
        return changePassword;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @Override
    public String toString() {
        return
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", mobile=" + mobile;
    }
}
