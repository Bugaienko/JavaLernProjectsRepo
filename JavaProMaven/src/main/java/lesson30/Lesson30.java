package lesson30;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Sergii Bugaienko
 */

public class Lesson30 {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        connect("jdbc:postgresql://localhost:5432/telran", "postgres", "CtYctq");
        System.out.println("Postgresql server connected");

        //use statement
        Statement statement = connection.createStatement();
//        Создание и заполнение таблицы
//        dropTable(statement);
//        System.out.println("Table deleted");
//        createTable(statement);
//        System.out.println("Table created");
//        insertInto(statement);
//        System.out.println("Records added");

        select(statement);
        findById(2);


        disconnect();
        System.out.println("Postgresql server disconnected");
    }

    static void findById(Integer id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM students WHERE id = ?");
        ps.setInt(1, id);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            System.out.printf("id: %d, name: %s, group: %s\n",
                    res.getInt(1), res.getString(2), res.getString(3));
        }

    }

    static void select(Statement statement) throws SQLException {
        ResultSet res = statement.executeQuery("SELECT * FROM students;");
        while (res.next()) {
            System.out.printf("id: %d, name: %s, group: %s\n",
                    res.getInt(1), res.getString(2), res.getString(3));
        }
    }

    static void insertInto(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO students (name, group_name) values ('Ivan', '38a'), \n" +
                "('Petr', '38a'), ('Oksana', '38a');");
    }

    static void dropTable(Statement statement) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS students");
    }

    static void createTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE students (\n" +
                "id SERIAL PRIMARY KEY,\n" +
                "name TEXT NOT NULL,\n" +
                "group_name TEXT NOT NULL\n);");
    }

    static void connect(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
