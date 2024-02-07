package jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpa.entities.User;

public class JpaTest {


    private EntityManager manager;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        EntityManager manager = EntityManagerHelper.getEntityManager();

        JpaTest test = new JpaTest(manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            User user = new User("john_doe", "john@example.com", "password123");
            // Persistance de l'utilisateur dans la base de données
            test.persistUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        manager.close();
        EntityManagerHelper.closeEntityManagerFactory();
        System.out.println(".. done");
    }

    public void persistUser(User user) {
        manager.persist(user);
    }


}
