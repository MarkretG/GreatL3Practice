package RailWayReservationSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class RailwayReservationSystem {
    int confirmedTicketsCount=63;
    int racTicketsCount=18;
    int waitingTicketsCount=10;
    int seatNo=1;
    int ticketId=1;
    Berth berth=new Berth();
    Cache cache=new Cache();
    RailwayReservationSystem()
    {
        berth.setLower(18);
        berth.setUpper(18);
        berth.setMidUpper(18);
        berth.setSideLower(9);
        berth.setSideUpper(9);
    }
    public Passenger getPassengerObject(String name,int age,String gender,String berthPreference)
    {
        Passenger passenger=new Passenger();
        passenger.setName(name);
        passenger.setAge(age);
        passenger.setGender(gender);
        passenger.setBerthPreference(berthPreference);
        return passenger;
    }
    public List<String> bookingTickets(List<Passenger> passengers)
    {
        int count=0;
        List<String> bookingStatus=new ArrayList<>();
        List<Passenger> confirmedTickets=new ArrayList<>();
        List<Passenger> racTickets=new ArrayList<>();
        List<Passenger> waitingTickets=new ArrayList<>();
        for (Passenger passenger:passengers)
        {
            if(confirmedTicketsCount>0)
            {
                allocateBirth(passenger);
                passenger.setConfirmationStatus("confirmed tickets");
                passenger.setTicketId(ticketId);
                passenger.setSeatNo(seatNo++);
                confirmedTickets.add(passenger);
                bookingStatus.add("booking successfully"+""+passenger.getName());
                confirmedTicketsCount--;
                count++;
            }
            else if(racTicketsCount>0)
            {
                passenger.setBerthPreference("side lower");
                passenger.setConfirmationStatus("Rac");
                passenger.setTicketId(ticketId);
                racTickets.add(passenger);
                bookingStatus.add("booking successfully"+""+passenger.getName());
                racTicketsCount--;
                count++;
            }
            else if(waitingTicketsCount>0){
                passenger.setConfirmationStatus("waiting list");
                waitingTickets.add(passenger);
                passenger.setTicketId(ticketId);
                bookingStatus.add("booking successfully"+""+passenger.getName());
                waitingTicketsCount--;
                count++;
            }
            else {
                passenger.setConfirmationStatus("rejected");
                bookingStatus.add("booking rejected"+""+passenger.getName());
            }
        }
        cache.addConfirmedTicketsInMap(ticketId,confirmedTickets);
        cache.addRacTickets(racTickets);
        cache.addWaitingTickets(waitingTickets);
        if(count>0) {
            ticketId++;
        }
        return bookingStatus;
    }
    public String cancelTicket(int ticketId,int seatNo)
    {
        Map<Integer,Map<Integer,Passenger>> map=getConfirmedTicketsHistory();
        Map<Integer,Passenger> innerMap=map.get(ticketId);
        if(innerMap!=null) {
            innerMap.remove(seatNo);
            if(racTicketsCount>0)
            {
                Map<Integer,List<Passenger>> rac=getRacTicketsHistory();
            }
            if(waitingTicketsCount>0)
            {
                Map<Integer,List<Passenger>> wait=getWaitingTicketsHistory();
            }
        }
        else {
            return "ticket id not available cancellation failed";
        }

        confirmedTicketsCount++;
    }
    public Map<Integer,Map<Integer,Passenger>> getConfirmedTicketsHistory()
    {
        return cache.getConfirmedTicketsMap();
    }

    public Map<Integer,List<Passenger>> getRacTicketsHistory()
    {
        return cache.getRacTicketsMap();
    }
    public Map<Integer,List<Passenger>> getWaitingTicketsHistory()
    {
        return cache.getRacTicketsMap();
    }
    private void allocateBirth(Passenger passenger)
    {
        if(passenger.getBerthPreference().equals("lower")&&berth.getLower()>0)
        {
            berth.setLower(berth.getLower()-1);
        }
        else if(passenger.getBerthPreference().equals("upper")&&berth.getLower()>0)
        {
            berth.setUpper(berth.getUpper()-1);
        }
        else if(passenger.getBerthPreference().equals("mid upper")&&berth.getLower()>0)
        {
            berth.setMidUpper(berth.getMidUpper()-1);
        }
        else if(passenger.getBerthPreference().equals("side upper")&&berth.getLower()>0)
        {
            berth.setLower(berth.getSideUpper()-1);
        }
        else {
            if(berth.getLower()>0)
            {
                berth.setLower(berth.getLower()-1);
            }
            else if(berth.getUpper()>0)
            {
                berth.setUpper(berth.getUpper()-1);
            }
            else if(berth.getMidUpper()>0)
            {
                berth.setMidUpper(berth.getUpper()-1);
            }
            else if(berth.getSideUpper()>0)
            {
                berth.setSideUpper(berth.getUpper()-1);
            }
        }
    }

}
