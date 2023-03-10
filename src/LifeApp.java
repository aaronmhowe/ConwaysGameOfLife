import javax.swing.*;

/**
 * Class to implement the algorithm for Conway's Game of Life
 * @author Aaron Howe
 * @version JDK 17
 */
public class LifeApp extends JFrame {

    public int gridSize;
    int[][] cells;
    int[][] newCells = new int[19][19];

    /**
     * Default constructor designs the board
     * @param initialState the initial state of the board
     */
    public LifeApp(int[][] initialState) {
        cells = initialState;
        gridSize = initialState.length;
    }

    /**
     * toString that translates int data to string
     * @return the board when outer for loop is satisfied
     */
    @Override
    public String toString() {
        // instantiate blank string object
        String board = "";
        // translate integer array data into strings
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                switch (cells[i][j]) {
                    case 0: board += "." + " ";
                    break;
                    case 1: board += "O" + " ";
                    break;
                }
            }
            // appends a new line for every 19 elements in a single row, return to inner loop
            board += "\n";
        }
        return board;
    }

    /**
     * method to check if all surrounding neighbors of a cell are alive
     * @param row the rows of the grid
     * @param col the columns
     * @return live cells
     */
    // short circuiting: if you have two parts of an if statement, if first check is false, then code will exit
    // - only works for &&, not ||
    public int checkNeighbors(int row, int col) {
        int count = 0;
        // check top left
        if (row != 0 && col != 0 && cells[row - 1][col - 1] == 1) {
            count++;
        }
        // check top
        if (row != 0 && cells[row - 1][col] == 1) {
            count++;
        }
        // check top right
        if (row != 0 && col != gridSize - 1 && cells[row - 1][col + 1] == 1) {
            count++;
        }
        // check left
        if (col != 0 && cells[row][col - 1] == 1) {
            count++;
        }
        // check right
        if (col != gridSize - 1 && cells[row][col + 1] == 1) {
            count++;
        }
        // check bot left
        if (row != gridSize - 1 && col != 0 && cells[row + 1][col - 1] == 1) {
            count++;
        }
        // check bot
        if (row != gridSize - 1 && cells[row + 1][col] == 1) {
            count++;
        }
        // check bot right
        if (row != gridSize - 1 && col != gridSize - 1 && cells[row + 1][col + 1] == 1) {
            count++;
        }
        return count;
    }


    /**
     * method to update dead cells into live cells
     */
    public void nextGen() {

        int numNeighbors;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                newCells[i][j] = cells[i][j];
            }
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                numNeighbors = checkNeighbors(i, j);
                // if dead has 3 live neighbors, dead cell becomes alive
                if (numNeighbors == 3 && cells[i][j] == 0) {
                    newCells[i][j] = 1;
                }
                // if dead cell does not have == 3 live neighbors, it remains dead
                if (numNeighbors != 3 && cells[i][j] == 0) {
                    continue;
                }
                // if a live cell has 2 or 3 neighbors, remains alive
                if (numNeighbors == 2 || numNeighbors == 3 && cells[i][j] == 1) {
                    continue;
                }
                // if a live cell has zero or one neighbor, it dies. Loneliness.
                if (numNeighbors == 0 || numNeighbors == 1 && cells[i][j] == 1) {
                    newCells[i][j] = 0;
                }
                // if a live cell has 4 or more live neighbors, it dies, overcrowding.
                if (numNeighbors >= 4 && cells[i][j] == 1) {
                    newCells[i][j] = 0;
                }
            }
        }
        // looping iteration that sets old generation to the new generation
        for (int i = 0; i < newCells.length; i++) {
            for (int j = 0; j < newCells[i].length; j++) {
                cells[i][j] = newCells[i][j];
            }
        }
    }
}
