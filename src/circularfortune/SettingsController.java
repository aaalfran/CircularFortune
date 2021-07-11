package circularfortune;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class SettingsController implements Initializable {
    
    int cantidadCirculos;
    public static int apuestaIni =0;

    @FXML
    private ImageView fondo;

    @FXML
    private Label titulo;

    @FXML
    private Button exit;
    
    @FXML
    private Button play;
    
    @FXML
    private Slider sliderCirculos;
    
    @FXML
    private TextField apuesta;

    @FXML
    void clickExit(ActionEvent event) throws IOException {
        playSound("click");
        Parent root = FXMLLoader.load(getClass().getResource("menuinicio.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
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
                    apuesta.setText(newValue.replaceAll("[^\\d]", ""));
                }else if(!apuesta.getText().equals("")){
                    apuestaIni= Integer.parseInt(apuesta.getText());
                }else if(apuesta.getText().equals("")){
                    apuestaIni = getRandom();
                }
                
            });
            //Si no se ingresa nada en el campo de texto,se genera un valor aleatorio
            if(apuesta.getText().equals("")){
                apuestaIni = getRandom();
            }
    }
    
    
    @FXML
    void clickPlay(ActionEvent event) throws IOException {

        playSound("start");
        Parent root = FXMLLoader.load(getClass().getResource("ventanaJuego.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        

    }
    
    private void playSound(String sonido){
        if(sonido.equals("click")){
            AudioClip note = new AudioClip(this.getClass().getResource("/resources/clickBoton.wav").toString());
            note.play();
        }else if(sonido.equals("start")){
            AudioClip note = new AudioClip(this.getClass().getResource("/resources/start.wav").toString());
            note.play();
        }
    }
    private int getRandom(){
        Random rd = new Random();
        int num = rd.nextInt((50-10))+10;
        
        return num;
    }
    
    
    
    


}

