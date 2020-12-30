package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/insert")
public class Insert extends SQLServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            addDayRow(req, resp);
            getServletContext().getRequestDispatcher("/select").forward(req,resp);
        } catch (SQLException | IOException | ServletException ex) {
            req.setAttribute("message", "Invalid input, try again. Data format must be YYYY-MM-DD");
        }
    }

    public void addDayRow(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String date = req.getParameter("add_date");
        String max_t = req.getParameter("max");
        String min_t = req.getParameter("min");
        String event = req.getParameter("event");
        String sqlQuery = ("INSERT INTO DAY VALUES" + " " +
                "('" + date + "', " + max_t + ", " + min_t + ", '"
                + event + "')");
        try{
            statement.execute(sqlQuery);
            req.setAttribute("message", sqlQuery + " : complete");


        }
        catch (SQLException ex){
            req.setAttribute("message", "Invalid input, try again. Data format must be YYYY-MM-DD");
        }

    }
}
