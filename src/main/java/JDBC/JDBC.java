package JDBC;

import model.Day;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.Scanner;

public class JDBC {
    public final static String URL = "jdbc:postgresql://localhost/JavaLabDB";
    public final static String username = "postgres";
    public final static String password = "VeryStrongPass(net)";

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, username, password);
    }

    /**
     * Executes 'SELECT * FROM EVENT' query, which returns whole Event table, directing to console
     * @param statement a Statement object for sending SQL statements to the database.
     */
    private static void outEventsTable(Statement statement) throws SQLException {
        String sqlQuery = "SELECT * FROM EVENT";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("event"));
        }
    }

    public static Statement getStatement() throws SQLException {
        Connection connection = connect();
        return connection.createStatement();
    }

    private static void outDaysTable(Statement statement) throws SQLException {
        String sqlQuery = "SELECT * FROM DAY ORDER BY date";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String date = resultSet.getString("date");
            int max_t = resultSet.getInt("max_t");
            int min_t = resultSet.getInt("min_t");
            String event = resultSet.getString("event");
            System.out.println(date + " " + max_t + " " + min_t + " " + event);
        }
    }

    private static void outDayRowByDate(Statement statement) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter day date: ");
        String date = in.nextLine();
        String sqlQuery = ("SELECT * FROM DAY" + " " +
                "WHERE date=" + "'" + date + "'");
        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                String dt = resultSet.getString("date");
                int max_t = resultSet.getInt("max_t");
                int min_t = resultSet.getInt("min_t");
                String event = resultSet.getString("event");
                System.out.println("Query result:");
                System.out.print(dt + " " + max_t + " " + min_t + " " + event);
        }

        }
        catch (PSQLException ex){
            System.err.println("Invalid input, try again. Data format must be YYYY-MM-DD");
        }
    }

    public static void deleteDayRowByDate(Statement statement) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter day date: ");
        String date = in.nextLine();
        String sqlQuery = ("DELETE FROM DAY" + " " +
                "WHERE date=" + "'" + date + "'");
        try{
            statement.execute(sqlQuery);
        }
        catch (PSQLException ex){
            System.err.println("Invalid input, try again. Data format must be YYYY-MM-DD");
        }
    }

    public static void addDayRow(Statement statement) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter day date: ");
        String date = in.nextLine();
        System.out.println("Enter day max temperature: ");
        String max_t = in.nextLine();
        System.out.println("Enter day min temperature: ");
        String min_t = in.nextLine();
        System.out.println("Enter day main weather event: ");
        String event = in.nextLine();
        String sqlQuery = ("INSERT INTO DAY VALUES" + " " +
                "('" + date + "', " + max_t + ", " + min_t + ", '"
                + event + "')");
        try{
            statement.execute(sqlQuery);
        }
        catch (PSQLException ex){
            System.err.println("Invalid input, try again. Data format must be YYYY-MM-DD");
        }

    }

    public static Day getDayByDate(Statement statement) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter day date: ");
        String date = in.nextLine();
        String sqlQuery = ("SELECT * FROM DAY" + " " +
                "WHERE date=" + "'" + date + "'");
        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                String dt = resultSet.getString("date");
                int max_t = resultSet.getInt("max_t");
                int min_t = resultSet.getInt("min_t");
                String event = resultSet.getString("event");
                Day day = new Day(dt, max_t, min_t, event);
                System.out.println(day.toString());
                return day;
            }
            System.out.println(new Day());
            return new Day();
        }
        catch (PSQLException ex){
            System.err.println("Invalid input, try again. Data format must be YYYY-MM-DD");
        }
        return new Day();
    }

    public static Day[] getDays(Statement statement) throws SQLException {
        String sqlQuery = "SELECT * FROM DAY ORDER BY date";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        int resultLength = 0;
        while (resultSet.next()) {
            resultLength++;
        }
        Day[] days = new Day[resultLength];
        int i = 0;
        resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String date = resultSet.getString("date");
            int max_t = resultSet.getInt("max_t");
            int min_t = resultSet.getInt("min_t");
            String event = resultSet.getString("event");
            Day day = new Day(date, max_t, min_t, event);
            days[i] = day;
            i++;
        }
        for (Day day : days) {
            System.out.println(day.toString());
        }
        return days;
    }


    public static void main(String[] args) throws SQLException {
        Statement statement = getStatement();
        try{
            Scanner in = new Scanner(System.in);
            while(true){
                outMenu();
                int function = in.nextInt();
                if (function == 0){System.exit(0);}
                else {runSwitch(statement, function);}
            }
        } catch (PSQLException ex)
        {
            System.err.println("Invalid input, try again");
        }
    }

    private static void outMenu() {
        System.out.println(
                "Enter:\n1 - to select whole Event table\n2 - to select whole Day table" +
                "\n3 - to select Day row by date\n4 - to delete Day row by date\n5 - to insert Day row\n" +
                "6 - to get Day object by date\n7 - to get Day[] object of whole table" +
                "\n0 - to exit");
    }

    private static void runSwitch (Statement statement, int function) throws SQLException {
        switch (function) {
            case (1):
                outEventsTable(statement);
                break;
            case (2):
                outDaysTable(statement);
                break;
            case (3):
                outDayRowByDate(statement);
                break;
            case (4):
                deleteDayRowByDate(statement);
                break;
            case (5):
                addDayRow(statement);
                break;
            case (6):
                getDayByDate(statement);
                break;
            case (7):
                getDays(statement);
                break;
            default:
                System.err.println("Invalid input, try again");
        }
    }
}
