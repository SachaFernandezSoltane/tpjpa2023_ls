package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpa.DAO.StatusDAO;
import jpa.DAO.TagDAO;
import jpa.DAO.TicketDAO;
import jpa.DAO.UserDAO;
import jpa.entities.Status;
import jpa.entities.Tag;
import jpa.entities.Ticket;
import jpa.entities.User;

public class JpaTest {

    private EntityManager manager;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
    }

    public static void main(String[] args) {
        EntityManager manager = EntityManagerHelper.getEntityManager();
        UserDAO userDAO = new UserDAO();
        TagDAO tagDAO = new TagDAO();
        TicketDAO ticketDAO = new TicketDAO();
        StatusDAO statusDAO = new StatusDAO();

        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            //TODO : fix concurence issue
//            manager.createNativeQuery("DELETE FROM tag_list").executeUpdate();
//            manager.createNativeQuery("DELETE FROM tag").executeUpdate();
//            manager.createNativeQuery("DELETE FROM ticket").executeUpdate();
//            manager.createNativeQuery("DELETE FROM user").executeUpdate();

            // Créer et sauvegarder un utilisateur
            User user = new User("john_doe", "john@example.com", "password123");
            userDAO.save(user);

            Status status = new Status();
            statusDAO.save(status);

            // Créer et sauvegarder un ticket associé à l'utilisateur
            Ticket ticket = new Ticket("ticket1", "Premier ticket de test", status,user);
            ticketDAO.save(ticket);

            // Créer un tag
            Tag tag = new Tag("Tag1");

            // Associer le ticket au tag
            tag.setTicketList(ticket);

            // Sauvegarder le tag
            tagDAO.save(tag);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // Annuler la transaction en cas d'erreur
        } finally {
            manager.close();
            EntityManagerHelper.closeEntityManagerFactory();
        }
    }
}
