package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final static Connection connect = Util.connect();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Statement statement = null;
        try{
            statement = connect.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS my_db.user(" +
                    "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " name VARCHAR(30), " +
                    "lastname VARCHAR(30)," +
                    "age SMALLINT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        try{
            statement = connect.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS my_db.user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, short age) {
        try{
            PreparedStatement statement = connect.prepareStatement("INSERT INTO my_db.user(name, lastname, age) VALUES(?,?,?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setShort(3, age);
            statement.executeUpdate();
            System.out.println(" User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        PreparedStatement statement = null;
        try{
            statement = connect.prepareStatement("DELETE FROM my_db.user where id= ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User with id= " + id + " has been removed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Statement statement = null;
        try{
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM my_db.user");
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getShort("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        try{
            statement = connect.createStatement();
            statement.executeUpdate("DELETE FROM my_db.user");
            System.out.println("из таблицы удалены все строки");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
