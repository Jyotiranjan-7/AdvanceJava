package com.example;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database Connection
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/emp_db",
                    "root",
                    "Jyoti@2003");

            System.out.println("Database Connected Successfully.");

            while (true) {

                System.out.println("\n===============================");
                System.out.println(" Employee Management System");
                System.out.println("===============================");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Search Employee");
                System.out.println("4. Update Employee");
                System.out.println("5. Delete Employee");
                System.out.println("6. Batch Insert");
                System.out.println("7. Transaction Demo");
                System.out.println("8. Exit");
                System.out.print("Enter Choice : ");

                int choice = sc.nextInt();

                switch (choice) {


                    // Add Employee

                    case 1:

                        System.out.print("Employee ID : ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Employee Name : ");
                        String name = sc.nextLine();

                        System.out.print("Designation : ");
                        String desig = sc.nextLine();

                        System.out.print("Salary : ");
                        double salary = sc.nextDouble();

                        String insertSql =
                                "INSERT INTO employee VALUES(?,?,?,?)";

                        ps = con.prepareStatement(insertSql);

                        ps.setInt(1, id);
                        ps.setString(2, name);
                        ps.setString(3, desig);
                        ps.setDouble(4, salary);

                        int row = ps.executeUpdate();

                        if (row > 0) {

                            System.out.println("Employee Added Successfully.");

                        } else {

                            System.out.println("Insertion Failed.");

                        }

                        break;

                    // View Employees

                    case 2:

                        String viewSql = "SELECT * FROM employee";

                        ps = con.prepareStatement(viewSql);

                        rs = ps.executeQuery();

                        System.out.println("\n----------------------------------------------");
                        System.out.printf("%-10s %-20s %-20s %-10s\n",
                                "ID", "Name", "Designation", "Salary");
                        System.out.println("----------------------------------------------");

                        while (rs.next()) {

                            System.out.printf("%-10d %-20s %-20s %-10.2f\n",
                                    rs.getInt("employee_id"),
                                    rs.getString("employee_name"),
                                    rs.getString("designation"),
                                    rs.getDouble("salary"));

                        }

                        System.out.println("----------------------------------------------");

                        break;


                    // Search Employee

                    case 3:

                        System.out.print("Enter Employee ID : ");
                        int searchId = sc.nextInt();

                        String searchSql =
                                "SELECT * FROM employee WHERE employee_id=?";

                        ps = con.prepareStatement(searchSql);
                        ps.setInt(1, searchId);

                        rs = ps.executeQuery();

                        if (rs.next()) {

                            System.out.println("\nEmployee Found");
                            System.out.println("--------------------------");
                            System.out.println("ID          : " + rs.getInt("employee_id"));
                            System.out.println("Name        : " + rs.getString("employee_name"));
                            System.out.println("Designation : " + rs.getString("designation"));
                            System.out.println("Salary      : " + rs.getDouble("salary"));

                        } else {

                            System.out.println("Employee Not Found.");

                        }

                        break;

                    // Update Employee

                    case 4:

                        System.out.print("Enter Employee ID : ");
                        int updateId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("New Name : ");
                        String newName = sc.nextLine();

                        System.out.print("New Designation : ");
                        String newDesig = sc.nextLine();

                        System.out.print("New Salary : ");
                        double newSalary = sc.nextDouble();

                        String updateSql =
                                "UPDATE employee SET employee_name=?, designation=?, salary=? WHERE employee_id=?";

                        ps = con.prepareStatement(updateSql);

                        ps.setString(1, newName);
                        ps.setString(2, newDesig);
                        ps.setDouble(3, newSalary);
                        ps.setInt(4, updateId);

                        int update = ps.executeUpdate();

                        if (update > 0) {

                            System.out.println("Employee Updated Successfully.");

                        } else {

                            System.out.println("Employee Not Found.");

                        }

                        break;

                    // Delete Employee

                    case 5:

                        System.out.print("Enter Employee ID : ");

                        int deleteId = sc.nextInt();

                        String deleteSql =
                                "DELETE FROM employee WHERE employee_id=?";

                        ps = con.prepareStatement(deleteSql);

                        ps.setInt(1, deleteId);

                        int delete = ps.executeUpdate();

                        if (delete > 0) {

                            System.out.println("Employee Deleted Successfully.");

                        } else {

                            System.out.println("Employee Not Found.");

                        }

                        break;

                    // Batch Insert

                    case 6:

                        System.out.print("How Many Employees : ");
                        int n = sc.nextInt();

                        String batchSql =
                                "INSERT INTO employee VALUES(?,?,?,?)";

                        ps = con.prepareStatement(batchSql);

                        for (int i = 1; i <= n; i++) {

                            System.out.println("\nEmployee " + i);

                            System.out.print("ID : ");
                            int empId = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Name : ");
                            String empName = sc.nextLine();

                            System.out.print("Designation : ");
                            String empDesig = sc.nextLine();

                            System.out.print("Salary : ");
                            double empSalary = sc.nextDouble();

                            ps.setInt(1, empId);
                            ps.setString(2, empName);
                            ps.setString(3, empDesig);
                            ps.setDouble(4, empSalary);

                            ps.addBatch();

                        }

                        int[] result = ps.executeBatch();

                        System.out.println(result.length + " Employees Inserted Successfully.");

                        break;

                    // Transaction Demo
                    case 7:

                        try {

                            con.setAutoCommit(false);

                            System.out.print("Sender Employee ID : ");
                            int sender = sc.nextInt();

                            System.out.print("Receiver Employee ID : ");
                            int receiver = sc.nextInt();

                            System.out.print("Transfer Amount : ");
                            double amount = sc.nextDouble();

                            // Check Sender Balance
                            String checkSql = "SELECT salary FROM employee WHERE employee_id=?";

                            ps = con.prepareStatement(checkSql);
                            ps.setInt(1, sender);

                            rs = ps.executeQuery();

                            if (!rs.next()) {

                                System.out.println("Sender Employee Not Found.");
                                con.rollback();
                                con.setAutoCommit(true);
                                break;

                            }

                            double senderSalary = rs.getDouble("salary");

                            if (senderSalary < amount) {

                                System.out.println("Insufficient Balance.");
                                con.rollback();
                                con.setAutoCommit(true);
                                break;

                            }

                            // Debit Sender
                            String debitSql =
                                    "UPDATE employee SET salary = salary - ? WHERE employee_id=?";

                            ps = con.prepareStatement(debitSql);

                            ps.setDouble(1, amount);
                            ps.setInt(2, sender);

                            ps.executeUpdate();

                            // Credit Receiver
                            String creditSql =
                                    "UPDATE employee SET salary = salary + ? WHERE employee_id=?";

                            ps = con.prepareStatement(creditSql);

                            ps.setDouble(1, amount);
                            ps.setInt(2, receiver);

                            int rows = ps.executeUpdate();

                            if (rows == 0) {

                                throw new SQLException("Receiver Employee Not Found...");

                            }

                            con.commit();

                            System.out.println("Transaction Successful...");
                            System.out.println("Amount Transferred Successfully...");

                        } catch (Exception e) {

                            System.out.println("Transaction Failed...");
                            System.out.println("Rollback Performed...");

                            con.rollback();

                        } finally {

                            con.setAutoCommit(true);

                        }

                        break;

                    case 8:

                        if (rs != null)
                            rs.close();

                        if (ps != null)
                            ps.close();

                        if (con != null)
                            con.close();

                        System.out.println("Thank You!");

                        System.exit(0);

                        break;

                    default:
                        System.out.println("Invalid Choice.");

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}