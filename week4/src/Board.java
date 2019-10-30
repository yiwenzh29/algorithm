import java.util.LinkedList;

public class Board {

    private int[][] tiles;
    private int[][] copy;
    private int row;
    private int col;
    private int[] blocks;


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {

        this.tiles = tiles;

        this.row = tiles.length;
        this.col = tiles.length;

        this.copy = new int[row][col];
        this.blocks = new int[row*col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                copy[i][j] = tiles[i][j];
                blocks[i * row + j] = tiles[i][j];
            }
        }

    }

    // string representation of this board
    public String toString() {
        String str = "";
        str = str + dimension() + "\r\n";
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                str+= " " + copy[i][j];
            }
            str+= "\r\n";
        }

        return str;

    }

    // board dimension n
    public int dimension() {
        return row;
    }
//
//    // number of tiles out of place
    public int hamming() {

        int hDis = 0;

        for (int i = 0; i < blocks.length; i++) {
            if (i != blocks.length-1 && blocks[i] != i+1)
                hDis++;
        }

        return hDis;

    }

     //sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int mDis = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (blocks[i*col + j] != i*col + j + 1 && blocks[i*col+j] != 0) {
                    mDis += Math.abs((blocks[i*col + j] - 1) / col - i) + Math.abs((blocks[i*col + j] - 1) % col - j);
                }
            }
        }

        return mDis;
    }
//
    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (blocks[i*col + j] != i*col + j + 1 && blocks[i*col+j] != 0)
                    return false;
                else if (blocks[i*col+j] == 0 && i+col+j != blocks.length-1)
                    return false;
            }
        }

        return true;

    }
//
    // does this board equal y?
    public boolean equals(Object y) {
        if (y == copy)
            return true;

        if (y == null)
            return false;


        Board test = (Board) y;

        if (this.dimension() != test.dimension())
            return false;

        for (int i = 0; i < test.blocks.length; i++)
            if (blocks[i] != test.blocks[i])
                return false;

        return true;



    }
//

    //swap: exchange the the blank space with another tile
    private int[][] swap(int row0, int col0, int row1, int col1) {
        int[][]swapCopy = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                swapCopy[i][j] = copy[i][j];
            }
        }

        int current = swapCopy[row0][col0];
        swapCopy[row0][col0] = swapCopy[row1][col1];
        swapCopy[row1][col1] = current;

        return swapCopy;
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {
        LinkedList<Board> neighbors = new LinkedList<Board>();
        int[] blankLoc = new int[2];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (blocks[i * col + j] == 0) {
                    blankLoc[0] = i;
                    blankLoc[1] = j;
                }
            }
        }

        int blankRow = blankLoc[0];
        int blankCol = blankLoc[1];

        if (blankRow > 0)
            neighbors.add(new Board(swap(blankRow, blankCol, blankRow-1, blankCol)));
        if (blankRow < dimension() - 1)
            neighbors.add(new Board(swap(blankRow, blankCol, blankRow+1, blankCol)));
        if (blankCol > 0)
            neighbors.add(new Board(swap(blankRow, blankCol, blankRow, blankCol-1)));
        if (blankCol < dimension() - 1)
            neighbors.add(new Board(swap(blankRow, blankCol, blankRow, blankCol+1)));

        return neighbors;

    }
//
    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

        if (blocks[0] != 0 && blocks[1] != 0)
            return new Board(swap(0, 0, 0, 1));

        else if (blocks[0] == 0)
            return new Board(swap(0,1,0,2));

        else if (blocks[1] == 0)
            return new Board(swap(0,0,0,2));

        throw new RuntimeException();

    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] test  =
                {
                        {8, 1, 3},
                        {4, 0, 2},
                        {7, 6, 5},
                };
        Board b = new Board(test);
//        for (int i = 0; i < test.length; i++) {
//            for (int j = 0; j < test.length; j++) {
//                System.out.printf("%8d", test[i][j]);
//            }
//            System.out.println();
//        }


        System.out.println(b.toString());


//        for (int k = 0; k < b.blocks.length; k++)
//            System.out.print(b.blocks[k]);
//
//        System.out.println();

//        System.out.println(b.hamming());

//        System.out.println(b.manhattan());

//        System.out.println(b.isGoal());


//        System.out.println(b.twin().toString());
    }

}
