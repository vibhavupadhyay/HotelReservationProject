import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import java.util.Scanner;

class CancelBooking implements ActionListener {
    private JFrame j1;
    private JComboBox<String> data;
    private JLabel mes, j2;
    private JButton cnc, back;

    void setButton() {
        cnc = new JButton("Cancel Ticket");
        cnc.setBounds(200, 460, 200, 40);
        cnc.setBackground(Color.MAGENTA);
        cnc.setFont(new Font(null, Font.BOLD, 20));

        back = new JButton("Back");
        back.setBounds(200, 500, 200, 40);
        back.setBackground(Color.MAGENTA);
        back.setFont(new Font(null, Font.BOLD, 20));

        cnc.addActionListener(this);
        back.addActionListener(this);

        j2.add(cnc);
        j2.add(back);
    }

    void setCombo() {
        String data1[] = new String[1];
        int size = 1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login",
                    "root",
                    "2020");
            Statement s = c.createStatement();
            s.execute("use login");
            PreparedStatement p1 = c.prepareStatement("select count(*) from booking where username=?");
            File f1 = new File("username.txt");
            Scanner x = new Scanner(f1);
            p1.setString(1, x.nextLine());
            ResultSet r1 = p1.executeQuery();
            while (r1.next()) {
                size = r1.getInt(1);
            }
            data1 = new String[size];
            r1.close();
            p1.close();
            x.close();
            x = new Scanner(f1);
            p1 = c.prepareStatement("select city,hotel,date from booking where username =?");
            p1.setString(1, x.nextLine());
            r1 = p1.executeQuery();
            int i = 0;
            while (r1.next()) {
                data1[i] = r1.getString("city") + "        " + r1.getString("hotel") + "        "
                        + r1.getString("date");
                i++;
            }
            r1.close();
            p1.close();
            x.close();
            s.close();
            c.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(j1, e.getMessage());
        }
        data = new JComboBox<>(data1);
        data.setBounds(50, 110, 500, 50);
        data.setFont(new Font(null, Font.BOLD, 25));
        data.setBackground(Color.orange);
        j2.add(data);

        mes = new JLabel("City            HotelName          Date");
        mes.setBounds(50, 70, 500, 40);
        mes.setFont(new Font(null, Font.BOLD, 22));
        mes.setBackground(Color.GRAY);
        j2.add(mes);
    }

    CancelBooking() {
        setFrame();
        setCombo();
        setButton();
    }

    void setFrame() {
        j1 = new JFrame("Manage Booking");

        j1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(j1, "Go Back ?");
                if (a == JOptionPane.YES_OPTION) {
                    j1.dispose();
                    new Hotel();
                }
            }
        });
        j2 = new JLabel();
        j2.setIcon(new ImageIcon("E:\\cancel.jpg"));
        j2.setBounds(0, 0, 600, 600);
        j1.add(j2);
        j1.setDefaultCloseOperation(0);
        j1.setSize(600, 600);
        j1.setLayout(null);
        j1.setLocationRelativeTo(null);
        j1.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cnc) {
            int a1 = JOptionPane.showConfirmDialog(j1, "Are U Sure Want To Cancel\nSelected Ticket");
            if (a1 == JOptionPane.YES_OPTION) {
                try {
                    FileWriter f1 = new FileWriter("booking.txt");
                    f1.write(data.getItemAt(data.getSelectedIndex()));
                    f1.close();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login",
                            "root",
                            "2020");
                    Statement s = c.createStatement();
                    s.execute("use login");
                    File f2 = new File("booking.txt");
                    Scanner x = new Scanner(f2);
                    PreparedStatement p1 = c
                            .prepareStatement("delete from booking where username=? and city=? and hotel=? and date=?");
                    p1.setString(2, x.next());
                    p1.setString(3, x.next());
                    p1.setString(4, x.next());
                    x.close();
                    f2 = new File("username.txt");
                    x = new Scanner(f2);
                    p1.setString(1, x.next());
                    p1.executeUpdate();
                    x.close();
                    s.close();
                    c.close();
                    int a = JOptionPane.showConfirmDialog(j1, "Cancelletion Successfull\nGo Back");
                    if (a == JOptionPane.YES_OPTION) {
                        j1.dispose();
                        new Hotel();
                    }
                } catch (Exception es) {
                    JOptionPane.showMessageDialog(j1, es.getMessage());
                }
            }
        }
        if (e.getSource() == back) {
            j1.dispose();
            new Hotel();
        }
    }
}