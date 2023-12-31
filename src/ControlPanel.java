import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements ActionListener {
    SortingPanel sortingPanel;
    JButton generateArrButton;
    JButton bubbleSortButton;
    public ControlPanel(SortingPanel sortingPanel) {
        this.sortingPanel = sortingPanel;
        this.setBackground(new Color(0, 150, 255));
        this.setPreferredSize(new Dimension(100, 100));

        generateArrButton = new JButton("Generate array");
        generateArrButton.addActionListener(this);

        bubbleSortButton = new JButton("Bubble sort");
        bubbleSortButton.addActionListener(this);

        this.add(generateArrButton);
        this.add(bubbleSortButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateArrButton) {
            sortingPanel.updateArray();
        }
        else if (e.getSource() == bubbleSortButton) {
            sortingPanel.bubbleSortArray();
        }
    }
}
