package RailWayReservationSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {
    private Map<Integer,Map<Integer,Passenger>> confirmedTickets=new HashMap<>();
    private List<Passenger> racTickets=new ArrayList<>();
    private List<Passenger> waitingTickets=new ArrayList<>();
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
    public void addRacTickets(List<Passenger> passenger)
    {
      for (Passenger p:passenger)
      {
          racTickets.add(p);
      }
    }
    public void addWaitingTickets(List<Passenger> passenger)
    {
        for (Passenger p:passenger)
        {
            waitingTickets.add(p);
        }
    }
    public List<Passenger> getRacTicketsList()
    {
        return racTickets;
    }
    public List<Passenger> getWaitingTicketsList()
    {
        return waitingTickets;
    }
    public void updateConfirmedTicketMap(int ticketId,Map<Integer,Passenger> map)
    {
        confirmedTickets.put(ticketId,map);
    }
    public void addConfirmedTicketsFromRac(Passenger passenger)
    {
        HashMap<Integer,Passenger> innerMap=new HashMap<>();
        innerMap.put(passenger.getSeatNo(),passenger);
        confirmedTickets.put(passenger.getTicketId(),innerMap);
    }
    public void removePassengerFromRacList()
    {
        racTickets.remove(0);
    }
    public void  addRacTicketsFromWaiting(Passenger passenger)
    {
        racTickets.add(passenger);
    }
    public void removePassengerFromWaitingList()
    {
        waitingTickets.remove(0);
    }

}
