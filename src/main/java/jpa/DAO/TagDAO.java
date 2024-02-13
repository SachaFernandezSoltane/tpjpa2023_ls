package jpa.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa.entities.Tag;

import java.util.List;

public class TagDAO {

    private final EntityManager entityManager;

    public TagDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public Tag findById(Long id) {
        return entityManager.find(Tag.class, id);
    }

    public List<Tag> findAll() {
        return entityManager.createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
    }

    public void save(Tag tag) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(tag);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // ou loguer l'erreur
        }
    }

    public void update(Tag tag) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(tag);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // ou loguer l'erreur
        }
    }

    public void delete(Tag tag) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(tag) ? tag : entityManager.merge(tag));
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // ou loguer l'erreur
        }
    }
}
