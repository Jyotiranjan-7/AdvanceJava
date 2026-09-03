package dao;
import entity.Result;
import java.sql.*;

public class ResultDAO {
    private String user="root";
    private String password="Jyoti@2003";
    private String url="jdbc:mysql://localhost:3306/std_manage";
    public Result getResult(String redgNo){
        Result r=null;
        String sql="SELECT * FROM result WHERE redgNo= ? ";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,user,password);
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,redgNo);
           ResultSet rs=ps.executeQuery();
           if(rs.next())
           {
               r=new Result();
            r.setJava(r.getJava("java"));
            r.setDbms(r.getDbms("dbms"));
            r.setDs(r.getDs(ds));
            r.setRedgNo(r.getRedgNo("redgNo"));
            r.setOs(r.getOs("os"));
            r.setPercentage(r.getPercentage("percentage"));
            r.setGrade(r.getGrade("grade"));
           }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return r;
    }
}
