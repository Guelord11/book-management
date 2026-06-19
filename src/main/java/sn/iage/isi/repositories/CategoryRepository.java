package sn.iage.isi.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import sn.iage.isi.entities.Category;

import java.util.List;

public class CategoryRepository {
    EntityManager em = JpaUtil.getEntityManager();

    public Category create(Category category) {
        EntityTransaction tx = em.getTransaction();
        Category c = Category.builder().name(category.getName()).state(Boolean.TRUE).build();
                 c.setUserCreated("admin");
                 c.setUserUpdated("admin");

        try {
            //Debut de la transaction
            tx.begin();
            em.persist(c);
            tx.commit(); //Valider la transaction
            return c;
        } catch (Exception e) {
            tx.rollback();
            return null;
        }
    }

    public List<Category> getAll() {
//        return em.createQuery("SELECT c FROM Category c ORDER BY c.name ASC", Category.class).getResultList();
        return em.createNamedQuery("Category.findAll", Category.class).getResultList();
    }
}
