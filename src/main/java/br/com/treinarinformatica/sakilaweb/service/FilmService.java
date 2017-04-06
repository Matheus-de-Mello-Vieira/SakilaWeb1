/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treinarinformatica.sakilaweb.service;

import br.com.treinarinformatica.sakilaweb.model.Film;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author aula
 */
@Stateless
public class FilmService {
    @PersistenceContext
    private EntityManager em;
    
    public List<Film> listAll() {
        return em.createQuery("select f from Film f").getResultList();
    }
    
    public void saveOrUpdate(Film film) {
        em.persist(film);
    }
    
    public void delete(Integer filmId) {
        em.createQuery("delete from Film f where f.id = :id")
                .setParameter("id", filmId)
                .executeUpdate();
    }
    
    public Film findById(Integer filmId) {
        return em.find(Film.class, filmId);
    }
    
    public Map<Integer, Integer> rentalCountPerFilm() {
List<Object[]> list = em.createQuery("select f.id,count(*) from Rental r " +
                "join r.inventory i join i.film f group by f.id")
        .getResultList();
        Map<Integer,Integer> map = new HashMap<>();
        for (Object[] result : list) {
            Integer filmId = (Integer) result[0];
            Long rentalCount = (Long) result[1];
            map.put(filmId, rentalCount.intValue());
        }
        return map;
    }
}
