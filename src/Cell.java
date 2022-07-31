import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Cell {

    private int xOrigin;
	private int yOrigin;
    private int height;
	private int width;

	private Cell north;
	private Cell ne;
	private Cell east;
	private Cell se;
	private Cell south;
	private Cell sw;
	private Cell west;
	private Cell nw;
	private int neighborSum;

	private int value = 0;
	private Color color = Color.BLACK;
	private int cellRow;
	private int cellColumn;

    public Cell(int x, int y, int width, int height, int i, int j) {
		this.xOrigin = x;
		this.yOrigin = y;
        this.height = height;
        this.width = width;
		this.cellRow = i;
		this.cellColumn = j;		
	}

	public int getNeighborSum(){
		this.neighborSum = this.north.getValue()+this.ne.getValue()+this.east.getValue()+this.se.getValue()+this.south.getValue()+this.sw.getValue()+this.west.getValue()+this.nw.getValue();
		return this.neighborSum;
	}

	public int getValue(){
		return this.value;
	}

	public int getRow(){
		return this.cellRow;
	}

	public int getColumn(){
		return this.cellColumn;
	}

	public void setValue(int value){
		this.value = value;
		this.color = new Color(255*this.value,255*this.value,255*this.value);
	}

	public void setNeighbors(Cell north,Cell ne,Cell east,Cell se,Cell south,Cell sw,Cell west,Cell nw){
		this.north = north;
		this.ne = ne;
		this.east = east;
		this.se = se;
		this.south = south;
		this.sw = sw;
		this.west = west;
		this.nw = nw;
	}

    public void drawOn(Graphics2D g2) {
		Rectangle box = new Rectangle(this.xOrigin,this.yOrigin,this.width,this.height);
		g2.setColor(this.color);
		g2.fill(box);
	}
}