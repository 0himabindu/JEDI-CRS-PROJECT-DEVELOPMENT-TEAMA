package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;


public class CRSAdminMenu {
    public String adminId;

    public CRSAdminMenu(String id) {
        adminId = id;
    }

    public void ShowOptions() {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        AdminInterface adminService = new AdminOperation();
        int a = 1;
        while (a != 0) { 
            System.out.println("Enter the choice: ");
            System.out.println("1. Add course ");
            System.out.println("2. Delete the course ");
            System.out.println("3. Close registration ");
            System.out.println("4. Show Courses ");
            System.out.println("5. Assign Courses ");
            System.out.println("0. Exit ");
            a = sc.nextInt();

            
        }

    }
}