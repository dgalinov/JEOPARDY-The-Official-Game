import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class fileAction {
    private final String filePath;
    private final Question[][] question = new Question[6][5];
    private final String[] topic = new String[6];

    public fileAction(String filePath) {
        this.filePath = filePath ;
    }

    public void readFile() throws FileNotFoundException, IOException {
        String line;
        int i = 0;
        int j = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                if (j == 0){
                    topic[i] = line;
                } else {
                    String[] parts = line.split(";", 2);
                    Question q = new Question(parts[0], parts[1]);
                    question[i][j] = q;
                }

                i++;
                if(i == 6){
                    i = 0;
                    j++;
                }

            }
            /* m = new Movie(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9]);
            movies.add(m); */
        }


    }
}
