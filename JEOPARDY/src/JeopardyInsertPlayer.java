import javax.swing.*;
import java.awt.*;

public class JeopardyInsertPlayer {
    public JLabel titleLabel, userOne, userTwo;
    public JTextField inputOne, inputTwo;
    public JButton nextButton, rulesButton;

    public JeopardyInsertPlayer() {
        JFrame frame = new JFrame("Jeopardy");
        addComponentsToPane(frame.getContentPane());
        frame.setSize(1366, 768);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void addComponentsToPane(Container contentPanel) {
        titleLabel = new JLabel("Welcome to Jeopardy");
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        userOne = new JLabel("Player 1:");
        userTwo = new JLabel("Player 2:");
        inputOne = new JTextField();
        inputTwo = new JTextField();

        JPanel userSelection = new JPanel();
        userSelection.setLayout(new GridLayout(2, 2));

        userSelection.add(userOne);
        userSelection.add(inputOne);
        userSelection.add(userTwo);
        userSelection.add(inputTwo);
        contentPanel.add(userSelection, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        rulesButton = new JButton("Instructions");

        JPanel gridSouth = new JPanel();
        gridSouth.setLayout(new GridLayout(1, 1));

        gridSouth.add(rulesButton);
        gridSouth.add(nextButton);

        contentPanel.add(gridSouth, BorderLayout.SOUTH);
    }
}
