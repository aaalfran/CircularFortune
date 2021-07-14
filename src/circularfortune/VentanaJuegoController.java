package circularfortune;

import Elements.vistaJuego;
import static Elements.vistaJuego.cirExterno;
import static Elements.vistaJuego.cirInterno;
import static Elements.vistaJuego.circulosInterno;
import static Elements.vistaJuego.labelsInt;
import TDAList.DoubleCircularList;
import static circularfortune.CircularFortune.musicaInicio;
import static circularfortune.CircularFortune.musicaJuego;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class VentanaJuegoController implements Initializable {
    //Ventana Principal

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button rotIzq;

    @FXML
    private Button eliminar;

    @FXML
    private Button rotDer;

    @FXML
    private Label score;

    @FXML
    private Label apuesta;

    @FXML
    private Button comodinCambiar;

    //Acciones de la ventana principal del juego
    @FXML
    void clickDer(ActionEvent event) throws IOException {

        rotIzq.setDisable(true);
        rotDer.setDisable(true);
        eliminar.setDisable(false);

        playSound("derecha");

        DoubleCircularList.moveRigth(cirExterno);
        DoubleCircularList.moveRigth(cirInterno);
        vistaJuego.actualizarValoresCirculos();

        Integer tot = DoubleCircularList.suma(vistaJuego.cirExterno, vistaJuego.cirInterno);
        score.setText(tot.toString());

        JuegoTerminado();

    }

    @FXML
    void clickElim(ActionEvent event) throws IOException {
        rotIzq.setDisable(true);
        rotDer.setDisable(true);
        playSound("eliminar");
        String indice = JOptionPane.showInputDialog("Ingrese el índice del círculo a eliminar: ");
        if (indice.equals("0") || indice.equals("1") || indice.equals("2")
                || indice.equals("3") || indice.equals("4") || indice.equals("5")
                || indice.equals("6") || indice.equals("7") || indice.equals("8") || indice.equals("9")) {

            if (Integer.parseInt(indice) < circulosInterno.size()) {
                eliminar.setDisable(true);
                rotIzq.setDisable(false);
                rotDer.setDisable(false);
                //Eliminar Círculos  
                for (int i = 0; i < circulosInterno.size(); i++) {
                    Node eliminarCInterno = circulosInterno.get(i);
                    Node eliminarCExterno = vistaJuego.circulosExterno.get(i);
                    Label eliminarLInterno = labelsInt.get(i);
                    Label eliminarLExterno = vistaJuego.labelsExt.get(i);
                    anchor.getChildren().remove(eliminarCInterno);
                    anchor.getChildren().remove(eliminarLInterno);
                    anchor.getChildren().remove(eliminarCExterno);
                    anchor.getChildren().remove(eliminarLExterno);
                }
                //ACTUALIZACIÓN DE LISTAS
                cirInterno.remove(Integer.parseInt(indice));
                cirExterno.remove(Integer.parseInt(indice));
                circulosInterno.remove(Integer.parseInt(indice));
                labelsInt.remove(Integer.parseInt(indice));
                vistaJuego.circulosExterno.remove(Integer.parseInt(indice));
                vistaJuego.labelsExt.remove(Integer.parseInt(indice));

                //AÑADIR CÍRCULOS
                for (int j = 0; j < circulosInterno.size(); j++) {
                    Node nI = circulosInterno.get(j);
                    Label lI = labelsInt.get(j);
                    Node nE = vistaJuego.circulosExterno.get(j);
                    Label lE = vistaJuego.labelsExt.get(j);

                    anchor.getChildren().addAll(nI, lI);
                    anchor.getChildren().addAll(nE, lE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Error ingrese solmanente"
                        + "´ números dentro de este Rango(0- " + (circulosInterno.size() - 1) + ")");
            }

        } else {
            JOptionPane.showMessageDialog(null, "¡Error ingrese solmanente"
                    + "´ números dentro de este Rango(0- " + (circulosInterno.size() - 1) + ")");
        }
        Integer tot = DoubleCircularList.suma(vistaJuego.cirExterno, vistaJuego.cirInterno);
        score.setText(tot.toString());
        JuegoTerminado();
    }

    @FXML
    void clickIzq(ActionEvent event) throws IOException {
        rotIzq.setDisable(true);
        rotIzq.setBlendMode(BlendMode.BLUE);
        rotDer.setDisable(true);
        eliminar.setDisable(false);
        playSound("izquierda");
        DoubleCircularList.moveLeft(cirExterno);
        DoubleCircularList.moveLeft(cirInterno);
        vistaJuego.actualizarValoresCirculos();

        Integer tot = DoubleCircularList.suma(vistaJuego.cirExterno, vistaJuego.cirInterno);
        score.setText(tot.toString());

        JuegoTerminado();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        musicaInicio.stop();
        musicaJuego.play();
        //Se fija la apuesta inicial
        String apuestaIni = String.valueOf(SettingsController.apuestaIni);
        apuesta.setText(apuestaIni);

        //Se agregan los circulos de base
        anchor.getChildren().add(vistaJuego.getCirculoExt());
        anchor.getChildren().add(vistaJuego.getCirculoInt());

        //Se establece el valor del score inicial
        vistaJuego.fijarCirculos(anchor);
        Integer tot = DoubleCircularList.suma(vistaJuego.cirExterno, vistaJuego.cirInterno);
        score.setText(tot.toString());

        System.out.println(apuestaIni);

    }

    @FXML
    void clickExitbtn(ActionEvent event) throws IOException {
        musicaInicio.play();
        musicaJuego.stop();
        playSound("click");
        Stage st = (Stage) anchor.getScene().getWindow();
        Alert.AlertType tipoAlerta = Alert.AlertType.CONFIRMATION;
        Alert alerta = new Alert(tipoAlerta, "");
        alerta.initModality(Modality.APPLICATION_MODAL);
        alerta.initOwner(st);
        alerta.getDialogPane().setContentText("¿Esta seguro que desea salir del juego?");
        alerta.getDialogPane().setHeaderText("Si sale perdera todo su progeso en el juego ");
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.get() == ButtonType.OK) {
            playSound("click");
            Parent root = FXMLLoader.load(getClass().getResource("menuinicio.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            vistaJuego.limpiarBuffer();
        } else {
            playSound("click");

        }
    }

    @FXML
    void change(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change.fxml"));
        try {
            Parent root = loader.load();
            ChangeController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
      ;
        
    }

    public void JuegoTerminado() throws IOException {
        boolean terminado = false;
        System.out.println(circulosInterno.size());

        if (score.getText().equals(apuesta.getText()) || circulosInterno.isEmpty()) {

            terminado = true;
        }

        if (terminado) {
            System.out.println("Juego terminado");
            Stage st = (Stage) anchor.getScene().getWindow();
            Alert.AlertType mensajeFinal = Alert.AlertType.INFORMATION;
            Alert alert = new Alert(mensajeFinal, "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(st);
            alert.getDialogPane().setContentText("Se lo va a regresar al menu principal");
            alert.getDialogPane().setHeaderText("JUEGO TERMINADO");
            alert.showAndWait();

            playSound("click");
            Parent root = FXMLLoader.load(getClass().getResource("menuinicio.fxml"));
            Scene scene = new Scene(root);
            st.setScene(scene);
            st.show();

            vistaJuego.limpiarBuffer();
        }

    }

    public void playSound(String s) {
        switch (s) {
            case "click": {
                AudioClip note = new AudioClip(this.getClass().getResource("/resources/clickBoton.wav").toString());
                note.play();
                break;
            }
            case "derecha": {
                AudioClip note = new AudioClip(this.getClass().getResource("/resources/der.wav").toString());
                note.play();
                break;
            }
            case "izquierda": {
                AudioClip note = new AudioClip(this.getClass().getResource("/resources/izq.wav").toString());
                note.play();
                break;
            }
            case "eliminar": {
                AudioClip note = new AudioClip(this.getClass().getResource("/resources/elim.wav").toString());
                note.play();
                break;
            }
            default:
                break;
        }
    }

}
