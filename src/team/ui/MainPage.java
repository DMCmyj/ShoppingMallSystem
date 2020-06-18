package team.ui;

import team.sys.Goods;
import team.ui.mainplug.GoodsListItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Objects;

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
        window.setSize(700,600);
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

//        GoodsListItem [] goodsListItems = {
//                new GoodsListItem(new Goods("wo","ff","f","144")),
//                new GoodsListItem(new Goods("wof","ff","f","144"))
//        };
//
//
//        DefaultListModel<GoodsListItem> goodsListItemDefaultListModel = new DefaultListModel<GoodsListItem>();
////        添加模板元素
//        for (GoodsListItem item : goodsListItems) {
//            goodsListItemDefaultListModel.addElement(item);
//        }
//
//        JList<GoodsListItem> goodsListItemJList = new JList<GoodsListItem>(goodsListItems);
//        goodsListItemJList.setBounds(120,20,500,350);
//
////        列表框载入数据模型
//        goodsListItemJList.setModel(goodsListItemDefaultListModel);
////        设置列表选择模式
//         /*
//     ListSelectionModel.SINGLE_SELECTION // 单选模式
//     ListSelectionModel.SINGLE_INTERVAL_SELECTION // 只能选择相邻的
//     ListSelectionModel.MULTIPLE_INTERVAL_SELECTION // 随便选
//         */
//        goodsListItemJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
////        添加滚动条
//        JScrollPane js = new JScrollPane(goodsListItemJList);
//
//
//        window.add(goodsListItemJList);
        String[] colName= {"商品名称","商品类型","商品价格","商品数量"};
        Object[][] items= {
                {"lalaa","f","x",3},
                {"lalaa","f","x",3},
                {"lalaa","f","x",3},
                {"lalaa","f","x",3},
                {"lalaa","f","x",3},
                {"lalaa","f","x",3},
                {"lalaa","f","x",3},
                {"lalaa","f","x",3},
                {"lalaa","f","x",3},
                {"lalaa","f","x",3},
                {"lalaa","f","x",3},
                {"lalaa","f","x",3},
                {"lalaa","f","x",3}
        };
        JTable jTable = new JTable(items,colName){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane jScrollPane = new JScrollPane(jTable);
        jTable.setRowHeight(30);
        jScrollPane.setBounds(140,20,500,300);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        window.getContentPane().add(jScrollPane,BorderLayout.CENTER);

        window.add(jScrollPane);
        window.setVisible(true);
    }
}
