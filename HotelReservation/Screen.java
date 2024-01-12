import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

class Screen implements ActionListener {

    private JFrame j1;
    private JButton x, y, z, res;

    void setScreen1() {
        j1 = new JFrame("Welcome Screen");

        j1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(j1, "Are You Want To Exit");
                if (a == JOptionPane.YES_OPTION) {
                    j1.setDefaultCloseOperation(3);
                }
            }
        });

        JLabel j3 = new JLabel();
        j3.setBounds(0, 0, 1132, 708);
        j3.setIcon(new ImageIcon("E:\\screen.jpg"));

        j1.add(j3);

        Color c1 = new Color(0, 30, 21);
        j1.getContentPane().setBackground(c1);

        x = new JButton("Login");
        y = new JButton("Signup");
        z = new JButton("Exit");
        res = new JButton("Reset Password");

        x.setBounds(100, 560, 200, 40);
        y.setBounds(300, 560, 200, 40);
        z.setBounds(300, 600, 200, 40);
        res.setBounds(100, 600, 200, 40);
        x.setFont(new Font("", Font.BOLD, 20));
        y.setFont(new Font("", Font.BOLD, 20));
        z.setFont(new Font("", Font.BOLD, 20));
        res.setFont(new Font("", Font.BOLD, 20));
        j3.add(x);
        j3.add(y);
        j3.add(z);
        j3.add(res);
        JLabel t1;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login",
                    "root",
                    "2020");
            Statement s = c.createStatement();
            s.execute("use login");
            Statement s2 = c.createStatement();
            ResultSet r2 = s2.executeQuery("select curdate(),curtime()");
            String cc = "";
            while (r2.next()) {
                cc += r2.getDate(1) + "   " + r2.getTime(2);
            }
            r2.close();
            s2.close();
            t1 = new JLabel(cc);

            t1.setBounds(180, 520, 400, 40);
            t1.setFont(new Font("", Font.BOLD, 20));
            t1.setBackground(Color.yellow);
            t1.setForeground(Color.RED);
            j3.add(t1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(j1, e.getMessage());
        }

        x.addActionListener(this);
        y.addActionListener(this);
        z.addActionListener(this);
        res.addActionListener(this);
        ;

        j1.setLayout(null);
        j1.setSize(1150, 750);
        j1.setDefaultCloseOperation(0);
        j1.setLocationRelativeTo(null);
        j1.setVisible(true);
    }

    Screen() {
        setScreen1();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == x) {
            j1.dispose();
            new Login();
        }
        if (e.getSource() == y) {
            j1.dispose();
            new SignUp();
        }
        if (e.getSource() == z) {
            j1.dispose();
        }
        if (e.getSource() == res) {
            j1.dispose();
            new ResetPass();
        }
    }

}