package team.db;

import team.sys.Goods;
import team.sys.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class LinkDB {
    public static Connection mysqlConnection;
    public static Statement mysqlStatement;


    public LinkDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            mysqlConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shoppingdb?serverTimezone=UTC",
                    "root",
                    "dreamcatcher");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            mysqlStatement = mysqlConnection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
//将用户添加到数据库
    public static boolean AddUser(User user){
        String sql = "insert into user value(null,\""+ user.getUsername() +"\",\""+ user.getPassword() +"\",\""+ user.getEmail() +"\")";

        try {
            if(mysqlStatement.executeLargeUpdate(sql) == 1){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
//检查用户密码是否正确
    public static boolean CheckUser(User user){
        String sql = "select password from user where username = '"+user.getUsername()+"'";
        try {
            ResultSet resultSet = mysqlStatement.executeQuery(sql);
            String checkPwd = "";
            if(resultSet.next()){
                checkPwd = resultSet.getString(1);
            }
//            判断密码是否正确
            if (Objects.equals(checkPwd, user.getPassword())){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

//    获取所有商品信息
    public static ResultSet getAllGoods(){
        String sql = "select * from goods";
        ResultSet resultSet = null;
        try {
             resultSet = mysqlStatement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

//    获取所有销售记录的信息
    public static ResultSet getAllSalesRecord(){
        String sql = "select * from sales_record";
        ResultSet resultSet = null;
        try {
            resultSet = mysqlStatement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

//    添加商品
    public static boolean AddGoods(Goods goods){
        String sql = "INSERT INTO `shoppingdb`.`goods` (`goods_name`, `goods_type`, `goods_price`, `goods_num`) VALUES ('"+ goods.getGoods_name() +"', '"+ goods.getGoods_type() +"', "+ goods.getGoods_price() +", "+ goods.getGoods_num() +");";
        try {
            if(1 == mysqlStatement.executeUpdate(sql)){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

//    更改商品数量
    public static boolean SetGoodsNum(Goods goods,boolean mode,int num){
        String sql;
        String sql2;
//        日期格式的控制
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(mode){
//            买
            sql = "UPDATE `shoppingdb`.`goods` SET goods_num = '"+ goods.getGoods_num() +"',buy_date = '"+ sdf.format(new Date().getTime()) +"' WHERE (`goods_name` = '"+ goods.getGoods_name() +"');";
            sql2 = "insert into sales_record value (null,'"+ goods.getGoods_name() +"','买入',"+ num +","+ goods.getGoods_price()*num +","+ goods.getGoods_id() +",'"+ sdf.format(new Date().getTime()) +"');";
        }else {
//            出售
            sql = "UPDATE `shoppingdb`.`goods` SET goods_num = '"+ goods.getGoods_num() +"',sell_date = '"+ sdf.format(new Date().getTime()) +"' WHERE (`goods_name` = '"+ goods.getGoods_name() +"');";
            sql2 = "insert into sales_record value (null,'"+ goods.getGoods_name() +"','出售',"+ num +","+ goods.getGoods_price()*num +","+ goods.getGoods_id() +",'"+ sdf.format(new Date().getTime()) +"');";
        }
        try {
            if(1 == mysqlStatement.executeUpdate(sql)){
                if(1 == mysqlStatement.executeUpdate(sql2)){
                    return true;
                }
            }else{
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

//    删除商品
    public static boolean deleteGoods(int goodsid){
        String sql = "DELETE FROM `shoppingdb`.`goods` WHERE (`idgoods` = '"+ goodsid +"');\n";
        try {
            if(1 == mysqlStatement.executeUpdate(sql)){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

//    更新商品信息
    public static boolean updataGoods(Goods goods){
        String sql = "UPDATE `shoppingdb`.`goods` SET goods_type = '"+ goods.getGoods_type() +"',goods_price = '"+ goods.getGoods_price() +"',goods_name = '" + goods.getGoods_name() + "' WHERE (idgoods = '"+ goods.getGoods_id() +"');";
        try {
            if(1 == mysqlStatement.executeUpdate(sql)){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
