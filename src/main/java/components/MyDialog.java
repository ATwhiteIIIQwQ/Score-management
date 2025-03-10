package components;

import javax.swing.*;

public class MyDialog extends JDialog {
    public MyDialog() {
        setTitle("My Dialog");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MyDialog();
    }
}
