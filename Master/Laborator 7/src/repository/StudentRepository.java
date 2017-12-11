package repository;

import model.Student;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.*;
import java.util.List;

@Stateless
@ManagedBean(name = "studentRepository")
@ApplicationScoped
public class StudentRepository {

    @PersistenceUnit(name="MyApplication")
    EntityManagerFactory factory;

    @PersistenceContext(name="MyApplication")
    EntityManager entityManager;

    @Resource
    UserTransaction tx;

    public StudentRepository() {
    }

    public List<Student> getAll() {
        EntityManager entityManager = factory.createEntityManager();

        List<Student> list = entityManager.createQuery("SELECT t FROM Student t").getResultList();
        return list;
    }

    public List<Student> getAllIncomplete() {
        EntityManager entityManager = factory.createEntityManager();

        List<Student> list = entityManager.createQuery("SELECT s FROM Student s WHERE s.projects.size < (select count(p) from Project p)").getResultList();
        return list;
    }

    @Transactional
    public void add(Student student) throws Exception {
        tx.begin();
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.persist(student);
        } finally {
            entityManager.close();
        }
        tx.commit();
    }

    @Transactional
    public void delete(Student student) throws Exception {
        tx.begin();
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.remove(student);
        } finally {
            entityManager.close();
        }
        tx.commit();
    }
}
