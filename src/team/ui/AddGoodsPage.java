package team.ui;

import team.db.LinkDB;
import team.sys.Goods;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Objects;

public class AddGoodsPage extends JFrame {
    JFrame window;

    JLabel goods_name;
    JLabel goods_type;
    JLabel goods_price;
    JLabel goods_num;

    JTextField goodsName;
    JTextField goodsType;
    JTextField goodsPrice;
    JTextField goodsNum;

    JButton submit;
    public static Goods newGoods = null;

    public AddGoodsPage(){
        window = new JFrame("添加商品");
        window.setLayout(null);
        window.setSize(400,360);
        window.setLocationRelativeTo(null);

        goods_name = new JLabel("商品名称:");
        goods_name.setBounds(50,20,80,50);
        window.add(goods_name);
        goodsName = new JTextField();
        goodsName.setBounds(120,30,200,30);
        window.add(goodsName);

        goods_type = new JLabel("商品类别:");
        goods_type.setBounds(50,70,80,50);
        window.add(goods_type);
        goodsType = new JTextField();
        goodsType.setBounds(120,80,200,30);
        window.add(goodsType);

        goods_price = new JLabel("商品价格:");
        goods_price.setBounds(50,120,80,50);
        window.add(goods_price);
        goodsPrice = new JTextField();
        goodsPrice.setBounds(120,130,200,30);
        window.add(goodsPrice);

        goods_num = new JLabel("商品数量:");
        goods_num.setBounds(50,170,80,50);
        window.add(goods_num);
        goodsNum = new JTextField();
        goodsNum.setBounds(120,180,200,30);
        window.add(goodsNum);

        submit = new JButton("确认添加");
        submit.setBounds(50,220,270,50);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals(goodsName.getText(), "") && !Objects.equals(goodsNum.getText(), "") && !Objects.equals(goodsPrice.getText(), "") && !Objects.equals(goodsType.getText(), "")){
                    Float price = Float.parseFloat(goodsPrice.getText());
                    int num = Integer.parseInt(goodsNum.getText());
                    newGoods = new Goods(goodsName.getText(),price,goodsType.getText(),num);
                    //向数据库中添加新的商品
                    LinkDB.AddGoods(newGoods);
                    JOptionPane.showMessageDialog(null,"添加成功！","提示",JOptionPane.PLAIN_MESSAGE);
                    window.setVisible(false);
                    MainPage mainPage = new MainPage();
                }else {
                    JOptionPane.showMessageDialog(null,"以上内容均不能为空！","警告",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        window.add(submit);

        window.setVisible(true);
    }
}
