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
import java.util.HashSet;
import java.util.Iterator;

public class JeopardyTableController implements ActionListener {
    private JeopardyTableView gameView;
    private String[] categories, unselectedCategories;
    private ModelPlayer[] players;
    static final String questionsSrc = "questions/";
    private ModelGame game;
    private int userChooseTime;



    public JeopardyTableController(ModelPlayer playerOne, ModelPlayer playerTwo, HashSet<String> categories, HashSet<String> unselectedCategories, int time) throws IOException {

        this.userChooseTime =time;
        players = new ModelPlayer[] {playerOne, playerTwo};
        gameView = new JeopardyTableView();
        this.game = new ModelGame(playerOne, playerTwo);
        gameView.actualTurn.setText("1");

        this.categories=fillCategoriesArrs(this.categories,categories,6);
        System.out.println(this.categories);

        this.unselectedCategories = fillCategoriesArrs(this.unselectedCategories,unselectedCategories,2);
        System.out.println(this.unselectedCategories);

        for (int j = 0; j < gameView.playerScores.length ; j++) {
            gameView.playerScores[j].setText(players[j].getName()+" : "+players[j].getMoney());
        }

        for (int j = 1; j < gameView.questionsBtn.length; j++) {
            for (int k = 0; k < gameView.questionsBtn[j].length ; k++) {
                gameView.questionsBtn[j][k].addActionListener(this);
            }
        }

        gameView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String categoryName=null;
        int questionValue;

        if (e.getSource() instanceof JButton){
            JButton pressedBtn = (JButton) e.getSource();
            questionValue = Integer.parseInt(pressedBtn.getText().substring(0,pressedBtn.getText().length()-1));
            System.out.println(questionValue);
            pressedBtn.setEnabled(false);
            for (int i=0;i<gameView.questionsBtn.length;i++){
                for (int j = 0; j < gameView.questionsBtn[i].length; j++) {
                    if (gameView.questionsBtn[i][j] == pressedBtn){
                        categoryName=gameView.categoriesLabels[j].getText();
                        System.out.println(categoryName);
                    }
                }
            }
            ModelQuestion quest = openQuestion(categoryName,questionValue);
            gameView.setVisible(false);
            new JeopardyQuestController(this,new JeopardyQuestView(), game.getCurrentPlayer(),quest, userChooseTime);
        }
        game.advanceTurn();
    }

    /*Este método nos permite sustraer una pregunta dado el nombre de la categoría y su puntuación.*/
    public ModelQuestion openQuestion(String categoryName, int questionValue){
        if (game.isNextRound()) questionValue/=2;
        Path path = Paths.get(questionsSrc+categoryName+".txt");
        ModelQuestion quest = null;
        BufferedReader br=null;
        String line;
        String[] splittedLine, splittedAns;

        try {
            br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            while ((line=br.readLine())!=null){
                splittedLine=line.split("::");
                if (Integer.parseInt(splittedLine[1])==questionValue){
                    if (game.isNextRound()) {
                        quest = new ModelQuestion(splittedLine[0],questionValue*2);
                    }else{
                        quest = new ModelQuestion(splittedLine[0],questionValue);
                    }
                    line=br.readLine();
                    splittedLine=line.split("::");
                    for (String ans: splittedLine){
                        splittedAns= ans.split(";;");
                        quest.getAnswer().add(new ModelAnswer(splittedAns[0],splittedAns[1]));
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
        return quest;
    }

    void updateData(ModelPlayer player){
        int questionValue=0;

        for (int i = 0; i < gameView.playerScores.length ; i++) {
            String aux = gameView.playerScores[i].getText();
            String[] auxArr = aux.split(":");

            if (player.getName().equals(auxArr[0].trim())){
                gameView.playerScores[i].setText(player.getName()+":"+player.getMoney());
            }
        }

        if (game.getRound()==10 && game.getActualTurn()==0){
            for (int i=0;i<gameView.questionsBtn.length;i++){
                for (int j = 0; j < gameView.questionsBtn[i].length; j++) {
                    if ( gameView.questionsBtn[i][j]!=null && gameView.questionsBtn[i][j].isEnabled()){
                        questionValue = Integer.parseInt(gameView.questionsBtn[i][j].getText().substring(0, gameView.questionsBtn[i][j].getText().length() - 1));
                        questionValue*=2;
                        gameView.questionsBtn[i][j].setText(String.valueOf(questionValue)+"$");
                    }
                }
            }
        }
        gameView.setVisible(true);
        gameView.actualTurn.setText(String.valueOf(game.getRound()));

        if (game.getRound()==game.end){
            if (players[0].getMoney()!=players[1].getMoney()){
                System.out.println("Fin de juego");
                JOptionPane.showMessageDialog(gameView,"Fin de juego");
                new JeopardyScoreController(players[0], players[1]);
                gameView.dispose();
            }else{
                System.out.println("Final round");
                JOptionPane.showMessageDialog(gameView,"Hora de desempatar");
                new JeopardyFinalController(players[0], players[1], unselectedCategories, game,0);

            }
        }
    }

    String[] fillCategoriesArrs(String[] arr, HashSet<String> hash, int arrSize){
        int x=0;
        String name;
        Iterator i = hash.iterator();

        arr = new String[arrSize];

        while (i.hasNext()){
            name = i.next().toString();
            System.out.println("x->"+x);
            if(arr.length==6){
                arr[x] = name;
                gameView.categoriesLabels[x].setText(name);
                System.out.println(name);
            }else{
                arr[x]=name;
            }
            x++;
        }
        return arr;
    }
}
