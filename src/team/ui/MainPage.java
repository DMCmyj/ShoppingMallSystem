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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MainPage extends JFrame {
    JFrame window;

    JButton goods_sold;
    JButton goods_buy;
    JButton add_goods;
    JButton delete_goods;
    JButton change_goods;

    JLabel salesrecordLabel;

    Font font_button = new Font("楷体", Font.PLAIN,18);


    public MainPage(){
        window = new JFrame("商品管理系统登录页面");
        window.setLayout(null);
        window.setSize(1120,1000);
        //设置窗口在屏幕居中
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        ResultSet resultSet = LinkDB.getAllGoods();
//---------------------------------商品数据表
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
//----------------------------------销售记录表
        //      创建表格
        JTable jTableSalesRecord = new JTable(getTableModelSalesRecord(LinkDB.getAllSalesRecord())){
//            设置为不可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
//      数据表外观设置
        jTableSalesRecord.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane jScrollPaneSalesRecord = new JScrollPane(jTableSalesRecord);
        jTableSalesRecord.setRowHeight(30);
        jScrollPaneSalesRecord.setBounds(180,450,900,300);
        jTableSalesRecord.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        window.getContentPane().add(jScrollPaneSalesRecord,BorderLayout.CENTER);
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
                int id = Integer.parseInt((String) jTable.getValueAt(jTable.getSelectedRow(),0));
                SellGoodsPage sellGoodsPage = new SellGoodsPage(
                        new Goods(
                                id,
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
                int id = Integer.parseInt((String) jTable.getValueAt(jTable.getSelectedRow(),0));
                BuyGoodsPage buyGoodsPage = new BuyGoodsPage(
                        new Goods(
                                id,
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
        change_goods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt((String) jTable.getValueAt(jTable.getSelectedRow(),4));
                int id = Integer.parseInt((String) jTable.getValueAt(jTable.getSelectedRow(),0));
                ChangeGoodsPage changeGoodsPage = new ChangeGoodsPage(
                        new Goods(
                                id,
                                (String) jTable.getValueAt(jTable.getSelectedRow(),1),
                                Double.parseDouble((String) jTable.getValueAt(jTable.getSelectedRow(),3)),
                                (String) jTable.getValueAt(jTable.getSelectedRow(),2),
                                num
                        )
                );
                window.setVisible(false);
            }
        });
        window.add(change_goods);

//        销售记录标签
        salesrecordLabel = new JLabel("销售记录表：");
        salesrecordLabel.setBounds(20,450,120,30);
        salesrecordLabel.setFont(new Font("楷体",Font.PLAIN,20));
        window.add(salesrecordLabel);


//----------------------商品数据表查询组件
        JLabel tipfind1 = new JLabel("商品数据表查询：");
        tipfind1.setBounds(20,340,150,30);
        tipfind1.setFont(new Font("楷体",Font.PLAIN,18));
        window.add(tipfind1);

//创建名称查询组件
        JPanel jPanelFindByName = new JPanel();
        jPanelFindByName.setBounds(340,330,window.getWidth()-550,90);
        jPanelFindByName.setLayout(null);
//        添加内部组件
        JLabel jLabelFindByName = new JLabel("要查询的商品名称：");
        jLabelFindByName.setBounds(20,10,200,30);
        jLabelFindByName.setFont(new Font("楷体",Font.PLAIN,20));
        JTextField jTextFieldFindByName = new JTextField();
        jTextFieldFindByName.setBounds(220,10,300,30);
        jPanelFindByName.add(jTextFieldFindByName);
        jPanelFindByName.add(jLabelFindByName);

        jPanelFindByName.setVisible(true);
        window.add(jPanelFindByName);

//创建类型查询组件
        JPanel jPanelFindByType = new JPanel();
        jPanelFindByType.setBounds(340,330,window.getWidth()-550,90);
        jPanelFindByType.setLayout(null);
        //        添加内部组件
        JLabel jLabelFindByType = new JLabel("要查询的商品类型：");
        jLabelFindByType.setBounds(20,10,200,30);
        jLabelFindByType.setFont(new Font("楷体",Font.PLAIN,20));
        JTextField jTextFieldFindByType = new JTextField();
        jTextFieldFindByType.setBounds(220,10,300,30);
        jPanelFindByType.add(jTextFieldFindByType);
        jPanelFindByType.add(jLabelFindByType);

        jPanelFindByType.setVisible(false);
        window.add(jPanelFindByType);


//创建时间查询组件
        JPanel jPanelFindBySellTime = new JPanel();
        jPanelFindBySellTime.setBounds(340,330,window.getWidth()-550,90);
        jPanelFindBySellTime.setLayout(null);
//        内部组件
        JLabel jLabelFindBySellTime1 = new JLabel("起始时间:");
        jLabelFindBySellTime1.setFont(new Font("楷体",Font.PLAIN,20));
        jLabelFindBySellTime1.setBounds(20,10,150,30);
        jPanelFindBySellTime.add(jLabelFindBySellTime1);
        JLabel jLabelFindBySellTime2 = new JLabel("结束时间:");
        jLabelFindBySellTime2.setFont(new Font("楷体",Font.PLAIN,20));
        jLabelFindBySellTime2.setBounds(20,50,150,30);
        jPanelFindBySellTime.add(jLabelFindBySellTime2);
//      时间输入条1
        JPanelTime jPanelTime1 = new JPanelTime();
        jPanelTime1.setLayout(new FlowLayout());
        jPanelTime1.setBounds(120,10,430,30);
        jPanelFindBySellTime.add(jPanelTime1);

        JPanelTime jPanelTime2 = new JPanelTime();
        jPanelTime2.setBounds(120,50,430,30);
        jPanelFindBySellTime.add(jPanelTime2);

        jPanelFindBySellTime.setVisible(false);
        window.add(jPanelFindBySellTime);


//        添加下拉选择框
        JComboBox<String> jComboBox = new JComboBox<>(new String[]{"按商品名称查询","按商品类别查询","按出售日期查询"});
        jComboBox.setBounds(170,340,150,30);
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jComboBox.getSelectedIndex();
                if(index == 0){
                    jPanelFindBySellTime.setVisible(false);
                    jPanelFindByType.setVisible(false);
                    jPanelFindByName.setVisible(true);
                }else if(index == 1){
                    jPanelFindByName.setVisible(false);
                    jPanelFindBySellTime.setVisible(false);
                    jPanelFindByType.setVisible(true);
                }else {
                    jPanelFindByName.setVisible(false);
                    jPanelFindByType.setVisible(false);
                    jPanelFindBySellTime.setVisible(true);
                }
            }
        });
        window.add(jComboBox);

//      查询按钮
        JButton jButtonFind = new JButton("查询");
        jButtonFind.setFont(new Font("楷体",Font.PLAIN,25));
        jButtonFind.setBounds(window.getWidth()-200,330,150,30);
        jButtonFind.setBackground(Color.pink);
        jButtonFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jComboBox.getSelectedIndex();
                if(index == 0){
                    String goodsName = "";
                    if(!Objects.equals(jTextFieldFindByName.getText(), "")){
                        goodsName = jTextFieldFindByName.getText();
                        jTable.setModel(getTableModel(LinkDB.findByName(goodsName)));
                        jTable.setRowSelectionInterval(0,0);

                    }
                }else if(index == 1){
                    String goodsType = "";
                    if(!Objects.equals(jTextFieldFindByType.getText(), "")){
                        goodsType = jTextFieldFindByType.getText();
                        jTable.setModel(getTableModel(LinkDB.findByType(goodsType)));
                        jTable.setRowSelectionInterval(0,0);
                    }
                }else{
                    if(jPanelTime1.compareTo(jPanelTime2) == -1){
                        jTable.setModel(getTableModel(LinkDB.findBySellTime(jPanelTime1.getTime(),jPanelTime2.getTime())));
                        jTable.setRowSelectionInterval(0,0);
                    }else {
                        JOptionPane.showMessageDialog(null,"起始时间不得晚于结束时间","警告",JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        window.add(jButtonFind);

//        浏览所有商品按钮
        JButton showAllGoods = new JButton("浏览所有商品");
        showAllGoods.setBounds(window.getWidth()-200,380,150,30);
        showAllGoods.setFont(new Font("楷体",Font.PLAIN,18));
        showAllGoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTable.setModel(getTableModel(LinkDB.getAllGoods()));
                jTable.setRowSelectionInterval(0,0);
            }
        });
        showAllGoods.setBackground(Color.pink);
        window.add(showAllGoods);

// ------------------------销售记录表时间查询
        JLabel tipfind2 = new JLabel("销售记录表查询：");
        tipfind2.setBounds(20,740,150,30);
        tipfind2.setFont(new Font("楷体",Font.PLAIN,18));
        window.add(tipfind2);

        JPanel jPanelfind1 = new JPanel(){
            @Override
            public void paint(Graphics g) {
                g.drawLine(0,5,window.getWidth()-20,5);
            }
        };
        jPanelfind1.setBounds(20,430,window.getWidth()-50,10);
        window.add(jPanelfind1);

        window.add(jScrollPane);
        window.setVisible(true);
    }

    //获取商品表格结构和最新数据
    public DefaultTableModel getTableModel(ResultSet resultSet){
        String[] colName= {"商品id","商品名称","商品类型","商品价格","商品数量","进货日期","出售日期"};
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
//  获取销售记录表格结构和最新数据
    public DefaultTableModel getTableModelSalesRecord(ResultSet resultSet){
        String[] colName= {"销售记录id","销售商品名称","行为","数量","总金额","交易时间"};
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
                        resultSet.getString(7),
                };
                tableModel.addRow(objects);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return tableModel;
    }
}
class JPanelTime extends JPanel implements Comparable{
    String[] years = new String[]{"2020","2019","2018"};
    String[] months = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] days = new String[31];
    String[] hours = new String[24];
    String[] minutesAndSeconds = new String[60];

    JComboBox<String> jComboBoxYear1;
    JComboBox<String> jComboBoxMonth1;
    JComboBox<String> jComboBoxDay1;
    JComboBox<String> jComboBoxHours1;
    JComboBox<String> jComboBoxMinutes1;
    JComboBox<String> jComboBoxSeconds1;

    String year = "";
    String month = "";
    String day = "";
    String hour = "";
    String miunte = "";
    String second = "";

    public JPanelTime(){
        for (int i = 0;i<31;i++){
            days[i] = ""+(i+1);
        }
        for (int i = 0;i<24;i++){
            hours[i] = ""+i;
        }
        for (int i = 0;i<60;i++){
            minutesAndSeconds[i] = ""+i;
        }
        jComboBoxYear1 = new JComboBox<>(years);
        this.add(jComboBoxYear1);
        JLabel year1 = new JLabel("年");
        this.add(year1);
        jComboBoxMonth1 = new JComboBox<>(months);
        this.add(jComboBoxMonth1);
        JLabel month1 = new JLabel("月");
        this.add(month1);
        jComboBoxDay1 = new JComboBox<>(days);
        this.add(jComboBoxDay1);
        JLabel day1 = new JLabel("日 ");
        this.add(day1);
        jComboBoxHours1 = new JComboBox<>(hours);
        this.add(jComboBoxHours1);
        JLabel hours1 = new JLabel("时");
        this.add(hours1);
        jComboBoxMinutes1 = new JComboBox<>(minutesAndSeconds);
        this.add(jComboBoxMinutes1);
        JLabel minutes1 = new JLabel("分");
        this.add(minutes1);
        jComboBoxSeconds1 = new JComboBox<>(minutesAndSeconds);
        this.add(jComboBoxSeconds1);
        JLabel seconds1 = new JLabel("秒");
        this.add(seconds1);
    }

    public String getTime(){
        year = years[jComboBoxYear1.getSelectedIndex()];
        month = Integer.parseInt(months[jComboBoxMonth1.getSelectedIndex()]) < 10 ? "0"+ months[jComboBoxMonth1.getSelectedIndex()] : months[jComboBoxMonth1.getSelectedIndex()];
        day = Integer.parseInt(days[jComboBoxDay1.getSelectedIndex()]) < 10 ? "0"+ days[jComboBoxDay1.getSelectedIndex()] : days[jComboBoxDay1.getSelectedIndex()];
        hour = Integer.parseInt(hours[jComboBoxHours1.getSelectedIndex()]) < 10 ? "0"+ hours[jComboBoxHours1.getSelectedIndex()] : hours[jComboBoxHours1.getSelectedIndex()];
        miunte = Integer.parseInt(minutesAndSeconds[jComboBoxMinutes1.getSelectedIndex()]) < 10 ? "0"+ minutesAndSeconds[jComboBoxMinutes1.getSelectedIndex()] : minutesAndSeconds[jComboBoxMinutes1.getSelectedIndex()];
        second = Integer.parseInt(minutesAndSeconds[jComboBoxSeconds1.getSelectedIndex()]) < 10 ? "0"+ minutesAndSeconds[jComboBoxSeconds1.getSelectedIndex()] : minutesAndSeconds[jComboBoxSeconds1.getSelectedIndex()];
        String date = year + "-" + month + "-" + day;
        String time = hour + ":" + miunte + ":" + second;
        return date + " " + time;
    }

    @Override
    public String toString() {
        year = years[jComboBoxYear1.getSelectedIndex()];
        month = Integer.parseInt(months[jComboBoxMonth1.getSelectedIndex()]) < 10 ? "0"+ months[jComboBoxMonth1.getSelectedIndex()] : months[jComboBoxMonth1.getSelectedIndex()];
        day = Integer.parseInt(days[jComboBoxDay1.getSelectedIndex()]) < 10 ? "0"+ days[jComboBoxDay1.getSelectedIndex()] : days[jComboBoxDay1.getSelectedIndex()];
        hour = Integer.parseInt(hours[jComboBoxHours1.getSelectedIndex()]) < 10 ? "0"+ hours[jComboBoxHours1.getSelectedIndex()] : hours[jComboBoxHours1.getSelectedIndex()];
        miunte = Integer.parseInt(minutesAndSeconds[jComboBoxMinutes1.getSelectedIndex()]) < 10 ? "0"+ minutesAndSeconds[jComboBoxMinutes1.getSelectedIndex()] : minutesAndSeconds[jComboBoxMinutes1.getSelectedIndex()];
        second = Integer.parseInt(minutesAndSeconds[jComboBoxSeconds1.getSelectedIndex()]) < 10 ? "0"+ minutesAndSeconds[jComboBoxSeconds1.getSelectedIndex()] : minutesAndSeconds[jComboBoxSeconds1.getSelectedIndex()];
        String date = year + "-" + month + "-" + day;
        String time = hour + ":" + miunte + ":" + second;
        return date + " " + time;
    }

    @Override
    public int compareTo(Object o) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = null;
        Date time2 = null;
        try {
            time1 = sdf.parse(this.getTime());
            time2 = sdf.parse(o.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert time1 != null;
        return time1.compareTo(time2);
    }
}