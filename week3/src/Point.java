import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;


public class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    // constructs the point (x, y)
    public Point(int x, int y){
        /* DO NOT MODIFY */
            this.x = x;
            this.y = y;
    }

    // draws this point
    public void draw(){
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }


    // draws the line segment from this point to that point
    public   void drawTo(Point that){
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    // string representation
    public String toString(){
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that){
        if ((this.y == that.y && this.x < that.x) || this.y < that.y )
            return -1;
        else if (this.y == that.y && this.x == that.x)
            return 0;
        else
            return 1;

    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    // the slope between this point and that point
    public double slopeTo(Point that){
        return (that.y - this.y) / (that.x - this.x);

    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder(){
        return new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Double.compare(slopeTo(o1), slopeTo(o2));
            }
        };
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point o1 = new Point(2,2);
        o1.draw();
        System.out.println(o1.toString());
        Point o2 = new Point(1, 1);
        o1.drawTo(o2);
        System.out.println(o1.compareTo(o2));
        System.out.println(o1.slopeTo(o2));
        System.out.println(o1.slopeTo(o2));

    }
}
