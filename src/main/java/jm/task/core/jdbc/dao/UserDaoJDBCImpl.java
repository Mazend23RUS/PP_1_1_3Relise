package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.ConnectionC();
    public UserDaoJDBCImpl() throws SQLException {
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()){

            connection.setAutoCommit(false);

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS User"+
                    "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(20) NOT NULL,"
                    + "lastName VARCHAR(30) NOT NULL,"
                    + "age INT NOT NULL)");

            connection.commit();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false);
            statement.execute("DROP TABLE IF EXISTS User");

            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User (NAME,LASTNAME,AGE) VALUES (?,?,?)")) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");

            while (resultSet.next()) {
                User newUser = new User();

                newUser.setId(resultSet.getLong("id"));
                newUser.setName(resultSet.getString("name"));
                newUser.setName(resultSet.getString("lastName"));
                newUser.setAge(resultSet.getByte("age"));
                listUser.add(newUser);
            }
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false);
            statement.execute("TRUNCATE TABLE User");

            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
