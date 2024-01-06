import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Frame extends JFrame {

    SortingPanel sortingPanel;
    ControlPanel controlPanel;

    public Frame() {
        // Set up frame
        this.setTitle("Sorting Visualizer"); // sets title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow exit out of application
        this.setSize(1280,720); // set window dimensions
        this.setLocationRelativeTo(null); // center window
        this.setLayout(new BorderLayout()); // set a border layout

        // Create panels
        sortingPanel = new SortingPanel(10);
        controlPanel = new ControlPanel(sortingPanel);

        // Add panels
        this.add(sortingPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.NORTH);

        this.setVisible(true); //make frame visible
    }

}
