package team.ui;

import team.sys.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class RegistPage extends JFrame {
    JFrame window;
    private String username;
    private String password;
    private String email;

    JTextField user;
    JTextField pwd1;
    JTextField pwd2;
    JTextField email_tf;

    JButton makeSure;

    public RegistPage(){
        window = new JFrame("商品管理系统注册页面");
        window.setLayout(null);
        window.setSize(400,400);
        window.setLocationRelativeTo(null);

        window.setLayout(null);
        window.setResizable(false);

        JLabel username_label = new JLabel("用户名：");
        username_label.setBounds(70,50,100,50);
        window.add(username_label);
        user = new JTextField(20);
        user.setBounds(140,60,200,30);
        window.add(user);

        JLabel password_label = new JLabel("密码：");
        password_label.setBounds(70,100,100,50);
        window.add(password_label);
        pwd1 = new JTextField(20);
        pwd1.setBounds(140,110,200,30);
        window.add(pwd1);

        JLabel password_label1 = new JLabel("确认密码：");
        password_label1.setBounds(70,150,100,50);
        window.add(password_label1);
        pwd2 = new JTextField(20);
        pwd2.setBounds(140,160,200,30);
        window.add(pwd2);

        JLabel email_tf_label = new JLabel("电子邮箱：");
        email_tf_label.setBounds(70,200,100,50);
        window.add(email_tf_label);
        email_tf = new JTextField(20);
        email_tf.setBounds(140,210,200,30);
        window.add(email_tf);

        makeSure = new JButton("提交注册");
        makeSure.setBounds(70,260,260,30);
        makeSure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = user.getText();
                password = pwd1.getText();
                email = email_tf.getText();
                if(!Objects.equals(user.getText(), "") && !Objects.equals(pwd1.getText(), "") && !Objects.equals(pwd2.getText(), "") && !Objects.equals(email_tf.getText(), "")){
                    if(Objects.equals(pwd1.getText(),pwd2.getText())){
                        User user = new User(username,password,email);
                        if(user.AddUser()){
                            JOptionPane.showMessageDialog(null,"注册成功","提示",JOptionPane.PLAIN_MESSAGE);
                            window.setVisible(false);
                        }else {
                            JOptionPane.showMessageDialog(null,"注册失败","提示",JOptionPane.PLAIN_MESSAGE);
                        }

                    }else{
                        JOptionPane.showMessageDialog(null,"两次输入的密码不同","警告",JOptionPane.PLAIN_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"内容均不能为空","警告",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        window.add(makeSure);

        window.setVisible(true);
    }
}
