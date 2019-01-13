public class ModelGame {
    public final int end = 16;
    private int actualTurn;
    private ModelPlayer playerOne;
    private ModelPlayer playerTwo;
    private int round;
    private boolean nextRound;

    public ModelGame(ModelPlayer playerOne, ModelPlayer playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        actualTurn = 0;
        round = 1;
        nextRound = false;
    }

    public boolean isNextRound() {
        return this.nextRound;
    }

    public void advanceTurn() {
        this.actualTurn++;
        if (this.actualTurn == 2) {
            round++;
            actualTurn = 0;
        }
        if (this.round == 10) {
            this.nextRound = true;
        }
    }

    public int getRound() {
        return round;
    }

    public int getActualTurn () {
        return actualTurn;
    }
    public ModelPlayer getCurrentPlayer() {
        if (this.actualTurn % 2 == 0) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }
}
