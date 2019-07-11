package firsttask;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateRun {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User();
        user.setName("test");
        session.save(user);
        System.out.println(session.createQuery("from User").list());
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
