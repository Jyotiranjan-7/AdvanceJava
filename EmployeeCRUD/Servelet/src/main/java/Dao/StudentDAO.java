package Dao;

import Entity.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StudentDAO {

    String url = "jdbc:mysql://localhost:3306/std_db";
    String username = "root";
    String password = "Jyoti@2003";

    public void saveStudent(Student student) {

        String sql = "INSERT INTO student " +
                "(name, registration_number, email, course) " +
                "VALUES (?, ?, ?, ?)";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(url, username, password);

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setString(2, student.getRegistrationNumber());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getCourse());

            int r=ps.executeUpdate();

            if(r==1)
                System.out.println("Student data saved successfully");

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}