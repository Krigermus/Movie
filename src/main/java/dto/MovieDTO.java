/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Movie;
import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.year;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Arrays.deepHashCode(this.actors);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MovieDTO other = (MovieDTO) obj;
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Arrays.deepEquals(this.actors, other.actors)) {
            return false;
        }
        return true;
    }
    
    
    
}
