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

    private Player setBye(Player player)
    {
        Match match=new Match();
        List<Match> matches=player.getMatchList();
        if (matches==null)
        {
            matches=new ArrayList<>();
        }
        match.setMatchPoint(1);
        int count=player.getBonusCount();
        player.setBonusCount(++count);
        player.setTotalPoint(player.getTotalPoint()+  match.getMatchPoint());
        player.setCurrentMatchPoint(1);
        match. setMatchPoint(1);
        match.setResult("win");
        match.setOpponentName("bye");
        matches.add(match);
        player.setMatchList(matches);
        return player;
    }

    Player getWinPlayer(Player player,Player player1)
    {
        Match match=new Match();
        List<Match> matches=player.getMatchList();
        if (matches == null) {
            matches = new ArrayList<>();
        }
        match.setMatchPoint(1);
        player.setCurrentMatchPoint(1);
        player.setTotalPoint(player.getTotalPoint()+match.getMatchPoint());
        match.setResult("won");
        match.setOpponentName(player1.getPlayerName());
        match.setOpponentPoint(0);
        matches.add(match);
        player.setMatchList(matches);
        return player;
    }
    private Player getLosePlayer(Player player,Player player1)
    {
        Match match=new Match();
        List<Match> matches=player.getMatchList();
        if (matches == null) {
            matches = new ArrayList<>();
        }
        match.setMatchPoint(0);
        player.setCurrentMatchPoint(1);
        player.setTotalPoint(player.getTotalPoint()+ match.getMatchPoint());
        match.setOpponentName(player1.getPlayerName());
        match.setResult("lose");
        match.setOpponentPoint(1);
        matches.add(match);
        player1.setMatchList(matches);
        return player;
    }
    private Player getDrawPlayer(Player player,Player player1)
    {
        Match match=new Match();
        List<Match> matches=player.getMatchList();
        if (matches == null) {
            matches = new ArrayList<>();
        }
        match.setMatchPoint(0.5f);
        player.setCurrentMatchPoint(0.5f);
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
            for (int i = 0; i < list.size()-1; i++) {
                Player player = list.get(i++).getValue();
                Player player1 = list.get(i).getValue();
                int res = (int) Math.floor(Math.random() * 3);
                if (res == 1) {
                    player = getWinPlayer(player, player1);
                    player1 = getLosePlayer(player1, player1);
                    map.put(player.getPlayerId(), player);
                    map.put(player1.getPlayerId(), player1);
                } else if (res == 0) {
                    player = getLosePlayer(player, player1);
                    player1 = getWinPlayer(player1, player);
                    map.put(player.getPlayerId(), player);
                    map.put(player1.getPlayerId(), player1);
                } else {
                    player = getDrawPlayer(player, player1);
                    player1 = getDrawPlayer(player1, player);
                    map.put(player.getPlayerId(), player);
                    map.put(player1.getPlayerId(), player1);

                }
            }
        if(list.size()%2!=0) {
            Map.Entry<Integer, Player> k = list.remove(list.size() - 1);
            Player player = k.getValue();
            setBye(player);
            map.put(player.getPlayerId(),player);
        }
        Cache.OBJ.updatePlayerMap(map);
        list = new ArrayList<>(map.entrySet());
       // System.out.println(list);
        return list;
    }
    public List<Map.Entry<Integer,Player>> getRankList(){
        return schedule();
    }
}
