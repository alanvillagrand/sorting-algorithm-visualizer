import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements ActionListener {
    SortingPanel sortingPanel;
    JButton generateArrButton;
    public ControlPanel(SortingPanel sortingPanel) {
        this.sortingPanel = sortingPanel;

        generateArrButton = new JButton();
        generateArrButton.addActionListener(this);
        this.add(generateArrButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateArrButton) {
            sortingPanel.updateArray();
        }
    }
}
