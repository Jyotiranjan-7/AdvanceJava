package Dao;

import Entity.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {

    String url = "jdbc:mysql://localhost:3306/std_db";
    String username = "root";
    String password = "Jyoti@2003";

    public void saveStudent(Student student) {

        String sql = "INSERT INTO student " +
                "(name, registration_number, email, course,password) " +
                "VALUES (?, ?, ?, ?,?)";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(url, username, password);

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setString(2, student.getRegistrationNumber());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getCourse());
            ps.setString(5, student.getPassword());

            int r = ps.executeUpdate();

            if (r == 1)
                System.out.println("Student data saved successfully");

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public boolean loginStudent(String registrationNumber, String studentPassword) {

        String sql = "SELECT * FROM student " +
                "WHERE registration_number = ? AND password = ?";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, registrationNumber);
            ps.setString(2, studentPassword);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("Login Successful");

                System.out.println("Name: " + rs.getString("name"));

                System.out.println("Registration Number: " + rs.getString("registration_number"));

                System.out.println("Email: " + rs.getString("email"));

                System.out.println("Course: " + rs.getString("course"));

                rs.close();
                ps.close();
                con.close();

                return true;
            }
            System.out.println("Login Failed");

            rs.close();
            ps.close();
            con.close();

            return false;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }
}