import javax.swing.*;
import java.awt.*;

public class JeopardyScoreView {
    public JLabel playerOneName, playerTwoName, playerWinner;

    public JeopardyScoreView() {
        JFrame frame = new JFrame("Jeopardy");
        addComponentsToPane(frame.getContentPane());
        frame.setSize(1366, 768);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void addComponentsToPane(Container contentPanel) {
        JPanel userOneContainer = new JPanel();
        userOneContainer.setLayout(new BoxLayout(userOneContainer, BoxLayout.X_AXIS));
        playerOneName = new JLabel("");
        userOneContainer.add(playerOneName);

        JPanel userTwoContainer = new JPanel();
        userTwoContainer.setLayout(new BoxLayout(userTwoContainer, BoxLayout.X_AXIS));
        playerTwoName = new JLabel("");
        userTwoContainer.add(playerTwoName);
        JPanel usersContainer = new JPanel();
        usersContainer.setLayout(new BoxLayout(usersContainer, BoxLayout.Y_AXIS));
        usersContainer.add(userOneContainer);
        usersContainer.add(userTwoContainer);
        contentPanel.add(usersContainer, BorderLayout.CENTER);
        playerWinner = new JLabel("Winner");
        contentPanel.add(playerWinner, BorderLayout.NORTH);

    }

    public void setVisible(boolean b) {
    }
}
