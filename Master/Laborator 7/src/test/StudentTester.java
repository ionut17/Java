package test;

        import model.Student;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.mockito.Mock;
        import repository.StudentRepository;

        import javax.annotation.Resource;
        import javax.faces.bean.ManagedProperty;
        import javax.persistence.EntityManager;
        import javax.persistence.EntityManagerFactory;
        import javax.persistence.PersistenceContext;
        import javax.persistence.PersistenceUnit;
        import javax.transaction.UserTransaction;
        import java.util.List;


        import java.util.List;
        import javax.inject.Inject;
        import javax.persistence.EntityManager;
        import javax.persistence.PersistenceContext;
        import javax.transaction.UserTransaction;
        import org.jboss.arquillian.container.test.api.Deployment;
        import org.jboss.arquillian.junit.Arquillian;
        import org.jboss.shrinkwrap.api.Archive;
        import org.jboss.shrinkwrap.api.ShrinkWrap;
        import org.jboss.shrinkwrap.api.asset.EmptyAsset;
        import org.jboss.shrinkwrap.api.spec.WebArchive;
        import org.junit.runner.RunWith;

        import static org.junit.Assert.assertEquals;

//@RunWith(Arquillian.class)
public class StudentTester {

//    @Deployment
//    public static Archive<?> createDeployment() {
//        return ShrinkWrap.create(WebArchive.class, "test.war")
//                .addPackage(StudentRepository.class.getPackage())
//                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
//                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
//
//    @PersistenceContext
//    EntityManager em;
//
//    @Inject
//    UserTransaction utx;
//
//    // tests go here
//
//    @PersistenceUnit(name="MyApplication")
//    EntityManagerFactory factory;
//
//    @Test
//    public void testGetAll() {
//        EntityManager entityManager = factory.createEntityManager();
//        List<Student> list = entityManager.createQuery("SELECT t FROM Student t").getResultList();
//        assertEquals(list.size(), 101);
//    }
}