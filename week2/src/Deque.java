import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    // construct an empty deque
    Node<Item> head, tail;

    public Deque(){

    }

    private class Node {
        Item item;
        Node next;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return head == null;
    }


    // return the number of items on the deque
    public int size(){

    }

    // add the item to the front
    public void addFirst(Item item){
        Node oldhead = head;
        head = new Node();
        head.item = item;
        head.next = oldhead;
    }

    // add the item to the back
    public void addLast(Item item){
        Node oldtail = tail;
        tail = new Node();
        tail.item = item;
        tail.next = null;
        if(isEmpty()) head = tail;
        else
            oldtail.next = tail;

    }

    // remove and return the item from the front
    public Item removeFirst(){
        Item item = head.item;
        head = head.next;
        if (isEmpty()) tail = null;
        return item;

    }

    // remove and return the item from the back
    public Item removeLast(){
        Item item = tail.item;
        tail = null;
        return item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new FrontIterator<Item>();

    }

    private class FrontIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){

    }
}
