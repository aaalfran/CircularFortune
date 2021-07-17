package circularfortune;

import Elements.vistaJuego;
import static Elements.vistaJuego.cirExterno;
import static Elements.vistaJuego.cirInterno;
import static Elements.vistaJuego.circulosInterno;
import static Elements.vistaJuego.labelsInt;
import TDAList.DoubleCircularList;
import static circularfortune.CircularFortune.musicaInicio;
import static circularfortune.CircularFortune.musicaJuego;
import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Button musicaPause;

    @FXML
    private Button musicaPlay;
    
     @FXML
    private Circle blurrDer;
     
     @FXML
    private Circle blurrElim;

    @FXML
    private Circle blurrIzq;
    
    @FXML
    private Circle blurrSwap;
    
     @FXML
    private Circle blurrEdit;
     
     @FXML
    private Circle blurrUnlock;

    //Comodines
    @FXML
    private Button swap;

    @FXML
    private Button edit;

    @FXML
    private Button unlock;

    // reproduce o pausa la musica
    @FXML
    private RadioButton botonMusica;

    @FXML
    private ImageView cross;

    boolean musicaActiva = true;

    //Acciones de la ventana principal del juego
    @FXML
    void clickDer(ActionEvent event) throws IOException {
        
        
        TextInputDialog dialogoTextual = new TextInputDialog();
        dialogoTextual.setTitle("Rotar");
        dialogoTextual.setHeaderText("¿Cual circulo desea rotar?");
        dialogoTextual.setContentText("Escriba 'interno' o 'externo' : ");
        dialogoTextual.initStyle(StageStyle.UTILITY);
        Optional<String> ingreso = dialogoTextual.showAndWait();
        switch (ingreso.get().toLowerCase()) {
            case "interno":
                {
                    rotIzq.setDisable(true);
                    rotDer.setDisable(true);
                    eliminar.setDisable(false);
                    blurrDer.setOpacity(0.5);
                    blurrIzq.setOpacity(0.5);
                    blurrElim.setOpacity(0);
                    DoubleCircularList.moveRigth(cirInterno);
                    vistaJuego.actualizarValoresCirculos();
                    Integer tot = DoubleCircularList.suma(vistaJuego.cirExterno, vistaJuego.cirInterno);
                    score.setText(tot.toString());
                    playSound("derecha");
                    JuegoTerminado();
                    break;
                }
            case "externo":
                {
                    rotIzq.setDisable(true);
                    rotDer.setDisable(true);
                    eliminar.setDisable(false);
                    blurrDer.setOpacity(0.5);
                    blurrIzq.setOpacity(0.5);
                    blurrElim.setOpacity(0);
                    DoubleCircularList.moveRigth(cirExterno);
                    vistaJuego.actualizarValoresCirculos();
                    Integer tot = DoubleCircularList.suma(vistaJuego.cirExterno, vistaJuego.cirInterno);
                    score.setText(tot.toString());
                    playSound("derecha");
                    JuegoTerminado();
                    break;
                }
            default:
                Alert.AlertType mensajeInfo = Alert.AlertType.WARNING;
                Alert alerta = new Alert(mensajeInfo, "");
                alerta.initModality(Modality.APPLICATION_MODAL);
                alerta.getDialogPane().setHeaderText("El valor ingresado no es valido! Ingrese 'interno' o 'externo'");
                alerta.showAndWait();
                break;
        }
    }

    @FXML
    void clickElim(ActionEvent event) throws IOException {
        rotIzq.setDisable(true);
        rotDer.setDisable(true);
        blurrDer.setOpacity(0.5);
        blurrIzq.setOpacity(0.5);
        blurrElim.setOpacity(0);
        playSound("eliminar");

        TextInputDialog dialogoTextual = new TextInputDialog();
        dialogoTextual.setTitle("Eliminación de Círculo");
        dialogoTextual.setHeaderText("Ingrese un número");
        dialogoTextual.initStyle(StageStyle.UTILITY);
        Optional<String> indice = dialogoTextual.showAndWait();
        try {
            if (indice.get().equals("0") || indice.get().equals("1") || indice.get().equals("2") || indice.get().equals("3")
                    || indice.get().equals("4") || indice.get().equals("5") || indice.get().equals("6")
                    || indice.get().equals("7") || indice.get().equals("8") || indice.get().equals("9")) {
                if (Integer.parseInt((String) indice.get()) < circulosInterno.size()) {
                    eliminar.setDisable(true);
                    rotIzq.setDisable(false);
                    rotDer.setDisable(false);
                    blurrDer.setOpacity(0);
                    blurrIzq.setOpacity(0);
                    blurrElim.setOpacity(0.5);
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
                    cirInterno.remove(Integer.parseInt(indice.get()));
                    cirExterno.remove(Integer.parseInt(indice.get()));
                    circulosInterno.remove(Integer.parseInt(indice.get()));
                    labelsInt.remove(Integer.parseInt(indice.get()));
                    vistaJuego.circulosExterno.remove(Integer.parseInt(indice.get()));
                    vistaJuego.labelsExt.remove(Integer.parseInt(indice.get()));
                    //AÑADIR CÍRCULO
                    for (int j = 0; j < circulosInterno.size(); j++) {
                        Node nI = circulosInterno.get(j);
                        Label lI = labelsInt.get(j);
                        Node nE = vistaJuego.circulosExterno.get(j);
                        Label lE = vistaJuego.labelsExt.get(j);

                        anchor.getChildren().addAll(nI, lI);
                        anchor.getChildren().addAll(nE, lE);
                    }
                } else {
                    Alert.AlertType mensajeInfo = Alert.AlertType.WARNING;
                    Alert alerta = new Alert(mensajeInfo, "");
                    alerta.initModality(Modality.APPLICATION_MODAL);
                    alerta.getDialogPane().setHeaderText("¡Ingrese solmanente números dentro de este Rango(0- " + (circulosInterno.size() - 1) + ")");
                    alerta.showAndWait();
                }

            } else {
                Alert.AlertType mensajeInfo = Alert.AlertType.WARNING;
                Alert alerta = new Alert(mensajeInfo, "");
                alerta.initModality(Modality.APPLICATION_MODAL);
                alerta.getDialogPane().setHeaderText("¡Ingrese solmanente números dentro de este Rango(0- " + (circulosInterno.size() - 1) + ")");
                alerta.showAndWait();
            }
        } catch (Exception ex) {
            System.out.println("Ventana de ingreso cerrada");
        }

        Integer tot = DoubleCircularList.suma(vistaJuego.cirExterno, vistaJuego.cirInterno);
        score.setText(tot.toString());
        JuegoTerminado();
    }

    @FXML
    void clickIzq(ActionEvent event) throws IOException {
        TextInputDialog dialogoTextual = new TextInputDialog();
        dialogoTextual.setTitle("Rotar");
        dialogoTextual.setHeaderText("¿Cual circulo desea rotar?");
        dialogoTextual.setContentText("Escriba 'interno' o 'externo' : ");
        dialogoTextual.initStyle(StageStyle.UTILITY);
        Optional<String> ingreso = dialogoTextual.showAndWait();
        switch (ingreso.get().toLowerCase()) {
            case "interno":
                {
                    rotIzq.setDisable(true);
                    rotDer.setDisable(true);
                    eliminar.setDisable(false);
                    blurrDer.setOpacity(0.5);
                    blurrIzq.setOpacity(0.5);
                    blurrElim.setOpacity(0);
                    DoubleCircularList.moveLeft(cirInterno);
                    vistaJuego.actualizarValoresCirculos();
                    Integer tot = DoubleCircularList.suma(vistaJuego.cirExterno, vistaJuego.cirInterno);
                    score.setText(tot.toString());
                    playSound("izquierda");
                    JuegoTerminado();
                    break;
                }
            case "externo":
                {
                    rotIzq.setDisable(true);
                    rotDer.setDisable(true);
                    eliminar.setDisable(false);
                    blurrDer.setOpacity(0.5);
                    blurrIzq.setOpacity(0.5);
                    blurrElim.setOpacity(0);
                    DoubleCircularList.moveLeft(cirExterno);
                    vistaJuego.actualizarValoresCirculos();
                    Integer tot = DoubleCircularList.suma(vistaJuego.cirExterno, vistaJuego.cirInterno);
                    score.setText(tot.toString());
                    playSound("izquierda");
                    JuegoTerminado();
                    break;
                }
            default:
                Alert.AlertType mensajeInfo = Alert.AlertType.WARNING;
                Alert alerta = new Alert(mensajeInfo, "");
                alerta.initModality(Modality.APPLICATION_MODAL);
                alerta.getDialogPane().setHeaderText("El valor ingresado no es valido! Ingrese 'interno' o 'externo'");
                alerta.showAndWait();
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        musicaInicio.stop();
        musicaJuego.play();
        //Se fija la apuesta inicial
        String apuestaIni = String.valueOf(SettingsController.apuestaIni);
        apuesta.setText(apuestaIni);

        //Se establecen las reglas del juego
        if (SettingsController.comodinesActivados) {

            swap.setDisable(false);
            edit.setDisable(false);
            unlock.setDisable(false);

        } else {

            swap.setDisable(true);
            blurrSwap.setOpacity(0.5);
            edit.setDisable(true);
            blurrEdit.setOpacity(0.5);
            unlock.setDisable(true);
            blurrUnlock.setOpacity(0.5);

        }

        //Se agregan los circulos de base
        anchor.getChildren().add(vistaJuego.getCirculoExt());
        anchor.getChildren().add(vistaJuego.getCirculoInt());

        //Se establece el valor del score inicial
        vistaJuego.fijarCirculos(anchor);
        Integer tot = DoubleCircularList.suma(vistaJuego.cirExterno, vistaJuego.cirInterno);
        score.setText(tot.toString());


    }

    @FXML
    void clickExitbtn(ActionEvent event) throws IOException {
        musicaJuego.pause();
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
            musicaJuego.stop();
            musicaActiva = false;
            musicaInicio.play();
            SettingsController.cantidadCirculos = 1;
            SettingsController.comodinesActivados = false;
            SettingsController.sinNegativos = false;
        } else {
            playSound("click");
            if (musicaActiva) {
                musicaJuego.play();
            } else {
                musicaJuego.pause();
            }
        }

        if (musicaActiva) {
            musicaJuego.play();
        } else {
            musicaJuego.pause();
        }
    }

    @FXML
    void clickUnlock(ActionEvent event) {
        eliminar.setDisable(false);
        rotIzq.setDisable(false);
        rotDer.setDisable(false);
        blurrDer.setOpacity(0);
        blurrIzq.setOpacity(0);
        blurrElim.setOpacity(0);
        try {
            JuegoTerminado();
            unlock.setDisable(true);
            blurrUnlock.setOpacity(0.5);
            playSound("unlock");
            

        } catch (IOException ex) {
            Logger.getLogger(VentanaJuegoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void clickSwap(ActionEvent event) {
        try {
            TextInputDialog dialogoTextual = new TextInputDialog();
            dialogoTextual.setTitle("Swap");
            dialogoTextual.setHeaderText("Ingrese los índices de los círculos a intercambiar");
            dialogoTextual.setContentText("Ingrese los Indices separados por coma: Círculo Interior,Círculo Exrterior");
            dialogoTextual.initStyle(StageStyle.UTILITY);
            Optional<String> respuesta = dialogoTextual.showAndWait();
            try {
                String s = respuesta.get();
                int validacion = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (String.valueOf(s.charAt(i)).equals(",")
                            && s.contains(",") && !s.contains(".") && !s.startsWith(",") && !s.endsWith(",") && !s.contains(".")) {
                        String[] cadena = s.split(",");
                        if ((cadena.length) == 2) {
                            validacion++;
                        }
                    }

                }
                if (validacion == 0) {
                    Alert.AlertType mensajeInfo = Alert.AlertType.WARNING;
                    Alert alerta = new Alert(mensajeInfo, "");
                    alerta.initModality(Modality.APPLICATION_MODAL);
                    alerta.getDialogPane().setHeaderText("¡Ingrese con este formato n,n!");
                    alerta.showAndWait();

                } else if (validacion == 1) {
                    String[] indices = s.split(",");
                    String num1 = indices[0];
                    String num2 = indices[1];
                    if ((num1.equals("0") || num1.equals("1") || num1.equals("2") || num1.equals("3") || num1.equals("4")
                            || num1.equals("5") || num1.equals("6") || num1.equals("7")
                            || num1.equals("8") || num1.equals("9"))
                            && (num2.equals("0") || num2.equals("1") || num2.equals("2") || num2.equals("3")
                            || num2.equals("4") || num2.equals("5") || num2.equals("6")
                            || num2.equals("7") || num2.equals("8") || num2.equals("9"))) {

                        int numI = Integer.parseInt(num1);
                        int numE = Integer.parseInt(num2);
                        if (numI < circulosInterno.size()) {

                            DoubleCircularList.changePosition(cirInterno, cirExterno, numI, numE);
                            vistaJuego.actualizarValoresCirculos();
                            swap.setDisable(true);
                            blurrSwap.setOpacity(0.5);
                            playSound("swap");
                            

                        } else {
                            ventanaWarning();

                        }

                    } else {
                        ventanaWarning();
                    }

                }
            } catch (NoSuchElementException e) {
            }
        } catch (NumberFormatException ex) {
            System.out.println("Cerrando Ventana ....");
        }
    }

    @FXML
    void editCircle(ActionEvent event) {
        try {
            TextInputDialog dialogoTextual = new TextInputDialog();
            dialogoTextual.setTitle("Comodín 2");
            dialogoTextual.setHeaderText("Ingresa el circulo al cual le deseas cambiar un numero 0 para el circulo interior y 1 para el exterior");
            dialogoTextual.setContentText("Ingresa el numero de la lista , la posicion del elemento que deseas cambiar y el muevo elemento separados por comae ejem(0,5,3)");
            dialogoTextual.initStyle(StageStyle.UTILITY);
            //AQUÍ OBTIENES LA RESPUESTA DEL USARIO
            Optional<String> respuesta = dialogoTextual.showAndWait();

            String s = respuesta.get();
            int validacion = 0;
            for (int i = 0; i < s.length(); i++) {
                if (String.valueOf(s.charAt(i)).equals(",")
                        && s.contains(",") && !s.contains(".") && !s.startsWith(",") && !s.endsWith(",") && !s.contains(".")) {
                    String[] cadena = s.split(",");
                    if ((cadena.length) >= 3) {
                        validacion++;
                    }
                }

            }
            if (validacion > 2 || validacion < 2) {
                Alert.AlertType mensajeInfo = Alert.AlertType.WARNING;
                Alert alerta = new Alert(mensajeInfo, "");
                alerta.initModality(Modality.APPLICATION_MODAL);
                alerta.getDialogPane().setHeaderText("¡Ingrese con este formato n,n,n!");
                alerta.showAndWait();
            } else if (validacion == 2) {
                String[] indices = s.split(",");
                String num1 = indices[0];
                String num2 = indices[1];
                String num3 = indices[2];

                if ((num1.equals("0") || num1.equals("1"))
                        && (num2.equals("0") || num2.equals("1") || num2.equals("2") || num2.equals("3")
                        || num2.equals("4") || num2.equals("5") || num2.equals("6")
                        || num2.equals("7") || num2.equals("8") || num2.equals("9"))
                        && (num3.equals("0") || num3.equals("1") || num3.equals("2") || num3.equals("3")
                        || num3.equals("4") || num3.equals("5") || num3.equals("6")
                        || num3.equals("7") || num3.equals("8") || num3.equals("9"))) {

                    int numEleccion = Integer.parseInt(num1);
                    int numPos = Integer.parseInt(num2);
                    int numEle = Integer.parseInt(num3);
                    if (numPos < circulosInterno.size()) {
                        if (numEleccion == 0) {

                            cirInterno.set(numPos, numEle);
                            vistaJuego.actualizarValoresCirculos();
                            Integer tot = DoubleCircularList.suma(vistaJuego.cirExterno, vistaJuego.cirInterno);
                            score.setText(tot.toString());
                            JuegoTerminado();

                        }
                        if (numEleccion == 1) {
                            cirExterno.set(numPos, numEle);
                            vistaJuego.actualizarValoresCirculos();
                            Integer tot = DoubleCircularList.suma(vistaJuego.cirExterno, vistaJuego.cirInterno);
                            score.setText(tot.toString());
                            JuegoTerminado();
                        }
                        edit.setDisable(true);
                        blurrEdit.setOpacity(0.5);
                        playSound("edit");

                    } else {
                        ventanaWarning();

                    }
                } else {
                    if (!num1.equals("0") && !num1.equals("1")) {
                        Alert.AlertType mensajeInfo = Alert.AlertType.WARNING;
                        Alert alerta = new Alert(mensajeInfo, "");
                        alerta.initModality(Modality.APPLICATION_MODAL);
                        alerta.getDialogPane().setHeaderText("¡El primer número debe ser 0 o 1!");
                        alerta.showAndWait();
                    } else {
                        ventanaWarning();
                    }
                }

            }
        } catch (Exception ex) {
            System.out.println("Cerrando Ventana ....");
        }

    }

    public void JuegoTerminado() throws IOException {
        boolean terminado = false;
        boolean ganador = false;
        boolean obtuvoNegativo = false;
        boolean sinCirculos = false;

        if (score.getText().equals(apuesta.getText())) {
            terminado = true;
            ganador = true;
        } else if (circulosInterno.isEmpty()) {
            terminado = true;
            ganador = false;
            sinCirculos = true;
        } else if (buscarNegativos()) {
            obtuvoNegativo = true;
            ganador = false;
            terminado = true;
        }

        if (terminado) {
            Stage st = (Stage) anchor.getScene().getWindow();
            Alert.AlertType mensajeFinal = Alert.AlertType.INFORMATION;
            Alert alert = new Alert(mensajeFinal, "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(st);

            //CASOS QUE SE PUEDEN DAR AL TERMINAR EL JUEGO
            if (ganador) {
                alert.getDialogPane().setContentText("Su score en el juego ha sido igualado a su apuesta inicial, se lo regresara al menu principal.");
                alert.getDialogPane().setHeaderText("FELICIDADES!!!!, USTED HA GANADO");
                ImageView win = new ImageView("/resources/winner.png");
                win.setFitHeight(60);
                win.setFitWidth(60);
                alert.getDialogPane().setGraphic(win);
                alert.showAndWait();
            } else if (sinCirculos) {
                alert.getDialogPane().setContentText("Fin del juego, se lo va a regresar al menu principal");
                alert.getDialogPane().setHeaderText("SE HA QUEDADO SIN CIRCULOS");
                ImageView loser = new ImageView("/resources/sad.png");
                loser.setFitHeight(60);
                loser.setFitWidth(60);
                alert.getDialogPane().setGraphic(loser);
                alert.showAndWait();
            } else if (obtuvoNegativo) {
                alert.getDialogPane().setContentText("Cuidado con los ceros para la proxima, se lo regresara al menu principal");
                alert.getDialogPane().setHeaderText("HA OBTENIDO UN VALOR NEGTIVO");
                ImageView negative = new ImageView("/resources/negative.png");
                negative.setFitHeight(60);
                negative.setFitWidth(60);
                alert.getDialogPane().setGraphic(negative);
                alert.showAndWait();

            }
            playSound("click");
            Parent root = FXMLLoader.load(getClass().getResource("menuinicio.fxml"));
            Scene scene = new Scene(root);
            st.setScene(scene);
            st.show();
            musicaJuego.stop();
            musicaActiva = false;
            musicaInicio.play();
            vistaJuego.limpiarBuffer();
            SettingsController.comodinesActivados = false;
            SettingsController.sinNegativos = false;
            SettingsController.cantidadCirculos = 1;
        }

    }

    public boolean buscarNegativos() throws IOException {
        if (SettingsController.sinNegativos) {
            for (Integer numInterno : cirInterno) {
                if (numInterno < 0) {
                    return true;
                }
            }

            for (Integer numExterno : cirExterno) {
                if (numExterno < 0) {
                    return true;
                }
            }
        }

        return false;
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
            case "edit":{
                AudioClip note = new AudioClip(this.getClass().getResource("/resources/edit.mp3").toString());
                note.play();
                break;
            }
            case "unlock": {
                AudioClip note = new AudioClip(this.getClass().getResource("/resources/unlock.wav").toString());
                note.play();
                break;
            }case "swap": {
                AudioClip note = new AudioClip(this.getClass().getResource("/resources/swap.wav").toString());
                note.play();
                break;
            }
            default:
                break;
        }
    }

    public void ventanaWarning() {
        Alert.AlertType mensajeInfo = Alert.AlertType.WARNING;
        Alert alerta = new Alert(mensajeInfo, "");
        alerta.initModality(Modality.APPLICATION_MODAL);
        alerta.getDialogPane().setHeaderText("¡Ingrese solmanente números dentro de este Rango(0- " + (circulosInterno.size() - 1) + ")");
        alerta.showAndWait();

    }

    @FXML
    void clickBotonMusica(ActionEvent event) {
        if (botonMusica.isSelected()) {
            musicaJuego.pause();
            cross.setOpacity(1);
            musicaActiva = false;
        } else {
            musicaJuego.play();
            cross.setOpacity(0);
            musicaActiva = true;
        }
    }

}
