package com.flipkart.client;

import java.util.*;

import com.flipkart.business.*;

public class CRSStudentMenu {
	Scanner sc = new Scanner(System.in);
		
	public void ShowOptions() {
        // TODO Auto-generated method stub
        Scanner sc= new Scanner(System.in);
        StudentInterface studentService = new StudentTasks();
        int a=1;
        while(a!=0)
        {
            System.out.println("Enter the choice: ");
            System.out.println("1. Register course ");
            System.out.println("2. View the result ");
            System.out.println("3. View the catalogue ");
            System.out.println("4. Pay Bills ");
            System.out.println("0. Exit ");
            a= sc.nextInt();

   
        }


    }
}
