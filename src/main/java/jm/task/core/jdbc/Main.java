package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import org.hibernate.HibernateException;

public class Main {
    public static void main(String[] args) throws HibernateException {
        UserDaoHibernateImpl udHB = new UserDaoHibernateImpl();
        //Util ut = new Util();
        //ut.dropUsersTable();
        //ut.createUsersTable();
//      udHB.createUsersTable();

//        udHB.saveUser("Fitag", "Luano", (byte) 15);
//        udHB.saveUser("Bob", "Marly", (byte) 35);
//        udHB.saveUser("Robert", "Kuno", (byte) 28);
//        udHB.saveUser("Sub", "Zero", (byte) 32);

        System.out.println(udHB.getAllUsers());
//
//        udHB.cleanUsersTable();
//        udHB.dropUsersTable();

    }
}
