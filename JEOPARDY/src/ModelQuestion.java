import java.util.ArrayList;

public class ModelQuestion {
    private int value;
    private String question;
    private ArrayList<ModelAnswer> answer;

    public ModelQuestion(String question, int value) {
        this.question = question;
        this.value = value;
        this.answer = new ArrayList<>();
    }

    public ArrayList<ModelAnswer> getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Question{" +
                "value=" + value +
                ", question='" + question + '\'' +
                ", answer=" + answer +
                '}';
    }
}
