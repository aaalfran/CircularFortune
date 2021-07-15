/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAList;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author fabri
 */
public class Node<E> extends Circle{
    
    private E content;
    private Node<E> next;
    private Node<E> previus;

    public Node(double x, double y, int rad){
        super(x, y, rad);
        super.setFill(Paint.valueOf("#FFFFFF"));
    }
    
    public Node(E content){
        this.content = content;
        this.next = this;
        this.previus=this;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public Node<E> getNext() {
        return next;
    }

    public Node<E> getPrevius() {
        return previus;
    }

    public void setPrevius(Node<E> previus) {
        this.previus = previus;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
    
    /*
    
    public  void clickCircle(Node n){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(n.getContent());
            }
        });
        
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                n.setCursor(Cursor.HAND);
            }
        });
           
    }
*/
}
