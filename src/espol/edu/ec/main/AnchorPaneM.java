/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 *
 * @author MiguelPS
 */
public class AnchorPaneM extends AnchorPane{

    double x;
    double y;
    
    public AnchorPaneM(Stage stage, AnchorPane root) {
        super(root);
        
        root.setEffect(new DropShadow(10, Color.PURPLE));
        this.setOnMousePressed( event -> {
            x = stage.getX() - event.getScreenX();
            y = stage.getY() - event.getScreenY();
        });
        
        this.setOnMouseDragged( event -> {
            stage.setX(event.getScreenX() + x);
            stage.setY(event.getScreenY() + y);
        });
        
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
        
    
    
}
