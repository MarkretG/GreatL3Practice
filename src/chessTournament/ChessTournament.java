package chessTournament;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class ChessTournament {
    public static void main(String[] args) throws ExceptionHandler {
        ChessTournament chess = new ChessTournament();
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
                        chess.scheduleMatch(list);

                        System.out.println("Result for Round:" + i);
                        list = obj.getResultOfRound(list);
                        chess.matchResult(list);

                        System.out.println("order of the player");
                        list = obj.schedule();
                        for (int k = 0; k < list.size(); k++) {
                            String name = list.get(k).getValue().getPlayerName();
                            System.out.println(name + " " + "(" + list.get(k).getValue().getTotalPoint() + ")");
                        }
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

                }
                break;
                case 4:
                {

                }
         }

        }
    }
        private void scheduleMatch (List < Map.Entry < Integer, Player >> list)
        {
            if (list.size() % 2 == 0) {
                for (int j = 0; j < list.size(); j += 2) {
                    System.out.print(list.get(j).getValue().getPlayerName() + "  " + "VS" + "  ");
                    System.out.println(list.get(j + 1).getValue().getPlayerName());
                }
            } else {
                for (int j = 0; j < list.size(); j += 2) {
                    if (j != list.size() - 1) {
                        System.out.print(list.get(j).getValue().getPlayerName() + "  " + "VS" + "  ");
                        System.out.println(list.get(j + 1).getValue().getPlayerName());
                        continue;
                    }
                    System.out.print(list.get(j).getValue().getPlayerName() + "  " + "VS" + "  ");
                    System.out.println("bye");
                }
            }
        }
        private void matchResult(List<Map.Entry<Integer,Player>> list)
        {
            if (list.size() % 2 == 0) {
                for (int j = 0; j < list.size(); j += 2) {
                    System.out.print(list.get(j).getValue().getPlayerName() + "  ");
                    System.out.print("(" + list.get(j).getValue().getMatchPoint() + ":" + list.get(j + 1).getValue().getMatchPoint() + ")");
                    System.out.println(list.get(j + 1).getValue().getPlayerName() + "  ");
                }
            } else {
                for (int j = 0; j < list.size(); j += 2) {
                    if (j != list.size() - 1) {
                        System.out.print(list.get(j).getValue().getPlayerName() + "  ");
                        System.out.print("(" + list.get(j).getValue().getMatchPoint() + ":" + list.get(j + 1).getValue().getMatchPoint() + ")");
                        System.out.println(list.get(j + 1).getValue().getPlayerName() + "  ");
                        continue;
                    }
                    System.out.print(list.get(j).getValue().getPlayerName() + "  ");
                    System.out.print("(" + list.get(j).getValue().getMatchPoint() + ":" + 0.0 + ")");
                    System.out.println("bye");
                }
            }
        }

}
