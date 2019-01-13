import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public class JeopardyCategoryController implements ActionListener {
    private ModelPlayer playerOne, playerTwo;
    private ModelCategory view;
    HashSet<String> categorieTitles, unselectedCategories;
    private int userChosenTime;

    Path path = Paths.get("categories.txt");


    public  JeopardyCategoryController(ModelPlayer playerOne, ModelPlayer playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        categorieTitles = new HashSet<>();

        try {
            String[] categoriesName = getCategories(path);
            int categoriesNameSizeHalf = categoriesName.length/2;
            this.view = new ModelCategory(categoriesName.length);


            view.nextLayout.addActionListener(this);
            view.userOne.setText(playerOne.getName());
            view.userTwo.setText(playerTwo.getName());


            for (int i = 0; i < categoriesNameSizeHalf; i++) {
                view.categoryCheckBoxOne[i].setText(categoriesName[i]);
                System.out.println(i+": "+categoriesName[i]);
                view.categoryCheckBoxOne[i].addActionListener(this);
            }

            for (int i = 0; i < categoriesNameSizeHalf ; i++) {
                view.categoryCheckBoxTwo[i].setText(categoriesName[i+categoriesNameSizeHalf]);
                System.out.println(categoriesName[i+4]);
                view.categoryCheckBoxTwo[i].addActionListener(this);
            }
            for (int i = 0; i < 4; i++) {
                view.timeLabelJRadioButton[i].addActionListener(this);
            }

        }catch (IOException e){
            System.out.println("File not found");

        }
    }


    private String[] getCategories(Path path) throws IOException {
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        String categories = reader.readLine();
        String[]categories_name;
        categories_name = categories.split(";");
        return categories_name;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JRadioButton){
            String checkBoxSelected = ((JRadioButton) e.getSource()).getText();
            userChosenTime = Integer.parseInt(checkBoxSelected);
        }
        view.checkForCheckBoxMaximSelection();
        if (e.getSource() instanceof JCheckBox){
            String checkStr= ((JCheckBox) e.getSource()).getText();
            System.out.println(checkStr);
            if (categorieTitles.contains(checkStr)){
                categorieTitles.remove(checkStr);
            }else{
                categorieTitles.add(checkStr);
            }
            System.out.println(categorieTitles);
        }

        if (e.getSource()==view.nextLayout){
            if (categorieTitles.size()==6){
                try {
                    setUnselectedCategories();
                    new JeopardyTableController(playerOne,playerTwo,categorieTitles,unselectedCategories, userChosenTime);
                } catch (IOException e1) {
                    e1.getMessage();
                }
                view.dispose();
            }else {
                JOptionPane.showMessageDialog(null,"You must need to select 6 category");
            }
        }
    }
    void setUnselectedCategories(){
        Path categoriesPath = Paths.get("categorias.txt");
        String line;
        String[] splittedLine;
        unselectedCategories = new HashSet<>();
        try {
            BufferedReader br = Files.newBufferedReader(categoriesPath, StandardCharsets.UTF_8);
            line=br.readLine();
            splittedLine = line.split(";");
            for (String elem: splittedLine){
                if (!categorieTitles.contains(elem)){
                    unselectedCategories.add(elem);
                    System.out.println(unselectedCategories);
                }
            }
        } catch (IOException e) {
            System.out.println("File doesn't exist "+e.getMessage());
        }
    }
}
