package RailWayReservationSystem;
public class Passenger {
    private String name;
    private int Age;
    private String gender;
    private String berthPreference;
    private  int seatNo;
    private String confirmationStatus;
    private int ticketId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBerthPreference() {
        return berthPreference;
    }

    public void setBerthPreference(String berthPreference) {
        this.berthPreference = berthPreference;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public String getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(String confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {

        String str="";
        str+="Passenger name:"+name+'\t'+"Age:"+Age+'\t'+"Gender:"+gender+'\t'+"Berth preference:"+berthPreference+'\t'+"seat No:"+seatNo+'\t'+"confirmationStatus"+confirmationStatus;
        /*return "Passenger{" +
                "name='" + name + '\'' +
                ", Age=" + Age +
                ", gender='" + gender + '\'' +
                ", berthPreference='" + berthPreference + '\'' +
                ", seatNo=" + seatNo +
                ", confirmationStatus='" + confirmationStatus + '\'' +
                '}';*/
        return str;
    }
}
