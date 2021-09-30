package chessTournament;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public enum Cache {
    OBJ;
    private Map<Integer,Player> playerMap=new LinkedHashMap<>();
    public void setPlayerMap(List<Player> players)
    {
        for (Player player:players)
        {
            playerMap.put(player.getPlayerId(),player);
        }
    }
    public Map<Integer,Player> getPlayerMap()
    {
        return playerMap;
    }
}
