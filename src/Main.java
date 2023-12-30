import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Frame frame = new Frame();
        SortingPanel sortingPanel = new SortingPanel(10);
        ControlPanel controlPanel = new ControlPanel(sortingPanel);
        frame.add(sortingPanel);
        //frame.add(controlPanel);

    }
}