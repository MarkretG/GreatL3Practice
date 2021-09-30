package chessTournament;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class ChessTournament {
    public static void main(String[] args) throws ExceptionHandler {
        Scanner sc=new Scanner(System.in);
        System.out.println("No of participants");
        int playerCount= sc.nextInt();
        System.out.println("No of rounds");
        int rounds=sc.nextInt();
        System.out.println("player details");
        List<Player> players=new ArrayList<>();
        for (int i=1;i<=playerCount;i++)
        {
            Player player=new Player();
            player.setPlayerName("player "+i);
            player.setPlayerId(i);
            players.add(player);
        }
        TournamentDriver obj=new TournamentDriver();
        obj.initialisePlayerList(players);
        for (int i=1;i<=rounds;i++) {
            List<Map.Entry<Integer, Player>> list = obj.schedule();
            if(list.size()%2==0) {
                for (int j = 0; j < list.size(); j += 2) {
                    System.out.print(list.get(j).getValue().getPlayerName() + "  " + "VS" + "  ");
                    System.out.println(list.get(j + 1).getValue().getPlayerName());
                }
            }
            else {
                for (int j = 0; j < list.size()-1; j += 2) {
                    if(j!=list.size()-1) {
                        System.out.print(list.get(i).getValue().getPlayerName() + "  " + "VS" + "  ");
                        System.out.println(list.get(i + 1).getValue().getPlayerName());
                        continue;}
                    System.out.print(list.get(i).getValue().getPlayerName() + "  " + "VS" + "  ");
                    System.out.println("bye");
                }
            }
            obj.getResultOfRound(list,playerCount);
        }

    }
}
