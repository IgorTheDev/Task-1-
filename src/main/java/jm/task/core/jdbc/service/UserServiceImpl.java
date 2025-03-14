package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService,AutoCloseable {
//    UserDaoJDBCImpl userDao =new UserDaoJDBCImpl();
    UserDaoHibernateImpl userDao=new UserDaoHibernateImpl();

    public void createUsersTable() throws SQLException {
        this.userDao.createUsersTable();
    }

    @Override
    public void close() throws Exception {
        this.userDao.close();
    }

    public void dropUsersTable() throws SQLException {
        this.userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        this.userDao.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) throws SQLException {
        this.userDao.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return this.userDao.getAllUsers();
    }

    public void cleanUsersTable() throws Exception {
        this.userDao.cleanUsersTable();
    }
}
