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
public class DoubleCircularList<E> implements List<E> {

    public Node<E> first;
    public Node<E> last;
    public Node<E> previus;

    public DoubleCircularList() {
        last = null;
    }

    @Override
    public void add(int index, E element) {
        if (isEmpty()) {
            addLast(element);
        } else if (index == 0) {
            addFirst(element);
        } else if (index == (size() - 1)) {
            addLast(element);
        } else {
            boolean vuelta = false;
            int cont = -1;
            for (Node<E> p = last.getNext(); vuelta != true; p = p.getNext()) {
                cont++;
                if ((index - 1) == cont) {
                    //LOGICA PARA Agregar
                    Node<E> nuevoSiguiente = new Node<>(element);
                    Node<E> continuar = p.getNext();
                    p.setNext(nuevoSiguiente);
                    nuevoSiguiente.setNext(continuar);
                    continuar.setPrevius(p);
                    break;
                }
            }
        }

    }

    public E removeFirst() {
        E temp = first.getContent();
        first = first.getNext();
        last.setNext(first);
        first.setPrevius(last);
        return temp;
    }

    public E removeLast() {
        E temp = last.getContent();
        last = last.getPrevius();
        last.setNext(first);
        first.setPrevius(last);
        return temp;
    }

    @Override
    public E remove(int index) {
        if (isEmpty()) {
            return null;
        } else if (index >= size()) {
            return null;
        } else if (index == 0) {
            removeFirst();
        } else if (index == (size() - 1)) {
            removeLast();
        } else {
            E temp = null;
            boolean vuelta = false;
            int cont = -1;
            for (Node<E> p = last.getNext(); vuelta != true; p = p.getNext()) {
                cont++;
                if ((index - 1) == cont) {
                    //LOGICA PARA ELIMINAR
                    temp = p.getNext().getContent();
                    Node<E> siguiente = p.getNext().getNext();
                    p.setNext(siguiente);
                    siguiente.setPrevius(p);
                    return temp;
                }
            }
        }
        return null;
    }

    public void addFirst(E element) {
        Node<E> n = new Node<>(element);
        if (isEmpty()) {
            last = n;
            first = n;
            last.setNext(first);
            previus = n;
        } else {
            last.setNext(n);
            first.setPrevius(n);
            n.setNext(first);
            first = n;
            first.setPrevius(last);
        }

    }

    @Override
    public void addLast(E element) {
        Node n = new Node<>(element);
        if (isEmpty()) {
            last = n;
            first = n;
            last.setNext(first);
            previus = n;
        } else {
            last.setNext(n);
            n.setPrevius(last);
            first.setPrevius(n);
            last = n;
            last.setNext(first);
        }

    }

    public int size() {
        boolean vuelta = false;
        int cont = 0;
        for (Node<E> p = last.getNext(); vuelta != true; p = p.getNext()) {
            cont++;
            if (p.getNext() == last.getNext()) {
                vuelta = true;
            }
        }
        return cont;
    }

    public boolean isEmpty() {
        return last == null;

    }

}