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

        String sqlRequest = "CREATE TABLE IF NOT EXISTS User"
                + "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + "name VARCHAR(20) NOT NULL,"
                + "lastName VARCHAR(30) NOT NULL,"
                + "age INT NOT NULL)";

        try (PreparedStatement ps = connection.prepareStatement(sqlRequest)) {

            connection.setAutoCommit(false);
            ps.executeUpdate();
            connection.commit();

            try {
                connection.close();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("В ходе закрытия соединения возникла ошибка");
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {
        String sqlRequest = "DROP TABLE IF EXISTS User";
        try (PreparedStatement ps = connection.prepareStatement(sqlRequest)) {

            connection.setAutoCommit(false);
            ps.executeUpdate();

            connection.commit();

            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("В ходе закрытия соединения возникла ошибка");
            }


        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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

            try {
                connection.close();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("В ходе закрытия соединения возникла ошибка");
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id = ?")) {

            connection.setAutoCommit(false);

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            connection.commit();


            try {
                connection.close();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("В ходе закрытия соединения возникла ошибка");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM User")) {

            connection.setAutoCommit(false);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                User newUser = new User();

                newUser.setId(resultSet.getLong("id"));
                newUser.setName(resultSet.getString("name"));
                newUser.setName(resultSet.getString("lastName"));
                newUser.setAge(resultSet.getByte("age"));
                listUser.add(newUser);
            }
            connection.commit();


            try {
                connection.close();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("В ходе закрытия соединения возникла ошибка");
            }


        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return listUser;
    }

    public void cleanUsersTable() {
        try (PreparedStatement ps = connection.prepareStatement("TRUNCATE TABLE User")) {

            connection.setAutoCommit(false);
            ps.execute();

            connection.commit();

            try {
                connection.close();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("В ходе закрытия соединения возникла ошибка");
            }


        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }
}
