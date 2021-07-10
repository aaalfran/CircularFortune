/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circularfortune;

import java.applet.AudioClip;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author aaron
 */
public class CircularFortune extends Application {

 
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("menuinicio.fxml"));
        stage.getIcons().add(new Image("/resources/chip.png"));
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setTitle("Circular Fortune");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
