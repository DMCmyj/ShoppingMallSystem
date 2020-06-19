package team.ui;

import team.db.LinkDB;
import team.sys.Goods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Objects;

public class BuyGoodsPage {
    JLabel goods_name;
    JLabel goods_num;
    JLabel sell_num_tip;
    JTextField goodsSellNum;

    JButton submit;
    JFrame window;

    public BuyGoodsPage(Goods goods){
        window = new JFrame("商品出售页面");
        window.setLayout(null);
        window.setSize(400,360);
        window.setLocationRelativeTo(null);

        ResultSet resultSet = LinkDB.getAllGoods();
        goods_name = new JLabel("商品名称:" + goods.getGoods_name());
        goods_name.setBounds(50,20,400,50);
        goods_name.setFont(new Font("楷体",Font.PLAIN,25));
        window.add(goods_name);

        goods_num = new JLabel("商品现有数量：" + goods.getGoods_num());
        goods_num.setFont(new Font("楷体",Font.PLAIN,25));
        goods_num.setBounds(50,60,400,50);
        window.add(goods_num);

        sell_num_tip = new JLabel("请输入需要买入的数量：");
        sell_num_tip.setFont(new Font("楷体",Font.PLAIN,20));
        sell_num_tip.setBounds(50,120,400,50);
        window.add(sell_num_tip);
//绘制水平线
        DrawLinePanel drawLinePanel = new DrawLinePanel();
        drawLinePanel.setBounds(0,120,window.getWidth(),20);
        window.add(drawLinePanel);

        goodsSellNum = new JTextField("0");
        goodsSellNum.setBounds(50,170,260,30);
        window.add(goodsSellNum);

        submit = new JButton("确认买入");
        submit.setBounds(50,220,270,50);
        submit.setFont(new Font("楷体",Font.PLAIN,25));
//        出售逻辑
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals(goodsSellNum.getText(), "")){
                    int buyNum = Integer.parseInt(goodsSellNum.getText());
                    if(buyNum>=0){
                        goods.setGoods_num(goods.getGoods_num()+buyNum);
                        if(LinkDB.SetGoodsNum(goods,true,buyNum)){
                            JOptionPane.showMessageDialog(null,"买入成功,花费为"+buyNum*goods.getGoods_price()+"元","警告",JOptionPane.PLAIN_MESSAGE);
                            window.setVisible(false);
                            MainPage mainPage = new MainPage();
                        }
                    }else {
                        JOptionPane.showMessageDialog(null,"输入错误","警告",JOptionPane.PLAIN_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"数量不能为空","警告",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        window.add(submit);
        window.setVisible(true);
    }
}
