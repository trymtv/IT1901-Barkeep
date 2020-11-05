package databasetest;

import barkeep.Drink;
import database.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HibernateDrinkTest {
    private static SessionFactory sf;

    @BeforeAll
    public static void setupHibernate(){
        sf = SessionManager.getSessionFactory();
    }

    @Test
    public void testHibernateGetDrink(){
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Drink testDrink = session.get(Drink.class, 1);
        Drink compareDrink = new Drink();
        compareDrink.setName("Vann");
        compareDrink.setId(1);
        compareDrink.setValue(20);
        compareDrink(testDrink, compareDrink);
        session.close();
    }

    @Test
    public void testHibernateStoreAndDeleteDrink(){
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Drink newDrink = new Drink();
        newDrink.setValue(100);
        newDrink.setName("TestDrink");
        session.save(newDrink);
        Query query = session.createQuery("from Drink order by id desc");
        query.setMaxResults(1);
        Drink storedDrink = (Drink) query.uniqueResult();
        session.delete(storedDrink);
        tx.commit();
        session.close();
    }

    private void compareDrink(Drink drink1, Drink drink2){
        assertEquals(drink1.getId(), drink2.getId());
        assertEquals(drink1.getValue(), drink2.getValue());
        assertEquals(drink1.getName(), drink2.getName());
    }

}
