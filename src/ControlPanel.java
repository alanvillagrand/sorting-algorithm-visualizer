import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * The ControlPanel class that creates a JPanel which includes all
 * the control functionality and graphics.
 */
public class ControlPanel extends JPanel implements ActionListener {

    SortingPanel sortingPanel;

    JPanel rightPanel;
    JPanel leftPanel;

    JButton generateArrButton;
    JButton bubbleSortButton;
    JButton insertionSortButton;
    JButton quickSortButton;
    JButton mergeSortButton;

    JLabel arrayLabel;
    JLabel sizeLabel;
    JLabel bubbleRunTimeLabel;
    JLabel insertionRunTimeLabel;
    JLabel quickRunTimeLabel;
    JLabel mergeRunTimeLabel;

    JTextField arrayField;
    JTextField sizeField;

    public ControlPanel(SortingPanel sortingPanel) {
        // Set panel attributes
        this.setBackground(new Color(0x1E3D57));
        this.setPreferredSize(new Dimension(100, 100));
        this.setLayout(new BorderLayout());

        // Create Panels
        this.sortingPanel = sortingPanel;

        rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        rightPanel.setBackground(new Color(0x1E3D57));
        rightPanel.setPreferredSize(new Dimension(450, 100));

        leftPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        //leftPanel.setPreferredSize(new Dimension(200, 100));
        leftPanel.setBackground(new Color(0x1E3D57));

        // Create buttons
        generateArrButton = new JButton("Generate new array");
        generateArrButton.addActionListener(this);

        bubbleSortButton = new JButton("Bubble Sort");
        bubbleSortButton.addActionListener(this);

        insertionSortButton = new JButton("Insertion Sort");
        insertionSortButton.addActionListener(this);

        quickSortButton = new JButton("Quick Sort");
        quickSortButton.addActionListener(this);

        mergeSortButton = new JButton("Merge Sort");
        mergeSortButton.addActionListener(this);

        //Create labels
        arrayLabel = new JLabel("Array =");
        arrayLabel.setForeground(Color.white);

        sizeLabel = new JLabel("Size =");
        sizeLabel.setForeground(Color.white);

        bubbleRunTimeLabel = new JLabel("          O(n^2)          ");
        bubbleRunTimeLabel.setForeground(Color.white);

        insertionRunTimeLabel = new JLabel("              O(n^2)              ");
        insertionRunTimeLabel.setForeground(Color.white);

        quickRunTimeLabel = new JLabel("    O(nlogn)        ");
        quickRunTimeLabel.setForeground(Color.white);

        mergeRunTimeLabel = new JLabel("        O(nlogn)    ");
        mergeRunTimeLabel.setForeground(Color.white);

        //Create text fields
        arrayField = new JTextField();
        arrayField.setPreferredSize(new Dimension(300, 20));
        arrayField.addActionListener(this);
        this.updateArrayField();

        sizeField = new JTextField();
        sizeField.setPreferredSize(new Dimension(30, 20));
        sizeField.setText(String.valueOf(sortingPanel.getArray().length));
        sizeField.addActionListener(this);

        // Add buttons to panels
        rightPanel.add(bubbleSortButton);
        rightPanel.add(insertionSortButton);
        rightPanel.add(quickSortButton);
        rightPanel.add(mergeSortButton);
        rightPanel.add(bubbleRunTimeLabel);
        rightPanel.add(insertionRunTimeLabel);
        rightPanel.add(quickRunTimeLabel);
        rightPanel.add(mergeRunTimeLabel);

        leftPanel.add(generateArrButton);
        leftPanel.add(arrayLabel);
        leftPanel.add(arrayField);
        leftPanel.add(sizeLabel);
        leftPanel.add(sizeField);

        // Add panels to main panel
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
    }

    private void updateArrayField() {
        String arrayString = Arrays.toString(sortingPanel.getArray());
        arrayField.setText(arrayString.substring(1, arrayString.length() - 1));
    }

    private int[] stringToArray(String str) {
        char[] charArray = str.toCharArray();
        int[] intArray = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            System.out.print(charArray[i]);
            System.out.print(" ");
        }
        return intArray;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateArrButton) {
            sortingPanel.updateArray();
            this.updateArrayField();
        }
        else if (e.getSource() == arrayField) {
            int[] array = stringToArray(arrayField.getText());

        }
        else if (e.getSource() == sizeField) {
            sortingPanel.resizeArray(Integer.parseInt(sizeField.getText()));
            this.updateArrayField();
        }
        else if (e.getSource() == bubbleSortButton) {
            sortingPanel.bubbleSortArray();
        }
    }
}
