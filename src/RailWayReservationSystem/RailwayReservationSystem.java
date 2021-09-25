package RailWayReservationSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class RailwayReservationSystem {
    static int confirmedTicketsCount=4;
    static int racTicketsCount=1;
    static int waitingTicketsCount=1;
    static int seatNo=1;
    static int ticketId=1;
    Berth berth=new Berth();
    Cache cache=new Cache();
    RailwayReservationSystem()
    {
        berth.setLower(1);
        berth.setUpper(1);
        berth.setMidUpper(1);
        berth.setSideLower(1);
        berth.setSideUpper(1);
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
        if(confirmedTickets.size()>0) {
            cache.addConfirmedTicketsInMap(ticketId, confirmedTickets);
        }
        if(racTickets.size()>0) {
            cache.addRacTickets(racTickets);
        }
        if(waitingTickets.size()>0) {
            cache.addWaitingTickets(waitingTickets);
        }
        if(count>0) {
            ticketId++;
        }
        return bookingStatus;
    }
    public String cancelTicket(int ticketId,int seatNo)
    {
        Map<Integer,Map<Integer,Passenger>> map=getConfirmedTicketsHistory();
        Map<Integer,Passenger> innerMap=map.get(ticketId);
        if (innerMap==null)
        {
            return "ticket id not available cancellation failed";
        }
        for (Map.Entry<Integer,Passenger> entry: innerMap.entrySet()) {
            if(entry.getKey()==seatNo) {
                String berthPreference=entry.getValue().getBerthPreference();
                innerMap.remove(seatNo);
                cache.updateConfirmedTicketMap(ticketId, innerMap);
                confirmedTicketsCount--;
                List<Passenger> rac = getRacTicketsHistory();
                List<Passenger> wait = getWaitingTicketsHistory();
                if (rac.size() > 0) {
                    confirmedTicketsCount++;
                    Passenger passenger=rac.get(0);
                    passenger.setBerthPreference(berthPreference);
                    passenger.setConfirmationStatus("confirmed");
                    cache.addConfirmedTicketsFromRac(passenger);
                    cache.removePassengerFromRacList();
                    racTicketsCount--;
                    if (wait.size() > 0) {
                        racTicketsCount++;
                        Passenger passenger1=wait.get(0);
                        passenger1.setConfirmationStatus("Rac");
                        cache.addRacTicketsFromWaiting(passenger1);
                        cache.removePassengerFromWaitingList();
                        waitingTicketsCount--;
                    }
                }

            }
        }
        return "Ticket cancel successfully"+"ticket id"+ticketId+"seat no:"+seatNo;
    }
    public Map<Integer,Map<Integer,Passenger>> getConfirmedTicketsHistory()
    {
        return cache.getConfirmedTicketsMap();
    }

    public List<Passenger> getRacTicketsHistory()
    {
        return cache.getRacTicketsList();
    }
    public List<Passenger> getWaitingTicketsHistory()
    {
        return cache.getWaitingTicketsList();
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

    public List<Integer> getAvailableTickets()
    {
     List<Integer> list=new ArrayList<>();
       list.add(confirmedTicketsCount);
       list.add(racTicketsCount);
       list.add(waitingTicketsCount);
       return list;
    }

}
