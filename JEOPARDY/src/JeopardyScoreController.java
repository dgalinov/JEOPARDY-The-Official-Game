public class JeopardyScoreController {
    private ModelPlayer playerOne;
    private ModelPlayer playerTwo;
    private JeopardyScoreView view;

    public JeopardyScoreController(ModelPlayer playerOne, ModelPlayer playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.view = new JeopardyScoreView();

        view.playerOneName.setText(playerOne.getName() + " " + String.valueOf(playerOne.getMoney()));

        view.playerTwoName.setText(playerTwo.getName() + " " + String.valueOf(playerTwo.getMoney()));

        view.playerWinner.setText("Winner: " + playerOne.isWinner(playerTwo).getName());

        view.setVisible(true);
    }
}
