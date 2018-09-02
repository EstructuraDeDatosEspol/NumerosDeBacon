/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SSAM
 */
public class Pelicula {
    private Integer id;
    private String nombre;
    private List<Actor> actores;

    public Pelicula(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        actores = new LinkedList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }

    @Override
    public String toString() {
        return "Pelicula " + id +" "+ nombre;
    }
    
    
}
