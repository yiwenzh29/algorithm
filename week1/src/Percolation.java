import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private int gridSize;
    private boolean[][] grid;
    private int virtualTop;
    private int virtualBottom;
    private int openCount;
    private WeightedQuickUnionUF ufDataType;


    public Percolation(int n)
    {
        if (n <= 0)
            throw new IllegalArgumentException();
        //initiate the grid
        this.gridSize = n;
        //top index and bottom index
        this.virtualTop = 0;
        this.virtualBottom = gridSize*gridSize +1;
        //initiate grid
        grid = new boolean[n][n];
        for(int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = false;

        ufDataType = new WeightedQuickUnionUF(n*n + 2);

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        checkValid(row, col);
        if(isOpen(row, col))
            return;
        //open site
        grid[row - 1][col - 1] = true;
        openCount++;

        //connect to adjacent site
        int index = transIndex(row, col);
        //if at top row:
        if (row == 1)
            ufDataType.union(index, virtualTop);
        //if at bottom row
        if (row == gridSize)
            ufDataType.union(index, virtualBottom);

        //up
        if (row > 1 && isOpen(row-1, col))
            ufDataType.union(transIndex(row-1, col), index);
        //else, at top row:

        //down
        if (row < gridSize && isOpen(row+1, col))
            ufDataType.union(transIndex(row+1, col), index);

        //left
        if (col > 1 && isOpen(row, col-1))
            ufDataType.union(transIndex(row, col-1), index);
        //right
        if(col < gridSize && isOpen(row, col+1))
            ufDataType.union(transIndex(row, col+1), index);

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        checkValid(row, col);
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        checkValid(row, col);
        int index = transIndex(row, col);
        return ufDataType.connected(virtualTop, index);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openCount;
    }

    //transform the (row, column) to index like in UF
    private int transIndex(int row, int col)
    {
        return gridSize * (row - 1) + col;
    }


    // does the system percolate?
    public boolean percolates()
    {
        return ufDataType.connected(virtualTop, virtualBottom);
    }

    private void checkValid(int row, int col)
    {
        if(row < 1 || col < 1 || row > gridSize || col > gridSize)
            throw new IllegalArgumentException(
                    "Index is out of bound.");
    }

    // test client (optional)
    public static void main(String[] args) {
    Percolation perc = new Percolation(3);
    perc.open(1, 2);
    perc.open(2, 2);
    perc.open(2, 3);
    perc.open(3, 3);
    boolean c = perc.isFull(3, 3);
    //boolean c1 = perc.ufDataType.connected(perc.transIndex(1, 2), perc.transIndex(2, 3));
    boolean c2 = perc.percolates();
    StdOut.println(c);
    //StdOut.println(c1);
    StdOut.println(c2);
    //StdOut.println(perc.isOpen(2,2));
  }

}
