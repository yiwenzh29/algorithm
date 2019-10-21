
public class BruteCollinearPoints {
    private int count;
    private LineSegment[] segments;


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points){

        for (int i = 0; i < N - 3; i++) {
            for (int j = i+1; j < N - 2; j++) {
                double slopeij = points[i].slopeTo(points[j]);
                for (int k = j+1; k < N - 1; k++) {
                    double slopeik = points[i].slopeTo(points[k]);
                    for (int l = k+1; l < N; l++) {
                        double slopeil = points[i].slopeTo(points[l]);
                        if (slopeij == slopeik && slopeij == slopeil) {
                            
                        }
                    }
                }
            }
        }


    }

    // the number of line segments
    public int numberOfSegments() {

        return count;

    }

    // the line segments
    public LineSegment[] segments() {

    }

}
