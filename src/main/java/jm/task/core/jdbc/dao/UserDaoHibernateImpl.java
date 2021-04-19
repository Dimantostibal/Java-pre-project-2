package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String CREATE = "CREATE TABLE IF NOT EXISTS Users (id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age int)";

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery(CREATE);
        int result = query.executeUpdate();
        System.out.println("Таблица создана!");
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String DROP = "DROP TABLE IF EXISTS Users";
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(DROP);
        int result = query.executeUpdate();
        System.out.println("Таблица удалена!");
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(new User(name, lastName, age));
        System.out.println("Пользователь " + name +" добавлен!");
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        String DELETE = "DELETE FROM Users WHERE id = '" + id + "'";

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(DELETE);
        int result = query.executeUpdate();
        System.out.println("Пользователь с id: " + id + " удален!");
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        String GET_ALL = "FROM User";

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query us = session.createQuery(GET_ALL);
        List<User> users = us.list();
        transaction.commit();
        session.close();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> list= session.createCriteria(User.class).list();
        for (User user : list ) {
            session.delete(user);
        }
        System.out.println("Все пользователи удалены!");
        transaction.commit();
        session.close();
    }
}