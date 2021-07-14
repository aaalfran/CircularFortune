/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circularfortune;

import Elements.vistaJuego;
import static Elements.vistaJuego.cirExterno;
import static Elements.vistaJuego.cirInterno;
import TDAList.DoubleCircularList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moise
 */
public class ChangeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button confirmar;

    @FXML
    private Button salir;

    @FXML
    private TextField interno;

    @FXML
    private TextField externo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void confirmarCambio(ActionEvent event) {

        interno.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                interno.setText(newValue.replaceAll("[^\\d]", ""));
                externo.setText(newValue.replaceAll("[^\\d]", ""));
            }else if (!interno.getText().equals("") && !externo.getText().equals("")) {
                DoubleCircularList.changePosition(vistaJuego.cirInterno, vistaJuego.cirExterno, Integer.parseInt(interno.getText()), Integer.parseInt(externo.getText()));
                vistaJuego.actualizarValores(interno.getText(), interno.getText());
            }
        });
        
        Stage s = (Stage) confirmar.getScene().getWindow();
        s.close();

    }

    @FXML
    void cerrar(ActionEvent event) {

    }

}
