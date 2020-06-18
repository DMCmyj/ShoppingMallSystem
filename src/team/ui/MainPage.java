package team.ui;

import javax.swing.*;

public class MainPage extends JFrame {
    JFrame window;

    JButton goods_sold;
    JButton goods_buy;
    JButton add_goods;
    JButton delete_goods;
    JButton change_goods;

    public MainPage(){
        window = new JFrame("商品管理系统登录页面");
        window.setLayout(null);
        window.setSize(400,300);
        //设置窗口在屏幕居中
        window.setLocationRelativeTo(null);

        goods_sold = new JButton("商品出售");
        goods_sold.setBounds(20,20,100,50);
        window.add(goods_sold);

        window.setVisible(true);
    }
}
