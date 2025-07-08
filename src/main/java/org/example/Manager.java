package org.example;


import org.example.db.dbConnection;
import java.util.Random;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Manager extends Admin {
    Random rand=new Random();
    static String mmname, mmid, cname, cemail;
    static long cphone;
    static int cid,ch ;
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
        //add update delete view
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

                    String query3 = "INSERT INTO CUSTOMER(Customer_name,Customer_id,Cemail,Cphone) VALUES(?,?,?,?)";
                    try (Connection dbconnection = dbConnection.getConnection();
                         PreparedStatement p = dbconnection.prepareStatement(query3)) {
                        p.setString(1, cname);
                        p.setInt(2,cid);
                        p.setString(3,cemail);
                        p.setLong(4,cphone);
                        p.executeUpdate();
                        System.out.println("Logged in successfully");
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    break;
                case 2:
                    System.out.println("Enter customer id");
                    cid=s.nextInt();
                    System.out.println("1.Name\n2.Email\n3.Phone\nEnter what to update");
                    int updatechoice=s.nextInt();
                    switch(updatechoice){
                        case 1:
                            String newname;
                            s.nextLine();
                            System.out.println("Enter new name");
                            newname=s.nextLine();
                            String query="UPDATE CUSTOMER SET Customer_name=? WHERE Customer_id=?";
                            try(Connection dbconnection=dbConnection.getConnection();
                            PreparedStatement p= dbconnection.prepareStatement(query)){
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
                            String query1="UPDATE CUSTOMER SET Cemail=? WHERE Customer_id=?";
                            try(Connection dbconnection=dbConnection.getConnection();
                                PreparedStatement p= dbconnection.prepareStatement(query1)){
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
                            String query2="UPDATE CUSTOMER SET Cphone=? WHERE Customer_id=?";
                            try(Connection dbconnection=dbConnection.getConnection();
                                PreparedStatement p= dbconnection.prepareStatement(query2)){
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




                    }
                    break;
                case 3:    String query="SELECT * FROM CUSTOMER";
                           try(Connection dbconnection=dbConnection.getConnection();
                           PreparedStatement p= dbconnection.prepareStatement(query)){
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
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid");


            }
        }while(true);
    }

    public static void main(String args[]){

    }
}

