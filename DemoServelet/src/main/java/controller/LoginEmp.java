package controller;

import dao.EmployeeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/loginEmp")
public class LoginEmp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emp_email=req.getParameter("emp_email");
        String emp_password=req.getParameter("emp_password");
        EmployeeDAO dao=new EmployeeDAO();
        boolean result=dao.loginEmployee(emp_email,emp_password);
        if(result)
        {
            Cookie cookies=new Cookie("EmployeeEmail",emp_email);
            cookies.setMaxAge(60*60);
            resp.addCookie(cookies);
            resp.sendRedirect("empHome.html");
        }
        else {
            resp.setContentType("text/html");
            resp.getWriter().println("<h2>Invalid Email or Password</h2>");
        }
    }
}
