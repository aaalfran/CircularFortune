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
public interface List<E> extends Iterable<E> {

    public void add(int index, E element); // inserta element en la posición index

    public void addLast(E element);//inserta element al final de la lista

    public void addFirst(E element); //inserta elemento al inicio de la lista

    public int getE(E element); //inserta el elemento y obtiene el indice

    public E remove(int index); // remueve y retorna el elemento en la posición index

    public E get(int index); //inserta el indice y obtiene el elemento

    public E set(int index, E element); // cambia un element por otro en la misma posicion.
}
