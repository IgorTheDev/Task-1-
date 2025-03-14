package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao,AutoCloseable {
    private Configuration cfg;
    private SessionFactory sessionFactory;


    public UserDaoHibernateImpl() {
        cfg = new Configuration().addAnnotatedClass(User.class);
        sessionFactory = cfg.buildSessionFactory();
    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sql="CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGSERIAL NOT NULL PRIMARY KEY," +
                    "name VARCHAR(64) NOT NULL," +
                    "last_Name VARCHAR(64) NOT NULL," +
                    "age SMALLINT CHECK(age>=0) NOT NULL);";
            session.createNativeQuery(sql).executeUpdate();

            session.getTransaction().commit();
            System.out.println("Creating users table");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    public void getUserById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.get(User.class, id);
            session.getTransaction().commit();
            System.out.println("Retrieving user by id: " + id);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users;").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Dropping users table");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("Saving user: " + name + " " + lastName + " " + age);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(session.get(User.class,id));
            session.getTransaction().commit();
            System.out.println("Removing user by id: " + id);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("from User").list();
            session.getTransaction().commit();
            return users;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Cleaning users table");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public void close() throws Exception {
    if (this.sessionFactory != null) {
        this.sessionFactory.close();
    }

    }
}
