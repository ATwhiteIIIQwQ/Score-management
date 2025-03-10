package components;

import javax.swing.*;

public class Dialog {

    private JDialog dialog;

    public Dialog() {
        dialog = new JDialog();

        dialog.setModal(true);
        dialog.setTitle("Dialog");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        dialog.setSize(400, 300);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new Dialog();
    }
}
