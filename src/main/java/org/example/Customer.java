package org.example;

import org.example.db.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Customer {
    static int ccid;
    static String ccname;
    Scanner s=new Scanner(System.in);
    public void login() {

        System.out.println("Enter the Username and Customer id ");
        ccname = s.next();
        ccid = s.nextInt();


        String query3 = "SELECT * FROM CUSTOMER WHERE Customer_id=? AND Customer_name=?";
        try (Connection dbconnection = dbConnection.getConnection();
             PreparedStatement p = dbconnection.prepareStatement(query3)) {
            p.setInt(1, ccid);
            p.setString(2,ccname);

            ResultSet res = p.executeQuery();
            if (res.next())
                System.out.println("Logged in successfully");
            else {
                System.out.println("Invalid");
                exit(0);
            }


        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void view(){
        String query="SELECT c.*,a.* FROM CUSTOMER c JOIN ACCOUNT a ON c.Customer_id=a.Customer_id WHERE c.Customer_id=?";
        try(Connection dbconnection=dbConnection.getConnection();
            PreparedStatement p= dbconnection.prepareStatement(query)){
            p.setInt(1,ccid);
            ResultSet rs=p.executeQuery();

            System.out.println("These are the details");
            System.out.println("Customer_id   Customer_Name    Cemail   Cphone   AccountId    Type    Balance\n----------------------------------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("Customer_id") + " | " +
                                rs.getString("Customer_name") + " | " +
                                rs.getString("Cemail") + " | " +
                                rs.getLong("Cphone")+"|"+
                                rs.getString("account_id")+"|"+
                                rs.getString("account_type")+"|"+
                                rs.getInt("balance")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args){

    }
}
