package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
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
            session.persist(user);
            transaction.commit();
            System.out.println("Все норм,данные сохранены");
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
        session.remove(session.get(User.class,id));
        transaction.commit();
        System.out.println("Все норм,данные User удалены ");
        session.close();

    } catch (HibernateException e) {
        System.out.println("Что то пошло не так");
        e.getStackTrace();
        transaction.rollback();
    }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);

        Query<User> query = session.createQuery(cq);
        List<User> userL = query.getResultList();

        try {
            transaction.commit();
            return userL;
        } catch (HibernateException e) {
            System.out.println("Что то пошло не так");
            e.getStackTrace();
            transaction.rollback();
        }
        session.close();
        return userL;
    }

    @Override
    public void cleanUsersTable() {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();

        try {
            session.createNativeQuery("TRUNCATE TABLE db.User;").executeUpdate();
            transaction.commit();
            System.out.println("Удалено");
            session.close();
        } catch (HibernateException e) {
            System.out.println("Что то пошло не так");
            e.printStackTrace();
            transaction.rollback();
        }

    }
}
