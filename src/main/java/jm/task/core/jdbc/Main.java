package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDaoJDBCImpl udHB = new UserDaoJDBCImpl();

        udHB.createUsersTable();

        udHB.saveUser("Fitag", "Luano", (byte) 15);
        udHB.saveUser("Bob", "Marly", (byte) 35);
        udHB.saveUser("Robert", "Kuno", (byte) 28);
        udHB.saveUser("Sub", "Zero", (byte) 32);

        System.out.println(udHB.getAllUsers());

        udHB.cleanUsersTable();
        udHB.dropUsersTable();

    }
}
