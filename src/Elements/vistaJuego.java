/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import TDAList.DoubleCircularList;
import circularfortune.SettingsController;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author aaron
 */
public class vistaJuego {
    
    
    public static Circle getCirculoExt(){
        
        Circle cExterno = new Circle(519, 310, 275,Paint.valueOf("#00FFFF00"));
        cExterno.setStroke(Paint.valueOf("#BAB3B1"));
        cExterno.setStrokeWidth(5);
        return cExterno;
    }
    
    public static Circle getCirculoInt(){
        
        Circle cExterno = new Circle(519, 310, 125,Paint.valueOf("#00FFFF00"));
        cExterno.setStroke(Paint.valueOf("#BAB3B1"));
        cExterno.setStrokeWidth(5);
        return cExterno;
    }
    
    public static Circle puntoBase(){
        Circle pbase = new Circle(519,185,25,Paint.valueOf("#FFFFFF"));
        
        return pbase;
    }
    
    public static void fijarCirculos(AnchorPane anchor){
        
        DoubleCircularList cirInterno = new DoubleCircularList();
        DoubleCircularList cirExterno = new DoubleCircularList();
        
        
        
        int numCirculos = SettingsController.cantidadCirculos;
        int radioMenor = 125;
        double centerX = 519;
        double centerY = 310;
        for (int i = 0; i < numCirculos; i++) {
            double angle = 2 * i * Math.PI / numCirculos;
            double xOffset = radioMenor * Math.cos(angle);
            double yOffset = radioMenor * Math.sin(angle);
            double x = centerX + xOffset;
            double y = centerY + yOffset;
            Circle cIn = new Circle(x, y, 25,Paint.valueOf("#FFFFFF"));
            anchor.getChildren().add(cIn);
            cirInterno.addLast(cIn);
        }
        int radioMayor = 275;
        for (int i = 0; i < numCirculos; i++) {
            double angle = 2 * i * Math.PI / numCirculos;
            double xOffset = radioMayor * Math.cos(angle);
            double yOffset = radioMayor * Math.sin(angle);
            double x = centerX + xOffset;
            double y = centerY + yOffset;
            Circle cExt = new Circle(x, y, 25,Paint.valueOf("#FFFFFF"));
            anchor.getChildren().add(cExt);
            cirExterno.addLast(cExt);
        }
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
