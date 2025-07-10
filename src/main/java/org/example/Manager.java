package org.example;


import org.example.db.dbConnection;

import java.sql.Date;
import java.util.Random;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Manager extends Admin {
    Random rand=new Random();
    static String mmname, mmid, cname, cemail,aid,type,date;
    static long cphone;
    static int cid,ch,balance,amount;
    Scanner s = new Scanner(System.in);

    public void login() {

        System.out.println("Enter the Username and Manager id ");
        mmname = s.next();
        mmid = s.next();


        String query3 = "SELECT * FROM MANAGER WHERE Mid=? AND Mname=?";
        try (Connection dbconnection = dbConnection.getConnection();
             PreparedStatement p = dbconnection.prepareStatement(query3)) {
            p.setString(1, mmid);
            p.setString(2,mmname);

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

    public void customer() {
        //add update view
        do {
            System.out.println("1.Add Customer\n2.Update Customer\n3.View Customers\n4.Exit");
            System.out.println("Enter choice");
            ch = s.nextInt();
            s.nextLine();
            switch (ch) {
                case 1:
                    System.out.println("Enter customer name");
                    cname = s.nextLine();
                    cid = rand.nextInt(100);
                    System.out.println("Customer id is "+cid);
                    System.out.println("Enter customer email");
                    cemail = s.nextLine();
                    System.out.println("Enter customer phone number ");
                    cphone = s.nextLong();

                    String query1 = "INSERT INTO CUSTOMER(Customer_name,Customer_id,Cemail,Cphone,Mid) VALUES(?,?,?,?,?)";
                    try (Connection dbconnection = dbConnection.getConnection();
                         PreparedStatement p = dbconnection.prepareStatement(query1)) {
                        p.setString(1, cname);
                        p.setInt(2,cid);
                        p.setString(3,cemail);
                        p.setLong(4,cphone);
                        p.setString(5,mmid);
                        p.executeUpdate();
                        System.out.println("Logged in successfully");
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    break;
                case 2:
                    System.out.println("Enter customer id");
                    cid=s.nextInt();
                    String checkQuery = "SELECT * FROM CUSTOMER WHERE Customer_id = ? AND Mid = ?";
                    try (Connection db = dbConnection.getConnection();
                         PreparedStatement p = db.prepareStatement(checkQuery)) {

                        p.setInt(1, cid);
                        p.setString(2, mmid);

                        ResultSet rs = p.executeQuery();

                        if (!rs.next()) {
                            System.out.println("Error: You don't have access to update this customer!");
                            return;
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                        return;
                    }
                    System.out.println("1.Name\n2.Email\n3.Phone\nEnter what to update");
                    int updatechoice=s.nextInt();
                    s.nextLine();
                    switch(updatechoice){
                        case 1:
                            String newname;
                            s.nextLine();
                            System.out.println("Enter new name");
                            newname=s.nextLine();
                            String query2="UPDATE CUSTOMER SET Customer_name=? WHERE Customer_id=?";
                            try(Connection dbconnection=dbConnection.getConnection();
                            PreparedStatement p= dbconnection.prepareStatement(query2)){
                                p.setString(1,newname);
                                p.setInt(2,cid);
                                p.executeUpdate();
                                System.out.println("Updated");


                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 2:
                            String newemail;
                            s.nextLine();
                            System.out.println("Enter new email");
                            newemail=s.nextLine();
                            String query3="UPDATE CUSTOMER SET Cemail=? WHERE Customer_id=?";
                            try(Connection dbconnection=dbConnection.getConnection();
                                PreparedStatement p= dbconnection.prepareStatement(query3)){
                                p.setString(1,newemail);
                                p.setInt(2,cid);
                                p.executeUpdate();
                                System.out.println("Updated");


                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 3:
                            String newphone;
                            s.nextLine();
                            System.out.println("Enter new phone");
                            newphone=s.nextLine();
                            String query4="UPDATE CUSTOMER SET Cphone=? WHERE Customer_id=?";
                            try(Connection dbconnection=dbConnection.getConnection();
                                PreparedStatement p= dbconnection.prepareStatement(query4)){
                                p.setString(1,newphone);
                                p.setInt(2,cid);
                                p.executeUpdate();
                                System.out.println("Updated");


                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        default:
                            System.out.println("Invalid");
                            exit(0);
                    }
                    break;
                case 3:    String query="SELECT * FROM CUSTOMER c JOIN MANAGER m ON c.Mid=m.mid WHERE c.Mid=?";
                           try(Connection dbconnection=dbConnection.getConnection();
                           PreparedStatement p= dbconnection.prepareStatement(query)){
                               p.setString(1,mmid);
                               ResultSet rs=p.executeQuery();

                               System.out.println("These are the details");
                               System.out.println("Customer_name   Customer_id   Cemail   Cphone\n--------------------------");
                               while (rs.next()) {
                                   System.out.println(
                                           rs.getInt("Customer_id") + " | " +
                                                   rs.getString("Customer_name") + " | " +
                                                   rs.getString("Cemail") + " | " +
                                                   rs.getLong("Cphone")
                                   );
                               }

                           } catch (Exception e) {
                               throw new RuntimeException(e);
                           }
                           break;
                case 4:
                    System.out.println("Logged out");
                    break;
                default:
                    System.out.println("Invalid");
                    exit(0);


            }
        }while(ch!=4);
    }
    public void account(){

        do{
            System.out.println("1.Create account\n2.View Account\n3.Exit");

            System.out.println("Enter the option");
            ch=s.nextInt();
            s.nextLine();
            switch(ch){
                case 1:
                    System.out.println("Enter the customer id ");
                    cid=s.nextInt();
                    String checkquery="SELECT * FROM CUSTOMER WHERE Customer_id=? AND Mid=?";
                    try(Connection dbconnection=dbConnection.getConnection();
                        PreparedStatement p= dbconnection.prepareStatement(checkquery)){
                        p.setInt(1,cid);
                        p.setString(2,mmid);
                        ResultSet r=p.executeQuery();
                        if(!(r.next())) {
                            System.out.println("The customer id does not exist");
                            exit(0);
                        }
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    System.out.println("Enter the balance ");
                    balance=s.nextInt();
                    aid="ACC"+rand.nextInt(100);


                    String query5="INSERT INTO ACCOUNT(account_id,Customer_id,balance) VALUES(?,?,?)";
                    try(Connection dbconnection=dbConnection.getConnection();
                    PreparedStatement p= dbconnection.prepareStatement(query5)){
                        p.setString(1,aid);
                        p.setInt(2,cid);
                        p.setInt(3,balance);

                        int rows=p.executeUpdate();
                        if(rows>0)
                            System.out.println("Account created successfully");
                        else
                            System.out.println("Not a valid customer id");


                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 2:String query6="SELECT a.* FROM ACCOUNT a JOIN CUSTOMER c ON a.Customer_id = c.Customer_id WHERE c.Mid = ?";
                    try(Connection dbconnection=dbConnection.getConnection();
                    PreparedStatement p= dbconnection.prepareStatement(query6)){
                        p.setString(1,mmid);
                        ResultSet r=p.executeQuery();
                        System.out.println("The account details are\n-----------------------"
                        );
                        while(r.next())
                            System.out.println(r.getString("account_id")+"|"+r.getInt("Customer_id")+"|"+r.getInt("balance")+"|"+r.getString("account_type"));
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                case 3:
                    System.out.println("Logged out");
                    break;

                default:
                    System.out.println("Invalid");
                    exit(0);
            }
        }while(ch!=3);
    }
    public void transaction() {

        do{
            System.out.println("1.Deposit\n2.Withdraw\n3.Submenu\nEnter choice");
            int ch=s.nextInt();
            s.nextLine();
            if (ch == 3) {
                return;
            }
            System.out.println("Enter the account id");
            aid=s.nextLine();
            String checkquery11="SELECT * FROM ACCOUNT a JOIN CUSTOMER c ON a.Customer_id=c.Customer_id WHERE c.Mid=? AND a.account_id=?";
            try(Connection dbconnection=dbConnection.getConnection();
                PreparedStatement p= dbconnection.prepareStatement(checkquery11)){
                p.setString(1,mmid);
                p.setString(2,aid);
                ResultSet r=p.executeQuery();
                if(!(r.next())) {
                    System.out.println("The account id does not exist");
                    return;
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
            switch (ch){
                case 1:
                    type="deposit";



                    System.out.println("Enter the amount to be deposited");
                    amount=s.nextInt();
                    if(amount>0){
                        balance+=amount;
                    }
                    else {
                        System.out.println("Invalid amount");
                        exit(0);
                    }

                    String query8="UPDATE ACCOUNT SET BALANCE=? WHERE account_id=?";
                    try(Connection dbconnection=dbConnection.getConnection();
                        PreparedStatement p= dbconnection.prepareStatement(query8)){
                        p.setInt(1,balance);
                        p.setString(2,aid);



                        p.executeUpdate();
                        System.out.println("Deposited");



                    }
                    catch (Exception e){
                        System.out.println(e);
                    }

                    String query9="INSERT INTO TRANSACTIONS(account_id,amount,type) VALUES(?,?,?)";
                    try(Connection dbconnection=dbConnection.getConnection();
                        PreparedStatement p= dbconnection.prepareStatement(query9)){

                        p.setString(1,aid);
                        p.setInt(2,amount);
                        p.setString(3,type);


                        p.executeUpdate();




                    }
                    catch (Exception e){
                        System.out.println(e);
                    }



                    break;
                case 2:
                    type="Withdraw";

                    System.out.println("Enter the amount to be withdrawn");
                    amount=s.nextInt();
                    if(amount>0){
                        balance-=amount;
                    }
                    else {
                        System.out.println("Invalid amount");
                        exit(0);
                    }

                    String query11="UPDATE ACCOUNT SET BALANCE=? WHERE account_id=?";
                    try(Connection dbconnection=dbConnection.getConnection();
                        PreparedStatement p= dbconnection.prepareStatement(query11)){
                        p.setInt(1,balance);
                        p.setString(2,aid);



                        p.executeUpdate();
                        System.out.println("Withdrawn");



                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    String query12="INSERT INTO TRANSACTIONS(account_id,amount,type) VALUES(?,?,?)";
                    try(Connection dbconnection=dbConnection.getConnection();
                        PreparedStatement p= dbconnection.prepareStatement(query12)){

                        p.setString(1,aid);
                        p.setInt(2,amount);
                        p.setString(3,type);


                        p.executeUpdate();




                    }
                    catch (Exception e){
                        System.out.println(e);
                    }



                    break;


                default:
                    System.out.println("Invalid");
                    exit(0);


            }

        }while(ch!=2);
    }
    public void report(){


            java.sql.Date sqlDate = null;


            while (true) {
                System.out.println("Enter the date in this format YYYY-MM-DD:");
                date = s.nextLine().trim();
                try {
                    sqlDate = java.sql.Date.valueOf(date);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format! Please use YYYY-MM-DD");
                }
            }

            String query13 = "SELECT t.Transaction_id, a.account_id, t.amount, t.type, a.balance, t.date " +
                    "FROM TRANSACTIONS AS t " +
                    "JOIN ACCOUNT AS a ON t.account_id = a.account_id " +
                    "JOIN CUSTOMER c ON c.Customer_id = a.Customer_id " +
                    "WHERE DATE(t.date) = ? AND c.Mid = ?";

            try (Connection dbconnection = dbConnection.getConnection();
                 PreparedStatement p = dbconnection.prepareStatement(query13)) {

                p.setDate(1, sqlDate);
                p.setString(2, mmid);
                ResultSet r = p.executeQuery();

                System.out.println("Transaction Id | Account Id | Amount | Type | Balance | Date");
                System.out.println("---------------------------------------------------------------");

                boolean found = false;
                while (r.next()) {
                    found = true;
                    System.out.println(r.getInt("Transaction_id") + " | " +
                            r.getString("account_id") + " | " +
                            r.getInt("amount") + " | " +
                            r.getString("type") + " | " +
                            r.getInt("balance") + " | " +
                            r.getDate("date"));
                }

                if (!found) {
                    System.out.println("No transactions found on this date.");
                }

            } catch (Exception e) {
                System.out.println("Error fetching report: " + e.getMessage());
            }
        }









    public static void main(String args[]){

    }
}

