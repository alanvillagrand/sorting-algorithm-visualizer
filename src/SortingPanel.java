import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SortingPanel extends JPanel {
    final int MIN_VALUE = 5;
    final int MAX_VALUE = 500;
    private final int[] array;
    private final ArrayList<JLabel> arrayLabels;
    private Sort sort;

    public SortingPanel(int initialSize) {
        //this.setBackground(Color.green);
        this.setPreferredSize(new Dimension(100, 100));
        //this.setLayout(new BorderLayout());

        array = new int[initialSize];
        arrayLabels = new ArrayList<JLabel>(initialSize);
        sort = new Sort();

        this.generateArray(MIN_VALUE, MAX_VALUE);
        this.displayArray();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 150, 255));

        int space = 0;

        for (int i = 0; i < array.length; i++) {
            g2d.drawRect(430 + space, 200, 20, array[i]);
            g2d.fillRect(430 + space, 200, 20, array[i]);
            space += 30;
        }

        System.out.println("Paint called");
    }

    private void generateArray(int min, int max) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
    }

    private void displayArray() {
        for (int i = 0; i < array.length; i++) {
            JLabel element = new JLabel(Integer.toString(array[i]));
            element.setForeground(Color.black);
            this.add(element);
            //element.setHorizontalAlignment(JLabel.CENTER);
            arrayLabels.add(element);
        }
    }

    public void updateArray() {
        this.generateArray(MIN_VALUE, MAX_VALUE);
        for (int i = 0; i < arrayLabels.size(); i++) {
            arrayLabels.get(i).setText(Integer.toString(array[i]));
        }
    }

    public void bubbleSortArray() {
        sort.bubbleSort(array);
        System.out.println(Arrays.toString(array));
        for (int i = 0; i < arrayLabels.size(); i++) {
            arrayLabels.get(i).setText(Integer.toString(array[i]));
        }
    }
}
