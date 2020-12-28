package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/servlet")
public class SelectInsert extends HttpServlet {
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
            addDayRow(req, resp);
        } catch (SQLException | IOException ex) {
            resp.getWriter().write("Invalid input, try again. Data format must be YYYY-MM-DD");
        }
    }

    public static void addDayRow(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String date = req.getParameter("add_date");
        String max_t = req.getParameter("max");
        String min_t = req.getParameter("min");
        String event = req.getParameter("event");
        String sqlQuery = ("INSERT INTO DAY VALUES" + " " +
                "('" + date + "', " + max_t + ", " + min_t + ", '"
                + event + "')");
        try{
            statement.execute(sqlQuery);
            resp.getWriter().write(sqlQuery + " : complete");

        }
        catch (IOException ex){
            resp.getWriter().write("Invalid input, try again. Data format must be YYYY-MM-DD");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            getDaysTable(resp);
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void getDaysTable(HttpServletResponse resp) throws SQLException, IOException {
        resp.setContentType("text/html");
        String sqlQuery = "SELECT * FROM DAY ORDER BY date";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String date = resultSet.getString("date");
            int max_t = resultSet.getInt("max_t");
            int min_t = resultSet.getInt("min_t");
            String event = resultSet.getString("event");
            resp.getWriter().write(date + " " + max_t + " " + min_t + " " + event + "<br>");
        }
    }
}
