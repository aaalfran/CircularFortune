/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAList;

import java.util.Iterator;

/**
 *
 * @author fabri
 * @param <E>
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
        if (index == 0) {
            addFirst(element);
        } else if (index == (size())) {
            addLast(element);
        } else {
            boolean vuelta = false;
            int cont = -1;

            Node<E> nuevo = new Node<>(element);
            for (Node<E> p = last.getNext(); vuelta != true; p = p.getNext()) {
                cont++;
                if ((index - 1) == cont) {
                    //LOGICA PARA Agregar
                    nuevo.setNext(p.getNext());
                    p.getNext().setPrevius(nuevo);
                    nuevo.setPrevius(p);
                    p.setNext(nuevo);
                    break;
                }
            }

        }

    }

    @Override
    public E removeFirst() {
        E temp = first.getContent();
        first = first.getNext();
        last.setNext(first);
        first.setPrevius(last);
        return temp;
    }

    @Override
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

    @Override
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

    @Override
    public int size() {
        if (isEmpty()) {
            return 0;
        }
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

    @Override
    public boolean isEmpty() {
        return last == null;

    }

    @Override
    public E get(int index) {
        if (index < size()) {
            Node<E> n;
            int v = 0;
            for (n = first; n != null; n = n.getNext()) {
                if (v == index) {
                    return (n.getContent());
                }
                v++;
            }
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        if (index < size()) {
            Node<E> n;
            int v = 0;
            for (n = first; n != null; n = n.getNext()) {
                if (v == index) {
                    n.setContent(element);
                    return (n.getContent());
                }
                v++;
            }
        }
        return null;
    }

    @Override
    public void clear() {

        this.first = null;
        this.last = null;

    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private Node<E> p = last;
            boolean vuelta = false;

            @Override
            public boolean hasNext() {
                if (!vuelta) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public E next() {
                E tmp = p.getContent();
                p = p.getNext();
                if (p == last) {
                    vuelta = true;
                }
                return tmp;
            }
        };

        return it;
    }

    public static Integer suma(DoubleCircularList<Integer> listaCircular1, DoubleCircularList<Integer> listaCircular2) {
        int lista1 = 0;
        int lista2 = 0;
        Iterator<Integer> iteratorA = listaCircular1.iterator();
        Iterator<Integer> iteratorB = listaCircular2.iterator();

        while (iteratorA.hasNext()) {
            int valor = iteratorA.next();
            lista1 += valor;
        }
        while (iteratorB.hasNext()) {
            int valor = iteratorB.next();
            lista2 += valor;
        }
        return lista1 + lista2;
    }

    // mueve los elementos a la derecha y les suma 1
    public static void moveRigth(DoubleCircularList<Integer> c) {
        DoubleCircularList<Integer> tmp = new DoubleCircularList<>();
        for (int i = 0; i < c.size() - 1; i++) {
            int e = c.get(i) + 1;
            tmp.add(i, e);

        }
        tmp.add(0, c.get(c.size() - 1) + 1);

        for (int i = 0; i < c.size(); i++) {
            c.set(i, tmp.get(i));

        }

    }
    // mueve los elementos a la izquierda y les resta 1

    public static void moveLeft(DoubleCircularList<Integer> c) {
        for (int i = c.size() - 1; i >= 0; i--) {
            int e = c.get(i) - 1;
            c.set(i, e);

        }
        int e = c.removeFirst();
        c.addLast(e);

    }

    @Override

    public int getE(E element) {
        boolean vuelta = false;
        int cont = 0;
        for (Node<E> p = last.getNext(); vuelta != true; p = p.getNext()) {

            if (p.getContent() == element) {
                vuelta = true;
                return cont;
            }
            cont++;

        }

        return -1;
    }

    @Override
    public String toString() {
        String acumulador = "";
        Node<E> p = first;
        for (int n = 0; n < size(); n++) {
            acumulador += (p.getContent() + " ");
            p = p.getNext();
        }

        return acumulador;
    }

    //intercambia elementos de dos listas
    public static void changePosition(DoubleCircularList<Integer> interior, DoubleCircularList<Integer> exterior, int posicion, int posicion2) {

        if (interior.get(posicion) != null && exterior.get(posicion2) != null) {

            int elementInt = interior.get(posicion);
            int elementExt = exterior.get(posicion2);
            interior.set(posicion, elementExt);
            exterior.set(posicion2, elementInt);

        }
         
    }

}
