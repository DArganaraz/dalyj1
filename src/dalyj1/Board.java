package dalyj1;

import aiproj.hexifence.*;

public class Board {
	
	public char[][] state;
	public Cell[][] cells;
	public int stateSize, cellsSize;
	public char[][] pieces = new char[3][2];

	public Board(int n) {
		
		pieces[Piece.BLUE][Devman.CELL] = Devman.BLUE_CELL;
		pieces[Piece.BLUE][Devman.EDGE] = Devman.BLUE_EDGE;
		pieces[Piece.RED][Devman.CELL] = Devman.RED_CELL;
		pieces[Piece.RED][Devman.EDGE] = Devman.RED_EDGE;
		
		stateSize = 4 * n - 1;
		cellsSize = 2 * n - 1;
		state = new char[stateSize][stateSize];
		cells = new Cell[cellsSize][cellsSize];
		

		// initialise state array
		for (int i = 0; i < stateSize; i++) {
			for (int j = 0; j < stateSize; j++) {

				if (i % 2 != 0 && j % 2 != 0) {
					state[i][j] = '-';
				} else if (i < 2 * n) {
					if (j < 2 * n + i) {
						state[i][j] = '+';
					} else {
						state[i][j] = '-';
					}
				} else {
					if (j > i - 2 * n) {
						state[i][j] = '+';
					} else {
						state[i][j] = '-';
					}
				}

			}
		}

		/*
		 * initialise all the cells (We didn't make the cells that don't exist =
		 * null because it makes if statements easier to use)
		 */
		for (int i = 0; i < cellsSize; i++) {
			for (int j = 0; j < cellsSize; j++) {
				if ((i < n && j >= i + n) || (i >= n && j <= i - n)) {
					cells[i][j] = new Cell();
				} else {
					cells[i][j] = new Cell(i, j);
				}
			}
		}

	}
	
	public void addMove(Move m){
		
	}

	
	public int updateCells(Move m) {
		int capture = Devman.NO;
		int row1=-1, row2=-1, col1=-1, col2=-1;

		if (m.Row % 2 == 0 && m.Col % 2 == 0) {
			row1 = (m.Row - 2) / 2;
			row2 = m.Row / 2;
			col1 = (m.Col - 2) / 2;
			col2 = m.Col / 2;
		} else if (m.Row % 2 != 0 && m.Col % 2 == 0) {
			row1 = (m.Row - 1) / 2;
			row2 = (m.Row - 1) / 2;
			col1 = (m.Col - 2) / 2;
			col2 = m.Col / 2;
		} else if (m.Row % 2 == 0 && m.Col % 2 != 0) {
			row1 = (m.Row - 2) / 2;
			row2 = m.Row / 2;
			col1 = (m.Col - 1) / 2;
			col2 = (m.Col - 1) / 2;
		}

		if (row1 >= 0 && row1 < cellsSize && col1 >= 0 && col1 < cellsSize && cells[row1][col1].addEdge()) {
			capture = Devman.YES;
			state[2*row1 + 1][2*col1 + 1] = pieces[m.P][Devman.CELL];
		}
		if (row2 >= 0 && row2 < cellsSize && col2 >= 0 && col2 < cellsSize && cells[row2][col2].addEdge()) {
			capture = Devman.YES;
			state[2*row2 + 1][2*col2 + 1] = pieces[m.P][Devman.CELL];
		}

		return capture;
	}

}
