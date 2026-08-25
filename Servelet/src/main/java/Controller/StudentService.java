package Controller;

import Dao.StudentDAO;
import Entity.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/student")
public class StudentService extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        String registrationNumber = request.getParameter("registrationNumber");

        String password = request.getParameter("password");

        StudentDAO dao = new StudentDAO();
        if ("Login".equals(action)) {

            boolean result = dao.loginStudent(registrationNumber, password);

            if (result) {
                response.getWriter().println("<h2>Login Successful</h2>");
                Cookie cookies=new Cookie("registrationNumber",registrationNumber);
                cookies.setMaxAge(60*60);
                response.addCookie(cookies);
                response.sendRedirect("home");
            } else {
                response.getWriter().println("<h2>Login Failed</h2>");
            }

        }
        else if("Register".equals(action)) {

            String name = request.getParameter("name");

            String email = request.getParameter("email");

            String course = request.getParameter("course");

            Student student = new Student(name, registrationNumber, email, course, password);

            dao.saveStudent(student);

            response.getWriter().println("<h2>Student Registered Successfully</h2>");
        }
    }
}
