
import com.mycompany.musicalistjpahws.models.Artista;
import com.mycompany.musicalistjpahws.models.Cancion;
import com.mycompany.musicalistjpahws.models.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static org.junit.Assert.*;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lesser
 */
public class TestSelectQuery {

    private static final String PERSISTENCE_UNIT_NAME = "MusicaListPU";

    @Test
    public void testSelect() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        assertNotNull(emf);
        System.out.println("Se creo un EntityManagerFactory");

        EntityManager entityManager = emf.createEntityManager();
        assertNotNull(entityManager);
        System.out.println("Se creo un EntityManager");

        Query query = entityManager.createQuery("SELECT c FROM Cancion c");
        assertNotNull(query);
        List<Cancion> canciones = query.getResultList();
        assertFalse(canciones.isEmpty());
        for (Cancion cancion : canciones) {
            System.out.println(cancion);
        }

        Query queryArtista = entityManager.createQuery("SELECT a FROM Artista a");
        assertNotNull(queryArtista);
        List<Artista> artistas = queryArtista.getResultList();
        assertFalse(artistas.isEmpty());
        for (Artista artista : artistas) {
            System.out.println(artista);
        }

        Cancion cancion1 = entityManager.find(Cancion.class, 1);
        assertNotNull(cancion1);
        System.out.println(cancion1);
        Cancion cancion2 = entityManager.find(Cancion.class, 9);
        assertNotNull(cancion2);
        System.out.println(cancion2);
        Cancion cancion3 = entityManager.find(Cancion.class, 10);
        assertNotNull(cancion3);
        System.out.println(cancion3);

        Query queryUsuario = entityManager.createQuery("SELECT u FROM Usuario u where u.correo = :correo  AND u.contraseña= :contraseña");
        queryUsuario.setParameter("correo", "luis.alberto.rea.lopez@hotmail.com");
        String contraseña = ("12345");
        assertNotNull(queryUsuario);
        queryUsuario.setParameter("contraseña", contraseña);
        Usuario usuario = (Usuario) queryUsuario.getSingleResult();
        assertNotNull(usuario);
        System.out.println(usuario);

        Query queryListaUsuario = entityManager.createQuery("FROM Usuario u");
        assertNotNull(queryListaUsuario);
        List<Usuario> usuarios = queryListaUsuario.getResultList();
        for (Usuario usuario1 : usuarios) {
            System.out.println(usuario1);
        }
    }
}
