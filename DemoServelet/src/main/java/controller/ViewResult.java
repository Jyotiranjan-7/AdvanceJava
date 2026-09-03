package controller;

import dao.ResultDAO;
import entity.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/viewResult")
public class ViewResult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String redgNo= req.getParameter("redgNo");
        ResultDAO dao=new ResultDAO();
        Result r=dao.getResult(redgNo);
        if(r!=null)
        {
            out.println("<h2 align='center'>Student Result</h2>");

            out.println("<table border='1' align='center' cellpadding='10'>");

            out.println("<tr><th>Registration</th><td>"+r.getRedgNo()+"</td></tr>");
            out.println("<tr><th>Java</th><td>"+r.getJava()+"</td></tr>");
            out.println("<tr><th>DBMS</th><td>"+r.getDbms()+"</td></tr>");
            out.println("<tr><th>DSA</th><td>"+r.getDs()+"</td></tr>");
            out.println("<tr><th>OS</th><td>"+r.getOs()+"</td></tr>");
            out.println("<tr><th>Percentage</th><td>"+r.getPercentage()+"%</td></tr>");
            out.println("<tr><th>Grade</th><td>"+r.getGrade()+"</td></tr>");

            out.println("</table>");
        }
        else {
            out.println("<h3>No Result Found</h3>");
        }
    }
}
