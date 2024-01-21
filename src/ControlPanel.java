import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * The ControlPanel class that creates a JPanel which includes all
 * the control functionality and graphics.
 * - Alan Villagrand
 */
public class ControlPanel extends JPanel implements ActionListener, ChangeListener {

    SortingPanel sortingPanel;

    JPanel rightPanel;
    JPanel leftPanel;
    JPanel speedPanel;

    JButton generateArrButton;
    JButton bubbleSortButton;
    JButton insertionSortButton;
    JButton quickSortButton;
    JButton mergeSortButton;
    JButton pauseResumeButton;
    JButton skipButton;

    JLabel arrayLabel;
    JLabel sizeLabel;
    JLabel bubbleRunTimeLabel;
    JLabel insertionRunTimeLabel;
    JLabel quickRunTimeLabel;
    JLabel mergeRunTimeLabel;
    JLabel speedLabel;

    JTextField arrayField;
    JTextField sizeField;

    JSlider speedSlider;

    boolean paused;

    public ControlPanel(SortingPanel sortingPanel) {
        // Set panel attributes
        this.setBackground(new Color(0x1E3D57));
        this.setPreferredSize(new Dimension(100, 100));
        this.setLayout(new BorderLayout());

        paused = false;

        // Create Panels
        this.sortingPanel = sortingPanel;

        rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        rightPanel.setBackground(new Color(0x1E3D57));
        rightPanel.setPreferredSize(new Dimension(520, 100));

        leftPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        //leftPanel.setPreferredSize(new Dimension(200, 100));
        leftPanel.setBackground(new Color(0x1E3D57));

        speedPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        speedPanel.setBackground(new Color(0x1E3D57));

        // Create buttons
        generateArrButton = new JButton("Generate new array");
        generateArrButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateArrButton.setForeground(Color.white);
        generateArrButton.setBackground(new Color(66, 139, 202));
        generateArrButton.setFocusPainted(false);
        generateArrButton.setBorderPainted(false);
        generateArrButton.addActionListener(this);

        bubbleSortButton = new JButton("Bubble Sort");
        bubbleSortButton.setFont(new Font("Arial", Font.BOLD, 14));
        bubbleSortButton.setForeground(Color.white);
        bubbleSortButton.setBackground(new Color(66, 139, 202));
        bubbleSortButton.setFocusPainted(false);
        bubbleSortButton.setBorderPainted(false);
        bubbleSortButton.addActionListener(this);

        insertionSortButton = new JButton("Insertion Sort");
        insertionSortButton.setFont(new Font("Arial", Font.BOLD, 14));
        insertionSortButton.setForeground(Color.white);
        insertionSortButton.setBackground(new Color(66, 139, 202));
        insertionSortButton.setFocusPainted(false);
        insertionSortButton.setBorderPainted(false);
        insertionSortButton.addActionListener(this);

        quickSortButton = new JButton("Quick Sort");
        quickSortButton.setFont(new Font("Arial", Font.BOLD, 14));
        quickSortButton.setForeground(Color.white);
        quickSortButton.setBackground(new Color(66, 139, 202));
        quickSortButton.setFocusPainted(false);
        quickSortButton.setBorderPainted(false);
        quickSortButton.addActionListener(this);

        mergeSortButton = new JButton("Merge Sort");
        mergeSortButton.setFont(new Font("Arial", Font.BOLD, 14));
        mergeSortButton.setForeground(Color.white);
        mergeSortButton.setBackground(new Color(66, 139, 202));
        mergeSortButton.setFocusPainted(false);
        mergeSortButton.setBorderPainted(false);
        mergeSortButton.addActionListener(this);

        pauseResumeButton = new JButton("Pause");
        pauseResumeButton.setFont(new Font("Arial", Font.BOLD, 14));
        pauseResumeButton.setForeground(Color.white);
        pauseResumeButton.setBackground(new Color(66, 139, 202));
        pauseResumeButton.setFocusPainted(false);
        pauseResumeButton.setBorderPainted(false);
        pauseResumeButton.addActionListener(this);

        skipButton = new JButton("Skip");
        skipButton.setFont(new Font("Arial", Font.BOLD, 14));
        skipButton.setForeground(Color.white);
        skipButton.setBackground(Color.RED);
        skipButton.setFocusPainted(false);
        skipButton.setBorderPainted(false);
        skipButton.addActionListener(this);

        //Create labels
        arrayLabel = new JLabel("Array =");
        arrayLabel.setForeground(Color.white);

        sizeLabel = new JLabel("Size =");
        sizeLabel.setForeground(Color.white);

        speedLabel = new JLabel("Speed =");
        speedLabel.setForeground(Color.white);

        bubbleRunTimeLabel = new JLabel("              O(n^2)              ");
        bubbleRunTimeLabel.setForeground(Color.white);

        insertionRunTimeLabel = new JLabel("              O(n^2)                  ");
        insertionRunTimeLabel.setForeground(Color.white);

        quickRunTimeLabel = new JLabel("        O(nlogn)          ");
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
        sizeField.addActionListener(this);
        this.updateSizeField();

        // Create slider
        speedSlider = new JSlider(0, 1000, 0);
        speedSlider.addChangeListener(this);
        speedSlider.setBackground(new Color(0x1E3D57));

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

        speedPanel.add(speedLabel);
        speedPanel.add(speedSlider);
        speedPanel.add(pauseResumeButton);
        speedPanel.add(skipButton);

        // Add panels to main panel
        this.add(leftPanel, BorderLayout.WEST);
        this.add(speedPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
        this.hideSpeedControls();
    }

    private void updateArrayField() {
        String arrayString = Arrays.toString(sortingPanel.getArray());
        arrayField.setText(arrayString.substring(1, arrayString.length() - 1));
    }

    private void updateSizeField() {
        sizeField.setText(String.valueOf(sortingPanel.getArray().length));
    }

    private int[] stringToArray(String str) {
        String[] strValues = str.split(",");
        for (int i = 0; i < strValues.length; i++) {
            strValues[i] = strValues[i].trim();
        }

        int[] intArray = new int[strValues.length];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = Integer.parseInt(strValues[i]);
        }

        return intArray;
    }

