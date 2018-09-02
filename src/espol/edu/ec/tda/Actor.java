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
public class Actor {
    private Integer id;
    private String nombre;
    private List<Pelicula> peliculas;

    public Actor(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        peliculas = new LinkedList<>();
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

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public String toString() {
        return "Actor:" + id + " " + nombre;
    }
}