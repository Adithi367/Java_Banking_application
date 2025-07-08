package org.example;

import java.util.Scanner;

import static java.lang.System.exit;


public class Main {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.println("1.Admin\n2.Manager\n3.Customer");
        System.out.println("Select from above\nEnter the choice");
        int choice=s.nextInt();
        switch(choice){
            case 1: Admin a=new Admin();
                    a.login();
                    a.branch();
                    a.manager();
                    System.out.println("Logged out successfully");
                    exit(0);
            case 2:Manager m=new Manager();
                    m.login();
                    m.customer();
        }
    }
}