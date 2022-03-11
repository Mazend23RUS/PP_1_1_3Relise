package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
private static SessionFactory sessionFactory = Util.HibernateConnection();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS db.User " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery(sql).executeUpdate();

            transaction.commit();
            sessionFactory.close();
            session.close();
            System.out.println("Все норм, табличка создана");

        } catch (HibernateException e) {
            System.out.println("Что то пошло не так");
            e.getStackTrace();
            transaction.rollback();
        }


    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS db.User";

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            sessionFactory.close();
            session.close();
            System.out.println("Все норм, табличка удалена");
        } catch (HibernateException e) {
            System.out.println("Что то пошло не так");
            e.getStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
            User user = new User(name,lastName,age);
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
        try {
            session.save(user);

            transaction.commit();
            sessionFactory.close();
            System.out.println("Все норм,данные сохранены ");
            session.close();

        } catch (HibernateException e) {
            System.out.println("Что то пошло не так");
            e.getStackTrace();
            transaction.rollback();
        }
    }


    @Override
    public void removeUserById (long id) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();

    try {
        session.delete(String.valueOf(User.class),id);
        transaction.commit();
        sessionFactory.close();
        System.out.println("Все норм,данные User удалены ");
        session.close();

    } catch (HibernateException e) {
        System.out.println("Что то пошло не так");
        e.getStackTrace();
        transaction.rollback();
    }
    }


    List <User> users;
    @Override
    public List<User> getAllUsers() {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();

    try {
        users = (List<User>) session.createCriteria(User.class).list();

    } catch (HibernateException e){
        System.out.println("Что то пошло не так");
        e.getStackTrace();
        transaction.rollback();
    }
        return users;
    }

    @Override
    public void cleanUsersTable() {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();

    try {
        users = (List<User>) session.createCriteria(User.class).list();
        for (User u:users) {
            session.delete(u);
        }
        transaction.commit();
        sessionFactory.close();
        System.out.println("Удалено");
    } catch (HibernateException e){
        System.out.println("Что то пошло не так");
        e.getStackTrace();
    }
    }
}
