package Layout;

import javax.swing.*;

public class NullLayoutDemo extends JFrame {

    private JLabel firstname;
    private JLabel lastname;
    private JTextField firstnameField;
    private JTextField lastnameField;

    public NullLayoutDemo() {
        //设置固定布局
        setLayout(null);

        firstname = new JLabel("First Name");
        lastname = new JLabel("Last Name");
        firstnameField = new JTextField();
        lastnameField = new JTextField();

        firstname.setBounds(10, 10, 100, 20);
        lastname.setBounds(10, 40, 100, 20);
        firstnameField.setBounds(10, 80, 100, 20);
        lastnameField.setBounds(10, 120, 100, 20);

        add(firstname);
        add(lastname);
        add(firstnameField);
        add(lastnameField);

        setTitle("Null Layout Demo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        NullLayoutDemo demo = new NullLayoutDemo();
        demo.setVisible(true);
    }
}
