package components;

import javax.swing.*;
//复合
public class Frame {

    private JFrame frame;

    public Frame() {
        frame = new JFrame();
        //窗口大小
        frame.setSize(800, 600);
        //关闭按钮的操作方式
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //居中显示
        frame.setLocationRelativeTo(null);
        //标题
        frame.setTitle("JFrame");
        //窗口可见
        frame.setVisible(true);
        //是否能被改变大小
        frame.setResizable(false);
    }
    public static void main(String[] args) {
        new Frame();
    }
}
