package mailManagementSystem;
import java.util.List;
public class User {
    private  String userName;
    private String email;
    private String password;
    List<Mail> inboxMail;
    List<Mail> sendMail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public List<Mail> getInboxMail() {
        return inboxMail;
    }

    public void setInboxMail(List<Mail> inboxMail) {
        this.inboxMail = inboxMail;
    }

    public List<Mail> getSendMail() {
        return sendMail;
    }

    public void setSendMail(List<Mail> sendMail) {
        this.sendMail = sendMail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", inboxMail=" + inboxMail +
                ", sendMail=" + sendMail +
                '}';
    }
}
