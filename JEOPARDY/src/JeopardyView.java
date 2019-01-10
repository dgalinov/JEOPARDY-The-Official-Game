import javax.swing.*;
import java.awt.*;

public class JeopardyView {
    public static void main(String[] args) {

        CreateAndShowGUI();
    }

    private static void CreateAndShowGUI(){
        JFrame frame = new JFrame("Add user");

        addComponentsToPane(frame.getContentPane());

        frame.setSize(1000,500);

        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    private static void addComponentsToPane(Container pane){

    }
}