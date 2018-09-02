/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;

import espol.edu.ec.tda.FileWorker;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author SSAM
 */
public class Main extends Application {

    FileWorker fw;

    @Override
    public void start(Stage stage) throws IOException {
        
        fw = new FileWorker();
        fw.crearGrafo();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        AnchorPaneM root = new AnchorPaneM(stage, loader.load());

        MainController controller = loader.getController();
        
        controller.setStageMain(stage);
        controller.setFw(fw);
        
        controller.init();

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));

        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
