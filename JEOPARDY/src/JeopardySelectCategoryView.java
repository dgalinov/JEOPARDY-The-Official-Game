import javax.swing.*;
import java.awt.*;

public class JeopardySelectCategoryView extends JFrame {
    public JLabel userOne, userTwo, title, timeLabel;
    public JCheckBox[] categoryCheckBoxOne, categoryCheckBoxTwo;
    public JRadioButton[] timeLabelJRadioButton;
    public JButton nextLayout;
    public JPanel gridCenter, player1Col, player2Col, timeCol;
    private int categoriesNumber;

    public JeopardySelectCategoryView(int number) {

        this.categoriesNumber = number;
        JFrame frame = new JFrame("Jeopardy");
        addComponentsToPane(frame.getContentPane());
        frame.setSize(1366, 768);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void addComponentsToPane(Container contentPanel) {
        title = new JLabel("Category", SwingConstants.CENTER);
        contentPanel.add(title, BorderLayout.NORTH);
        gridCenter = new JPanel();
        gridCenter.setLayout(new GridLayout(1, 3));

        player1Col = new JPanel();
        player1Col.setLayout(new BoxLayout(player1Col, BoxLayout.Y_AXIS));

        userOne = new JLabel();
        player1Col.add(userOne);

        categoryCheckBoxOne = new JCheckBox[categoriesNumber / 2];
        for (int i = 0; i < categoryCheckBoxOne.length; i++) {
            categoryCheckBoxOne[i] = new JCheckBox();
            player1Col.add(categoryCheckBoxOne[i]);
        }
        gridCenter.add(player1Col);

        player2Col = new JPanel();
        player2Col.setLayout(new BoxLayout(player2Col, BoxLayout.Y_AXIS));

        userTwo = new JLabel();
        player2Col.add(userTwo);

        categoryCheckBoxTwo = new JCheckBox[categoriesNumber / 2];
        for (int i = 0; i < categoryCheckBoxTwo.length; i++) {
            categoryCheckBoxTwo[i] = new JCheckBox();
            player2Col.add(categoryCheckBoxTwo[i]);
        }

        gridCenter.add(player2Col);

        timeCol = new JPanel();
        timeCol.setLayout(new BoxLayout(timeCol, BoxLayout.Y_AXIS));


        timeLabel = new JLabel("Time");
        timeCol.add(timeLabel);

        timeLabelJRadioButton = new JRadioButton[4];


        ButtonGroup radioButtonGroup = new ButtonGroup();
        for (int i = 1; i <= timeLabelJRadioButton.length; i++) {
            timeLabelJRadioButton[i - 1] = new JRadioButton();
            timeLabelJRadioButton[i - 1].setText(String.valueOf(i * 5));
            radioButtonGroup.add(timeLabelJRadioButton[i - 1]);
            timeCol.add(timeLabelJRadioButton[i - 1]);
        }
        gridCenter.add(timeCol);

        add(gridCenter, BorderLayout.CENTER);

        nextLayout = new JButton("Next");
        contentPanel.add(nextLayout, BorderLayout.SOUTH);
    }

    public void checkForCheckBoxMaximSelection() {
        int countCheck1 = 0;
        int countCheck2 = 0;
        for (JCheckBox check : categoryCheckBoxOne) {
            if (check.isSelected()) countCheck1++;
        }
        for (JCheckBox check : categoryCheckBoxTwo) {
            if (check.isSelected()) countCheck2++;
        }
        if (countCheck1 >= 3) {
            for (JCheckBox check : categoryCheckBoxOne) {
                check.setEnabled(false);
            }
        }
        if (countCheck2 >= 3) {
            for (JCheckBox check : categoryCheckBoxTwo) {
                check.setEnabled(false);
            }
        }

    }
}