    private void hideMainControls() {
        leftPanel.hide();
        rightPanel.hide();
    }

    private void showMainControls() {
        leftPanel.show();
        rightPanel.show();
    }

    private void hideSpeedControls() {
        speedPanel.hide();
    }

    private void showSpeedControls() {
        speedPanel.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateArrButton) {
            sortingPanel.updateArray();
            this.updateArrayField();
        }
        else if (e.getSource() == arrayField) {
            int[] newArray = stringToArray(arrayField.getText());
            sortingPanel.updateArray(newArray);
            this.updateArrayField();
            this.updateSizeField();

        }
        else if (e.getSource() == sizeField) {
            sortingPanel.resizeArray(Integer.parseInt(sizeField.getText()));
            this.updateArrayField();
            this.updateSizeField();
        }
        else if (e.getSource() == bubbleSortButton) {
            sortingPanel.bubbleSortArray();
            this.hideMainControls();
            this.showSpeedControls();
        }
        else if (e.getSource() == pauseResumeButton) {
            if (paused){
                sortingPanel.resumeSort();
                pauseResumeButton.setBackground(new Color(66, 139, 202));
                pauseResumeButton.setText("Pause");
                paused = false;
            }
            else {
                sortingPanel.pauseSort();
                pauseResumeButton.setBackground(new Color(66, 202, 109));
                pauseResumeButton.setText("Resume");
                paused = true;
            }
        }
        else if (e.getSource() == skipButton) {
            sortingPanel.skipSort();
            this.hideSpeedControls();
            this.showMainControls();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        sortingPanel.setSpeed(1000 - speedSlider.getValue());
    }
}
