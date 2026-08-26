package controller;

import dao.EmployeeDAO;
import dao.StudentDAO;
import entity.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registerEmp")
public class RegisterEmp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int emp_id = Integer.parseInt(req.getParameter("emp_id"));
    String emp_name=req.getParameter("emp_name");
    String emp_email=req.getParameter("emp_email");
    String emp_department=req.getParameter("emp_department");
    String emp_password=req.getParameter("emp_password");
    Employee employee=new Employee();
    employee.setEmp_id(emp_id);
    employee.setEmp_name(emp_name);
    employee.setEmp_email(emp_email);
    employee.setEmp_department(emp_department);
    employee.setEmp_password(emp_password);
        EmployeeDAO dao=new EmployeeDAO();
        dao.saveEmployee(employee);
        resp.sendRedirect("loginEmployee.html");

    }
}
