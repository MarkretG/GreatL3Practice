package chessTournament;
import java.util.*;
public class TournamentDriver {
    public void initialisePlayerList(List<Player> players) throws ExceptionHandler {
        if(players.isEmpty()||players==null)
        {
            throw new ExceptionHandler("Initialise player list is null");
        }
        Cache.OBJ.setPlayerMap(players);
    }
    public List<Map.Entry<Integer,Player>> schedule()
    {
        Map<Integer,Player> playerMap=Cache.OBJ.getPlayerMap();
        List<Map.Entry<Integer,Player>> list=new ArrayList<>(playerMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Player>>() {
            @Override
            public int compare(Map.Entry<Integer, Player> o1, Map.Entry<Integer, Player> o2) {
                float num1=o1.getValue().getTotalPoint();
                float num2=o2.getValue().getTotalPoint();
                if(num1>num2)
                {
                    return -1;
                }
                else if(num1<num2)
                {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });
        return list;
    }

    public List<Map.Entry<Integer,Player>> getResultOfRound(List<Map.Entry<Integer,Player>> list,int count) {
        for (int i = 0; i < list.size(); i++) {
            Player player = list.get(i).getValue();
            Player player1 = list.get(i + 1).getValue();
            Match match=new Match();
            Match match1=new Match();
            int res=(int) Math.floor(Math.random()*3);
            if(res==1)
            {
                player.setMatchPoint(1);
                player.setTotalPoint(player.getTotalPoint()+player.getMatchPoint());
                List<Match> matches=player.getMatchList();
                if (matches==null)
                {
                    matches=new ArrayList<>();
                }
                match.setResult("won");
                match.setOpponentName(player1.getPlayerName());
                match.setOpponentPoint(0);
                player1.setMatchPoint(0);
                player1.setTotalPoint(player1.getTotalPoint()+player1.getMatchPoint());
                match1.setOpponentName(player.getPlayerName());
                match1.setResult("");
            }
            else if (res==0)
            {

            }
            else
            {

            }
        }
              /*Match match=new Match();
               if (list.size()%2!=0) {
                for (int j = 0; j < list.size()-1; j +=2){
                    String status=getMatchStatus();
                    if(j!=list.size()-1&&status.equals("won"))
                    {
                        match.setPoint(1);
                        match.setPoint(1);
                        match.setOpponentName(list.get(j).getValue().getPlayerName());
                    }
                }

    }*/
    }
}
