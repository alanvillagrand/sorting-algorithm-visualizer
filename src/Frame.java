import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Frame extends JFrame {

    public Frame() {
        this.setTitle("Sorting Visualizer"); //sets title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        this.setSize(1280,720); //set window dimensions
        this.setLocationRelativeTo(null); //center window
        this.setLayout(new BorderLayout());
        //this.getContentPane().setBackground(new Color(123, 50, 250)); //change color of background

        SortingPanel sortingPanel = new SortingPanel(10);
        ControlPanel controlPanel = new ControlPanel(sortingPanel);
        this.add(sortingPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.NORTH);

        this.setVisible(true); //make frame visible
    }

    public void loadLabels() {
        JLabel label = new JLabel("Sorting Visualizer");
        label.setForeground(Color.green);
        label.setFont(new Font("MV Boli", Font.PLAIN, 40));
        label.setBackground(Color.black);
        label.setOpaque(true);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        //label.setBounds(100, 100, 250, 250);

        this.add(label);
    }
}
