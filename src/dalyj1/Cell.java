package dalyj1;


public class Cell {
	public final static int NUM_EDGES = 6;
	public boolean exists;
	
	public int edgesFree = NUM_EDGES;
	
	// create a cell that isn't actually part of the board
	public Cell() {
		this.exists = false;
	}

	/*
	 * create a cell that is part of the board and set the row and col to the
	 * values that this cell belongs to in the array
	 */
	public Cell(int row, int col) {
		this.exists = true;
	}
	
	//returns true if the cell is full 
	public boolean addEdge(){
		
		if(!this.exists) return false;
		
		edgesFree--;
		
		if(edgesFree == 0) return true;
		
		return false;
	}

}