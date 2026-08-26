package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class Home extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");

        resp.getWriter().println("<html>");
        resp.getWriter().println("<head><title>Student Home</title></head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<h1>Welcome to Student Management System</h1>");
        resp.getWriter().println("<h2>Login Successful</h2>");
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");
    }
}
