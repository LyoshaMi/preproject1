package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceHiberImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        User user1 = new User("aleksey", "mikhailov", (short)21);
        User user2 = new User("dimasik", "pevn", (short)22);
        User user3 = new User("nikitosik", "dyachenko", (short)20);
        User user4 = new User("danila", "sashkin", (short)19);

//        JDBC
//
//        UserService userService = new UserServiceImpl();
//
//        userService.dropUsersTable();
//
//        userService.createUsersTable();

//        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
//
//        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
//
//        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
//
//        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
//
//        List<User> users = userService.getAllUsers();
//        System.out.println(users);
//
//        userService.cleanUsersTable();

//        ------------------------------------------------------------------------
//        HIBERNATE

        UserService userService = new UserServiceHiberImpl();

//        userDaoHibernate.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

//        userDaoHibernate.removeUserById(7);

//        List<User> users = userDaoHibernate.getAllUsers();
//        System.out.println(users);

//        userDaoHibernate.dropUsersTable();

//        userDaoHibernate.createUsersTable();

//        userDaoHibernate.cleanUsersTable();
    }
}
