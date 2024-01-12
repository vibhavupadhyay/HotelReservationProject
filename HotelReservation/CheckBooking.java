import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.io.*;
import java.util.Scanner;

class CheckBooking {
    private JFrame j1;
    private JTable info;

    CheckBooking() {
        setFrame();
        setInfo();
    }
    void setInfo() {
        String data[][];
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "2020");
            Statement s = c.createStatement();
            s.execute("use login");
            PreparedStatement p1 = c.prepareStatement("select * from booking where username=?");
            File f1 = new File("username.txt");
            Scanner x = new Scanner(f1);
            p1.setString(1, x.nextLine());
            ResultSet r1 = p1.executeQuery();
            PreparedStatement p2 = c.prepareStatement("select * from booking where username=?");
            x.close();
            x = new Scanner(f1);
            p2.setString(1, x.nextLine());
            ResultSet r2 = p2.executeQuery();
            int row = 0;
            while (r2.next()) {
                row++;
            }
            r2.close();
            p2.close();
            data = new String[row][8];
            int j = 0;
            while (r1.next()) {
                data[j][0] = r1.getString("city");
                data[j][1] = r1.getString("hotel");
                data[j][2] = r1.getString("hoteltype");
                data[j][3] = r1.getString("ticket");
                data[j][4] = r1.getString("ticketprice");
                data[j][5] = r1.getString("date");
                data[j][6] = r1.getString("bookingdate");
                data[j][7] = r1.getString("bookingtime");
                j++;
            }
            p1.close();
            x.close();
            x.close();
            s.close();
            c.close();
            String col[] = { "City", "HotelName", "HotelType", "Ticket", "Price", "Date", "BookingDate",
                    "BookingTime" };
            info = new JTable(data, col);
            info.setFont(new Font(null, Font.BOLD, 15));
            info.setBackground(Color.cyan);
            JScrollPane j2 = new JScrollPane(info);
            j1.add(j2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(j1, e.getMessage());
        }
    }

    void setFrame() {
        j1 = new JFrame("Booking Status");
        j1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(j1, "Go BAck ?");
                if (a == JOptionPane.YES_OPTION) {
                    j1.dispose();
                    new Hotel();
                }
            }
        });
        j1.setSize(800, 300);
        j1.setDefaultCloseOperation(0);
        j1.setLocationRelativeTo(null);
        j1.setVisible(true);
    }
}