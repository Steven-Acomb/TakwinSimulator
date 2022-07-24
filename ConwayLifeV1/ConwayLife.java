package ConwayLifeV1;

import javax.swing.JFrame;
import java.awt.Insets;

public class ConwayLife {
    public static void main(String args[]) {
        int numRows = 500;
        int numColumns = 500;
        int cellSize = 2;
        int iterations = -1;
        int tickDelay = 2;
        String frameTitle = "Conway's Game of Life";

        Game game = new Game(numRows,numColumns,cellSize,iterations,frameTitle,tickDelay);
        game.runGame();
    }
}