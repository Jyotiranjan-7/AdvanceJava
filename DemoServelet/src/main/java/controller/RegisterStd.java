package controller;

import dao.StudentDAO;
import entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registerstd")
public class RegisterStd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redgNo = req.getParameter("redgNo");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String course = req.getParameter("course");
        String password = req.getParameter("password");

        Student student = new Student();
        student.setRedgNo(redgNo);
        student.setName(name);
        student.setEmail(email);
        student.setCourse(course);
        student.setPassword(password);

        StudentDAO dao = new StudentDAO();
        dao.saveStudent(student);
    }
}
