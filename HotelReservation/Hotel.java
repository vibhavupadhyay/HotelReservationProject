import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

class Hotel implements ActionListener {
    private JFrame j1;
    private JLabel image, image1, wel;
    private JButton cont, check, retu, manage;

    Hotel() {

        setFrame();
        setImage();
        setBottons();
    }

    void setBottons() {
        cont = new JButton("Continue To Booking");
        check = new JButton("Check Bookings");
        retu = new JButton("Back To Main");
        manage = new JButton("Manage Booking");
        setButtonSize(manage);
        setButtonSize(cont);
        setButtonSize(check);
        setButtonSize(retu);
        cont.setBounds(878, 480, 300, 40);
        check.setBounds(878, 520, 300, 40);
        manage.setBounds(878, 560, 300, 40);
        retu.setBounds(878, 600, 300, 40);
        cont.addActionListener(this);
        retu.addActionListener(this);
        check.addActionListener(this);
        manage.addActionListener(this);
        image.add(cont);
        image.add(retu);
        image.add(check);
        image.add(manage);
    }

    void setButtonSize(JButton j1) {
        j1.setFont(new Font("", Font.BOLD, 20));
    }

    void setImage() {
        image = new JLabel();
        image.setBounds(10, 0, 1238, 672);
        image.setIcon(new ImageIcon("E:\\hotel.jpg"));

        image1 = new JLabel();
        image1.setIcon(new ImageIcon("E:\\OIP.jpeg"));
        image1.setBounds(20, 0, 240, 200);
        image.add(image1);
        try {
            File f1 = new File("name.txt");
            Scanner x = new Scanner(f1);
            wel = new JLabel("Welcome " + x.nextLine());
            wel.setFont(new Font(null, Font.BOLD, 30));
            wel.setBounds(200, 70, 500, 50);
            wel.setBackground(Color.DARK_GRAY);
            wel.setForeground(Color.PINK);
            image.add(wel);
            x.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(j1, e.getMessage());
        }

        j1.add(image);
    }

    void setFrame() {
        j1 = new JFrame("HotelMain Screen");

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
        j1.setSize(1278, 740);
        j1.setLayout(null);
        j1.setLocationRelativeTo(null);
        j1.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int size = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login",
                    "root",
                    "2020");
            Statement s = c.createStatement();
            s.execute("use login");
            File f1 = new File("username.txt");
            Scanner x = new Scanner(f1);
            PreparedStatement p1 = c.prepareStatement("select count(*) from booking where username=?");
            p1.setString(1, x.next());
            ResultSet r1 = p1.executeQuery();
            while (r1.next()) {
                size = r1.getInt(1);
            }
            r1.close();
            p1.close();
            s.close();
            c.close();
            x.close();
        } catch (Exception es) {
            JOptionPane.showMessageDialog(j1, es.getMessage());
        }
        if (e.getSource() == cont) {
            j1.dispose();
            new Booking();
        }
        if (e.getSource() == retu) {
            j1.dispose();
            new Screen();
        }
        if (e.getSource() == check) {
            if (size == 0) {
                JOptionPane.showMessageDialog(j1, "You Dont Have Any Bookings");
            } else {
                j1.dispose();
                new CheckBooking();
            }
        }
        if (e.getSource() == manage) {
            if (size == 0) {
                JOptionPane.showMessageDialog(j1, "You Dont Have Any Bookings");
            } else {
                j1.dispose();
                new CancelBooking();
            }
        }
    }
}