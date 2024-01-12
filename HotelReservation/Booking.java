import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.sql.*;

class Booking implements ActionListener {
    private JFrame j1;
    private JLabel image, c, h, ht, ns, mnth, d;
    private JComboBox<String> city, hotel, hoteltype, month;
    private JComboBox<Integer> ticket, day;
    private JButton cnfrm;
    private String roomtype[] = { "Ac-1800", "Standard-1400", "Economy-800" };
    private int[] price = { 1800, 1400, 800 };
    private Integer numberofticket[] = { 1, 2, 3, 4 };
    private String[] ct = { "Delhi", "Mumbai", "Chennai", "Patna", "Sasaram", "Hyderabad", "Agra", "Kanpur",
            "Bengaluru" };
    private String[] mn = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };
    private String[] htl = { "Hotel1", "Hotel2", "Hotel4", "Hotel5", "Hotel6" };

    void setInputARea() {

        city = new JComboBox<>(ct);
        city.setFont(new Font(null, Font.BOLD, 20));
        city.setBounds(375, 215, 200, 40);
        city.setBackground(Color.GRAY);

        hotel = new JComboBox<>(htl);
        hotel.setFont(new Font(null, Font.BOLD, 20));
        hotel.setBounds(625, 215, 200, 40);
        hotel.setBackground(Color.MAGENTA);

        hoteltype = new JComboBox<>(roomtype);
        hoteltype.setFont(new Font(null, Font.BOLD, 20));
        hoteltype.setBounds(375, 325, 200, 40);
        hoteltype.setBackground(Color.CYAN);

        ticket = new JComboBox<Integer>(numberofticket);
        ticket.setFont(new Font(null, Font.BOLD, 20));
        ticket.setBounds(625, 325, 200, 40);
        ticket.setBackground(Color.ORANGE);

        month = new JComboBox<>(mn);
        month.setFont(new Font(null, Font.BOLD, 20));
        month.setBounds(375, 445, 200, 40);
        month.setBackground(Color.PINK);

        Integer da[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
                27, 28, 29, 30, 31 };

        day = new JComboBox<>(da);
        day.setFont(new Font(null, Font.BOLD, 20));
        day.setBounds(625, 445, 200, 40);
        day.setBackground(Color.lightGray);

        image.add(city);
        image.add(hotel);
        image.add(hoteltype);
        image.add(ticket);
        image.add(month);
        image.add(day);
    }

    Booking() {
        setImage();
        setLabels();
        setButton();
        setInputARea();
    }

    void setFrame() {
        j1 = new JFrame("Booking");

        j1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(j1, "Go Back ?");
                if (a == JOptionPane.YES_OPTION) {
                    j1.dispose();
                    new Hotel();
                }
            }
        });
        j1.setDefaultCloseOperation(0);
        j1.setSize(1200, 720);
        j1.setLayout(null);
        j1.setLocationRelativeTo(null);
        j1.setVisible(true);
    }

    void setImage() {
        setFrame();
        image = new JLabel();
        image.setBounds(2, 0, 1180, 700);
        image.setIcon(new ImageIcon("E:\\pexels-juan-agustin-2340254.jpg"));
        j1.add(image);
    }

    void setLabels() {
        c = new JLabel("Select City");
        c.setFont(new Font(null, Font.BOLD, 20));
        c.setBounds(375, 185, 200, 30);
        h = new JLabel("Select Hotel");
        h.setFont(new Font(null, Font.BOLD, 20));
        h.setBounds(625, 185, 200, 30);
        ht = new JLabel("HotelType");
        ht.setFont(new Font(null, Font.BOLD, 20));
        ht.setBounds(375, 295, 200, 30);
        ns = new JLabel("Number OfTicket");
        ns.setFont(new Font(null, Font.BOLD, 20));
        ns.setBounds(625, 295, 200, 30);
        mnth = new JLabel("Select Month");
        mnth.setBounds(375, 405, 200, 40);
        mnth.setFont(new Font(null, Font.BOLD, 20));
        d = new JLabel("Select Day");
        d.setBounds(625, 405, 200, 40);
        image.add(c);
        image.add(h);
        image.add(ht);
        image.add(ns);
        image.add(mnth);
        image.add(d);
    }

    void setButton() {
        cnfrm = new JButton("Confirm");
        cnfrm.setBounds(500, 550, 200, 40);
        cnfrm.setFont(new Font(null, Font.BOLD, 20));
        cnfrm.addActionListener(this);
        image.add(cnfrm);
    }

    public void actionPerformed(ActionEvent e) {
        int val = month.getSelectedIndex();
        Integer[] da = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (da[val] >= day.getItemAt(day.getSelectedIndex())) {
            if (e.getSource() == cnfrm) {
                JFrame j2 = new JFrame("Confrm Booking");
                j2.setSize(600, 300);
                j2.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        int a = JOptionPane.showConfirmDialog(j2, "Are You Want To Cancel Booking ?");
                        if (a == JOptionPane.YES_OPTION) {
                            j2.dispose();
                        }
                    }
                });
                JLabel image1 = new JLabel();
                image1.setBounds(0, 0, 600, 300);
                image1.setIcon(new ImageIcon("E:\\cnfrmpass.jpg"));
                j2.add(image1);
                JLabel mes = new JLabel("Enter Password To Confirm Booking");
                mes.setFont(new Font(null, Font.BOLD, 20));
                mes.setBounds(125, 50, 500, 50);
                image1.add(mes);
                JPasswordField ps = new JPasswordField();
                ps.setBounds(200, 100, 200, 50);
                ps.setFont(new Font(null, Font.BOLD, 20));
                image1.add(ps);
                JButton cnc = new JButton("Continue");
                cnc.setFont(new Font(null, Font.BOLD, 20));
                cnc.setBounds(200, 170, 200, 50);
                cnc.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == cnc) {
                            File f2;
                            Scanner xx;
                            String temp = "";
                            try {
                                f2 = new File("password.txt");
                                xx = new Scanner(f2);
                                temp = xx.nextLine();
                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(j2, es.getMessage());
                            }
                            if (new String(ps.getPassword()).equals(temp)) {
                                try {
                                    String username;
                                    File f1 = new File("username.txt");
                                    Scanner x = new Scanner(f1);
                                    username = new String(x.nextLine());
                                    x.close();
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login",
                                            "root",
                                            "2020");
                                    Statement s = c.createStatement();
                                    s.execute("use login");
                                    PreparedStatement p1 = c.prepareStatement(
                                            "insert into booking(username,city,hotel,hoteltype,ticket,ticketprice,date,bookingdate,bookingtime)values(?,?,?,?,?,?,?,?,?)");
                                    p1.setString(1, username);
                                    p1.setString(2, city.getItemAt(city.getSelectedIndex()));
                                    p1.setString(3, hotel.getItemAt(hotel.getSelectedIndex()));
                                    p1.setString(4, hoteltype.getItemAt(hoteltype.getSelectedIndex()));
                                    p1.setInt(5, ticket.getItemAt(ticket.getSelectedIndex()));
                                    p1.setString(7,
                                            "2024" + "/" + (month.getSelectedIndex() + 1) + "/" +
                                                    day.getItemAt(day.getSelectedIndex()));
                                    int pr = ticket.getItemAt(ticket.getSelectedIndex()) *
                                            price[hoteltype.getSelectedIndex()];
                                    p1.setInt(6, pr);
                                    s.close();
                                    Statement s2 = c.createStatement();
                                    ResultSet r2 = s2.executeQuery("select curdate(),curtime()");
                                    while (r2.next()) {
                                        p1.setDate(8, r2.getDate(1));
                                        p1.setTime(9, r2.getTime(2));
                                    }
                                    p1.executeUpdate();
                                    r2.close();
                                    s2.close();
                                    p1.close();

                                    c.close();
                                    j2.dispose();
                                    int a = JOptionPane.showConfirmDialog(j1, "Booking Success\nGo Back");
                                    if (a == JOptionPane.YES_OPTION) {
                                        j1.dispose();
                                        new Hotel();
                                    }
                                } catch (Exception es) {
                                    JOptionPane.showMessageDialog(j1, es.getMessage());
                                }
                            } else {
                                JOptionPane.showMessageDialog(j2, "Password Mismatchs");
                                ps.setText(null);
                            }
                        }
                    }
                });
                image1.add(cnc);
                j2.setDefaultCloseOperation(0);
                j2.setLayout(null);
                j2.setBackground(Color.cyan);
                j2.setLocationRelativeTo(null);
                j2.setVisible(true);
            }
        } else

        {
            JOptionPane.showMessageDialog(j1, "Enter Valid Inputs");
        }
    }
}