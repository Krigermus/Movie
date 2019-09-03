/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Movie;

/**
 *
 * @author Joe
 */
public class MovieDTO {
    private Long id;
    private int year;
    private String name;
    private String[] actors;

    public MovieDTO(Movie m) {
        id = m.getId();
        year = m.getYear();
        name = m.getName();
        actors = m.getActors();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAcotrs() {
        return actors;
    }

    public void setAcotrs(String[] acotrs) {
        this.actors = acotrs;
    }
    
}
