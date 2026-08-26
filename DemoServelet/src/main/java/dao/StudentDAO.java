package dao;
import entity.Employee;
import entity.Student;

import java.sql.*;

public class StudentDAO {

    private final String url="jdbc:mysql://localhost:3306/std_manage";
    private final String user="root";
    private final String password="Jyoti@2003";
    public void saveStudent(Student student)
    {
        String sql = "INSERT INTO student (redgNo, name, email, course, password) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,user,password);
           PreparedStatement ps=con.prepareStatement(sql);
           ps.setString(1,student.getRedgNo());
           ps.setString(2, student.getName());
           ps.setString(3,student.getEmail());
           ps.setString(4,student.getCourse());
           ps.setString(5, student.getPassword());
           int r=ps.executeUpdate();
           if(r>0)
           {
               System.out.println("Student register successfully..");
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean loginStudent(String redgNo,String password){
        String sql=("SELECT * FROM student " + "WHERE redgNo = ? AND password = ?");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try {
                    Connection con=DriverManager.getConnection(url,user,this.password);
                    PreparedStatement ps=con.prepareStatement(sql);
                    ps.setString(1,redgNo);
                    ps.setString(2,password);
                    ResultSet rs= ps.executeQuery();
                    boolean result;
                    result = rs.next();
                    return result;
                } catch (SQLException e) {
                    throw new RuntimeException(e);

                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
    }
}