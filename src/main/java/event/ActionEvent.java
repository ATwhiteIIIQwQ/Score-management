package event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ActionEvent extends JFrame {

    private JPanel panel = new JPanel();
    private JButton red = new JButton("Red");
    private JButton blue = new JButton("Blue");
    private JButton green = new JButton("Green");

    public ActionEvent() {

        //注册侦听器对象
        red.addActionListener(new ButtonListener());
        blue.addActionListener(new ButtonListener());
        green.addActionListener(new ButtonListener());

        panel.add(red);
        panel.add(blue);
        panel.add(green);

        add(panel);

        setTitle("ActionEvent");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new ActionEvent();
    }

    //Action Key 侦听器
    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            String buttonString = e.getActionCommand();
            switch (buttonString) {
                case "Red":
                    panel.setBackground(Color.RED);
                    break;
                case "Blue":
                    panel.setBackground(Color.BLUE);
                    break;
                case "Green":
                    panel.setBackground(Color.GREEN);
            }
        }
    }
}
