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
public class ArrayList<E> implements List<E> {

    private E[] elements;
    private E[] nElements;
    private int capacity = 10;
    private int effectiveSize;

    //Constructor
    public ArrayList() {
        elements = (E[]) new Object[capacity];
        effectiveSize = 0;
    }

    @Override
    public void addFirst(E e) {
        if (effectiveSize == 0) {
            elements[0] = e;
            effectiveSize += 1;
        } else if (effectiveSize > 0) {
            if (isFull()) {
                makeSpace();
                addFirst(e);
            } else {
                for (int elemento = effectiveSize; elemento > 0; elemento--) {
                    elements[elemento] = elements[elemento - 1];
                }
                elements[0] = e;
                effectiveSize += 1;
            }
        }
    }

    @Override
    public void addLast(E e) {
        if (effectiveSize == 0) {
            elements[0] = e;
            effectiveSize += 1;
        } else if (effectiveSize >= 0) {
            if (isFull()) {
                makeSpace();
                addLast(e);
            } else {
                elements[effectiveSize] = e;
                effectiveSize += 1;
            }
        }
    }

    @Override
    public E removeFirst() {
        E temp = null;
        for (int i = 0; i < effectiveSize; i++) {
            temp = elements[0];
            elements[i] = elements[i + 1];
        }
        elements[effectiveSize - 1] = null;
        effectiveSize -= 1;
        return temp;
    }

    @Override
    public E removeLast() {
        E temp = elements[effectiveSize - 1];
        elements[effectiveSize - 1] = null;
        effectiveSize -= 1;
        return temp;
    }

    @Override
    public int size() {
        int tamnio = effectiveSize;
        return tamnio;
    }

    @Override
    public boolean isEmpty() {
        if (effectiveSize == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < effectiveSize; i++) {
            elements[i] = null;
        }
        effectiveSize = 0;
    }

    @Override
    public void add(int index, E element) {
        if (isFull()) {
            makeSpace();
            add(index, element);
        } else {
            for (int i = effectiveSize; i > index; i--) {
                elements[i] = elements[i - 1];
            }
            elements[index] = element;
            effectiveSize += 1;
        }
    }

    @Override
    public E remove(int index) {
        E temp = elements[index];
        if (effectiveSize == 0) {
            return null;
        } else if (effectiveSize <= index) {
            return null;
        } else {
            for (int i = index; i < effectiveSize; i++) {
                elements[i] = elements[i + 1];
            }
            effectiveSize -= 1;
            return temp;
        }
    }

    @Override
    public E get(int index) {
        if (index < 0) {
            return null;
        } else if (index >= effectiveSize) {
            return null;
        } else {
            return elements[index];
        }
    }

    @Override
    public E set(int index, E element) {
        E temp = elements[index];
        elements[index] = element;
        return temp;
    }

    @Override
    public String toString() {
        String acumulador = "";
        for (int i = 0; i < effectiveSize; i++) {
            acumulador += (elements[i] + " ");
        }
        return acumulador;
    }

    public void makeSpace() {
        capacity *= 2;
        nElements = (E[]) new Object[capacity];
        for (int i = 0; i < effectiveSize; i++) {
            nElements[i] = elements[i];
        }
        elements = nElements;
    }

    public boolean isFull() {
        if (effectiveSize == capacity) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < effectiveSize;
            }

            @Override
            public E next() {
                E tmp = get(index);
                index++;
                return tmp;
            }

        };

        return it;
    }

    @Override
    public int getE(E element) {
        int cont = 0;
        for (int i = 0; i < size(); i++) {
            cont++;
            if (element == this.get(i)) {
                return cont;
            }

        }
        return -1;

    }

}
