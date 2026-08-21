package dao;

import config.DBConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    private Connection con;

    public CustomerDAO() {
        con = DBConnection.getConnection();
    }


    // Add Customer

    public boolean addCustomer(Customer customer) {

        String sql = "INSERT INTO customer(customer_name, phone) VALUES(?, ?)";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getPhone());

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Customer Added Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // View All Customers

    public void viewCustomers() {

        String sql = "SELECT * FROM customer";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n============== CUSTOMER LIST ==============");

            System.out.printf("%-10s %-25s %-15s\n",
                    "ID", "NAME", "PHONE");

            while (rs.next()) {

                System.out.printf("%-10d %-25s %-15s\n",
                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("phone"));
            }

            System.out.println("===========================================\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Search Customer

    public Customer searchCustomer(int customerId) {

        String sql = "SELECT * FROM customer WHERE customer_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Customer customer = new Customer();

                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setPhone(rs.getString("phone"));

                return customer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // Update Customer

    public boolean updateCustomer(Customer customer) {

        String sql = "UPDATE customer SET customer_name=?, phone=? WHERE customer_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getPhone());
            ps.setInt(3, customer.getCustomerId());

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Customer Updated Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // Delete Customer

    public boolean deleteCustomer(int customerId) {

        String sql = "DELETE FROM customer WHERE customer_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, customerId);

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Customer Deleted Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // Check Customer Exists

    public boolean customerExists(int customerId) {

        String sql = "SELECT * FROM customer WHERE customer_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
