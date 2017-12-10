package repository;

import model.Student;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@ManagedBean(name = "studentRepository")
@ApplicationScoped
public class StudentRepository {

    @PersistenceUnit(name="MyApplication")
    EntityManagerFactory factory;

    @PersistenceContext(name="MyApplication")
    EntityManager entityManager;

    public StudentRepository() {
    }

    public List<Student> getAll() {
        EntityManager entityManager = factory.createEntityManager();

        List<Student> list = entityManager.createQuery("SELECT t FROM Student t").getResultList();
        return list;
    }

    public List<Student> getAllIncomplete() {
        EntityManager entityManager = factory.createEntityManager();

        List<Student> list = entityManager.createQuery("SELECT t FROM Student t").getResultList();
        return list;
    }
}
