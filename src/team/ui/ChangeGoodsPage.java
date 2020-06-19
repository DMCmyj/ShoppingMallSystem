package team.ui;

import team.db.LinkDB;
import team.sys.Goods;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ChangeGoodsPage extends JFrame {
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

    public ChangeGoodsPage(Goods goods){
        window = new JFrame("修改商品");
        window.setLayout(null);
        window.setSize(400,300);
        window.setLocationRelativeTo(null);

        goods_name = new JLabel("商品名称:");
        goods_name.setBounds(50,20,80,50);
        window.add(goods_name);
        goodsName = new JTextField(goods.getGoods_name());
        goodsName.setBounds(120,30,200,30);
        window.add(goodsName);

        goods_type = new JLabel("商品类别:");
        goods_type.setBounds(50,70,80,50);
        window.add(goods_type);
        goodsType = new JTextField(goods.getGoods_type());
        goodsType.setBounds(120,80,200,30);
        window.add(goodsType);

        goods_price = new JLabel("商品价格:");
        goods_price.setBounds(50,120,80,50);
        window.add(goods_price);
        goodsPrice = new JTextField(String.valueOf(goods.getGoods_price()));
        goodsPrice.setBounds(120,130,200,30);
        window.add(goodsPrice);

        submit = new JButton("确认添加");
        submit.setBounds(50,180,270,50);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals(goodsName.getText(), "") && !Objects.equals(goodsPrice.getText(), "") && !Objects.equals(goodsType.getText(), "")){
                    Float price = Float.parseFloat(goodsPrice.getText());
                    newGoods = new Goods(goods.getGoods_id(), goodsName.getText(),price,goodsType.getText(),0);
                    //向数据库中添加新的商品
                    LinkDB.updataGoods(newGoods);
                    JOptionPane.showMessageDialog(null,"修改成功！","提示",JOptionPane.PLAIN_MESSAGE);
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
