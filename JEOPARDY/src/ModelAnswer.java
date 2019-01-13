public class ModelAnswer {
    private String answer;
    private boolean correct;

    public ModelAnswer (String answer, String correct) {
        this.answer = answer;
        if (correct.equals("1")) {
            this.correct = true;
        } else {
            this.correct = false;
        }
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect () {
        return correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + answer + '\'' +
                ", correct=" + correct +
                '}';
    }
}
