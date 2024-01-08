import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * The SortingPanel class that creates a JPanel which includes
 * all the array functionality and graphics.
 */
public class SortingPanel extends JPanel implements ActionListener {

    final int MIN_VALUE = 5;
    final int MAX_VALUE = 500;
    private final int[] array;
    private final ArrayList<JLabel> arrayLabels;
    private final Sort sort;
    Timer checkTimer;
    Timer swtichTimer;
    boolean bubbleSortRunning;
    boolean switchElements;
    int selectedIndex1;
    int selectedIndex2;
    int indexVelocity;
    int innerLoopCompletions;
    int uncheckedElements;

    public SortingPanel(int arraySize) {

        // Assign variables
        array = new int[arraySize];
        arrayLabels = new ArrayList<>(arraySize);
        sort = new Sort();
        bubbleSortRunning = false;
        switchElements = false;
        selectedIndex1 = -1;
        selectedIndex2 = -1;
        indexVelocity = 0;
        innerLoopCompletions = 0;
        uncheckedElements = 0;

        // Generate random array
        this.generateArray(MIN_VALUE, MAX_VALUE);
        this.displayArray();

        checkTimer = new Timer(100, this);
        swtichTimer = new Timer(0, this);
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
            if (i == selectedIndex1 || i == selectedIndex2) {
                g2d.setColor(Color.green);
                if (switchElements) {
                    if (i == selectedIndex1) {
                        g2d.drawRect(rectX + space + indexVelocity, rectY, 20, -(array[i]));
                        g2d.fillRect(rectX + space + indexVelocity, rectY, 20, -(array[i]));
                    }
                    else {
                        g2d.drawRect(rectX + space - indexVelocity, rectY, 20, -(array[i]));
                        g2d.fillRect(rectX + space - indexVelocity, rectY, 20, -(array[i]));
                    }
                }
                else {
                    g2d.drawRect(rectX + space, rectY, 20, -(array[i]));
                    g2d.fillRect(rectX + space, rectY, 20, -(array[i]));
                }
            }
            else {
                if (array.length - innerLoopCompletions <= i) {
                    g2d.setColor(Color.orange);
                }
                else {
                    g2d.setColor(new Color(0, 150, 255));
                }
                g2d.drawRect(rectX + space, rectY, 20, -(array[i]));
                g2d.fillRect(rectX + space, rectY, 20, -(array[i]));
            }
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
        bubbleSortRunning = true;
        uncheckedElements = array.length;
        selectedIndex1 = 0;
        selectedIndex2 = 1;
        checkTimer.start();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (array[selectedIndex1] > array[selectedIndex2]) {
            if (indexVelocity >= 30) {
                switchElements = false;
                int temp = array[selectedIndex1];
                array[selectedIndex1] = array[selectedIndex2];
                array[selectedIndex2] = temp;
                swtichTimer.stop();
                checkTimer.start();
                indexVelocity = 0;
            }
            else {
                switchElements = true;
                checkTimer.stop();
                swtichTimer.start();
                indexVelocity++;
            }
        }
        else if (uncheckedElements <= 1) {
            bubbleSortRunning = false;
            selectedIndex1 = selectedIndex2 = -1;
            innerLoopCompletions = 0;
            uncheckedElements = 0;
            checkTimer.stop();
        }
        else if (selectedIndex2 >= uncheckedElements - 1) {
            innerLoopCompletions++;
            uncheckedElements--;
            selectedIndex1 = 0;
            selectedIndex2 = 1;
        }
        else {
            selectedIndex1++;
            selectedIndex2++;
        }
        repaint();
    }
}
