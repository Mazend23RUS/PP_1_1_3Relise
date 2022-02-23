package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        //UserDaoJDBCImpl udJDBS = new UserDaoJDBCImpl();
        UserServiceImpl udJDBS = new UserServiceImpl();
        udJDBS.createUsersTable();
        
        udJDBS.saveUser("Работает", "Нихера не работает", (byte) 15);

        System.out.println(udJDBS.getAllUsers());

        udJDBS.cleanUsersTable();
        udJDBS.dropUsersTable();

    }
}
