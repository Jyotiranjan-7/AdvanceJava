package dao;

import entity.Employee;

import java.sql.*;

public class EmployeeDAO {

    private final String url = "jdbc:mysql://localhost:3306/std_manage";
    private final String user = "root";
    private final String password = "Jyoti@2003";

    public void saveEmployee(Employee employee) {

        String sql = "INSERT INTO employee " +
                "(emp_id, emp_name, emp_email, emp_department, emp_password) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, employee.getEmp_id());
            ps.setString(2, employee.getEmp_name());
            ps.setString(3, employee.getEmp_email());
            ps.setString(4, employee.getEmp_department());
            ps.setString(5, employee.getEmp_password());

            int r = ps.executeUpdate();

            if (r > 0) {
                System.out.println("Employee registered successfully.");
            } else {
                System.out.println("Employee registration failed.");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean loginEmployee(String emp_email,String password){
        String sql="SELECT * FROM employee " + "WHERE emp_email= ? AND emp_password= ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,user,this.password);
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,emp_email);
            ps.setString(2,password);
           ResultSet rs= ps.executeQuery();
           boolean result;
           result=rs.next();
            return result;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}