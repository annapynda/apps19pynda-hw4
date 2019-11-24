package ua.edu.ucu.tries;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
    }


    Queue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }


    public boolean isEmpty() {
        if (first == null) {
            return true;
        }
        return false;

    }


    public int size() {
        return size;
    }


    public void enqueue(Item item) {
        Node l = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (!(isEmpty())) {
            l.next = last;
        }
        else {
            first = last;
        }
        size -= 1;
    }



    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item).append(" ");
        String res = s.toString();
        return res;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            if (current == null) {
                return false;
            }
            return true;
        }


        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}