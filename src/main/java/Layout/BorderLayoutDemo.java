package Layout;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutDemo extends JFrame {

    private JButton east = new JButton("East");
    private JButton west = new JButton("West");
    private JButton north = new JButton("North");
    private JButton south = new JButton("South");
    private JButton center = new JButton("Center");

    public BorderLayoutDemo() {

        setLayout(new BorderLayout());

        add(east, BorderLayout.EAST);
        west.setPreferredSize(new Dimension(100, 50));
        add(west, BorderLayout.WEST);
        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);

        setTitle("BorderLayout");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        BorderLayoutDemo demo = new BorderLayoutDemo();
        demo.setVisible(true);
    }
}
