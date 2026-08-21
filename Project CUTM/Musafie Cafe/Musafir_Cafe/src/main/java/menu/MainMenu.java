package menu;

import java.util.Scanner;

public class MainMenu {

    private Scanner sc = new Scanner(System.in);

    //=================================
    // Start Application
    //=================================
    public void start() {

        int choice;

        do {

            System.out.println("\n========================================");
            System.out.println("        WELCOME TO MUSAFIR CAFE");
            System.out.println("========================================");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.println("========================================");
            System.out.print("Enter Your Choice : ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    adminLogin();
                    break;

                case 2:

                    customerMenu();
                    break;

                case 3:

                    System.out.println("\nThank You For Visiting Musafir Cafe.");
                    System.out.println("Visit Again!");
                    break;

                default:

                    System.out.println("Invalid Choice.");

            }

        } while (choice != 3);

    }

    //=================================
    // Admin Login
    //=================================
    private void adminLogin() {

        System.out.println("\n========== ADMIN LOGIN ==========");

        System.out.print("Username : ");
        String username = sc.next();

        System.out.print("Password : ");
        String password = sc.next();

        AdminMenu adminMenu = new AdminMenu();
        adminMenu.login(username, password);

    }

    //=================================
    // Customer Menu
    //=================================
    private void customerMenu() {

        CustomerMenu customerMenu = new CustomerMenu();
        customerMenu.customerHome();

    }

}