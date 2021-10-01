package chessTournament;
public class Match {
    private String result;
    private float opponentPoint;
    private String opponentName;


    public void setResult(String result) {
        this.result = result;
    }
    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public void setOpponentPoint(float opponentPoint) {
        this.opponentPoint = opponentPoint;
    }


    public String getResult() {
        return result;
    }

    public float getOpponentPoint() {
        return opponentPoint;
    }

    public String getOpponentName() {
        return opponentName;
    }


    @Override
    public String toString() {
        return "Match{" +
                "result='" + result + '\'' +
                ", opponentPoint=" + opponentPoint +
                ", opponentName='" + opponentName + '\'' +
                '}';
    }
}
