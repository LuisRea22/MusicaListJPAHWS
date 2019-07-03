/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.generation.musicalistjpahws.servicios;

import com.generation.musicalistjpahws.servicios.respuestas.RespuestaServicio;
import com.google.gson.Gson;
import com.mycompany.musicalistjpahws.models.PlayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author USUARIO
 */

@Path("playlists")
public class ServicioPlaylist {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearPlaylist(String json) {

        Gson gson = new Gson();
        EntityManager entityManager = null;

        try {

            PlayList playlist = gson.fromJson(json, PlayList.class);
            playlist.setFechaRegistro(new Date());

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusicaListPU");
            entityManager = emf.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(playlist);
            entityManager.getTransaction().commit();
            
            String respuestaJson = new Gson().toJson(playlist);
            return Response.ok().entity(respuestaJson).build();

        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager != null) {
                if (entityManager.getTransaction() != null) {
                    entityManager.getTransaction().rollback();
                }
            }
            RespuestaServicio rs = new RespuestaServicio();
            rs.setCodigo(0);
            rs.setMensaje(e.getMessage());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rs).build();

        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPlaylists() {

        EntityManager entityManager = null;

        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusicaListPU");

            entityManager = emf.createEntityManager();

            List<PlayList> playlists = entityManager.createQuery("SELECT p FROM PlayList p ").getResultList();

            String respuestaJson = new Gson().toJson(playlists);
            return Response.ok().entity(respuestaJson).build();

        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager != null) {
                if (entityManager.getTransaction() != null) {
                    entityManager.getTransaction().rollback();
                }
            }
            RespuestaServicio rs = new RespuestaServicio();
            rs.setCodigo(0);
            rs.setMensaje(e.getMessage());

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR).entity(rs).build();

        }
    }
}
