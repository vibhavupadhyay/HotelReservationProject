import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.awt.*;
import java.sql.*;

class Login implements ActionListener {
    private JFrame j1;
    private JPasswordField username, password;
    private JLabel j2, j3, j4;
    private JButton j5, j6;

    Login() {
        loginscreen();
    }

    void loginscreen() {
        j1 = new JFrame("Login Page");
        j1.getContentPane().setBackground(Color.CYAN);

        j2 = new JLabel();
        j2.setBounds(20, 16, 1238, 662);
        j2.setIcon(new ImageIcon("E:\\Login.jpg"));

        j1.add(j2);

        j3 = new JLabel("Enter userName");
        j3.setBounds(678, 342, 200, 40);
        j3.setFont(new Font("", Font.BOLD, 20));
        j3.setForeground(Color.RED);
        j2.add(j3);

        j4 = new JLabel("Enter Password");
        j4.setBounds(678, 402, 200, 40);
        j4.setForeground(Color.RED);
        j4.setFont(new Font("", Font.BOLD, 20));
        j2.add(j4);

        j5 = new JButton("Confirm");
        j5.setBounds(878, 482, 200, 40);
        j5.setFont(new Font("", Font.BOLD, 20));
        j6 = new JButton("Back");
        j6.setBounds(878, 522, 200, 40);
        j6.setFont(new Font("", Font.BOLD, 20));
        j2.add(j5);
        j2.add(j6);

        username = new JPasswordField();
        username.setBounds(878, 342, 200, 40);
        username.setFont(new Font("", Font.BOLD, 20));
        password = new JPasswordField();
        password.setBounds(878, 402, 200, 40);
        password.setFont(new Font("", Font.BOLD, 20));

        j2.add(username);
        j2.add(password);

        j1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(j1, "Are You Want To Exit From This Menu");
                if (a == JOptionPane.YES_OPTION) {
                    j1.dispose();
                    new Screen();
                }
            }
        });
        j5.addActionListener(this);
        j6.addActionListener(this);

        j1.setLayout(null);
        j1.setSize(1185, 735);
        j1.setDefaultCloseOperation(0);
        j1.setLocationRelativeTo(null);
        j1.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == j6) {
            j1.dispose();
            new Screen();
        }
        if (e.getSource() == j5) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root",
                        "2020");
                Statement s = c.createStatement();
                PreparedStatement p1 = c
                        .prepareStatement(
                                "select username,password,name from personaldetail where username=? and password=?");
                p1.setString(1, new String(username.getPassword()));
                p1.setString(2, new String(password.getPassword()));
                ResultSet r1 = p1.executeQuery();
                s.execute("use login");
                if (r1.next()) {
                    j1.dispose();
                    try {
                        FileWriter f1 = new FileWriter("username.txt");
                        f1.write(new String(username.getPassword()));
                        f1.close();
                        f1 = new FileWriter("password.txt");
                        f1.write(new String(password.getPassword()));
                        f1.close();
                        f1 = new FileWriter("name.txt");
                        f1.write(r1.getString("name"));
                        f1.close();
                    } catch (Exception es) {
                        System.out.println(es);
                    }
                    new Hotel();
                } else {
                    JOptionPane.showMessageDialog(j1, "Invalid Username Or Password");
                }
                r1.close();
                c.close();
                s.close();
            } catch (Exception es) {
                System.out.println(es);
            }
        }
    }
}