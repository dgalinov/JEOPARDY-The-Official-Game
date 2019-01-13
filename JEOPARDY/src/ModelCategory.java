import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ModelCategory {
    public AbstractButton nextLayout;
    public AbstractButton userOne;
    public AbstractButton userTwo;
    public AbstractButton[] categoryCheckBoxOne;
    public AbstractButton[] categoryCheckBoxTwo;
    public AbstractButton[] timeLabelJRadioButton;
    private String categoryType;
    private ArrayList<ModelQuestion> quest;

    public ModelCategory (int categoryType) throws IOException {
        this.categoryType = String.valueOf(categoryType);
        setQuestion();
    }

    private void setQuestion() throws IOException {
        quest = new ArrayList<>();
        String line;
        String[] separated;
        Path path = Paths.get("questions/" + categoryType + ".txt");
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        while ((line = reader.readLine()) != null) {
            separated = line.split("::");
            this.quest.add(new ModelQuestion(separated[0], Integer.parseInt(separated[1])));
        }
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public String toString() {
        return "ModelCategory{" +
                "categoryType='" + categoryType + '\'' +
                ", quest=" + quest +
                '}';
    }

    public void checkForCheckBoxMaximSelection() {
    }

    public void dispose() {
    }
}
