/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SSAM
 */
public class FileWorker {
    private  List<Actor> listaActores;
    private  List<Pelicula> listaPeliculas;
    private  Map<Integer, String> mapaActores;
    private  Map<Integer, String> mapaPeliculas;
    private  Map<Integer, List<Integer>> mapaPeliculaActor;
    private  List<String> listaNombres;
    private  Graph<Integer> grafo;

    public FileWorker() {
    }
    
    public List<Actor> leerArchivosActoresL(){
        listaActores = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src\\espol\\edu\\ec\\recursos\\actores.txt"))){
            String linea = "";
            while((linea = br.readLine()) != null){
                String[] arreglo = linea.split("[|]");
                listaActores.add(new Actor(Integer.valueOf(arreglo[0]), arreglo[1]));
            }
        }catch(Exception e){
            e.getStackTrace();
        }        
        return listaActores;
    }
    
    public List<Pelicula> leerArchivosPeliculasL(){
        listaPeliculas = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src\\espol\\edu\\ec\\recursos\\peliculas.txt"))){
            String linea = "";
            while((linea = br.readLine()) != null){
                String[] arreglo = linea.split("[|]");
                listaPeliculas.add(new Pelicula(Integer.valueOf(arreglo[0]), arreglo[1]));
            }
        }catch(Exception e){
            e.getStackTrace();
        }        
        return listaPeliculas;
    }
    
    public void relacionarL(){
        leerArchivosPeliculasL();
        leerArchivosActoresL();
        
        try (BufferedReader br = new BufferedReader(new FileReader("src\\espol\\edu\\ec\\recursos\\pelicula-actores.txt"))){
            String linea = "";
            while((linea = br.readLine()) != null){
                String[] arreglo = linea.split("[|]");
                for(Pelicula p: listaPeliculas){
                    if(p.getId().equals(Integer.valueOf(arreglo[0]))){
                        for(Actor a: listaActores){
                            if(a.getId().equals(Integer.valueOf(arreglo[1]))){
                                a.getPeliculas().add(p);
                                p.getActores().add(a);
                            }                                
                        }
                    }                        
                }
            }
        }catch(Exception e){
            e.getStackTrace();
        }        
    }
    
    
    public Map<Integer,String> leerArchivosActoresM(){
        mapaActores = new HashMap<>();
        listaNombres = new LinkedList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("src\\espol\\edu\\ec\\recursos\\actores.txt"))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] arreglo = linea.split("\\|");
                mapaActores.put(Integer.parseInt(arreglo[0]), arreglo[1]);
                listaNombres.add(arreglo[1]);
            }
        }catch(Exception e){
            e.getStackTrace();
        }        
        return mapaActores;
    }
    
    public Map<Integer, String> leerArchivosPeliculasM(){
        mapaPeliculas = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src\\espol\\edu\\ec\\recursos\\peliculas.txt"))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] arreglo = linea.split("\\|");
                mapaPeliculas.put(Integer.valueOf(arreglo[0]), arreglo[1]);
                
            }
        }catch(Exception e){
            e.getStackTrace();
        }        
        return mapaPeliculas;
    }
    
    public void relacionarM(){
        leerArchivosPeliculasM();
        leerArchivosActoresM();
        mapaPeliculaActor = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("src\\espol\\edu\\ec\\recursos\\pelicula-actores.txt"))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] arreglo = linea.split("\\|");
                if(mapaPeliculaActor.containsKey(Integer.valueOf(arreglo[0]))){
                    mapaPeliculaActor.get(Integer.valueOf(arreglo[0])).add(Integer.valueOf(arreglo[1]));
                }else{
                    List<Integer> l = new LinkedList<>();
                    l.add(Integer.valueOf(arreglo[1]));
                    mapaPeliculaActor.put(Integer.valueOf(arreglo[0]), l);
                }                
            }
        }catch(Exception e){
            e.getStackTrace();
        }        
    }
    
    public void crearGrafo(){
        relacionarM();
        grafo = new Graph<>(false);
        
        for(Integer i: mapaActores.keySet()){
            grafo.addVertex(i);
        }
        
        for(Map.Entry<Integer, List<Integer>> e: mapaPeliculaActor.entrySet()){
            for(Integer origen: e.getValue()){
                for(Integer destino: e.getValue()){
                    if(!origen.equals(destino)){
                        grafo.addEdge(origen, destino, e.getKey());
                        
                    }
                }
            }
        }
    }

    public List<Actor> getListaActores() {
        return listaActores;
    }

    public List<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    public Map<Integer, String> getMapaActores() {
        return mapaActores;
    }

    public Map<Integer, String> getMapaPeliculas() {
        return mapaPeliculas;
    }

    public Map<Integer, List<Integer>> getMapaPeliculaActor() {
        return mapaPeliculaActor;
    }

    public List<String> getListaNombres() {
        return listaNombres;
    }

    public Graph<Integer> getGrafo() {
        return grafo;
    }
    
    public String getActor(int id) {
        return mapaActores.get(id);
    }
    
    public String getPelicula(int id) {
        return mapaPeliculas.get(id);
    }
    
    public int getActorId(String nombre) {
        for(Map.Entry<Integer, String> m: mapaActores.entrySet()) {
            if(m.getValue().equals(nombre))
                return m.getKey();
        }
        return -1;
    }
    
}