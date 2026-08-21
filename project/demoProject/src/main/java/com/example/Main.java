package com.example;
import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static final String url="jdbc:mysql://localhost:3306/employee_db";
    static final String user="root";
    static final String password="Jyoti@2003";
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con=DriverManager.getConnection(url,user,password);
                int choice=0;
                do{
                    System.out.println("1.insert data");
                    System.out.println("2.delete data");
                    System.out.println("3.display data");
                    System.out.println("Enter your choice");
                    choice=sc.nextInt();
                    switch(choice)
                    case 1:
                        System.out.println("Enter the id");
                        int id=sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter the name");
                        String name=sc.nextLine();
                        System.out.println("Enter the designation");
                        String des=sc.nextLine();
                        String q="INSERT INTO Employee_data VALUES (?,?,?)";
                        preparedSteatment ps=con.prepareStatement(q);
                        ps.setInt(1,id);
                        ps.setString(2,name);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }

    }
}