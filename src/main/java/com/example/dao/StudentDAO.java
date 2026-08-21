package com.example.dao;

import com.example.connection.DBConnection;
import com.example.model.Student;

import java.sql.*;

public class StudentDAO {

    Connection con = DBConnection.getConnection();

    public void insertStudent(Student student) {

        try {

            String sql = "insert into student(name,course) values(?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setString(2, student.getCourse());

            int row = ps.executeUpdate();

            if (row > 0)
                System.out.println("Student Inserted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void displayStudents() {

        try {

            String sql = "select * from student";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("----------------------------");
            System.out.println("ID\tNAME\tCOURSE");
            System.out.println("----------------------------");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + "\t"
                                + rs.getString("name") + "\t"
                                + rs.getString("course"));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void updateStudent(Student student) {

        try {

            String sql = "update student set name=?,course=? where id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setString(2, student.getCourse());
            ps.setInt(3, student.getId());

            int row = ps.executeUpdate();

            if (row > 0)
                System.out.println("Student Updated Successfully");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void deleteStudent(int id) {

        try {

            String sql = "delete from student where id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int row = ps.executeUpdate();

            if (row > 0)
                System.out.println("Student Deleted Successfully");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}