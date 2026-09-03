package Controller;

import Dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/loginstd")
public class Stdlogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String registrationNumber = req.getParameter("registrationNumber");
        String password = req.getParameter("password");
        StudentDAO dao = new StudentDAO();
        boolean result = dao.loginStudent(registrationNumber, password);
        if (result) {
            Cookie cookies = new Cookie("registrationNumber", registrationNumber);
            cookies.setMaxAge(60 * 60);
            resp.addCookie(cookies);
            resp.sendRedirect("home.jsp");
        }
    }
}
