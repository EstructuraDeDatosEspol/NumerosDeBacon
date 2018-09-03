/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author MiguelPS
 */
public class Vertex<E> {
    private E data;
    private List<Edge<E>> edges;
    
    private int distancia;
    private Vertex<E> antecesor;
    
    private boolean visitado;

    public Vertex(E data) {
        this.data = data;
        this.edges = new LinkedList<>();
        this.visitado = false;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public List<Edge<E>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<E>> arcos) {
        this.edges = arcos;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Vertex<E> getAntecesor() {
        return antecesor;
    }

    public void setAntecesor(Vertex<E> antecesor) {
        this.antecesor = antecesor;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (this == obj) {
            return true;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Vertex<E> other = (Vertex<E>) obj;
        return this.data== other.data;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.data);
        hash = 59 * hash + Objects.hashCode(this.edges);
        hash = 59 * hash + (this.visitado ? 1 : 0);
        return hash;
    }

    @Override
    public String toString() {
        return data.toString();
    }


    
}
