package repository;

import jdk.nashorn.internal.runtime.ECMAException;
import model.Project;
import model.Student;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@Stateless
@ManagedBean(name = "projectRepository")
@ApplicationScoped
public class ProjectRepository {

    @PersistenceUnit(name="MyApplication")
    EntityManagerFactory factory;

    @PersistenceContext(name="MyApplication")
    EntityManager entityManager;

    @Resource
    UserTransaction tx;

    public ProjectRepository() {
    }

    public List<Project> getAll() {
        EntityManager entityManager = factory.createEntityManager();

        List<Project> list = entityManager.createQuery("SELECT t FROM Project t").getResultList();
        return list;
    }

    public Project getById(Long id){
        EntityManager entityManager = factory.createEntityManager();
        List<Project> project = entityManager.createQuery("SELECT p from Project p where p.id = "+id).getResultList();
        return project.get(0);
    }

    @Transactional
    public void add(Project project) throws Exception{
        tx.begin();
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.persist(project);
        } finally {
            entityManager.close();
        }
        tx.commit();
    }

    @Transactional
    public void delete(Project project) throws Exception{
        tx.begin();
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.remove(project);
        } finally {
            entityManager.close();
        }
        tx.commit();
    }
}
