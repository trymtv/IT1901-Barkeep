package database;

import barkeep.Drink;
import barkeep.Friendship;
import barkeep.IOweYou;
import barkeep.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {
  private static SessionFactory sf;

  /**
   * Sets a global session factory for the hibernate session.
   *
   * @return The configured session factory
   */
  public static SessionFactory getSessionFactory() {
    if (sf == null) {
      sf = new Configuration()
          .addAnnotatedClass(User.class)
          .addAnnotatedClass(Friendship.class)
          .addAnnotatedClass(IOweYou.class)
          .addAnnotatedClass(Drink.class)
          .configure("./hibernate.cfg.xml")
          .buildSessionFactory();
    }
    return sf;
  }
}
