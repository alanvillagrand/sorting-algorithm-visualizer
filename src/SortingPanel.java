import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SortingPanel extends JPanel implements ActionListener {
    final int MIN_VALUE = 5;
    final int MAX_VALUE = 500;
    private final int[] array;
    private ArrayList<JLabel> arrayLabels;
    JButton gennerateArrButton;

    public SortingPanel(int initialSize) {
        array = new int[initialSize];
        arrayLabels = new ArrayList<JLabel>(initialSize);
        this.generateArray(MIN_VALUE, MAX_VALUE);
        this.displayArray();

        gennerateArrButton = new JButton();
        gennerateArrButton.addActionListener(this);
        this.add(gennerateArrButton);
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

    private void updateArray() {
        this.generateArray(MIN_VALUE, MAX_VALUE);
        for (int i = 0; i < arrayLabels.size(); i++) {
            arrayLabels.get(i).setText(Integer.toString(array[i]));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gennerateArrButton) {
            this.updateArray();
        }
    }
}
