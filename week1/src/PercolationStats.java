import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
    private int gridSize;
    private int trials;
    private double[] results;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        this.gridSize = n;
        this.trials = trials;
        if(gridSize < 1 || trials < 1)
            throw new IllegalArgumentException("Grid size and trials should be at least 1");

        results = new double[trials];

        Percolation perc;
        for(int i = 0; i < trials; i++){
            perc = new Percolation(gridSize);
            double count = 0;
            do{
                int row = StdRandom.uniform(1, gridSize + 1);
                int col = StdRandom.uniform(1, gridSize + 1);
                if (perc.isOpen(row, col))
                    continue;
                perc.open(row, col);
                count++;
            }
            while (!perc.percolates());
            count = perc.numberOfOpenSites();
            results[i] = count / gridSize*gridSize;
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean() - 1.96*stddev()/Math.sqrt(results.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + 1.96*stddev()/Math.sqrt(results.length);
    }

    // test client (see below)
    public static void main(String[] args)
    {
        Integer n = Integer.parseInt(args[0]);
        Integer T = Integer.parseInt(args[1]);
        //Stopwatch timer = new Stopwatch();

        PercolationStats stats = new PercolationStats(n, T);

        System.out.printf("mean" , stats.mean());
        System.out.printf("stddev" , stats.stddev());
        System.out.printf("95% confidence interval" + "[" + stats.confidenceLo() + "," + stats.confidenceHi() + "]");

    }
}
