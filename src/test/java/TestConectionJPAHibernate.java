
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Test;


public class TestConectionJPAHibernate {
    
    public static final String PERSISTENCE_UNIT_NAME = "MusicaListPU";
    
    
    @Test
    public void testConection(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        assertNotNull(emf);
        System.out.println("Creo el EntityManagerFactory");
        EntityManager emanager = emf.createEntityManager();
        assertNotNull(emanager);
        System.out.println("Creo el EntityManager");
        emanager.close();
        assertTrue(!emanager.isOpen());
        System.out.println("Se cerro corectamente");
    }
}
