/* *****************************************************************************
 *  Name:Satyajit Sarangdhar
 *  Date:
 *  Description: Priceton Algo  course assignment 2
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] trialCount;

    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {

            throw new IllegalArgumentException("Non-valid inputs provided");

        }


        trialCount = new double[trials];

        for (int trialNumber = 0; trialNumber < trials; trialNumber++) {

            Percolation percolationTrial = new Percolation(n);

            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);

            while (!percolationTrial.percolates()) {

                while (percolationTrial.isOpen(row, col)) {

                    row = StdRandom.uniform(1, n + 1);
                    col = StdRandom.uniform(1, n + 1);

                }

                percolationTrial.open(row, col);
                //StdOut.println(percolationTrial.numberOfOpenSites());
            }


            trialCount[trialNumber] = (double) percolationTrial.numberOfOpenSites() / (double) (n
                    * n);
        }

    }


    // sample mean of percolation threshold
    public double mean() {

        return StdStats.mean(trialCount);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {

        return StdStats.stddev(trialCount);

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {

        return mean() - (stddev() * 1.96 / Math.sqrt(trialCount.length));

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {

        return mean() + (stddev() * 1.96 / Math.sqrt(trialCount.length));

    }

    // test client (see below)
    public static void main(String[] args) {

        int n, trials;

        n = Integer.parseInt(args[0]);

        trials = Integer.parseInt(args[1]);

        PercolationStats percolationExp = new PercolationStats(n, trials);

        double mean = percolationExp.mean();

        double stddev = percolationExp.stddev();

        double confhi = percolationExp.confidenceHi();

        double conflo = percolationExp.confidenceLo();

        System.out.println("mean                    = " + mean);
        System.out.println("stddev                  = " + stddev);
        System.out
                .println("95% confidence interval = " + "[" + confhi + ", " + conflo + "]");

    }


}
