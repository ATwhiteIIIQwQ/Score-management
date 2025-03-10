package Layout;

import javax.swing.*;
import java.awt.*;

public class FlowLayoutDemo extends JFrame{

    private JButton b1 = new JButton("Button 1");
    private JButton b2 = new JButton("Button 2");
    private JButton b3 = new JButton("Button 3");
    private JButton b4 = new JButton("Button 4");
    private JButton b5 = new JButton("Button 5");

    public FlowLayoutDemo() {
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        setTitle("FlowLayout");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        FlowLayoutDemo frame = new FlowLayoutDemo();
        frame.setVisible(true);
    }
}
