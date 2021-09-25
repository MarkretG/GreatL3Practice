package RailWayReservationSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {
    private Map<Integer,Map<Integer,Passenger>> confirmedTickets=new HashMap<>();
    private List<Passenger> racTickets=new ArrayList<>();
    private List<Passenger> waitingTickets=new ArrayList<>();
   // private Map<Integer,List<Passenger>> racTickets=new HashMap<>();
    //private Map<Integer,List<Passenger>> waitingTickets=new HashMap<>();

    public void  addConfirmedTicketsInMap(int ticketId, List<Passenger> passengers)
    {
      for (Passenger passenger:passengers)
      {
          Map<Integer,Passenger> innerMap=new HashMap<>();
          innerMap.put(passenger.getSeatNo(),passenger);
          confirmedTickets.put(ticketId,innerMap);
      }
    }
    public Map<Integer,Map<Integer,Passenger>> getConfirmedTicketsMap()
    {
        return confirmedTickets;
    }
    /*public void addRacTickets(int ticketId,List<Passenger> passengers)
    {
        racTickets.put(ticketId,passengers);
    }
    public Map<Integer,List<Passenger>> getRacTicketsMap()
    {
        return racTickets;
    }
    public void addWaitingTickets(int ticketId,List<Passenger> passengers)
    {
        waitingTickets.put(ticketId,passengers);
    }
    public Map<Integer,List<Passenger>> getWaitingTicketsMap()
    {
        return waitingTickets;
    }*/
    public void updateConfirmedTickets()
    {

    }
}
