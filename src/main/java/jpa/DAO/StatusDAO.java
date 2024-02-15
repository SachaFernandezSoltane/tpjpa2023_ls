package jpa.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa.entities.Status;

import java.util.List;

public class StatusDAO {

    private final EntityManager entityManager;

    public StatusDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public Status findById(Long id) {
        return entityManager.find(Status.class, id);
    }

    public List<Status> findAll() {
        return entityManager.createQuery("SELECT u FROM Status u", Status.class).getResultList();
    }

    public void save(Status status) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(status);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // ou loguer l'erreur
        }
    }

    public void update(Status status) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(status);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // ou loguer l'erreur
        }
    }

    public void delete(Status status) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(status) ? status : entityManager.merge(status));
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // ou loguer l'erreur
        }
    }
}
