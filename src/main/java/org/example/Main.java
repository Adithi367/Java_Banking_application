package org.example;

import java.util.Scanner;

import static java.lang.System.exit;


public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int choice,ch;
        do {

            System.out.println("1.Admin\n2.Manager\n3.Customer\nExit");
            System.out.println("Select from above\nEnter the choice");
             choice = s.nextInt();
            s.nextLine();
            switch (choice) {
                case 1:
                    Admin a = new Admin();
                    a.login();
                    a.branch();
                    a.manager();
                    System.out.println("Logged out successfully");
                    break;
                case 2:
                    Manager m = new Manager();
                    m.login();
                    do{
                        System.out.println("1.Customer Management\n2.Account Management\n3.Transaction\n4.Report\n5.Main menu");
                         ch=s.nextInt();
                        s.nextLine();
                        switch(ch){
                            case 1:m.customer();
                                   break;
                            case 2:m.account();
                                    break;
                            case 3:m.transaction();
                                    break;
                            case 4:m.report();
                                    break;
                            case 5:break;
                            default:
                                System.out.println("Invalid");
                                exit(0);
                        }
                }while(ch!=5);




                    break;
                case 3:Customer c=new Customer();
                        c.login();
                        do{
                            System.out.println("1.View Details\n2.Logout\nEnter choice");
                            ch=s.nextInt();
                            switch (ch){
                                case 1:c.view();
                                        break;
                                case 2:
                                    System.out.println("Logged out");
                                    exit(0);
                                default:
                                    System.out.println("Invalid");
                            }
                        }while (ch!=2);

                    break;
                case 4:
                    System.out.println("Exited");
                    exit(0);
                default:
                    System.out.println("Invalid");
                    exit(0);


            }
        }while(choice!=4);
    }
}
