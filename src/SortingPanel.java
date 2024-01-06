import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * The SortingPanel class that creates a JPanel which includes
 * all the array functionality and graphics.
 */
public class SortingPanel extends JPanel {

    final int MIN_VALUE = 5;
    final int MAX_VALUE = 500;
    private final int[] array;
    private final ArrayList<JLabel> arrayLabels;
    private final Sort sort;

    public SortingPanel(int arraySize) {

        // Assign variables
        array = new int[arraySize];
        arrayLabels = new ArrayList<>(arraySize);
        sort = new Sort();

        // Generate random array
        this.generateArray(MIN_VALUE, MAX_VALUE);
        this.displayArray();
    }

    public void paintComponent(Graphics g) {
        // Set graphic attributes
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 150, 255));

        // Draw array values as rectangles
        int space = 0;
        int rectX = (this.getSize().width / 2) - (array.length * 10 + 30);
        int rectY = this.getSize().height;

        for (int i = 0; i < array.length; i++) {
            g2d.drawRect(rectX + space, rectY, 20, -(array[i]));
            g2d.fillRect(rectX + space, rectY, 20, -(array[i]));
            space += 30;
        }
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
            arrayLabels.add(element);
        }
    }

    public void updateArray() {
        this.generateArray(MIN_VALUE, MAX_VALUE);
        for (int i = 0; i < arrayLabels.size(); i++) {
            arrayLabels.get(i).setText(Integer.toString(array[i]));
        }
        repaint();
    }

    public void bubbleSortArray() {
        sort.bubbleSort(array);
        System.out.println(Arrays.toString(array));
        for (int i = 0; i < arrayLabels.size(); i++) {
            arrayLabels.get(i).setText(Integer.toString(array[i]));
        }
        repaint();
    }
}
