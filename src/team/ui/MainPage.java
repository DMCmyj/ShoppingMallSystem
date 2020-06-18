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
        window.setSize(600,400);
        //设置窗口在屏幕居中
        window.setLocationRelativeTo(null);

        goods_sold = new JButton("商品出售");
        goods_sold.setBounds(20,20,100,50);
        window.add(goods_sold);

        goods_buy = new JButton("商品买入");
        goods_buy.setBounds(20,80,100,50);
        window.add(goods_buy);

        add_goods = new JButton("添加商品");
        add_goods.setBounds(20,140,100,50);
        window.add(add_goods);

        delete_goods = new JButton("删除商品");
        delete_goods.setBounds(20,200,100,50);
        window.add(delete_goods);

        change_goods = new JButton("修改商品");
        change_goods.setBounds(20,140,100,50);
        window.add(change_goods);

        window.setVisible(true);
    }
}
