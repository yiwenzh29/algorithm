import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int count;

    // construct an empty randomized queue
    public RandomizedQueue() {
        count = 0;
        queue = (Item[]) new Object[1];

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        queue[count++] = item;
        if (count == queue.length)
            resize(2 * queue.length);
    }

    // remove and return a random item
    public Item dequeue() {
        if (count == 0)
            throw new NoSuchElementException();
        int random = StdRandom.uniform(1, count);
        Item item = queue[random];
        queue[random] = queue[random-1];
        queue[count--] = null;
        if (count > 0 && count == queue.length/4)
            resize(queue.length/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (count == 0) throw new NoSuchElementException();
        int random = StdRandom.uniform(count);
        return queue[random];

    }

    private void resize(int newSize) {
        Item[] newQueue = (Item[]) new Object[newSize];
        for (int i = 0; i < count; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int cursor = count;
        private int[] order;

        public RandomizedQueueIterator() {
            order = new int[cursor];
            for (int j = 0; j < cursor; j++) {
                order[j] = j;
            }
            StdRandom.shuffle(order);
        }

        public boolean hasNext() {return cursor > 0;}

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            cursor--;
            return queue[order[cursor]];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue rQueue = new RandomizedQueue();
        StdOut.println(rQueue.isEmpty());
        StdOut.println(rQueue.size());

        rQueue.enqueue(1);
        rQueue.enqueue(2);
        StdOut.println(rQueue.isEmpty());
        StdOut.println(rQueue.size());

        rQueue.enqueue(3);
        rQueue.enqueue(4);

        StdOut.println(rQueue.sample());

        rQueue.dequeue();
        rQueue.dequeue();

        StdOut.println(rQueue.size());
        Iterator ri = rQueue.iterator();
        while (ri.hasNext()) {
            StdOut.println(ri.next());
            ri.remove();
        }



    }

}
