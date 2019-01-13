import javax.swing.*;
import java.awt.*;

public class JeopardyQuestView extends JFrame {
    public JButton[] jButtonsQuestions;
    public JLabel jLabelQuestions, jLabelTime, jLabelFinalRound;


    public JeopardyQuestView() {
        JFrame frame = new JFrame("Jeopardy");
        addComponentsToPane(frame.getContentPane());
        frame.setSize(1366,768);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void addComponentsToPane(Container contentPanel) {
        jLabelQuestions = new JLabel();

        JPanel jPanelBoxLayout = new JPanel();
        jPanelBoxLayout.setLayout(new BoxLayout(jPanelBoxLayout, BoxLayout.Y_AXIS));
        jLabelTime = new JLabel("");
        jLabelTime.setHorizontalAlignment(JLabel.CENTER);

        jLabelFinalRound = new JLabel("");

        jButtonsQuestions = new JButton[4];

        JPanel questionsTable = new JPanel();
        questionsTable.setLayout(new GridLayout(2, 2));

        for (int i = 0; i < jButtonsQuestions.length; i++) {
            jButtonsQuestions[i] = new JButton("");
            questionsTable.add(jButtonsQuestions[i]);
        }
        contentPanel.add(jLabelQuestions, BorderLayout.NORTH);
        contentPanel.add(questionsTable, BorderLayout.CENTER);
        jPanelBoxLayout.add(jLabelTime);
        jPanelBoxLayout.add(jLabelFinalRound);
        contentPanel.add(jPanelBoxLayout, BorderLayout.SOUTH);

    }
}
