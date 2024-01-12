import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import java.sql.*;

class SignUp implements ActionListener {
    private JFrame j1;
    private JTextField name, email, mobile, username;
    private JPasswordField password, confrmpass;
    private JLabel l1, l2, l3, l4, l5, l6, gender;;

    private JComboBox<String> comb1;

    private JButton cont, retu;

    SignUp() {
        setSignup();
    }

    void setSignup() {
        j1 = new JFrame("Signup Page");

        name = new JTextField();
        email = new JTextField();
        mobile = new JTextField();
        username = new JTextField();
        password = new JPasswordField();
        confrmpass = new JPasswordField();
        l1 = new JLabel("Enter Name");
        l2 = new JLabel("Enter Email");
        l3 = new JLabel("Enter Number");
        l4 = new JLabel("Enter UserName");
        l5 = new JLabel("Enter Password");
        l6 = new JLabel("Confirm PassWord");

        JLabel image = new JLabel();
        image.setBounds(0, 0, 1000, 800);
        image.setIcon(new ImageIcon("E:\\ENTERS THE DETAILS.jpg"));

        l1.setBounds(200, 200, 200, 40);
        l2.setBounds(200, 250, 200, 40);
        l3.setBounds(200, 300, 200, 40);
        l4.setBounds(200, 350, 200, 40);
        l5.setBounds(200, 400, 200, 40);
        l6.setBounds(200, 450, 200, 40);
        gender = new JLabel("Select Gender");
        gender.setFont(new Font("", Font.BOLD, 20));
        gender.setBounds(200, 500, 200, 40);

        setfont(l1, l2, l3, l4, l5, l6);

        name.setBounds(400, 200, 200, 40);
        email.setBounds(400, 250, 200, 40);
        mobile.setBounds(400, 300, 200, 40);
        username.setBounds(400, 350, 200, 40);
        password.setBounds(400, 400, 200, 40);
        confrmpass.setBounds(400, 450, 200, 40);
        comb1 = new JComboBox<>(new String[] { "Male", "Female", "Other" });
        comb1.setFont(new Font("", Font.BOLD, 20));
        comb1.setBounds(400, 500, 200, 40);
        setfont(username, name, mobile, email, password, confrmpass);

        image.add(name);
        image.add(email);
        image.add(mobile);
        image.add(username);
        image.add(password);
        image.add(confrmpass);
        image.add(comb1);

        image.add(l1);
        image.add(l2);
        image.add(l3);
        image.add(l4);
        image.add(l5);
        image.add(l6);
        image.add(gender);

        cont = new JButton("Continue");
        retu = new JButton("Return");
        setfont(retu, cont);

        cont.setBounds(400, 550, 200, 40);
        retu.setBounds(400, 590, 200, 40);

        image.add(cont);
        image.add(retu);

        cont.addActionListener(this);
        retu.addActionListener(this);

        j1.add(image);
        j1.getContentPane().setBackground(new Color(100, 34, 65));
        j1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(j1, "Are You Want To Go Back");
                if (a == JOptionPane.YES_OPTION) {
                    j1.dispose();
                    new Screen();
                }
            }
        });
        j1.setSize(1000, 800);
        j1.setLayout(null);
        j1.setDefaultCloseOperation(0);
        j1.setLocationRelativeTo(null);
        j1.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cont) {
            if (name.getText() != null && email.getText() != null && mobile.getText().length() == 10
                    && username.getText().length() >= 4
                    && new String(password.getPassword()).equals(new String(confrmpass.getPassword()))
                    && comb1.getItemAt(comb1.getSelectedIndex()) != null
                    && new String(password.getPassword()).length() >= 4) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "2020");
                    PreparedStatement p1 = c.prepareStatement(
                            "insert into login.personaldetail(name,email,mobile,username,password,gender)values(?,?,?,?,?,?)");
                    p1.setString(1, name.getText());
                    p1.setString(2, email.getText());
                    p1.setString(3, mobile.getText());
                    p1.setString(4, username.getText());
                    p1.setString(5, new String(password.getPassword()));
                    p1.setString(6, comb1.getItemAt(comb1.getSelectedIndex()));
                    p1.executeUpdate();
                    p1.close();
                    c.close();
                    int xx = JOptionPane.showConfirmDialog(j1, "Account Created SuccesFully\n" +
                            "Are You Want To\n" + "Goto Login Page");
                    if (xx == JOptionPane.YES_OPTION) {
                        j1.dispose();
                        new Login();
                    }
                } catch (Exception es) {
                    JOptionPane.showMessageDialog(j1, es.getMessage());
                    username.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(j1, "Please Provide Correct Details");
            }
        }
        if (e.getSource() == retu) {
            j1.dispose();
            new Screen();
        }
    }

    void setfont(JLabel l1, JLabel l2, JLabel l3, JLabel l4, JLabel l5, JLabel l6) {
        l1.setFont(new Font("", Font.BOLD, 20));
        l2.setFont(new Font("", Font.BOLD, 20));
        l3.setFont(new Font("", Font.BOLD, 20));
        l4.setFont(new Font("", Font.BOLD, 20));
        l5.setFont(new Font("", Font.BOLD, 20));
        l6.setFont(new Font("", Font.BOLD, 20));
    }

    void setfont(JTextField l1, JTextField l2, JTextField l3, JTextField l4, JPasswordField l5, JPasswordField l6) {
        l1.setFont(new Font("", Font.BOLD, 20));
        l2.setFont(new Font("", Font.BOLD, 20));
        l3.setFont(new Font("", Font.BOLD, 20));
        l4.setFont(new Font("", Font.BOLD, 20));
        l5.setFont(new Font("", Font.BOLD, 20));
        l6.setFont(new Font("", Font.BOLD, 20));
    }

    void setfont(JButton j1, JButton j2) {
        j1.setFont(new Font("", Font.BOLD, 20));
        j2.setFont(new Font("", Font.BOLD, 20));
    }

    void setfont(JLabel j1) {
        j1.setFont(new Font("", Font.BOLD, 20));
    }
}