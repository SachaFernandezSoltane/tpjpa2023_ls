package jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpa.entities.Tag;
import jpa.entities.Ticket;
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
            Tag tag = new Tag("Tag1");
            Ticket ticket = new Ticket("ticket1","Premier ticket de test","Pending");
            ticket.setTagList(tag);
            tag.setTicketList(ticket);

            manager.persist(tag);
            manager.persist(user);
            manager.persist(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        manager.close();
        EntityManagerHelper.closeEntityManagerFactory();
        System.out.println(".. done");
    }
}
