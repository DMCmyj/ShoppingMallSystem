package team.ui;

import team.db.LinkDB;
import team.sys.Goods;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainPage extends JFrame {
    JFrame window;

    JButton goods_sold;
    JButton goods_buy;
    JButton add_goods;
    JButton delete_goods;
    JButton change_goods;

    Font font_button = new Font("楷体", Font.PLAIN,18);

    public MainPage(){
        window = new JFrame("商品管理系统登录页面");
        window.setLayout(null);
        window.setSize(1120,400);
        //设置窗口在屏幕居中
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        ResultSet resultSet = LinkDB.getAllGoods();

//      创建表格
        JTable jTable = new JTable(getTableModel(resultSet)){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//      数据表外观设置
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jTable.setRowHeight(30);
        jScrollPane.setBounds(180,20,900,300);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        window.getContentPane().add(jScrollPane,BorderLayout.CENTER);
//        默认选择第一行，需要在表格初始化完成后才可操作
        jTable.setRowSelectionInterval(0,0);


//      商品出售按钮
        goods_sold = new JButton("商品出售");
        goods_sold.setBounds(20,20,140,50);
        goods_sold.setFont(font_button);
        goods_sold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt((String) jTable.getValueAt(jTable.getSelectedRow(),4));
                SellGoodsPage sellGoodsPage = new SellGoodsPage(
                        new Goods(
                                (String) jTable.getValueAt(jTable.getSelectedRow(),1),
                                Double.parseDouble((String) jTable.getValueAt(jTable.getSelectedRow(),3)),
                                (String) jTable.getValueAt(jTable.getSelectedRow(),2),
                                num
                                )
                );
                window.setVisible(false);
            }
        });
        window.add(goods_sold);

        goods_buy = new JButton("商品买入");
        goods_buy.setBounds(20,80,140,50);
        goods_buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt((String) jTable.getValueAt(jTable.getSelectedRow(),4));
                BuyGoodsPage buyGoodsPage = new BuyGoodsPage(
                        new Goods(
                                (String) jTable.getValueAt(jTable.getSelectedRow(),1),
                                Double.parseDouble((String) jTable.getValueAt(jTable.getSelectedRow(),3)),
                                (String) jTable.getValueAt(jTable.getSelectedRow(),2),
                                num
                        )
                );
                window.setVisible(false);
            }
        });
        goods_buy.setFont(font_button);
        window.add(goods_buy);

        add_goods = new JButton("添加商品");
        add_goods.setBounds(20,140,140,50);
        add_goods.setFont(font_button);
        add_goods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGoodsPage addGoodsPage = new AddGoodsPage();
                window.setVisible(false);
            }
        });
        window.add(add_goods);

        delete_goods = new JButton("删除商品");
        delete_goods.setFont(font_button);
        delete_goods.setBounds(20,200,140,50);
        delete_goods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt((String) jTable.getValueAt(jTable.getSelectedRow(),0));
                int check = JOptionPane.showConfirmDialog(null,"您确认要删除商品id为" + num + "的商品吗？","温馨提示",JOptionPane.YES_NO_OPTION);
                if(check == 0){
                    if(LinkDB.deleteGoods(num)){
                        JOptionPane.showMessageDialog(null,"删除成功","提示",JOptionPane.PLAIN_MESSAGE);
                        jTable.setModel(getTableModel(LinkDB.getAllGoods()));
                    }
                }
            }
        });
        window.add(delete_goods);

        change_goods = new JButton("修改商品");
        change_goods.setFont(font_button);
        change_goods.setBounds(20,260,140,50);
        window.add(change_goods);

        window.add(jScrollPane);
        window.setVisible(true);
    }

    //获取表格结构和最新数据
    public DefaultTableModel getTableModel(ResultSet resultSet){
        String[] colName= {"商品id","商品名称","商品类型","商品价格","商品数量","注册日期","出售日期"};
        DefaultTableModel tableModel = new DefaultTableModel();
        //        添加表格数据
        for (String colname:colName) {
            tableModel.addColumn(colname);
        }
        while (true) {
            try {
                if (!resultSet.next()) break;
                Object[] objects = {
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                };
                tableModel.addRow(objects);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return tableModel;
    }

}
