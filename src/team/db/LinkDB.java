package team.db;

import team.sys.User;

import java.sql.*;
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
        return true;
    }
}
