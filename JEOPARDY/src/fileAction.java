import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class fileAction {
    private final String filePath;
    private final Question[][] question = new Question[5][6];
    private final String[] topic = new String[6];

    public fileAction(String filePath) {
        this.filePath = filePath ;
    }

    public void readFile() throws FileNotFoundException, IOException {
        String line;
        int i = 0;
        int j = 0;
        int x = 0;
        int z = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                if (z == 0){
                    topic[x] = line;
                } else {
                    String[] parts = line.split(";", 2);
                    Question q = new Question(parts[0], parts[1]);
                    question[i][j] = q;
                    i++;
                }
                z++;
                if (z == 5){
                    z = 0;
                    x++;
                }

                if(i == 5){
                    i = 0;
                    j++;
                }

            }
        }


    }
}
