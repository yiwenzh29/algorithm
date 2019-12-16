import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class Solver {

    private MinPQ<SearchNode> pq;
    private MinPQ<SearchNode> pqtwin;
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

        this.initial = initial;
        this.N = initial.dimension();
        this.pq = new MinPQ<SearchNode>();
        this.pqtwin = new MinPQ<SearchNode>();


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

        SearchNode minNode;
        SearchNode minNodeTwin;

        pq.insert(new SearchNode(initial, 0, null));
        pqtwin.insert(new SearchNode(initial.twin(), 0, null));


        while (!pq.min().board.equals(goal)) {
            minNode = pq.min();
            pq.delMin();

            minNodeTwin = pqtwin.min();
            pqtwin.delMin();

            for(Board neighbor: minNode.board.neighbors()) {
                if (minNode.moves == 0) {
                    pq.insert(new SearchNode(neighbor, minNode.moves + 1, minNode));
                }
                else if (!neighbor.equals(minNode.previousNode.board)) {
                    pq.insert(new SearchNode(neighbor, minNode.moves+1, minNode));
                }
            }

            for(Board neighbor: minNodeTwin.board.neighbors()) {
                if (minNodeTwin.moves == 0) {
                    pqtwin.insert(new SearchNode(neighbor, minNodeTwin.moves + 1, minNodeTwin));
                }
                else if (!neighbor.equals(minNodeTwin.previousNode.board)) {
                    pqtwin.insert(new SearchNode(neighbor, minNodeTwin.moves+1, minNodeTwin));
                }
            }
        }


    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        if (pq.min().board.equals(goal)) {
            return true;
        }

        if (pqtwin.min().board.equals(goal)) {
            return false;
        }

        return false;

    }

    // min number of moves to solve initial board
    public int moves() {

        if (!isSolvable()) {
            return -1;
        }
        return pq.min().moves;

    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {

        if (!isSolvable()) {
            return null;
        }

        Stack<Board> moves = new Stack<Board>();
        SearchNode tmp = pq.min();
        while(tmp.previousNode != null) {
            moves.push(tmp.board);
            tmp = tmp.previousNode;
        }

        moves.push(initial);
        return moves;

    }

    // test client (see below)
    public static void main(String[] args) {

//        // create initial board from file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        int[][] tiles = new int[n][n];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                tiles[i][j] = in.readInt();
        int[][] tiles  =
                {
                        {0, 1, 3},
                        {4, 2, 5},
                        {7, 8, 6},
                };


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
