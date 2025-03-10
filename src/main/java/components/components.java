package components;

import javax.swing.*;

public class components extends JFrame {

    private JPanel panel;
    private JLabel label;
    private JTextField textField;

    public components() {
        panel = new JPanel();

        label = new JLabel("name:");

        textField = new JTextField();
        textField.setText("Tom");

        panel.add(label);
        panel.add(textField);

        add(panel);

        setTitle("Panel");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new components();
    }
}
