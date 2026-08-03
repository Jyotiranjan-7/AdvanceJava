package com.example.main;

import com.example.dao.StudentDAO;
import com.example.model.Student;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentDAO dao = new StudentDAO();

        while (true) {

            System.out.println("\n========== Student Management ==========");
            System.out.println("1. Insert Student");
            System.out.println("2. Display Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    Student s = new Student();

                    System.out.print("Enter Name : ");
                    s.setName(sc.next());

                    System.out.print("Enter Course : ");
                    s.setCourse(sc.next());

                    dao.insertStudent(s);

                    break;

                case 2:

                    dao.displayStudents();

                    break;

                case 3:

                    Student s1 = new Student();

                    System.out.print("Enter ID : ");
                    s1.setId(sc.nextInt());

                    System.out.print("Enter New Name : ");
                    s1.setName(sc.next());

                    System.out.print("Enter New Course : ");
                    s1.setCourse(sc.next());

                    dao.updateStudent(s1);

                    break;

                case 4:

                    System.out.print("Enter ID : ");

                    int id = sc.nextInt();

                    dao.deleteStudent(id);

                    break;

                case 5:

                    System.out.println("Thank You");

                    System.exit(0);

                default:

                    System.out.println("Invalid Choice");

            }

        }

    }

}
