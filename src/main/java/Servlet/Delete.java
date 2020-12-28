package Servlet;

import org.postgresql.util.PSQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/delete")
public class Delete extends HttpServlet {
    private final static String URL = "jdbc:postgresql://localhost/JavaLabDB";
    private final static String username = "postgres";
    private final static String password = "VeryStrongPass(net)";
    private static Statement statement;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DriverManager.getConnection(URL, username, password);
            statement = connection.createStatement();
            super.init();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            deleteDayRowByDate(req, resp);
        } catch (SQLException ex) {
            resp.getWriter().write("Invalid input, try again. Data format must be YYYY-MM-DD");
        }
    }

    public static void deleteDayRowByDate(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String date = req.getParameter("del_date");
        String sqlQuery = ("DELETE FROM DAY" + " " +
                "WHERE date=" + "'" + date + "'");
        try{
            statement.execute(sqlQuery);
            resp.getWriter().write(sqlQuery + " : complete");
        }
        catch (PSQLException | IOException ex){
            System.err.println("Invalid input, try again. Data format must be YYYY-MM-DD");
        }
    }
}
