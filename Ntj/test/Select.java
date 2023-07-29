package test;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectStudent {
    public static void main(String[] args) {
        // read from end-user
        Scanner sc = null;
        String city1 = null, city2 = null, city3 = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            sc = new Scanner(System.in);
            if (sc != null) {
                System.out.println("Enter first city name");
                city1 = sc.next();
                System.out.println("Enter second city name");
                city2 = sc.next();
                System.out.println("Enter third city name");
                city3 = sc.next();
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ayush", "root", "Ayush878123");
            if (con != null) {
                st = con.createStatement();
                String sql = "SELECT * FROM student WHERE city IN ('" + city1 + "', '" + city2 + "', '" + city3 + "') ORDER BY city";
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources in the finally block to ensure they are released properly
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
                if (sc != null) {
                    sc.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
