import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JeopardyQuestController implements ActionListener {
    private JeopardyTableController gc;
    private JeopardyQuestView view;
    private ModelPlayer player;
    private ModelQuestion question;
    private Timer timer;
    private int userChooseTime;

    public JeopardyQuestController(JeopardyTableController gc, JeopardyQuestView view, ModelPlayer player, ModelQuestion question, int time){
        this.userChooseTime = time;
        this.gc = gc;
        this.view = view;
        this.player = player;
        this.question = question;


        view.jLabelQuestions.setText(question.getQuestion());
        for (int i = 0; i < view.jButtonsQuestions.length ; i++) {
            view.jButtonsQuestions[i].addActionListener(this);
            view.jButtonsQuestions[i].setText(question.getAnswer().get(i).getAnswer());
        }

        timer = new Timer(1000, new ActionListener() {
            int count=userChooseTime;
            @Override
            public void actionPerformed(ActionEvent e) {
                count--;
                if (count >= 0) {
                    view.jLabelTime.setText(Integer.toString(count));
                } else {
                    ((Timer)(e.getSource())).stop();
                }
                if (count==0){
                    JOptionPane.showMessageDialog(null, "Times Out");
                    player.setMoney(-question.getValue());
                    gc.updateData(player);
                    view.dispose();
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        boolean correcto = false;
        if (e.getSource() instanceof JButton){
            JButton btn = (JButton) e.getSource();
            for(ModelAnswer answer : question.getAnswer()){
                if (answer.getAnswer().equals(btn.getText()) && answer.isCorrect()){
                    correcto = true;
                }
            }
            if (correcto){
                timer.stop();
                JOptionPane.showMessageDialog(null, "Correct");
                player.setMoney(question.getValue());
                gc.updateData(player);
                view.dispose();

            }else {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Wrong");
                player.setMoney(-question.getValue());
                gc.updateData(player);
                view.dispose();
            }
        }
    }
}
