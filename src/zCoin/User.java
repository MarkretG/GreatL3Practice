package zCoin;
public class User {
    private String name;
    private String Password;
    private String email;
    private long mobilNo;
    private long h_Id;
    private double rc;
    private long z_Id;
    private double zc;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobilNo() {
        return mobilNo;
    }

    public void setMobilNo(long mobilNo) {
        this.mobilNo = mobilNo;
    }

    public long getH_Id() {
        return h_Id;
    }

    public void setH_Id(long h_Id) {
        this.h_Id = h_Id;
    }

    public double getRc() {
        return rc;
    }

    public void setRc(double rc) {
        this.rc = rc;
    }

    public long getZ_Id() {
        return z_Id;
    }

    public double getZc() {
        return zc;
    }

    public void setZc(double zc) {
        this.zc = zc;
    }

    public void setZ_Id(long z_Id) {
        this.z_Id = z_Id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", Password='" + Password + '\'' +
                ", email='" + email + '\'' +
                ", mobilNo=" + mobilNo +
                ", h_Id=" + h_Id +
                ", rc=" + rc +
                ", z_Id=" + z_Id +
                '}';
    }
}
