package circularfortune;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class menuinicioController {

    @FXML
    private ImageView fondo;

    @FXML
    private Label titulo;

    @FXML
    private Button play;

    @FXML
    private Button reglas;

    @FXML
    private Button settings;

    @FXML
    private Button exit;

    @FXML
    private Button musicaPause;

    @FXML
    private Button musicaPlay;

    @FXML
    void pause(ActionEvent event) {
        
        CircularFortune.musicaInicio.stop();
        CircularFortune.musicaJuego.stop();
        
        musicaPlay.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            
            public void handle(ActionEvent event) {
              CircularFortune.musicaInicio.play();
            }
        }));

    }

    @FXML
    void click(ActionEvent event) throws IOException {
        playSound();
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickReglas(ActionEvent event) throws IOException {
        playSound();
        Parent root = FXMLLoader.load(getClass().getResource("reglas.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickExit(ActionEvent event) {
        Stage st = (Stage) exit.getScene().getWindow();
        st.close();
    }

    private void playSound() {
        AudioClip note = new AudioClip(this.getClass().getResource("/resources/clickBoton.wav").toString());
        note.play();
    }
}
