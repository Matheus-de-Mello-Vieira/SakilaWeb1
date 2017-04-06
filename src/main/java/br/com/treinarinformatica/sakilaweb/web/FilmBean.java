/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treinarinformatica.sakilaweb.web;

import br.com.treinarinformatica.sakilaweb.model.Film;
import br.com.treinarinformatica.sakilaweb.service.FilmService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author aula
 */
@Named
@ViewScoped
public class FilmBean implements Serializable {
    
    @EJB
    private FilmService filmService;
    
    private List<Film> filmList;
    
    /**
     * LIST, CREATE
     */
    private String currentState;
    
    private Film film;
    
    @PostConstruct
    private void init() {
        filmList = filmService.listAll();
        currentState = "LIST";
    }
    
    public void newFilm() {
        film = new Film();
        currentState = "CREATE";
    }
    
    public void save() {
        FacesMessage msg = null;
        try {
            filmService.saveOrUpdate(film);
            film = null;
            currentState = "LIST";
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
            "Saved successfully",null);
            filmList = filmService.listAll();
        } catch (Exception ex) {
            msg = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,"Failed to save film",null);
        } finally {
            if (msg != null) {
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    /**
     * @return the filmList
     */
    public List<Film> getFilmList() {
        return filmList;
    }

    /**
     * @param filmList the filmList to set
     */
    public void setFilmList(List<Film> filmList) {
        this.filmList = filmList;
    }

    /**
     * @return the currentState
     */
    public String getCurrentState() {
        return currentState;
    }

    /**
     * @param currentState the currentState to set
     */
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    /**
     * @return the film
     */
    public Film getFilm() {
        return film;
    }

    /**
     * @param film the film to set
     */
    public void setFilm(Film film) {
        this.film = film;
    }
}
