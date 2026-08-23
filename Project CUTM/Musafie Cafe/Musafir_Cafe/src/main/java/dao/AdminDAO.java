package dao;
import config.DBConnection;
import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    private Connection con;

    public AdminDAO() {
        con = DBConnection.getConnection();
    }

    public boolean adminLogin(String username, String password) {

        String sql = "SELECT * FROM admin WHERE username=? AND password=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean addAdmin(Admin admin) {

        String sql = "INSERT INTO admin(admin_name,username,password) VALUES(?,?,?)";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, admin.getAdminName());
            ps.setString(2, admin.getUsername());
            ps.setString(3, admin.getPassword());

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Admin Added Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void viewAdmins() {

        String sql = "SELECT * FROM admin";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("----------------------------------------------");
            System.out.printf("%-10s %-20s %-15s\n",
                    "ID", "NAME", "USERNAME");
            System.out.println("----------------------------------------------");

            while (rs.next()) {

                System.out.printf("%-10d %-20s %-15s\n",
                        rs.getInt("admin_id"),
                        rs.getString("admin_name"),
                        rs.getString("username"));

            }

            System.out.println("----------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Admin searchAdmin(int id) {

        String sql = "SELECT * FROM admin WHERE admin_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Admin admin = new Admin();

                admin.setAdminId(rs.getInt("admin_id"));
                admin.setAdminName(rs.getString("admin_name"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));

                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateAdmin(Admin admin) {

        String sql = "UPDATE admin SET admin_name=?, username=?, password=? WHERE admin_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, admin.getAdminName());
            ps.setString(2, admin.getUsername());
            ps.setString(3, admin.getPassword());
            ps.setInt(4, admin.getAdminId());

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Admin Updated Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteAdmin(int id) {

        String sql = "DELETE FROM admin WHERE admin_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Admin Deleted Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
