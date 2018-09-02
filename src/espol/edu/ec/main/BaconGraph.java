/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;

import espol.edu.ec.tda.FileWorker;
import espol.edu.ec.tda.Graph;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 *
 */
public class BaconGraph {
    
    FileWorker fw;
    Graph<Integer> grafo;
    private final int ID_BACON = 1;
    private final String NOMBRE_BACON;
    private final VBox root;
    
    int numeroBaconEncontrado;
    
    public BaconGraph() {
        this.NOMBRE_BACON = "Kevin Bacon";
        root = new VBox();
        root.setAlignment(Pos.CENTER); 

    }
    
    public void dijkstra(Integer id) {
        
        root.getChildren().clear();
        
        List<Integer> camino = grafo.caminoMinimo(id, ID_BACON);
        
        if(!camino.get(camino.size()-1).equals(ID_BACON)){
            //no tiene conexion con Bacon
            return;
        }

        numeroBaconEncontrado = camino.size()-1;
        
        for(int col=0; col<camino.size(); col++) {
            String actor = fw.getActor(camino.get(col));
            String pelicula = "";
            if(col < camino.size() - 1) {
                int idPelicula = grafo.getIdEdge(camino.get(col), camino.get(col+1));
                pelicula = fw.getPelicula(idPelicula);
                root.getChildren().addAll(drawRectangle(actor, Color.BLUE), drawArrow("Actuó en"), 
                        drawRectangle(pelicula, Color.RED), drawArrow("Con"));
            }else
                root.getChildren().add(drawRectangle(actor, Color.BLUE));
        }
    }
    
    public void bsf(int id) {
        root.getChildren().clear();
        List<Integer> camino = grafo.bfs(id, ID_BACON);
        
        numeroBaconEncontrado = camino.size()-1;
        int col = 0;
        String actor;
        while(col < camino.size()) {
            actor = fw.getActor(camino.get(col));
            String pelicula = "";
            if(col < camino.size() - 1) {
                int idPelicula = grafo.getIdEdge(camino.get(col), camino.get(col+1));
                pelicula = fw.getPelicula(idPelicula);
                root.getChildren().addAll(drawRectangle(actor, Color.BLUE), drawArrow("Actuó en"), 
                        drawRectangle(pelicula, Color.RED), drawArrow("Con"));
            }else
                root.getChildren().add(drawRectangle(actor, Color.BLUE));
            col++;
        }
    }
    
    private StackPane drawRectangle(String nombre, Color color) {
        StackPane sp = new StackPane();
        Text txt = new Text(nombre);
        txt.setFont(Font.font(20)); 
        Rectangle r = new Rectangle(txt.getBoundsInLocal().getWidth() + 15, txt.getBoundsInLocal().getHeight() + 15);
        r.setFill(color);
        r.setStroke(Color.WHITE);
        r.setStrokeWidth(5); 
        r.setStrokeLineJoin(StrokeLineJoin.ROUND);
        DropShadow shadow = new DropShadow();
        r.setEffect(shadow); 
        sp.setAlignment(Pos.CENTER);
        sp.getChildren().addAll(r, txt); 
        return sp;
    } 
    
    private StackPane drawArrow(String tipo) {
        StackPane sp = new StackPane();
        ImageView iv = new ImageView(new Image(
                BaconGraph.class.getResourceAsStream("/espol/edu/ec/assets/flecha.png"), 75, 75, true, true));
        iv.setRotate(90); 
        Text txt = new Text(tipo);
        txt.setFont(Font.font(24));
        txt.setFill(Color.DARKCYAN); 
        sp.setAlignment(Pos.CENTER);
        sp.getChildren().addAll(iv, txt); 
        return sp;
    }

    public void setGrafo(Graph<Integer> grafo) {
        this.grafo = grafo;
    }

    public void setFw(FileWorker fw) {
        this.fw = fw;
    }

    public VBox getRoot() {
        return root;
    }

    public int getNumeroBaconEncontrado() {
        return numeroBaconEncontrado;
    }
    
    
}
