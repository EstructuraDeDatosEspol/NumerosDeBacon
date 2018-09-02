/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph<E> {

    private List<Vertex<E>> vertices;
    private boolean dirigido;

    public Graph(boolean dirigido) {
        this.vertices = new LinkedList<>();
        this.dirigido = dirigido;
    }

    private void dijkstra(Vertex<E> origen, Vertex<E> destino) {
        cleanRoute();

        PriorityQueue<Vertex<E>> tempVertexQueue = new PriorityQueue<>((Vertex<E> v1, Vertex<E> v2) -> (v1.getDistancia() - v2.getDistancia()));

        origen.setDistancia(0);
        Vertex<E> v = origen;

        while (v!= null && !v.equals(destino)) {
            
            if(!v.isVisitado()){
                for (Edge<E> edge : v.getEdges()) {
                    if (v.getDistancia() + edge.getPeso() < edge.getDestino().getDistancia()) {
                        edge.getDestino().setDistancia(v.getDistancia() + edge.getPeso());
                        edge.getDestino().setAntecesor(v);
                        tempVertexQueue.offer(edge.getDestino());
                    }
                }
                v = tempVertexQueue.poll();
            }
            
        }
    }

    public List<E> bfs(E inicio, E destino) {
        cleanRoute();

        LinkedList<E> path = new LinkedList<>();
        
        if (!contains(inicio)) {
            return path;
        }

        Queue<Vertex<E>> tempQueue = new LinkedList<>();

        Vertex<E> v = searchVertex(inicio);

        if (v == null) {
            return path;
        }

        v.setVisitado(true);
        tempQueue.offer(v);

        Vertex<E> tempVertex;

        while (!tempQueue.isEmpty()) {
            tempVertex = tempQueue.poll();

            for (Edge<E> edge : tempVertex.getEdges()) {
                if (!edge.getDestino().isVisitado()) {
                    edge.getDestino().setVisitado(true);
                    edge.getDestino().setAntecesor(tempVertex);
                    tempQueue.offer(edge.getDestino());
                }
            }
        }

        Vertex<E> d = searchVertex(destino);
        
        while (d != null) {
            path.addFirst(d.getData());
            d = d.getAntecesor();
        }

        return path;
    }

    public List<E> caminoMinimo(E origen, E destino) {
        LinkedList<E> camino = new LinkedList<>();

        Vertex<E> o = searchVertex(origen);
        Vertex<E> d = searchVertex(destino);

        if (o == null || d == null) {
            return camino;
        }

        dijkstra(o, d);
        Vertex<E> temp = d;

        camino.add(temp.getData());
        while (temp.getAntecesor() != null) {
            camino.addFirst(temp.getAntecesor().getData());
            temp = temp.getAntecesor();
        }

        return camino;
    }

    public int getIdEdge(E inicio, E fin) {
        Vertex<E> vi = searchVertex(inicio);
        Vertex<E> vf = searchVertex(fin);
        
        if(vi == null || vf == null)
            return 0;
        
        for (Edge<E> e : vi.getEdges()) {
            if (e.getOrigen().equals(vi) && e.getDestino().equals(vf)) {
                return e.getIdPelicula();
            }
        }
        return -1;
    }

    public int distanciaMinima(E origen, E destino) {

        int distancia = 0;

        Vertex<E> o = searchVertex(origen);
        Vertex<E> d = searchVertex(destino);

        if (o == null || d == null) {
            return distancia;
        }

        dijkstra(o, d);

        return d.getDistancia();
    }

    public boolean contieneCiclo() {

        for (Vertex<E> v : vertices) {
            cleanRoute();
            if (contieneCiclo(v)) {
                return true;
            }
        }
        cleanRoute();
        return false;
    }

    private boolean contieneCiclo(Vertex<E> temp) {
        temp.setVisitado(true);
        for (Edge<E> edge : temp.getEdges()) {
            if (edge.getDestino().isVisitado()) {
                return true;
            }
            contieneCiclo(edge.getDestino());
        }
        return false;
    }

    public boolean addVertex(E data) {
        if (contains(data)) {
            return false;
        }

        return vertices.add(new Vertex<>(data));
    }

    public boolean contains(E data) {

        if (data == null) {
            return true;
        }

        for (Vertex<E> v : vertices) {
            if (v.getData().equals(data)) {
                return true;
            }
        }
        return false;
    }

    private Vertex<E> searchVertex(E data) {
        for (Vertex<E> v : vertices) {
            if (v.getData().equals(data)) {
                return v;
            }
        }
        return null;
    }

    public boolean addEdge(E origen, E destino, Integer idPelicula) {
        Vertex<E> vo = searchVertex(origen);
        Vertex<E> vd = searchVertex(destino);

        if (vo == null || vd == null) {
            return false;
        }

        Edge<E> newEdge = new Edge<>(vo, vd, 1, idPelicula);

        if (vo.getEdges().contains(newEdge)) {
            return false;
        }

        vo.getEdges().add(newEdge);

        if (!dirigido) {
            Edge<E> e = new Edge<>(vd, vo, 1, idPelicula);
            vd.getEdges().add(e);
        }
        return true;
    }

    public boolean removeVertex(E data) {
        if (data == null || !contains(data)) {
            return false;
        }
        Vertex<E> v = searchVertex(data);

        if (v == null) {
            return false;
        }

        for (Edge<E> edge : v.getEdges()) {
            if (edge.getDestino().equals(v)) {
                removeEdge(edge.getDestino().getData(), v.getData());
            }
        };
        vertices.remove(v);
        return true;

    }

    public boolean removeEdge(E origen, E destino) {
        Vertex<E> o = searchVertex(origen);
        Vertex<E> d = searchVertex(destino);

        if (o == null || d == null) {
            return false;
        }
        if (dirigido) {
            return removeEdge(o, d);
        } else {
            return removeEdge(o, d) && removeEdge(d, o);
        }

    }

    private boolean removeEdge(Vertex<E> o, Vertex<E> d) {

        for (Edge<E> edge : o.getEdges()) {
            if (edge.getOrigen().equals(o) && edge.getDestino().equals(d)) {
                o.getEdges().remove(edge);
                return true;
            }
        }
        return false;
    }

    public int outDegree(E data) {
        int degree = 0;

        for (Vertex<E> vertice : vertices) {
            if (data.equals(vertice.getData())) {
                return vertice.getEdges().size();
            }
        }
        return degree;
    }

    public int inDegree(E data) {
        int degree = 0;
        for (Vertex<E> v : vertices) {
            for (Edge<E> edge : v.getEdges()) {
                if (edge.getDestino().getData().equals(data)) {
                    degree++;
                }
            }
        }

        return degree;
    }

    // limpia recorridos previos
    private void cleanRoute() {

        for (Vertex<E> v : vertices) {
            v.setVisitado(false);
            v.setDistancia(Integer.MAX_VALUE);
            v.setAntecesor(null);
        }
    }

    public List<E> getVertex() {
        List<E> vertex = new LinkedList<>();

        for (Vertex<E> v : vertices) {
            vertex.add(v.getData());
        }

        return vertex;
    }

    public Graph<E> invertir() {
        Graph<E> inverted = new Graph<>(dirigido);

        for (Vertex<E> v : vertices) {
            inverted.addVertex(v.getData());
        }

        for (Vertex<E> v : vertices) {
            for (Edge<E> edge : v.getEdges()) {
                inverted.addEdge(edge.getDestino().getData(), edge.getOrigen().getData(), edge.getPeso());
            }
        }

        return inverted;
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        temp.append("Vertex: ");
        temp.append(vertices).append("\n");

        temp.append("Edges: ");
        for (Vertex<E> v : vertices) {
            for (Edge<E> edge : v.getEdges()) {
                temp.append(edge).append(" ");
            }

        }
        return temp.toString();
    }
}
