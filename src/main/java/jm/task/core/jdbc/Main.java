package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl udJDBS = new UserDaoJDBCImpl();

        udJDBS.createUsersTable();

        udJDBS.saveUser("Tom","Hurdy", (byte) 35);
        udJDBS.saveUser("Tim","Murry",(byte) 27);
        udJDBS.saveUser("Alien","Evil",(byte) 41);
        udJDBS.saveUser("Jason","Whorehis",(byte) 50);

        System.out.println(udJDBS.getAllUsers());

        udJDBS.cleanUsersTable();
        udJDBS.dropUsersTable();

    }
}
