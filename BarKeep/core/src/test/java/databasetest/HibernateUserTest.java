package databasetest;

import barkeep.User;
import database.SessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HibernateUserTest {
    private static SessionFactory sf;

    @BeforeAll
    public static void setupHibernate(){
        sf = SessionManager.getSessionFactory();
    }

    @Test
    public void testHibernateGetUser(){
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        User testUser= session.get(User.class, 1);
        assertEquals(1, testUser.getId());
        assertEquals("per", testUser.getUsername());
        assertTrue(testUser.isPassword("passord123"));
        assertEquals("per@gmail.com", testUser.getEmail());
        assertNotNull(testUser.getFriendList());
        assertNotNull(testUser.getIOweYouList());
        session.close();
    }

    @Test
    public void testHibernateStoreAndDeleteUser(){
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        User newUser = new User("test", "test", "test", "test");
        session.save(newUser);
        Query query = session.createQuery("from User order by id desc");
        query.setMaxResults(1);
        User storedUser = (User) query.uniqueResult();
        assertEquals(newUser, storedUser);
        session.delete(storedUser);
        tx.commit();
        session.close();
    }
}
