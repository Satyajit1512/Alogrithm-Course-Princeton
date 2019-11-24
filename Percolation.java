/* *****************************************************************************
 *  Name:Satytajit Sarangdhar
 *  Date:
 *  Description: Princeton Algo course assignment 1
 **************************************************************************** */

public class Percolation {

    // Brute force apporach
    private int[][] twoD_arr;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {


        if (n <= 0) {

            throw new IllegalArgumentException("The size of grid entered is invalid");

        }

        twoD_arr = new int[n][n];

        for (int i = 0; i < n; i++) { // iterating through each row of the grid

            for (int j = 0; j < n; j++) { // iterating through each column of the grid

                twoD_arr[i][j]
                        = 0;   //0 denotes that the site is blocked, intially setting all sites to be closed

            }
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (row < 1 || col < 1) {

            throw new IllegalArgumentException("The entered index was invalid");

        }

        twoD_arr[row - 1][col - 1] = 1;  // Setting particular grid element to be open

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        if (row < 1 || col < 1) {

            throw new IllegalArgumentException("The entered index was invalid");

        }

        return twoD_arr[row - 1][col - 1] == 1; // Checking if the site is open

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        int n = twoD_arr[0].length;

        boolean[] topRow = new boolean[n];

        for (int i = 0; i < n; i++) {

            topRow[i] = true;
        }

        if (row < 1 || col < 1) {

            throw new IllegalArgumentException("The entered index was invalid");
        }
        else {

            for (int i = 1; i <= row; i++) {


                boolean[] curRow = new boolean[n];

                for (int j = 1; j <= n; j++) {

                    if (i == 1) {

                        curRow[j - 1] = isOpen(i, j);

                    }
                    else {

                        if (j == 1) {

                            curRow[j - 1] = isOpen(i, j) && ((topRow[j - 1]) || (isOpen(i, j + 1)
                                    && topRow[j]));

                        }
                        else if (j == n) {

                            curRow[j - 1] = isOpen(i, j) && ((topRow[j - 1]) || (isOpen(i, j - 1)
                                    && topRow[j - 2]));


                        }
                        else {

                            curRow[j - 1] = isOpen(i, j) && ((topRow[j - 1]) || (isOpen(i, j - 1)
                                    && topRow[j - 2])
                                    || (isOpen(i, j + 1) && topRow[j]));
                        }

                    }

                }

                topRow = curRow;
            }

        }

        return topRow[col - 1];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {

        int totalOpenSites = 0;

        int n = twoD_arr[0].length;

        for (int i = 0; i < n; i++) { // iterating through each row of the grid
            for (int j = 0; j < n; j++) { // iterating through each column of the grid

                if (twoD_arr[i][j] == 1) {

                    totalOpenSites += 1;

                }

            }
        }

        return totalOpenSites;
    }


    // does the system percolate?
    public boolean percolates() {

        boolean percolates = false;

        int n = twoD_arr[0].length;

        for (int i = 1; i <= n; i++) {

            if (isFull(n, i)) {
                percolates = true;
                break;
            }

        }

        return percolates;
    }

    public static void main(String[] args) {

    }
}
