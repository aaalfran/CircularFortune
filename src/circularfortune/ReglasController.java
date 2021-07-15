package circularfortune;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class ReglasController {

    @FXML
    private Button exit;

    @FXML
    void clickExit(ActionEvent event) throws IOException {
        AudioClip note = new AudioClip(this.getClass().getResource("/resources/clickBoton.wav").toString());
        note.play();
        Parent root = FXMLLoader.load(getClass().getResource("menuinicio.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

