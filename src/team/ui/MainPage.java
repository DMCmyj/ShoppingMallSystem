package team.ui;

import javax.swing.*;

public class MainPage extends JFrame {
    JFrame window;

    public MainPage(){
        window = new JFrame("商品管理系统登录页面");
        window.setLayout(null);
        window.setSize(400,300);
        //设置窗口在屏幕居中
        window.setLocationRelativeTo(null);

        window.setVisible(true);
    }
}
