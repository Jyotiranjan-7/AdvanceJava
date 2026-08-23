package service;

import dao.AdminDAO;
import model.Admin;

public class AdminService {

    private AdminDAO adminDAO;

    public AdminService() {
        adminDAO = new AdminDAO();
    }

    // ==========================================
    // Admin Login
    // ==========================================
    public Admin login(String username, String password) {

        if (username == null || username.trim().isEmpty()) {

            System.out.println("Username cannot be empty.");
            return null;
        }

        if (password == null || password.trim().isEmpty()) {

            System.out.println("Password cannot be empty.");
            return null;
        }

        boolean status =
                adminDAO.adminLogin(username, password);

        if (status) {

            System.out.println("Admin Login Successful.");

            // Get Admin Details
            // using username
            //
            // Current AdminDAO.adminLogin()
            // returns only boolean.
            //
            // So create an Admin object containing
            // the login information.

            Admin admin = new Admin();

            admin.setUsername(username);
            admin.setPassword(password);

            return admin;

        } else {

            System.out.println("Invalid Username or Password.");

            return null;
        }
    }

    // ==========================================
    // Add Admin
    // ==========================================
    public boolean addAdmin(Admin admin) {

        if (admin == null) {

            System.out.println("Admin data cannot be null.");
            return false;
        }

        if (admin.getAdminName() == null ||
                admin.getAdminName().trim().isEmpty()) {

            System.out.println("Admin Name cannot be empty.");
            return false;
        }

        if (admin.getUsername() == null ||
                admin.getUsername().trim().isEmpty()) {

            System.out.println("Username cannot be empty.");
            return false;
        }

        if (admin.getPassword() == null ||
                admin.getPassword().trim().isEmpty()) {

            System.out.println("Password cannot be empty.");
            return false;
        }

        return adminDAO.addAdmin(admin);
    }

    // ==========================================
    // View All Admins
    // ==========================================
    public void viewAdmins() {

        adminDAO.viewAdmins();
    }

    // ==========================================
    // Delete Admin
    // ==========================================
    public boolean deleteAdmin(int id) {

        if (id <= 0) {

            System.out.println("Invalid Admin ID.");
            return false;
        }

        return adminDAO.deleteAdmin(id);
    }

    // ==========================================
    // Get Admin By ID
    // ==========================================
    public Admin getAdminById(int id) {

        if (id <= 0) {

            System.out.println("Invalid Admin ID.");
            return null;
        }

        Admin admin =
                adminDAO.searchAdmin(id);

        if (admin == null) {

            System.out.println("Admin Not Found.");
        }

        return admin;
    }

    // ==========================================
    // Update Admin
    // ==========================================
    public boolean updateAdmin(Admin admin) {

        if (admin == null) {

            System.out.println("Admin data cannot be null.");
            return false;
        }

        if (admin.getAdminId() <= 0) {

            System.out.println("Invalid Admin ID.");
            return false;
        }

        if (admin.getAdminName() == null ||
                admin.getAdminName().trim().isEmpty()) {

            System.out.println("Admin Name cannot be empty.");
            return false;
        }

        if (admin.getUsername() == null ||
                admin.getUsername().trim().isEmpty()) {

            System.out.println("Username cannot be empty.");
            return false;
        }

        if (admin.getPassword() == null ||
                admin.getPassword().trim().isEmpty()) {

            System.out.println("Password cannot be empty.");
            return false;
        }

        return adminDAO.updateAdmin(admin);
    }
}