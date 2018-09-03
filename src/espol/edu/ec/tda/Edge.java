/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;


/**
 *
 * @author MiguelPS
 */
public class Edge<E> {
    private int peso;
    private Integer idPelicula;
    private Vertex<E> origen;
    private Vertex<E> destino;

    public Edge( Vertex<E> origen, Vertex<E> destino, int peso, Integer idPelicula) {
        this.peso = peso;
        this.origen = origen;
        this.destino = destino;
        this.idPelicula = idPelicula;
    }

    @Override
    public int hashCode() {
        return 7;
    }

    @Override
    public boolean equals(Object obj) {
      
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge<E> other = (Edge<E>) obj;
        
        return this.origen.equals(other.origen) && this.destino.equals(other.destino) && this.peso==other.peso;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Vertex<E> getOrigen() {
        return origen;
    }

    public void setOrigen(Vertex<E> origen) {
        this.origen = origen;
    }

    public Vertex<E> getDestino() {
        return destino;
    }

    public void setDestino(Vertex<E> destino) {
        this.destino = destino;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    @Override
    public String toString() {
        StringBuilder temp= new StringBuilder();
        temp.append("(");
        temp.append(origen.getData()).append(" -|").append(peso).append("|- ").append(destino.getData());
        temp.append(")");
        
        return temp.toString();
    }
    
    
    
    
}
