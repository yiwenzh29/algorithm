import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> input = new RandomizedQueue<String>();
        int size = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            input.enqueue(StdIn.readString());
        }

        for (int i = 0; i < size; i++) {
            StdOut.println(input.dequeue());
        }
    }
}
