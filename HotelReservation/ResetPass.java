import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ResetPass implements ActionListener {

    private JFrame j1;
    private JLabel image, old, new1;
    private JButton confrm, cont, retu;
    private JPasswordField o1, n1;

    ResetPass() {
        setFrame();
        setImage();
        setpasswordField();
        setLabels();
        setBottons();
    }

    void setpasswordField() {
        o1 = new JPasswordField(null);
        o1.setBounds(200, 75, 150, 40);
        o1.setFont(new Font(null, Font.BOLD, 20));
        n1 = new JPasswordField(null);
        n1.setBounds(200, 135, 150, 40);
        n1.setFont(new Font(null, Font.BOLD, 20));
        image.add(o1);
        image.add(n1);
    }

    void setLabels() {
        old = new JLabel("Old Password");
        new1 = new JLabel("New Password");
        old.setBounds(50, 75, 150, 40);
        new1.setBounds(50, 135, 150, 40);
        old.setFont(new Font("", Font.BOLD, 20));
        new1.setFont(new Font("", Font.BOLD, 20));
        image.add(old);
        image.add(new1);
    }

    void setBottons() {
        cont = new JButton("ContinueToLogin");
        confrm = new JButton("ConfirmPassword");
        retu = new JButton("Main");
        setButtonSize(cont);
        setButtonSize(confrm);
        setButtonSize(retu);
        confrm.setBounds(50, 225, 300, 40);
        cont.setBounds(50, 265, 300, 40);
        retu.setBounds(50, 305, 300, 40);
        cont.addActionListener(this);
        retu.addActionListener(this);
        confrm.addActionListener(this);
        image.add(cont);
        image.add(retu);
        image.add(confrm);
    }

    void setButtonSize(JButton j1) {
        j1.setFont(new Font("", Font.BOLD, 20));
    }

    void setImage() {
        image = new JLabel();
        image.setBounds(0, 0, 400, 400);
        image.setIcon(new ImageIcon("E:\\reset.jpg"));
        j1.add(image);
    }

    void setFrame() {
        j1 = new JFrame("Reset Password");

        j1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(j1, "Are You Want To \nGo Back");
                if (a == JOptionPane.YES_OPTION) {
                    j1.dispose();
                    new Screen();
                }
            }
        });
        j1.setDefaultCloseOperation(0);
        j1.setSize(400, 400);
        j1.setLayout(null);
        j1.setLocationRelativeTo(null);
        j1.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cont) {
            j1.dispose();
            new Login();
        }
        if (e.getSource() == retu) {
            j1.dispose();
            new Screen();
        }
        if (e.getSource() == confrm) {
            if (new String(o1.getPassword()).length() >= 4 && new String(n1.getPassword()).length() >= 4
                    && !new String(n1.getPassword()).equals(new String(o1.getPassword()))) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "2020");
                    Statement s = c.createStatement();
                    s.execute("use login");
                    PreparedStatement p1 = c.prepareStatement("select username from personaldetail where password=?");
                    p1.setString(1, new String(o1.getPassword()));
                    ResultSet r1 = p1.executeQuery();
                    if (r1.next()) {
                        PreparedStatement p2 = c
                                .prepareStatement("update login.personaldetail set password= ? where username=?");
                        p2.setString(2, r1.getString("username"));
                        p2.setString(1, new String(n1.getPassword()));
                        p2.executeUpdate();
                        p2.close();
                        int a = JOptionPane.showConfirmDialog(j1, "Password Updates\nContinue to Login");
                        if (a == JOptionPane.YES_OPTION) {
                            j1.dispose();
                            new Login();
                        }
                    } else {
                        JOptionPane.showMessageDialog(j1, "!!Invalid Details!!");
                        o1.setText("");
                        n1.setText("");
                    }
                    r1.close();
                    p1.close();
                    s.close();
                    c.close();
                } catch (Exception es) {
                    JOptionPane.showMessageDialog(j1, es.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(j1, "Invalid");
            }
        }
    }
}