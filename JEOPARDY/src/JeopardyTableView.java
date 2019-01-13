import javax.swing.*;
import java.awt.*;

public class JeopardyTableView  extends JFrame {

    private JPanel gridCenter;
    private JPanel gridSouth;
    public JButton[][] questionsBtn;
    public JLabel[] categoriesLabels, playerScores;
    public JLabel actualTurn;

    private void JeopardyTableView(){
        JFrame frame = new JFrame("Jeopardy");
        addComponentsToPane(frame.getContentPane());
        frame.setSize(1366,768);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void addComponentsToPane(Container contentPanel) {
        JPanel jPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);
        jPanel.setLayout(flowLayout);

        actualTurn = new JLabel();
        jPanel.add(actualTurn);
        add(jPanel, BorderLayout.NORTH);

        gridCenter = new JPanel(new GridLayout(6, 6));
        questionsBtn = new JButton[6][6];


        for (int i = 0; i < questionsBtn.length; i++) {
            for (int j = 0; j < questionsBtn[i].length; j++) {
                if (i == 0) {
                    System.out.println("i->" + i);
                    categoriesLabels[j] = new JLabel("");
                    categoriesLabels[j].setText("");
                    gridCenter.add(categoriesLabels[j]);
                } else {
                    questionsBtn[i][j] = new JButton(i + "00$");
                    gridCenter.add(questionsBtn[i][j]);
                }
            }
        }

        playerScores = new JLabel[2];

        gridSouth = new JPanel(new GridLayout(1, 2));
        for (int i = 0; i < playerScores.length; i++) {
            playerScores[i] = new JLabel();
            playerScores[i].setHorizontalAlignment(JLabel.CENTER);
            gridSouth.add(playerScores[i]);
        }
        contentPanel.add(gridCenter, BorderLayout.CENTER);
        contentPanel.add(gridSouth, BorderLayout.SOUTH);
    }
}