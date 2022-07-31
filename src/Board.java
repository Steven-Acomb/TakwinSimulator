import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.util.Random;

public class Board extends JComponent{
	private int numRows;
	private int numColumns;
	private int cellSize;
	private Cell[][] currentCells;
	private int[][] updatedValues;

	public Board(int numRows, int numColumns, int cellSize){
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.cellSize = cellSize;
		this.currentCells = new Cell[this.numRows][this.numColumns];
		this.updatedValues = new int[this.numRows][this.numColumns];

		this.setInitialState();
	}

	public void advanceBoard(){
		this.evaluateRules();
		this.updateBoard();
		// this.printAscii();
	}

	public void printAscii(){
		for(int i = 0;i<this.numRows;i++) {
			for(int j = 0;j<this.numColumns;j++) {
				if(this.currentCells[i][j].getValue() == 1)
					System.out.print(" # ");
				else
					System.out.print("   ");
				if(j == this.numColumns - 1)
					System.out.println("");
			}
		}
		System.out.println("");
	}

	public void updateBoard(){
		for(int i = 0;i<this.numRows;i++){
			for(int j = 0;j<this.numColumns;j++){
				this.currentCells[i][j].setValue(this.updatedValues[i][j]);
			}
		}
	}

	public void evaluateRules(){
		int neighborSum;
		for(int i = 0;i<this.numRows;i++) {
			for(int j = 0;j<this.numColumns;j++) {
				neighborSum = this.currentCells[i][j].getNeighborSum();
				// System.out.print("[(" + neighborSum +")");

				//test conditions
				if((neighborSum == 3)||(currentCells[i][j].getValue()>0 && neighborSum == 2)){
					this.updatedValues[i][j] = 1;
				}
				else{
					this.updatedValues[i][j] = 0;
				}
			}
		}
	}

	public void setCellNeighbors(Cell center){
		Cell west = currentCells[center.getRow()][(center.getColumn()+this.numColumns-1)%(this.numColumns)];
		Cell sw = currentCells[(center.getRow()+this.numRows+1)%(this.numRows)][(center.getColumn()+this.numColumns-1)%(this.numColumns)];
		Cell south = currentCells[(center.getRow()+this.numRows+1)%(this.numRows)][center.getColumn()];
		Cell se = currentCells[(center.getRow()+this.numRows+1)%(this.numRows)][(center.getColumn()+this.numColumns+1)%(this.numColumns)];
		Cell east = currentCells[center.getRow()][(center.getColumn()+this.numColumns+1)%(this.numColumns)];
		Cell ne = currentCells[(center.getRow()+this.numRows-1)%(this.numRows)][(center.getColumn()+this.numColumns+1)%(this.numColumns)];
		Cell north = currentCells[(center.getRow()+this.numRows-1)%(this.numRows)][center.getColumn()];
		Cell nw = currentCells[(center.getRow()+this.numRows-1)%(this.numRows)][(center.getColumn()+this.numColumns-1)%(this.numColumns)];
		center.setNeighbors(north, ne, east, se, south, sw, west, nw);
	}

	public void setInitialState(){
        for(int i = 0;i<this.numRows;i++){
			for(int j = 0;j<this.numColumns;j++){
				if((i+j)%2==0)
					this.currentCells[i][j] = new Cell(i*this.cellSize,j*this.cellSize,this.cellSize,this.cellSize,i,j);
				else
					this.currentCells[i][j] = new Cell(i*this.cellSize,j*this.cellSize,this.cellSize,this.cellSize,i,j);
			}
		}
		for(int i = 0;i<this.numRows;i++){
			for(int j = 0;j<this.numColumns;j++){
				this.setCellNeighbors(this.currentCells[i][j]);
			}
		}
        this.setInitialPattern();
    }

	public void setInitialPattern(){
		//corner blocks
        // this.currentCells[0][0].setValue(1);
        // this.currentCells[this.numRows-1][0].setValue(1);
        // this.currentCells[0][this.numColumns-1].setValue(1);
        // this.currentCells[this.numRows-1][this.numColumns-1].setValue(1);

        //beehive
        // this.currentCells[2][2].setValue(1);
        // this.currentCells[2][3].setValue(1);
        // this.currentCells[3][1].setValue(1);
        // this.currentCells[3][4].setValue(1);
        // this.currentCells[4][2].setValue(1);
        // this.currentCells[4][3].setValue(1);

        //glider 1
        // this.currentCells[1][3].setValue(1);
        // this.currentCells[2][4].setValue(1);
        // this.currentCells[3][2].setValue(1);
        // this.currentCells[3][3].setValue(1);
        // this.currentCells[3][4].setValue(1);

		//glider
		// this.currentCells[5][3].setValue(1);
        // this.currentCells[6][4].setValue(1);
        // this.currentCells[7][2].setValue(1);
        // this.currentCells[7][3].setValue(1);
        // this.currentCells[7][4].setValue(1);

		//random
		Random rand = new Random();
		for(int i = 0;i<this.numRows;i++){
			for(int j = 0;j<this.numColumns;j++){
				this.currentCells[i][j].setValue(rand.nextInt((1 - 0) + 1) + 0);
			}
		}
	}

    @Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2 = (Graphics2D) graphics;
		for(int i = 0;i<this.numRows;i++){
			for(int j = 0;j<this.numColumns;j++)
				currentCells[i][j].drawOn(graphics2);
		}
	}
}