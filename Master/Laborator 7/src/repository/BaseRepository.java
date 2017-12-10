package repository;

import model.Item;

import javax.persistence.*;
import java.util.List;

public abstract class BaseRepository<T extends Item> {

    protected String table;

    @PersistenceUnit
    EntityManagerFactory factory;

    @PersistenceContext
    EntityManager entityManager;

    public BaseRepository(){
    }

    public List<T> getAll() {
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();
        List<T> list = entityManager.createQuery("SELECT t FROM "+this.table+" t").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return list;
    }

}
