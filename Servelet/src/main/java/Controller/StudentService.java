package Controller;

import Dao.StudentDAO;
import Entity.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/student")
public class StudentService extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");

        String registrationNumber =
                request.getParameter("registrationNumber");

        String email =
                request.getParameter("email");

        String course =
                request.getParameter("course");
        System.out.println("Name: " + name);

        System.out.println("Registration Number: " + registrationNumber);

        System.out.println("Email: " + email);

        System.out.println("Course: " + course);
        Student student = new Student(
                name,
                registrationNumber,
                email,
                course
        );

        StudentDAO dao = new StudentDAO();

        dao.saveStudent(student);
        request.setAttribute("name", name);
        request.setAttribute("registrationNumber", registrationNumber);
        request.setAttribute("email", email);
        request.setAttribute("course", course);

        request.getRequestDispatcher("/login.jsp")
                .forward(request, response);

    }
}
