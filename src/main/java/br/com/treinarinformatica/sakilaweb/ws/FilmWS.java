/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treinarinformatica.sakilaweb.ws;

import br.com.treinarinformatica.sakilaweb.model.Film;
import br.com.treinarinformatica.sakilaweb.service.FilmService;
import br.com.treinarinformatica.sakilaweb.ws.model.FilmWSModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author aula
 */
@Path("/films")
public class FilmWS {
    @EJB
    private FilmService filmService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Film film) {
        filmService.saveOrUpdate(film);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer filmId) {
        
        filmService.delete(filmId);
        return Response.ok().build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(@QueryParam("countRental") Boolean countRental) {
        List<Film> filmList = filmService.listAll();
        List<FilmWSModel> filmWSModelList = new ArrayList<>();
        Map<Integer,Integer> mapRentalCount = filmService.rentalCountPerFilm();
        for (Film film : filmList) {
            FilmWSModel filmWSModel = new FilmWSModel();
            BeanUtils.copyProperties(filmWSModel,film);
            filmWSModel.setCountRental(mapRentalCount.get(film.getId()));
            filmWSModelList.add(filmWSModel);
        }
        GenericEntity<List<FilmWSModel>> entity = 
                new GenericEntity<List<FilmWSModel>>(filmWSModelList){};
        return Response.ok(entity).build();
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer filmId) {
        Film film = filmService.findById(filmId);
        return Response.ok(film).build();
    }
}
