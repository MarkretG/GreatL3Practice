package RailWayReservationSystem;
import java.util.*;
public class RailWayReservation {
    RailwayReservationSystem railwayReservationSystem = new RailwayReservationSystem();

    public static void main(String[] args) {
        RailwayReservationSystem railwayReservationSystem = new RailwayReservationSystem();
        Scanner sc = new Scanner(System.in);
        System.out.println("welcome to railway reservation system");
        while (true) {
            System.out.println("1.Booking");
            System.out.println("2.Booked tickets");
            System.out.println("3.Cancel ticket");
            System.out.println("4.Available tickets");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    List<Passenger> passengers = new ArrayList<>();
                    System.out.println("How many tickets");
                    int ticketCount = sc.nextInt();
                    for (int i = 0; i < ticketCount; i++) {
                        System.out.println("enter name");
                        String name = sc.nextLine();
                        System.out.println("enter age");
                        int age = sc.nextInt();
                        System.out.println("enter gender");
                        String gender = sc.nextLine();
                        System.out.println("enter berth preference");
                        String berthPreference = sc.next();
                        if (gender.equals("female") && age >= 60) {
                            System.out.println("Do you have children");
                            String haveChildren = sc.next();
                            berthPreference = "lower";
                        }
                        Passenger passenger = railwayReservationSystem.getPassengerObject(name, age, gender, berthPreference);
                        passengers.add(passenger);
                    }
                    List<String> list=railwayReservationSystem.bookingTickets(passengers);
                    for (String l:list)
                    {
                        System.out.println(l);
                    }
                }
                break;
                case 2: {
                    Map<Integer, Map<Integer, Passenger>> confirmedTicks = railwayReservationSystem.getConfirmedTicketsHistory();
                    printConfirmedTickets(confirmedTicks);
                    List<Passenger> racTickets = railwayReservationSystem.getRacTicketsHistory();
                    if(racTickets.size()>0) {
                        print(racTickets);
                    }
                    List<Passenger> waitingTickets = railwayReservationSystem.getWaitingTicketsHistory();
                    if(waitingTickets.size()>0) {
                        print(waitingTickets);
                    }
                }
                break;
                case 3:
                    System.out.println("Enter ticket id");
                    int ticketId=sc.nextInt();
                    System.out.println("enter seat number");
                    int seatNumber=sc.nextInt();

            }
        }

    }

    private static void printConfirmedTickets(Map<Integer, Map<Integer, Passenger>> confirm)
    {
        for (Integer i : confirm.keySet())
            {
                Map<Integer, Passenger> inner = confirm.get(i);
                for (Map.Entry<Integer, Passenger> entry : inner.entrySet()) {
                    System.out.println("SeatNo:" + entry.getKey());
                    System.out.println(entry.getValue());
                }
            }
        }
        private static void print(List<Passenger> list)
        {
                for (Passenger passenger:list)
                {
                    System.out.println(passenger);
                }
        }

    }

