package controller;

import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/loginstd")
public class LoginStd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redgNo=req.getParameter("redgNo");
        String password=req.getParameter("password");
        StudentDAO dao=new StudentDAO();
        boolean result=dao.loginStudent(redgNo,password);
        if(result)
        {
            Cookie cookies=new Cookie("registrationNumber",redgNo);
            cookies.setMaxAge(60*60);
            resp.addCookie(cookies);
            resp.sendRedirect("home");

        }
        else {
            resp.setContentType("text/html");
            resp.getWriter().println("<h2>Invalid Registration Number or Password</h2>");
        }
    }
}
