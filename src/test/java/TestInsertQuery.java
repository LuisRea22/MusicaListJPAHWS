
import com.mycompany.musicalistjpahws.models.Artista;
import com.mycompany.musicalistjpahws.models.Cancion;
import com.mycompany.musicalistjpahws.models.PlayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USUARIO
 */
public class TestInsertQuery {
    
    private static final String PERSISTENCE_UNIT_NAME = "MusicaListPU";
    
    private EntityManager entityManager;
    
    @Before
    public void testConnection(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        assertNotNull(emf);
        System.out.println("Se creo un EntityManager");
        entityManager = emf.createEntityManager();
        assertNotNull(entityManager);
        System.out.println("Se creo un EntityManagerFactory");
    }
    
    @Test
    public void testCancionQuery(){
       /* EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        assertNotNull(emf);
        System.out.println("Se creo un EntityManager");
        EntityManager entityManager = emf.createEntityManager();
        assertNotNull(entityManager);
        System.out.println("Se creo un EntityManagerFactory");
        */
       
        entityManager.getTransaction().begin();
    
        Artista cepillin = new Artista();
        cepillin.setNombre("Italobrothers");
        cepillin.setResume("Musica movida");
        cepillin.setGenero("Electronica");
        cepillin.setImagenUrl("https://media.metrolatam.com/2018/01/24/cepillinrecuperacionestbale-15d0713ba0b475aa516b054aa4fb7d60-600x400.jpg");
        cepillin.setFechaRegistro(new Date());
        
        entityManager.persist(cepillin);
        
        assertTrue(cepillin.getId() > 0);
        System.out.println("Se creo un un objeto artista");

        Cancion lasMañanitas = new Cancion();
        lasMañanitas.setNombre("Sorry");
        lasMañanitas.setDuracion(3);
        lasMañanitas.setArtista(cepillin);
        lasMañanitas.setIdArtista(cepillin.getId());
        lasMañanitas.setFechaRegistro(new Date());
        
        entityManager.persist(lasMañanitas);
        
        assertTrue(lasMañanitas.getId() > 0);
        System.out.println("Se creo un objeto cancion");  
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        
        assertFalse(entityManager.isOpen());
        System.out.println("Se cerro");
        
    }
    
    //@Test
     public void  testCancion1(){
       /* EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        assertNotNull(emf);
        System.out.println("Se creo un EntityManager");
        entityManager = emf.createEntityManager();
        assertNotNull(entityManager);
        System.out.println("Se creo un EntityManagerFactory");
        */
         
        Artista artista = entityManager.find(Artista.class, 2);
        assertNotNull(artista);
        
        Cancion cancion2 = new Cancion();
        cancion2.setArtista(artista);
        cancion2.setIdArtista(artista.getId());
        cancion2.setNombre("Generation Party");
        cancion2.setDuracion(3);
        cancion2.setFechaRegistro(new Date());
        
        entityManager.getTransaction().begin();
        
        entityManager.persist(cancion2);
        
        assertTrue(cancion2.getId() > 0);
        System.out.println("Se creo un objeto cancion");  
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        
        assertFalse(entityManager.isOpen());
        System.out.println("Se cerro");
                
     }
    
    //@Test
     public void testPlaylistCancionInsertQuery(){
         
         try {
                Cancion cancion2 = entityManager.find(Cancion.class, 9);
                assertNotNull(cancion2);
                PlayList playlist = entityManager.find(PlayList.class, 1);
                assertNotNull(playlist);
                
                List<Cancion> canciones = playlist.getCanciones();
                
                int numeroCanciones = canciones.size();
                entityManager.getTransaction().begin();
                //Agregar la cancion  a la playlist
                canciones.add(cancion2);
                assertTrue(canciones.size() > numeroCanciones);
                
                //Enviar cambios a la base de tados
                entityManager.getTransaction().commit();
                System.out.println("Se agrego la cancion a la playlist");
     
         } catch (Exception e) {
             e.printStackTrace();
             if(entityManager.getTransaction() != null){
                 entityManager.getTransaction().rollback();
             }
             
         }
     } 
     
    /* @Test
     public void testPlaylistUsuarioInsertQuery(){
         
         try {
                PlayList playlist = entityManager.find(PlayList.class, 1);
                assertNotNull(playlist);
                Usuario usuario1 = entityManager.find(Usuario.class, 1);
                assertNotNull(usuario1);
                
                List<PlayList> playlists = usuario1.getPlaylists();
                
                int numeroPlaylist = playlists.size();
                entityManager.getTransaction().begin();
                //Agregar la playlist  a al usuario
                playlists.add(playlist);
                assertTrue(playlists.size() > numeroPlaylist);
                
                //Enviar cambios a la base de tados
                entityManager.getTransaction().commit();
                System.out.println("Se agrego la playlisyt al usuario");
     
         } catch (Exception e) {
             e.printStackTrace();
             if(entityManager.getTransaction() != null){
                 entityManager.getTransaction().rollback();
             }
             
         }
     } */
     
         @After
         public void closeConnection(){
            entityManager.close();
            assertFalse(entityManager.isOpen());
            System.out.println("Se cerro el sistema");
     }
   }
     
    
       

