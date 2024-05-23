package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;


public class CRSAdminMenu {
    public int adminId;

    public CRSAdminMenu(int id) {
        adminId = id;
    }

    public void ShowOptions() {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        AdminInterface adminService = new AdminService();
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

            switch (a) {
                case 0:
                    return;
                case 1:
                    adminService.addCourse(adminId);
                    break;
                case 2:
                    adminService.deleteCourse(adminId);
                    break;
                case 3:
                    adminService.closeRegistration();
                    break;
                case 4:
                    adminService.showCourses(adminId);
                    break;
                case 5:
                    adminService.assignCourse();
                default:
                    continue;
            }
        }

    }
}