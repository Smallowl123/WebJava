package Servlet;

import org.postgresql.util.PSQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/delete")
public class Delete extends SQLServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            deleteDayRowByDate(req);
            getServletContext().getRequestDispatcher("/select").forward(req,resp);
        } catch (SQLException | ServletException ex) {
            req.setAttribute("message", "Invalid input, try again. Data format must be YYYY-MM-DD");
        }
    }

    public static void deleteDayRowByDate(HttpServletRequest req) throws SQLException {
        String date = req.getParameter("del_date");
        String sqlQuery = ("DELETE FROM DAY" + " " +
                "WHERE date=" + "'" + date + "'");
        try{
            statement.execute(sqlQuery);
            req.setAttribute("message", sqlQuery + " : complete");
        }
        catch (PSQLException ex){
            req.setAttribute("message", "Invalid input, try again. Data format must be YYYY-MM-DD");
        }
    }
}
