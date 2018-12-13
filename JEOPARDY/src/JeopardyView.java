import javax.swing.*;
import java.awt.*;

public class JeopardyView {
    public static void main(String[] args) { ShowMainMenu();}

    private static void ShowMainMenu() {
        JFrame frameMainMenu = new JFrame("Jeopardy");
        addPanel(frameMainMenu.getContentPane());
        frameMainMenu.setSize(1250,750);
        frameMainMenu.setResizable(false);
        frameMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMainMenu.setVisible(true);
    }
    private static void addPanel(Container contentPane) {
        JPanel topicButtons = new JPanel();
        topicButtons.setLayout(new GridBagLayout());

        JLabel topicButton1 = new JLabel("JUAN");
        topicButton1.setForeground(Color.green);
        JLabel topicButton2 = new JLabel("ES");
        topicButton2.setForeground(Color.red);
        JLabel topicButton3 = new JLabel("MARICA");
        topicButton3.setForeground(Color.blue);
        JLabel topicButton4 = new JLabel("!");
        topicButton4.setForeground(Color.pink);
        JLabel topicButton5 = new JLabel("MARC");
        topicButton5.setForeground(Color.orange);
        JLabel topicButton6 = new JLabel("GAY");
        topicButton6.setForeground(Color.magenta);

        topicModelText(topicButtons, "", 1, topicButton1);
        topicModelText(topicButtons, "", 2, topicButton2);
        topicModelText(topicButtons, "", 3, topicButton3);
        topicModelText(topicButtons, "", 4, topicButton4);
        topicModelText(topicButtons, "", 5, topicButton5);
        topicModelText(topicButtons, "", 6, topicButton6);

        contentPane.add(topicButtons, BorderLayout.PAGE_START);
    }
    private static void topicModelText(JPanel topicButtons, String label, int x, JLabel topicButtonCreation) {
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel labelModel = new JLabel(label);
        gbc.gridx = x;
        gbc.gridy = 0;
        topicButtons.add(labelModel, gbc);
        gbc.gridy = 1;
        topicButtons.add(topicButtonCreation, gbc);
    }
}