package components;

import javax.swing.*;
import java.awt.*;

public class Panel extends JFrame {

    private JPanel panel;

    public Panel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        add(panel);

        setTitle("Panel");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Panel();
    }
}
