import javax.swing.*;
import java.awt.*;

/**
 * class to implement a GUI for Conway's Game of Life
 * @author Aaron Howe
 * @version JDK 17
 */
public class LifeOutput extends JPanel {

    private int gridSize = 19;

    int[][] cells = new int[][]
            {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            };

    String[][] newCells = new String[19][19];

    // initialize the window using JFrame
    private JFrame lifeFrame;
    // Instantiation of the JLabel
    private JLabel label = new JLabel();
    // instantiation of the window panel
    private JPanel lifePanel = new JPanel();
    // counter for button pressing
    private int counter = 0;

    /**
     * Default constructor for the state of the window
     */
    public LifeOutput() {
        lifeFrame = new JFrame();
        lifeFrame.setTitle("Conway's Game of Life");
        lifeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lifeFrame.setLocation(100, 100);
        lifeFrame.setSize(500, 500);
        lifeFrame.setVisible(true);
    }

    public LifeOutput(String[][] str) {
        this();
        Dimension size = getSize();
        if (str.length * 20 + 50 > size.width || str.length * 20 + 50 > size.height) {
            setSize(size.height + 50, size.width + 50);
        }
        // instantiate blank string object
        String board = "";
        // translate integer array data into strings
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                switch (cells[i][j]) {
                    case 0: board += "." + " ";
                        setVisible(true);
                        break;
                    case 1: board += "O" + " ";
                        setVisible(true);
                        break;
                }
            }
            // appends a new line for every 19 elements in a single row, return to inner loop
            board += "\n";
        }
    }

    /**
     * String constructor to build the grid of cells
     * @param str two-dimensional string array
     * @return the 2D array
     */
    public String[][] grid(String[][] str) {
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str.length; j++) {
                str[i][j] = ".";
            }
        }
        return str;
    }

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
    public String[][] nextGen(String[][] str, int[][] cells) {

        int numNeighbors;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                newCells[i][j] = String.valueOf(cells[i][j]);
            }
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                numNeighbors = checkNeighbors(i, j);
                // if dead has 3 live neighbors, dead cell becomes alive
                if (numNeighbors == 3 && cells[i][j] == 0) {
                    newCells[i][j] = String.valueOf(1);
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
                    newCells[i][j] = String.valueOf(0);
                }
                // if a live cell has 4 or more live neighbors, it dies, overcrowding.
                if (numNeighbors >= 4 && cells[i][j] == 1) {
                    newCells[i][j] = String.valueOf(0);
                }
            }
        }
        // looping iteration that sets old generation to the new generation
        for (int i = 0; i < newCells.length; i++) {
            for (int j = 0; j < newCells[i].length; j++) {
                cells[i][j] = Integer.parseInt(newCells[i][j]);
            }
        }
        return str;
    }
}
