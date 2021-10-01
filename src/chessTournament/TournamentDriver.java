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
    public List<Map.Entry<Integer,Player>> schedule() {
        Map<Integer, Player> playerMap = Cache.OBJ.getPlayerMap();
        List<Map.Entry<Integer, Player>> list = new ArrayList<>(playerMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Player>>() {
            @Override
            public int compare(Map.Entry<Integer, Player> o1, Map.Entry<Integer, Player> o2) {
                float num1 = o1.getValue().getTotalPoint();
                float num2 = o2.getValue().getTotalPoint();
                if (num1 > num2) {
                    return -1;
                } else if (num1 < num2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return list;
    }
    public Player getPlayer(int playerId)
    {
      Player player=Cache.OBJ.getPlayerMap().get(playerId);
      return player;
    }

    public Player getWinPlayer(Player player,Player player1,Match match,List<Match> matches)
    {
        match.setMatchPoint(1);
        player.setTotalPoint(player.getTotalPoint()+match.getMatchPoint());
        match.setResult("won");
        match.setOpponentName(player1.getPlayerName());
        match.setOpponentPoint(0);
        matches.add(match);
        player.setMatchList(matches);
        return player;
    }
    public Player getLosePlayer(Player player,Player player1,Match match,List<Match> matches)
    {
        match.setMatchPoint(0);
        player.setTotalPoint(player.getTotalPoint()+ match.getMatchPoint());
        match.setOpponentName(player1.getPlayerName());
        match.setResult("lose");
        match.setOpponentPoint(1);
        matches.add(match);
        player1.setMatchList(matches);
        return player;
    }
    public Player getDrawPlayer(Player player,Player player1,Match match,List<Match> matches)
    {
        match.setMatchPoint(0.5f);
        player.setTotalPoint(player.getTotalPoint()+ match.getMatchPoint());
        match.setResult("draw");
        match.setOpponentName(player1.getPlayerName());
        match.setOpponentPoint(0.5f);
        matches.add(match);
        player.setMatchList(matches);
      return player;
    }
    public List<Map.Entry<Integer,Player>> getResultOfRound(List<Map.Entry<Integer,Player>> list) {
        Map<Integer,Player> map=new LinkedHashMap<>();
        if(list.size()%2==0){
            for (int i = 0; i < list.size(); i += 2) {
                Player player = list.get(i).getValue();
                Player player1 = list.get(i + 1).getValue();
                Match match = new Match();
                Match match1 = new Match();
                List<Match> matches = player.getMatchList();
                List<Match> matches1 = player1.getMatchList();
                if (matches == null) {
                    matches = new ArrayList<>();
                }
                if (matches1 == null) {
                    matches1 = new ArrayList<>();
                }
                int res = (int) Math.floor(Math.random() * 3);
                if (res == 1) {
                    player = getWinPlayer(player, player1, match, matches);
                    player1 = getLosePlayer(player1, player, match1, matches1);
                    map.put(player.getPlayerId(), player);
                    map.put(player1.getPlayerId(), player1);
                } else if (res == 0) {
                    player = getLosePlayer(player, player1, match, matches);
                    player1 = getWinPlayer(player1, player, match1, matches1);
                    map.put(player.getPlayerId(), player);
                    map.put(player1.getPlayerId(), player1);

                } else {
                    player = getDrawPlayer(player, player1, match, matches);
                    player1 = getDrawPlayer(player1, player, match1, matches1);
                    map.put(player.getPlayerId(), player);
                    map.put(player1.getPlayerId(), player1);

                }
            }
        }
            if (list.size()%2!=0)
            {
                for (int i = 0; i < list.size()-1; i += 2) {
                    Player player = list.get(i).getValue();
                    Player player1 = list.get(i + 1).getValue();
                    Match match = new Match();
                    Match match1 = new Match();
                    List<Match> matches = player.getMatchList();
                    List<Match> matches1 = player1.getMatchList();
                    if (matches == null) {
                        matches = new ArrayList<>();
                    }
                    if (matches1 == null) {
                        matches1 = new ArrayList<>();
                    }
                    int res = (int) Math.floor(Math.random() * 3);
                    if (res == 1) {
                        player = getWinPlayer(player, player1, match, matches);
                        player1 = getLosePlayer(player1, player, match1, matches1);
                        map.put(player.getPlayerId(), player);
                        map.put(player1.getPlayerId(), player1);
                    } else if (res == 0) {
                        player = getLosePlayer(player, player1, match, matches);
                        player1 = getWinPlayer(player1, player, match1, matches1);
                        map.put(player.getPlayerId(), player);
                        map.put(player1.getPlayerId(), player1);

                    } else {
                        player = getDrawPlayer(player, player1, match, matches);
                        player1 = getDrawPlayer(player1, player, match1, matches1);
                        map.put(player.getPlayerId(), player);
                        map.put(player1.getPlayerId(), player1);

                    }

                }
            }
        if(list.size()%2!=0)
        {
            Match match=new Match();
            Map.Entry<Integer,Player> k=list.remove(list.size()-1);
            Player player=k.getValue();
            List<Match> matches=player.getMatchList();
            if (matches==null)
            {
                matches=new ArrayList<>();
            }
            match.setMatchPoint(1);
            int count=player.getBonusCount();
            player.setBonusCount(++count);
            player.setTotalPoint(player.getTotalPoint()+  match.getMatchPoint());
            match. setMatchPoint(1);
            match.setResult("win");
            match.setOpponentName("bye");
            matches.add(match);
            player.setMatchList(matches);
            map.put(player.getPlayerId(),player);
        }
        list = new ArrayList<>(map.entrySet());
        System.out.println(list);
        Cache.OBJ.updatePlayerMap(map);
        return list;
    }
}
