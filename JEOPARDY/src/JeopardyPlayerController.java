import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JeopardyPlayerController implements ActionListener {
    private JeopardyInsertPlayer newUser;
    private ModelPlayer playerOne, playerTwo;

    public JeopardyPlayerController(JeopardyInsertPlayer view) {
        this.newUser = view;
        newUser.nextButton.addActionListener(this);
        newUser.rulesButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newUser.nextButton) {
            if (newUser.inputOne.getText().isEmpty() || newUser.inputTwo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insert 2 players");
            } else {
                if (newUser.inputOne.getText().equals(newUser.inputTwo.getText())) {
                    playerOne = new ModelPlayer(newUser.inputOne.getText() + "(1)");
                    playerTwo = new ModelPlayer(newUser.inputTwo.getText() + "(2)");
                } else {
                    playerOne = new ModelPlayer(newUser.inputOne.getText());
                    playerTwo = new ModelPlayer(newUser.inputTwo.getText());
                }
                JeopardyCategoryController cc = new JeopardyCategoryController(playerOne, playerTwo);
            }
        }
    }
}
