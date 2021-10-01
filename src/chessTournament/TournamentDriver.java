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

    public List<Map.Entry<Integer,Player>> getResultOfRound(List<Map.Entry<Integer,Player>> list) {
        for (int i = 0; i < list.size()-1; i+=2) {
            Player player = list.get(i).getValue();
            Player player1 = list.get(i + 1).getValue();
            Match match=new Match();
            Match match1=new Match();
            List<Match> matches=player.getMatchList();
            List<Match> matches1=player1.getMatchList();
            if (matches==null)
            {
                matches=new ArrayList<>();
            }
            if(matches1==null)
            {
                matches1=new ArrayList<>();
            }
            int res=(int) Math.floor(Math.random()*3);
            if(res==1)
            {
                player.setMatchPoint(1);
                player.setTotalPoint(player.getTotalPoint()+player.getMatchPoint());
                match.setResult("won");
                match.setOpponentName(player1.getPlayerName());
                match.setOpponentPoint(0);
                matches.add(match);
                player.setMatchList(matches);
                player1.setMatchPoint(0);
                player1.setTotalPoint(player1.getTotalPoint()+player1.getMatchPoint());
                match1.setOpponentName(player.getPlayerName());
                match1.setResult("lose");
                match1.setOpponentPoint(1);
                matches1.add(match);
                player1.setMatchList(matches1);
            }
            else if (res==0)
            {
                player.setMatchPoint(0);
                player.setTotalPoint(player.getTotalPoint()+player.getMatchPoint());
                match.setResult("lose");
                match.setOpponentName(player1.getPlayerName());
                match.setOpponentPoint(1);
                matches.add(match);
                player.setMatchList(matches);
                player1.setMatchPoint(1);
                player1.setTotalPoint(player1.getTotalPoint()+player1.getMatchPoint());
                match1.setOpponentName(player.getPlayerName());
                match1.setResult("won");
                match1.setOpponentPoint(0);
                matches1.add(match);
                player1.setMatchList(matches1);

            }
            else
            {
                player.setMatchPoint((float)0.5);
                player.setTotalPoint(player.getTotalPoint()+player.getMatchPoint());
                match.setResult("draw");
                match.setOpponentName(player1.getPlayerName());
                match.setOpponentPoint((float)0.5);
                matches.add(match);
                player.setMatchList(matches);
                player1.setMatchPoint((float)0.5);
                player1.setTotalPoint(player1.getTotalPoint()+player1.getMatchPoint());
                match1.setOpponentName(player.getPlayerName());
                match1.setResult("draw");
                match1.setOpponentPoint((float)0.5);
                matches1.add(match);
                player1.setMatchList(matches1);

            }
        }
        return list;
    }
}
