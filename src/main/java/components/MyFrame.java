package components;

import javax.swing.*;
//继承
public class MyFrame extends JFrame {
    public MyFrame() {
        setTitle("My Frame");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MyFrame();
    }
}
