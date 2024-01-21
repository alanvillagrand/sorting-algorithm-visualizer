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
 * - Alan Villagrand
 */
public class SortingPanel extends JPanel implements ActionListener {

    final int MIN_VALUE = 5;
    final int MAX_VALUE = 500;
    private int[] array;
    private final Sort sort;
    Timer checkTimer;
    Timer switchTimer;
    boolean bubbleSortRunning;
    boolean switchElements;
    int selectedIndex1;
    int selectedIndex2;
    int indexVelocity;
    int innerLoopCompletions;
    int uncheckedElements;
    int speed;

    public SortingPanel(int arraySize) {

        // Assign variables
        array = new int[arraySize];
        sort = new Sort();
        bubbleSortRunning = false;
        switchElements = false;
        selectedIndex1 = -1;
        selectedIndex2 = -1;
        indexVelocity = 0;
        innerLoopCompletions = 0;
        uncheckedElements = 0;
        speed = 1000;

        // Generate random array
        this.generateArray(MIN_VALUE, MAX_VALUE);

        checkTimer = new Timer(speed, this);
        switchTimer = new Timer(0, this);
    }

    public void paintComponent(Graphics g) {
        // Set graphic attributes
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 150, 255));

        // Draw array values as rectangles
        int space = 0;
        int width = 20;
        int rectX = (this.getSize().width / 2) - 10 - ((array.length - 1) * 15);
        int rectY = this.getSize().height;

        //g2d.drawLine(this.getSize().width / 2, 100, this.getSize().width / 2, this.getSize().height);

        for (int i = 0; i < array.length; i++) {
            if (i == selectedIndex1 || i == selectedIndex2) {
                g2d.setColor(Color.green);
                if (switchElements) {
                    if (i == selectedIndex1) {
                        g2d.drawRect(rectX + space + indexVelocity, rectY, width, -(array[i]));
                        g2d.fillRect(rectX + space + indexVelocity, rectY, width, -(array[i]));
                    }
                    else {
                        g2d.drawRect(rectX + space - indexVelocity, rectY, width, -(array[i]));
                        g2d.fillRect(rectX + space - indexVelocity, rectY, width, -(array[i]));
                    }
                }
                else {
                    g2d.drawRect(rectX + space, rectY, width, -(array[i]));
                    g2d.fillRect(rectX + space, rectY, width, -(array[i]));
                }
            }
            else {
                if (array.length - innerLoopCompletions <= i) {
                    g2d.setColor(Color.orange);
                }
                else {
                    g2d.setColor(new Color(0, 150, 255));
                }
                g2d.drawRect(rectX + space, rectY, width, -(array[i]));
                g2d.fillRect(rectX + space, rectY, width, -(array[i]));
            }
            space = space + 30;
        }
    }

    private void generateArray(int min, int max) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
    }

    public void updateArray() {
        this.generateArray(MIN_VALUE, MAX_VALUE);
        repaint();
    }

    public void updateArray(int[] newArray) {
        this.array = newArray;
        repaint();
    }

    public void resizeArray(int newSize) {
        int originalSize = this.array.length;
        this.array = Arrays.copyOf(this.array, newSize);
        if (newSize > originalSize) {
            Random random = new Random();
            for (int i = originalSize; i < newSize; i++) {
                this.array[i] = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
            }
        }
        repaint();
    }

    public int[] getArray() {
        return array;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        checkTimer.stop();
        checkTimer = new Timer(this.speed, this);
        if (bubbleSortRunning && !switchElements) {
            checkTimer.start();
        }
    }

    public void pauseSort() {
        if (switchElements) {
            switchTimer.stop();
        }
        else {
            checkTimer.stop();
        }
    }

    public void resumeSort() {
        if (switchElements) {
            switchTimer.start();
        }
        else {
            checkTimer.start();
        }
    }

    public void skipSort() {
        checkTimer.stop();
        switchTimer.stop();
        bubbleSortRunning = false;
        selectedIndex1 = selectedIndex2 = -1;
        innerLoopCompletions = uncheckedElements = 0;
        sort.bubbleSort(array);
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
                switchTimer.stop();
                checkTimer.start();
                indexVelocity = 0;
            }
            else {
                switchElements = true;
                checkTimer.stop();
                switchTimer.start();
                if (this.speed > 900) {
                    indexVelocity += 1;
                } else if (this.speed > 800) {
                    indexVelocity += 3;
                } else if (this.speed > 700) {
                    indexVelocity += 6;
                } else if (this.speed > 600) {
                    indexVelocity += 9;
                } else if (this.speed > 500) {
                    indexVelocity += 12;
                } else if (this.speed > 400) {
                    indexVelocity += 15;
                } else if (this.speed > 300) {
                    indexVelocity += 18;
                } else if (this.speed > 200) {
                    indexVelocity += 21;
                } else if (this.speed > 100) {
                    indexVelocity += 24;
                } else {
                    indexVelocity += 27;
                }
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
