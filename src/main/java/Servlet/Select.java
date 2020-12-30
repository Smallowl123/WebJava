package Servlet;
import model.Day;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/select")
public class Select extends SQLServlet {
    private ArrayList<Day> dayList = new ArrayList<Day>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.setAttribute("dayList", getDays(req));
            getServletContext().getRequestDispatcher("/main.jsp").forward(req,resp);
        } catch (SQLException | ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        doGet(req,resp);
    }

    public ArrayList<Day> getDays(HttpServletRequest req) throws SQLException {
        String sqlQuery = "SELECT * FROM DAY ORDER BY date";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        dayList.clear();
        while (resultSet.next()) {
            String date = resultSet.getString("date");
            int max_t = resultSet.getInt("max_t");
            int min_t = resultSet.getInt("min_t");
            String event = resultSet.getString("event");
            Day day = new Day(date, max_t, min_t, event);
            dayList.add(day);

        }
        return dayList;
    }
}
