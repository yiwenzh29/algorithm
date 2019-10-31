import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class Solver {

    private MinPQ<SearchNode> pq;
    private int N;
    private Board initial;
    private Board goal;


    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;
        private int priority;
        private SearchNode previousNode;

        public SearchNode(Board board, int moves, SearchNode previousNode) {
            this.board = board;
            this.moves = moves;
            priority = moves + board.manhattan();
            this.previousNode = previousNode;
        }

        public int compareTo(SearchNode that) {
            if (this.priority - that.priority < 0)
                return -1;

            else
                return 1;
        }

    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

        if (initial == null)
            throw new IllegalArgumentException();

        this.N = initial.dimension();
        this.pq = new MinPQ<SearchNode>();

        int[][] goalBlocks = new int[N][N];
        int tile = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                goalBlocks[i][j] = tile;
                tile++;
            }
        }

        goalBlocks[N-1][N-1] = 0;

        this.goal = new Board(goalBlocks);


    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {

    }

    // min number of moves to solve initial board
    public int moves() {

    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {

    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
