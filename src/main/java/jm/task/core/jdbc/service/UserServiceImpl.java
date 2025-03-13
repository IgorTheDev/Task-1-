package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService,AutoCloseable {
    UserDaoJDBCImpl userDaoJDBC=new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException {
        this.userDaoJDBC.createUsersTable();
    }

    @Override
    public void close() throws Exception {
        this.userDaoJDBC.close();
    }

    public void dropUsersTable() throws SQLException {
        this.userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        this.userDaoJDBC.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) throws SQLException {
        this.userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return this.userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() throws Exception {
        this.userDaoJDBC.cleanUsersTable();
    }
}
