import javax.swing.JFrame;
import java.awt.Insets;
import java.util.concurrent.TimeUnit;

public class Game {
    private int numRows;
    private int numColumns;
    private int cellSize;
    private int iterations;
    private String frameTitle;
    private int tickDelay;

    private int frameWidth;
    private int frameHeight;

    private JFrame frame;
    private Insets insets;
    private Board board;

    public Game(int numRows,int numColumns,int cellSize,int iterations,String frameTitle,int tickDelay){
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.cellSize = cellSize;
        this.iterations = iterations;
        this.frameTitle = frameTitle;
        this.tickDelay = tickDelay;

        this.frameWidth = this.numColumns*this.cellSize;
        this.frameHeight = this.numRows*this.cellSize;

        this.frame = new JFrame();
        this.frame.setSize(this.frameWidth,this.frameHeight);
        this.frame.setTitle(this.frameTitle);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.insets = this.frame.getInsets();
        this.frame.setSize(this.frameWidth+this.insets.left+this.insets.right-2,this.frameHeight+this.insets.top+this.insets.bottom-2);
        this.board = new Board(this.numRows, this.numColumns, this.cellSize);
    }

    public void displayBoard(){
        this.frame.add(this.board);
        this.frame.setVisible(true);
    }

    private void advanceOneTick(){
        this.board.advanceBoard();
        this.board.repaint();
        try{
            TimeUnit.MILLISECONDS.sleep(this.tickDelay);
        } catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    public void runGame(){
        this.displayBoard();
        if(this.iterations<0){
            while(true){
                this.advanceOneTick();
            }
        }
        else{
            for(int n = 0; n<this.iterations;n++){
                this.advanceOneTick();
            }
        }
    }
}