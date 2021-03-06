package chessTournament;
import java.util.List;
public class Player {
    private int playerId;
    private String playerName;
    private float currentMatchPoint;
    private float TotalPoint;
    private List<Match> matchList;
    private int bonusCount;

    public int getBonusCount() {
        return bonusCount;
    }

    public void setBonusCount(int bonusCount) {
        this.bonusCount = bonusCount;
    }

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

    public float getCurrentMatchPoint() {
        return currentMatchPoint;
    }

    public void setCurrentMatchPoint(float currentMatchPoint) {
        this.currentMatchPoint = currentMatchPoint;
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


    @Override
    public String toString() {
        return
                "  playerId:" + playerId +'\t'+
                "  playerName:" + playerName + '\'' +
                ", TotalPoint=" + TotalPoint +
                ", matchList="  +  matchList +
                ", bonusCount=" + bonusCount;
    }
}
