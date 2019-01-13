import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JeopardyFinalController implements ActionListener {
    static final String questionsSrc = "questions/";
    JeopardyFinalView view;
    ModelPlayer playerOne, playerTwo;
    String[] categories;
    ModelGame game;
    ArrayList<ModelQuestion> questions;
    int index;
    int[] intAux = {100,200,300,400,500};



    public JeopardyFinalController(ModelPlayer playerOne, ModelPlayer playerTwo, String[] categories, ModelGame game, int index){
        this.index=index;
        this.playerOne =playerOne;
        this.playerTwo = playerTwo;
        questions = new ArrayList<>();
        this.game = game;
        this.categories = categories;
        for (int i = 0; i <categories.length ; i++) {
            for (int j = 0; j <intAux.length ; j++) {
                questions.add(openQuestion(categories[i],intAux[j]));
            }
        }

        view = new JeopardyFinalView();
        view.jLabelFinalRound.setText(game.getCurrentPlayer().getName());


        view.jLabelQuestions.setText(questions.get(index).getQuestion());
        for (int i = 0; i < view.jButtonsQuestions.length ; i++) {
            view.jButtonsQuestions[i].addActionListener(this);
            view.jButtonsQuestions[i].setText(questions.get(index).getAnswer().get(i).getAnswer());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean correcto = false;

        if (e.getSource() instanceof JButton){
            JButton btn = (JButton) e.getSource();
            for(ModelAnswer answer : questions.get(index).getAnswer()){
                if (answer.getAnswer().equals(btn.getText()) && answer.isCorrect()){
                    correcto = true;
                }
            }
        }
        if (correcto){
            index++;
            game.advanceTurn();
            new JeopardyFinalController(playerOne, playerTwo, categories, game,index);
        }else{
            game.getCurrentPlayer().setMoney(-1);
            new JeopardyScoreController(playerOne, playerTwo);
            view.dispose();

        }
    }

    public ModelQuestion openQuestion(String categoryName, int questionValue){
        Path path = Paths.get(questionsSrc+categoryName+".txt");
        ModelQuestion question = null;
        BufferedReader br=null;
        String line;
        String[] splittedLine, splittedAns;

        try {
            br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            while ((line=br.readLine())!=null){
                splittedLine=line.split("::");
                if (Integer.parseInt(splittedLine[1])==questionValue){
                    if (game.isNextRound()) {
                        question = new ModelQuestion(splittedLine[0],questionValue*2);
                    }else{
                        question = new ModelQuestion(splittedLine[0],questionValue);
                    }
                    line=br.readLine();
                    splittedLine=line.split("::");
                    for (String ans: splittedLine){
                        splittedAns= ans.split(";;");
                        question.getAnswer().add(new ModelAnswer(splittedAns[0],splittedAns[1]));
                    }
                    break;
                }else{
                    br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return question;
    }
}
