package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class SQLServlet extends HttpServlet {
    protected final static String URL = "jdbc:postgresql://localhost/JavaLabDB";
    protected final static String username = "postgres";
    protected final static String password = "VeryStrongPass(net)";
    protected static Statement statement;

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
}
