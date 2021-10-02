package chessTournament;
import java.util.*;
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
                        chess.printPointsOrder(list);

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
                    List<Map.Entry<Integer,Player>> list= obj.getRankList();
                    chess.printRankList(list);
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
        private void scheduleMatch (List < Map.Entry < Integer, Player >> list) {
            for(int i=0;i<list.size();i++) {
                String player1=list.get(i++).getValue().getPlayerName();
                String player2=null;
                if(i<list.size()) {
                    player2=list.get(i).getValue().getPlayerName();
                }
                else {
                    player2="Bye";
                }
                System.out.println(player1+"  "+"vs"+" "+player2);
            }
        }
        private void matchResult(List<Map.Entry<Integer,Player>> list) {
            for (int i=0;i<list.size();i++){
                Player player=list.get(i++).getValue();
                String player1=player.getPlayerName()+"("+player.getCurrentMatchPoint();
                String player2=null;
                if(i<list.size()) {
                    player2=list.get(i).getValue().getCurrentMatchPoint()+")"+list.get(i).getValue().getPlayerName();
                }
                else {
                    player2=0.0+"bye";
                }
                System.out.println(player1+" "+player2);
            }
        }
        private void printPointsOrder(List<Map.Entry<Integer,Player>> list) {
            for (int k = 0; k < list.size(); k++) {
                String name = list.get(k).getValue().getPlayerName();
                float totalPoint=list.get(k).getValue().getTotalPoint();
                System.out.println(name + " " + "(" + totalPoint+ ")");
            }
        }
        private void printRankList(List<Map.Entry<Integer,Player>> list)
        {
            for (int i=0;i<list.size();i++)
            {
                Player player=list.get(i).getValue();
                System.out.println("RANK: "+(i+1) +player.getPlayerName()+" "+player.getTotalPoint());
            }
        }

}
