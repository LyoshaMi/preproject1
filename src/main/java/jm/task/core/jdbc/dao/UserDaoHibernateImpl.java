package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS my_db.user(" +
                    "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " name VARCHAR(30), " +
                    "lastname VARCHAR(30)," +
                    "age SMALLINT)").executeUpdate();
            session.getTransaction().commit();
            System.out.println("таблица создана");
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS my_db.user").executeUpdate();
            session.getTransaction().commit();
            System.out.println("таблица удалена");
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, short age) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age)); // INSERT
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List<User> resultset = session.createSQLQuery("SELECT * FROM my_db.user").getResultList();
            Iterator it = resultset.iterator();
            while(it.hasNext()){
                Object[] result = (Object[]) it.next();
                users.add(new User((String) result[1], (String) result[2], (Short) result[3]));
            }
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM my_db.user").executeUpdate();
            session.getTransaction().commit();
            System.out.println("из таблицы удалены все строки");
        } finally {
            session.close();
        }
    }
}
