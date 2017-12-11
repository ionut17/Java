package repository;

import model.Project;
import model.Student;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@ManagedBean(name = "projectRepository")
@ApplicationScoped
public class ProjectRepository {

    @PersistenceUnit(name="MyApplication")
    EntityManagerFactory factory;

    @PersistenceContext(name="MyApplication")
    EntityManager entityManager;

    public ProjectRepository() {
    }

    public List<Project> getAll() {
        EntityManager entityManager = factory.createEntityManager();

        List<Project> list = entityManager.createQuery("SELECT t FROM Project t").getResultList();
        return list;
    }
}
