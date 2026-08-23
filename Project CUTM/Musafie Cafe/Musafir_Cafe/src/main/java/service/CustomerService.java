package service;

import dao.CustomerDAO;
import model.Customer;

public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    // ==========================================
    // Register Customer
    // ==========================================
    public boolean registerCustomer(String customerName, String phone) {

        if (customerName == null || customerName.trim().isEmpty()) {

            System.out.println("Customer Name cannot be empty.");
            return false;
        }

        if (phone == null || phone.trim().isEmpty()) {

            System.out.println("Phone Number cannot be empty.");
            return false;
        }

        Customer customer = new Customer();

        customer.setCustomerName(customerName);
        customer.setPhone(phone);

        return customerDAO.addCustomer(customer);
    }

    // ==========================================
    // View All Customers
    // ==========================================
    public void viewCustomers() {

        customerDAO.viewCustomers();
    }

    // ==========================================
    // Search Customer
    // ==========================================
    public Customer searchCustomer(int customerId) {

        Customer customer =
                customerDAO.searchCustomer(customerId);

        if (customer == null) {

            System.out.println("Customer Not Found.");
        }

        return customer;
    }

    // ==========================================
    // Update Customer
    // ==========================================
    public boolean updateCustomer(Customer customer) {

        if (customer == null) {

            System.out.println("Customer Data Cannot Be Null.");
            return false;
        }

        if (customer.getCustomerName() == null ||
                customer.getCustomerName().trim().isEmpty()) {

            System.out.println("Customer Name cannot be empty.");
            return false;
        }

        if (customer.getPhone() == null ||
                customer.getPhone().trim().isEmpty()) {

            System.out.println("Phone Number cannot be empty.");
            return false;
        }

        return customerDAO.updateCustomer(customer);
    }

    // ==========================================
    // Delete Customer
    // ==========================================
    public boolean deleteCustomer(int customerId) {

        if (!customerDAO.customerExists(customerId)) {

            System.out.println("Customer Not Found.");
            return false;
        }

        return customerDAO.deleteCustomer(customerId);
    }

    // ==========================================
    // Check Customer Exists
    // ==========================================
    public boolean customerExists(int customerId) {

        return customerDAO.customerExists(customerId);
    }
}