package circularfortune;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class menuinicioController {

     @FXML
    private ImageView fondo;

    @FXML
    private Label titulo;

    @FXML
    private Button play;

    @FXML
    private Button settings;

    @FXML
    private Button exit;

    @FXML
    void click(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ventanaJuego.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void clickExit(ActionEvent event) {
            Stage st = (Stage) exit.getScene().getWindow();
            st.close();
    }

    @FXML
    void clickSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}