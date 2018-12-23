import javax.swing.*;
import java.awt.*;

public class JeopardyView {
    public static void main(String[] args) {}

    public static void ShowGame() {
        JFrame frame = new JFrame();
        JLabel[][] questions = new JLabel[5][6];
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        for(int i = 0; i < questions.length; i++) {
            for(int j = 0; j < questions[i].length;j++) {
                int money = ((j + 1)+100);
                JLabel label = new JLabel(String.valueOf(money));
                gbc.gridx = i;
                gbc.gridy = j + 1;
                panel.add(label, gbc);
                questions[i][j] = label;
            }
        }
    }
}