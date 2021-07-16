package circularfortune;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.ContinueNode;

public class SettingsController implements Initializable {

    public static int cantidadCirculos = 1;
    public static int apuestaIni = 0;

    public static boolean comodinesActivados = false;
    public static boolean sinNegativos = false;
    
    @FXML
    private AnchorPane anchor;
    
    @FXML
    private ImageView fondo;

    @FXML
    private Label titulo;

    @FXML
    private Button exit;

    @FXML
    private Button play;
    
    @FXML
    private Circle blurr;

    @FXML
    private Slider sliderCirculos;

    @FXML
    private TextField apuesta;

    @FXML
    private CheckBox comodines;

    @FXML
    private CheckBox noNegativos;

    @FXML
    void clickExit(ActionEvent event) throws IOException {
        playSound("click");
        Parent root = FXMLLoader.load(getClass().getResource("menuinicio.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sliderCirculos.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            Double d = sliderCirculos.getValue();
            cantidadCirculos = d.intValue();
            System.out.println(cantidadCirculos);
        });
        apuesta.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                play.setDisable(false);
                blurr.setOpacity(0);
                apuesta.setText(newValue.replaceAll("[^\\d]", ""));
            } else if (!apuesta.getText().equals("")) {
                try{
                    apuestaIni = Integer.parseInt(apuesta.getText());
                }catch(NumberFormatException e){
                    System.out.println("Rango no permitido");
                    Stage st = (Stage) anchor.getScene().getWindow();
                    Alert.AlertType tipoAlerta = Alert.AlertType.WARNING;
                    Alert alerta = new Alert(tipoAlerta, "");
                    alerta.initModality(Modality.APPLICATION_MODAL);
                    alerta.initOwner(st);
                    alerta.getDialogPane().setHeaderText("POR FAVOR INGRESE VALORES RAZONABLES");
                    alerta.getDialogPane().setContentText("El boton play se desbloqueara una vez se cumpla este requerimiento");
                    play.setDisable(true);
                    blurr.setOpacity(0.50);
                    alerta.showAndWait();
                }
            } else if (apuesta.getText().equals("")) {
                play.setDisable(false);
                blurr.setOpacity(0);
                apuestaIni = getRandom();
            }

        });
        //Si no se ingresa nada en el campo de texto,se genera un valor aleatorio
        if (apuesta.getText().equals("")) {
            apuestaIni = getRandom();
        }
    }

    @FXML
    void clickPlay(ActionEvent event) throws IOException {

        playSound("start");
        Parent root = FXMLLoader.load(getClass().getResource("ventanaJuego.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void activarComodines(ActionEvent event) {

        comodinesActivados = true;

    }

    @FXML
    void noNegativos(ActionEvent event) {
        sinNegativos = true;
    }

    private void playSound(String sonido) {
        if (sonido.equals("click")) {
            AudioClip note = new AudioClip(this.getClass().getResource("/resources/clickBoton.wav").toString());
            note.play();
        } else if (sonido.equals("start")) {
            AudioClip note = new AudioClip(this.getClass().getResource("/resources/start.wav").toString());
            note.play();
        }
    }

    private int getRandom() {
        Random rd = new Random();
        int num = rd.nextInt((50 - 10)) + 10;

        return num;
    }

}
