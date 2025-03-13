package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        try(UserServiceImpl userService = new UserServiceImpl()){

            userService.createUsersTable();
            userService.saveUser("Igor","Sergienko",(byte) 30);
            userService.saveUser("Nikita","Volkov",(byte) 27);
            userService.saveUser("Dmitriy","Nagiev",(byte) 44);
            userService.saveUser("Ilon","Musk",(byte) 40);
            System.out.println(userService.getAllUsers());
            userService.cleanUsersTable();
            userService.dropUsersTable();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }





       
    }
}
