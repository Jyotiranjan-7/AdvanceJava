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

            String name = request.getParameter("name");

            String email = request.getParameter("email");

            String course = request.getParameter("course");
            String registrationNumber=request.getParameter("registrationNumber");
            String password=request.getParameter("password");

            Student student = new Student(name, registrationNumber, email, course, password);
            StudentDAO dao=new StudentDAO();
            dao.saveStudent(student);

            response.getWriter().println("<h2>Student Registered Successfully</h2>");

    }
}
