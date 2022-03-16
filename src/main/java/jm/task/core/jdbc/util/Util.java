package jm.task.core.jdbc.util;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;


public class Util extends UserDaoHibernateImpl {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "Mazend23";
    private static final String PASSWORD = "S5846124Q/&dl*d/";
    private static SessionFactory sessionfactory;

    public static SessionFactory HibernateConnection() {

        if (sessionfactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/db?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
                settings.put(Environment.USER, "Mazend23");
                settings.put(Environment.PASS, "S5846124Q/&dl*d/");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionfactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Соединение установлено");
            } catch (Exception e) {
                System.out.println("Не удалось устновить соединение");
                e.printStackTrace();
            }
        }
        return sessionfactory;
    }

//    public static void main(String[] args) {
//        HibernateConnection();
//    }
//UserDaoHibernateImpl usHB = new UserDaoHibernateImpl();
//
//
//    @Override
//    public void dropUsersTable() {
//        super.dropUsersTable();
//    }

    //    public static Connection ConnectionC() throws SQLException {
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            return connection;
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new SQLException("Не удалось установить соединение");
//        }
//    }

}




