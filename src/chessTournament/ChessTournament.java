package chessTournament;
import java.util.*;
public class ChessTournament {
    public static void main(String[] args) throws ExceptionHandler {
        Scanner sc = new Scanner(System.in);
        List<Player> players = new ArrayList<>();
        TournamentDriver obj = new TournamentDriver();
        boolean end=true;
        while (end)
        {
            System.out.println("1.Conduct chess tournament");
            System.out.println("2.Display matches");
            System.out.println("3.Rank list");
            System.out.println("4.exit");
            int choice=sc.nextInt();
            switch (choice)
            {
                case 1: {
                    System.out.println("No of participants");
                    int playerCount = sc.nextInt();
                    System.out.println("No of rounds");
                    int rounds = sc.nextInt();


                    for (int i = 1; i <= playerCount; i++) {
                        Player player = new Player();
                        player.setPlayerName("player " + i);
                        player.setPlayerId(i);
                        players.add(player);
                    }
                    obj.initialisePlayerList(players);


                    System.out.println("player details");
                    for (int i = 1; i <= rounds; i++) {
                        System.out.println("Round:" + i);
                        List<Map.Entry<Integer, Player>> list = obj.schedule();
                        String result=obj.getScheduleMatch(list);
                        System.out.println(result);

                        System.out.println("Result for Round:" + i);
                        list = obj.getResultOfRound(list);
                        result=obj.getMatchResult(list);
                        System.out.println(result);

                        System.out.println("Points of the players");
                        list = obj.schedule();
                        result=obj.getPointsTable(list);
                        System.out.println(result);

                    }
                }
                break;
                case 2:
                {
                    System.out.println("enter player id");
                    int playerId= sc.nextInt();
                    Player player= obj.getPlayer(playerId);
                    System.out.println(player);

                }
                break;
                case 3:
                {
                    System.out.println("Rank list of tournament");
                    String result= obj.getRankList();
                    System.out.println(result);
                }
                break;
                case 4:
                {
                 end=false;
                 break;
                }
            }

        }
    }
}
