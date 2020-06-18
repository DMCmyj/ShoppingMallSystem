package team.ui;

import team.sys.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LoginPage extends JFrame{
    JFrame window;

    private String username;
    private String password;

    JTextField user;
    JTextField pwd;

    JButton login;
    JButton regist;

    public LoginPage(){
        window = new JFrame("商品管理系统登录页面");
        window.setLayout(null);
        window.setSize(400,300);
        //设置窗口在屏幕居中
        window.setLocationRelativeTo(null);

        //关闭此窗口结束程序
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setLayout(null);
        window.setResizable(false);
        JLabel username_label = new JLabel("用户名：");
        username_label.setBounds(70,50,80,50);
        window.add(username_label);
        user = new JTextField(20);
        user.setBounds(120,60,200,30);
        window.add(user);

        JLabel password_label = new JLabel("密码：");
        password_label.setBounds(70,100,80,50);
        window.add(password_label);

        pwd = new JTextField(20);
        pwd.setBounds(120,110,200,30);
        window.add(pwd);

        login = new JButton("登录");
        login.setBounds(70,160,100,30);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = user.getText();
                password = pwd.getText();
                //判断是否为空
                if(!Objects.equals(user.getText(),"") && !Objects.equals(pwd.getText(),"")){
                    User new_user = new User(username,password);
                    //检查用户和密码是否正确
                    if(new_user.CheckUser()){
                        JOptionPane.showMessageDialog(null,"登录成功","提示",JOptionPane.PLAIN_MESSAGE);
                        window.setVisible(false);
                        MainPage mainPage = new MainPage();
                    }else{
                        JOptionPane.showMessageDialog(null,"用户名或密码错误","警告",JOptionPane.PLAIN_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"内容均不能为空","警告",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        window.add(login);

        regist = new JButton("注册");
        regist.setBounds(230,160,100,30);
        regist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistPage registPage = new RegistPage();
            }
        });
        window.add(regist);

        window.setVisible(true);

    }


}