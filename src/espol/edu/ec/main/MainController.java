/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;

import espol.edu.ec.tda.FileWorker;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MiguelPS
 */
public class MainController {

    FileWorker fw;
    
    Stage stageMain;
    @FXML
    TextField actor;
    @FXML
    ContextMenu menuSugerencias;


    ToggleGroup toggleSelectors;
    @FXML
    RadioButton radioButton_dijkstra;
    @FXML
    RadioButton radioButton_bfs;

    @FXML
    ScrollPane root;
    @FXML
    Label numeroBaconEncontradoLabel;

    List<String> nombresActores;
    List<Shape> listaShapes;

    public void init() {
        nombresActores = fw.getListaNombres();
        
        actor.textProperty().addListener((ObservableValue<? extends String> observableValue, String s, String s2) -> {
            if (actor.getText().length() == 0) {
                menuSugerencias.hide();
            } else {
                try {
                    populatePopup();
                } catch (Exception ex) {
                    System.out.println("Problema al crear sugerencias");
                }
                menuSugerencias.show(actor, Side.BOTTOM, 0, 0);
            }
        });

        toggleSelectors = new ToggleGroup();
        radioButton_dijkstra.setToggleGroup(toggleSelectors);
        radioButton_bfs.setToggleGroup(toggleSelectors);
        
        radioButton_dijkstra.setSelected(true);
    }

    @FXML
    void start() {

        BaconGraph bacon = new BaconGraph();
        bacon.setFw(fw);
        bacon.setGrafo(fw.getGrafo());
        root.setContent(bacon.getRoot());
        String act = actor.getText();
        
        if (!act.isEmpty()) {
            if (radioButton_dijkstra.isSelected()) {
                bacon.dijkstra(fw.getActorId(act));
                numeroBaconEncontradoLabel.setText(String.valueOf(bacon.getNumeroBaconEncontrado()));
            } else {
                bacon.bsf(fw.getActorId(act));
                numeroBaconEncontradoLabel.setText(String.valueOf(bacon.getNumeroBaconEncontrado()));
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setContentText("Selecione un actor..!!");
            alert.show();
        }
    }

    public void populatePopup() {

        List<String> suggestions = getSuggestions(actor.getText(), 10);
        List<String> itemsString = new LinkedList<>();

        suggestions.forEach((s) -> {
            itemsString.add(s);
        });

        List<CustomMenuItem> menuItems = new LinkedList<>();

        for (String text : itemsString) {

            CustomMenuItem item = new CustomMenuItem(new Label(text), true);
            item.setOnAction((ActionEvent actionEvent) -> {
                String nombre = ((Label) item.getContent()).getText();

                actor.setText(nombre);
                menuSugerencias.hide();
            });
            menuItems.add(item);
        }
        menuSugerencias.getItems().clear();
        menuSugerencias.getItems().addAll(menuItems);
    }

    private List<String> getSuggestions(String subString, int limit) {
        List<String> result = new LinkedList<>();

        String s;
        Iterator<String> itr = nombresActores.iterator();
        while (itr.hasNext() && result.size() < limit) {
            s = itr.next();
            if (s.toLowerCase().startsWith(subString.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    @FXML
    void close() {
        stageMain.close();
    }

    public void setStageMain(Stage stageMain) {
        this.stageMain = stageMain;
    }

    public void setFw(FileWorker fw) {
        this.fw = fw;
    }
    
}
