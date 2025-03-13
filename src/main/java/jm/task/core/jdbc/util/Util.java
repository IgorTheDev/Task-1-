package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import org.hibernate.boot.model.relational.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:postgresql://localhost:5432/myDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "C0cb;jge";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static Statement statement=null;
    private static Connection connection=null;

    public static Connection getConnection() {
        if(connection==null) {
            try{
                Class.forName("org.postgresql.Driver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Соединение установлено");
            } catch (ClassNotFoundException e) {
                System.err.println("Драйвер PostgreSQL не найден!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Ошибка подключения к БД!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
