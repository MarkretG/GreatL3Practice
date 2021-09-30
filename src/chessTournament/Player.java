package chessTournament;
import java.util.List;
public class Player {
    private int playerId;
    private String playerName;
    private int matchPoint;
    private float TotalPoint;
    private List<Match> matchList;


    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getMatchPoint() {
        return matchPoint;
    }

    public void setMatchPoint(int matchPoint) {
        this.matchPoint = matchPoint;
    }
    public float getTotalPoint() {
        return TotalPoint;
    }

    public void setTotalPoint(float totalPoint) {
        TotalPoint = totalPoint;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }
}
