package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String CREATE = "CREATE TABLE IF NOT EXISTS Users (id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age int)";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(CREATE);
            System.out.println("Таблица создана!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String DROP = "DROP TABLE IF EXISTS Users";
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate(DROP);
            System.out.println("Таблица удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String INSERT = "INSERT INTO Users (name, lastName, age) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(INSERT)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с именем " + name + " добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String DELETE = "DELETE FROM Users WHERE id=?";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с id " + id + " удален!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        String GET_ALL = "SELECT * FROM Users";
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Не удалось получить список пользователей!");
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String DELETE_ALL = "DELETE FROM Users";
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate(DELETE_ALL);
            System.out.println("Все пользователи удалены!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}