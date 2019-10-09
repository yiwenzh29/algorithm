import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;


public class Deque<Item> implements Iterable<Item> {
    // construct an empty deque
    private Node head, tail;
    private int count;

    public Deque(){
        head = null;
        tail = null;
        count = 0;

    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return count == 0;
    }


    // return the number of items on the deque
    public int size(){
        return count;

    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null)
            throw new IllegalArgumentException("Please enter a valid item;");

        Node oldhead = head;
        head = new Node();
        head.item = item;
        head.next = oldhead;
        if(isEmpty()) tail = head;
        else oldhead.prev = head;
        count++;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null)
            throw new IllegalArgumentException("Please enter a valid item;");

        Node oldtail = tail;
        tail = new Node();
        tail.item = item;
        tail.prev = oldtail;
        if(isEmpty()) head = tail;
        else
            oldtail.next = tail;
        count++;

    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = head.item;
        head = head.next;
        count--;
        if (isEmpty()) tail = head;
        else head.prev = null;
        return item;

    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = tail.item;
        tail = tail.prev;
        count--;
        if (isEmpty()) head = tail;
        else tail.next = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new FrontIterator();

    }

    private class FrontIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }
        public Item next(){
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException("remove is not supported by iterators.");
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque deque = new Deque();
        boolean c1 = deque.isEmpty();
        StdOut.println(deque.size());

        //addFirst
        deque.addFirst(3);
        deque.addFirst(4);
        boolean c2 = deque.isEmpty();
        StdOut.println(deque.size());
        deque.addLast(5);
        deque.addLast(6);

        //remove first
        deque.removeFirst();
        StdOut.println(deque.size());

        Iterator fi = deque.iterator();
        while (fi.hasNext())
            StdOut.println(fi.next());
        //remove last
        deque.removeLast();
        StdOut.println(deque.size());
        //Iterator
        Iterator fi2 = deque.iterator();
        while (fi2.hasNext())
            StdOut.println(fi2.next());
        fi2.remove();







    }
}
