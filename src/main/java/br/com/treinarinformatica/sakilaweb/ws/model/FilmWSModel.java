/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treinarinformatica.sakilaweb.ws.model;

/**
 *
 * @author aula
 */
public class FilmWSModel {
    private Integer id;
    private String title;
    private Integer countRental;
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the countRental
     */
    public Integer getCountRental() {
        return countRental;
    }

    /**
     * @param countRental the countRental to set
     */
    public void setCountRental(Integer countRental) {
        this.countRental = countRental;
    }
    
}
