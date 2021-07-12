/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAList;

/**
 *
 * @author fabri
 */
public class Node<E> {
    
    private E content;
    private Node<E> next;
    private Node<E> previus;

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
}
