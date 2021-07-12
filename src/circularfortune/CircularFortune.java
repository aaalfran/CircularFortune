/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circularfortune;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author aaron
 */
public class CircularFortune extends Application {

    static String musicPathI = "Audio.mp3";
    static String musicPath = "AudioJuego.mp3";
    static MediaPlayer musicaInicio = Music(musicPathI);
    static MediaPlayer musicaJuego = Music(musicPath);

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("menuinicio.fxml"));
        stage.getIcons().add(new Image("/resources/chip.png"));
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setTitle("Circular Fortune");
        stage.setScene(scene);
        stage.show();
        musicaInicio.play();
        musicaJuego.stop();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
    //Codigo para la musica

    public static MediaPlayer Music(String nombre) {
        File archivo = new File(nombre);
        Media audio = new Media(archivo.toURI().toString());
        MediaPlayer reproductor = new MediaPlayer(audio);
        return reproductor;

    }

}
