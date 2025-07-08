package org.example;
import org.example.db.dbConnection;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static java.lang.System.exit;
public class Admin {
    Scanner s=new Scanner(System.in);
    Random r=new Random();
    String name="Adithi",password="adithib53@bank";
    static  String aname,Password,branchname,blocation,ifsc,mname,mid;
    static long contact;
    static int bid;
    public void login() {
        System.out.println("Enter the name");
        aname = s.next();
        System.out.println("Enter the password");
        Password = s.next();
        if (aname.equals(name) && Password.equals(password)) {
            System.out.println("Logined Successfully");
        } else {
            System.out.println("Invalid!Login again");
            exit(0);
        }
    }
    public void branch() {
        System.out.println("Enter the branch");
        branchname = s.next();
        bid=r.nextInt(100);
        System.out.println("The branch id is "+bid);
        System.out.println("Enter the IFSC Code");
        ifsc = s.next();
        System.out.println("Enter the Location");
        blocation = s.next();
        String query2 = "INSERT INTO BRANCH(bname,bid,ifsc,blocation) VALUES(?,?,?,?)";
        try (Connection dbconnection = dbConnection.getConnection();
             PreparedStatement p = dbconnection.prepareStatement(query2)) {
            p.setString(1,branchname);
            p.setInt(2, bid);
            p.setString(3,ifsc);
            p.setString(4,blocation);
            p.executeUpdate();
            System.out.println("New branch created");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void manager() {
        System.out.println("Enter the manager name");
        mname = s.next();
        mid = "MGR" + r.nextInt(100);
        System.out.println("Manager id is " + mid);
        System.out.println("Enter the contact number");
        contact = s.nextLong();
        String getAvailableBidQuery = "SELECT bid FROM branch WHERE bid NOT IN (SELECT DISTINCT bid FROM manager) LIMIT 1";
        int chosenBid = -1;
        try (Connection dbconnection = dbConnection.getConnection();
             PreparedStatement ps = dbconnection.prepareStatement(getAvailableBidQuery)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                chosenBid = rs.getInt("bid");
            }
            if (chosenBid == -1) {
                System.out.println("No available branch bid to assign!");
                return;
            }
        }
        catch (SQLException e) {
            System.out.println("Error accessing database: " + e.getMessage());
        }
        String query3 = "INSERT INTO MANAGER(Mname,Mid,Contact,bid) VALUES(?,?,?,?)";
        try (Connection dbconnection = dbConnection.getConnection();
             PreparedStatement p = dbconnection.prepareStatement(query3)) {
            p.setString(1,mname);
            p.setString(2, mid);
            p.setLong(3,contact);
            p.setInt(4,chosenBid);
            p.executeUpdate();
            System.out.println("New manager created");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String args[]){
    }
}
