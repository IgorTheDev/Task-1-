package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao,AutoCloseable {
    private Connection connection=null;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGSERIAL NOT NULL PRIMARY KEY," +
                "name VARCHAR(64) NOT NULL," +
                "last_Name VARCHAR(64) NOT NULL," +
                "age SMALLINT CHECK(age>=0) NOT NULL);";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.execute();
    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE IF EXISTS users";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.execute();
        System.out.println("Таблица users удалена");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "insert into users (name, last_name, age) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3, age);
        preparedStatement.executeUpdate();
        System.out.printf("%s %s %d добавлен в таблицу users\n", name, lastName, age);
        preparedStatement.close();
    }

    public void removeUserById(long id) throws SQLException {
       String sql = "delete from users where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "select * from users";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<User>();
        while(resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setAge(resultSet.getByte("age"));
            users.add(user);
        }
        preparedStatement.close();
        resultSet.close();
        preparedStatement.close();
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "delete from users";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение закрыто");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
