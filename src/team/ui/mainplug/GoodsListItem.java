package team.ui.mainplug;

import team.sys.Goods;

import javax.swing.*;

public class GoodsListItem extends JPanel {
    public GoodsListItem(Goods goods){
        this.setSize(500,50);
        this.setLayout(null);
        //商品名
        JLabel label_goods_name = new JLabel("商品名："+goods.getGoods_name());
        label_goods_name.setBounds(10,10,50,30);
        this.add(label_goods_name);
    }
}
