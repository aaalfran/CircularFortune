/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import TDAList.ArrayList;
import TDAList.DoubleCircularList;
import TDAList.Node;
import circularfortune.SettingsController;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author aaron
 */
public class vistaJuego {

    public static DoubleCircularList<Integer> cirInterno;
    public static DoubleCircularList<Integer> cirExterno;

    //Arreglos con las labels para actualizar
    public static ArrayList<Label> labelsExt = new ArrayList<>();
    public static ArrayList<Label> labelsInt = new ArrayList<>();
    public static ArrayList<Node> circulosInterno = new ArrayList<>();
    public static ArrayList<Node> circulosExterno = new ArrayList<>();

    public static Circle getCirculoExt() {

        Circle cExterno = new Circle(519, 310, 275, Paint.valueOf("#00FFFF00"));
        cExterno.setStroke(Paint.valueOf("#BAB3B1"));
        cExterno.setStrokeWidth(5);
        return cExterno;
    }

    public static Circle getCirculoInt() {

        Circle cExterno = new Circle(519, 310, 125, Paint.valueOf("#00FFFF00"));
        cExterno.setStroke(Paint.valueOf("#BAB3B1"));
        cExterno.setStrokeWidth(5);
        return cExterno;
    }

    public static Circle puntoBase() {
        Circle pbase = new Circle(519, 185, 25, Paint.valueOf("#FFFFFF"));

        return pbase;
    }

    public static void fijarCirculos(AnchorPane anchor) {
        cirExterno = new DoubleCircularList<>();
        cirInterno = new DoubleCircularList<>();
        circulosInterno = new ArrayList<>();
        Random rd = new Random();

        int numCirculos = SettingsController.cantidadCirculos;
        double centerX = 519;
        double centerY = 310;

        //Elementos del Circulo Interno
        int radioMenor = 125;

        for (int i = 0; i < numCirculos; i++) {
            double angle = 2 * i * Math.PI / numCirculos;
            double xOffset = radioMenor * Math.cos(angle);
            double yOffset = radioMenor * Math.sin(angle);
            double x = centerX + xOffset;
            double y = centerY + yOffset;

            //Etiquetas del circulo Interno
            Integer num = rd.nextInt(9);
            cirInterno.addLast(num);
            Node n = new Node(x, y, 30);
            Label l = new Label(num.toString());
            l.setLayoutX(x - 10);
            l.setLayoutY(y - 10);
            l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            circulosInterno.addLast(n);
            labelsInt.addLast(l);
            anchor.getChildren().addAll(n, l);

        }

        //Elementos del circulo Externo
        //Circulos Externos
        int radioMayor = 275;
        for (int i = 0; i < numCirculos; i++) {
            double angle = 2 * i * Math.PI / numCirculos;
            double xOffset = radioMayor * Math.cos(angle);
            double yOffset = radioMayor * Math.sin(angle);
            double x = centerX + xOffset;
            double y = centerY + yOffset;

            //Se generan valores aleatorios a las etiquetas
            Integer num = rd.nextInt(9);
            cirExterno.addLast(num);

            //Etiquetas del Circulo Externo
            Node n = new Node(x, y, 40);
            Label l = new Label(num.toString());
            l.setLayoutX(x - 10);
            l.setLayoutY(y - 10);
            l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            circulosExterno.addLast(n);
            labelsExt.addLast(l);
            anchor.getChildren().addAll(n, l);

        }

    }

    public static void actualizarValoresCirculos() {
        int contadorExt = 0;
        int contadorInt = 0;
        for (Label l : labelsExt) {

            l.setText(cirExterno.get(contadorExt).toString());
            contadorExt++;
        }

        for (Label l : labelsInt) {

            l.setText(cirInterno.get(contadorInt).toString());
            contadorInt++;
        }

    }

    public static void actualizarValores(String interior, String exterior) {

        int con1 = Integer.parseInt(interior);
        int con2 = Integer.parseInt(exterior);
        String elementInt = String.valueOf(cirInterno.get(con1));
        String elementExt = String.valueOf(cirExterno.get(con2));
        Label l = labelsInt.get(con1);
        Label l2 = labelsExt.get(con2);
        l.setText(elementExt);
        l2.setText(elementInt);

    }

    public static void actualizarValoresCirculos(int lista, String PosElemento, String NuevoElement) {
        int pos = Integer.valueOf(PosElemento);
        int nuevo = Integer.valueOf(NuevoElement);
        if (lista == 0) {
            for (Label l : labelsInt) {
                if (l.getText().equals(String.valueOf(cirInterno.get(pos)))) {
                    l.setText(NuevoElement);
                    cirInterno.set(pos, nuevo);

                }

            }

        } else if (lista == 1) {

            for (Label l : labelsExt) {
                if (l.getText().equals(String.valueOf(cirExterno.get(pos)))) {
                    l.setText(NuevoElement);
                    cirExterno.set(pos, nuevo);

                }

            }

        }

    }

    //Elimina los elementos de Listas.
    public static void limpiarBuffer() {
        cirExterno.clear();
        cirInterno.clear();
        circulosExterno.clear();
        circulosInterno.clear();
        labelsExt.clear();
        labelsInt.clear();
    }
}
