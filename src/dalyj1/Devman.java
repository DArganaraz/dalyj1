package dalyj1;

import java.io.PrintStream;

import aiproj.hexifence.*;

public class Devman implements Player {
	public static final int ERROR = -1;
	public static final int SUCCESS = 1;
	public static final int EDGES_PER_CELL = 6;
	public static final int ADJACENT_CELLS = 2;
	public static final char BLUE_EDGE = 'B';
	public static final char RED_EDGE = 'R';
	public static final char BLUE_CELL = 'b';
	public static final char RED_CELL = 'r';
	public static final int CELL = 0;
	public static final int EDGE = 1;
	public static final int YES = 1;
	public static final int NO = 0;

	public int myColour, dimension;
	public Board board;

	public int init(int n, int p) {
		/*
		 * n = dimension of board. 
		 * p = piece that player will use
		 */

		// check for possible errors
		if (n <= 0) {
			return ERROR;
		} else if (p != Piece.BLUE && p != Piece.RED) {
			return ERROR;
		}
		
		board = new Board(n);
		dimension = n;
		
		myColour = p;
		return SUCCESS;
	}

	public Move makeMove() {
		Move ourMove = new Move();

		for (int i = 0; i < board.stateSize; i++) {
			for (int j = 0; j < board.stateSize; j++) {
				if (board.state[i][j] == '+') {
					
					ourMove.Col = j;
					ourMove.Row = i;
					ourMove.P = myColour;
					board.state[i][j] = board.pieces[myColour][EDGE];
					board.updateCells(ourMove);
					
					i = board.stateSize;
					j = board.stateSize;
				}
			}
		}

		return ourMove;
	}

	public int opponentMove(Move m) {

		/*
		 * return -1 if invalid move 
		 * return 0 if valid move but no capture
		 * return 1 if move made a capture
		 */

		if (m.P == myColour) {
			return Piece.INVALID;
		} else if (board.state[m.Row][m.Col] != '+') {
			return Piece.INVALID;
		}

		board.state[m.Row][m.Col] = board.pieces[m.P][EDGE];
		
		
		return board.updateCells(m);
	}

	public int getWinner() {

		int redCount = 0, blueCount = 0;

		for (int i = 0; i < board.stateSize; i++) {
			for (int j = 0; j < board.stateSize; j++) {

				if (board.state[i][j] == '+') {
					return Piece.EMPTY;
				} else if (board.state[i][j] == RED_CELL) {
					redCount++;
				} else if (board.state[i][j] == BLUE_CELL) {
					blueCount++;
				}
			}
		}

		if (redCount == blueCount) {
			return Piece.DEAD;
		} else if (redCount < blueCount) {
			return Piece.BLUE;
		} else {
			return Piece.RED;
		}
	}

	public void printBoard(PrintStream output) {

		for (int i = 0; i < board.stateSize; i++) {
			for (int j = 0; j < board.stateSize; j++) {
				output.print(board.state[i][j] + " ");
			}
			output.println();
		}

	}

	public static void main(String[] args) {
		Move move = new Move();
		Move move1 = new Move();
		Move move2 = new Move();
		Move move3 = new Move();
		Move move4 = new Move();
		Move move5 = new Move();
		Move move6 = new Move();
		Move move7 = new Move();
		Move move8 = new Move();
		Move move9 = new Move();
		
		move.Row = 1;
		move.Col = 2;
		move.P = Piece.BLUE;

		move1.Row = 0;
		move1.Col = 1;
		move1.P = Piece.BLUE;

		move2.Row = 2;
		move2.Col = 1;
		move2.P = Piece.BLUE;

		move3.Row = 2;
		move3.Col = 2;
		move3.P = Piece.BLUE;

		move4.Row = 1;
		move4.Col = 0;
		move4.P = Piece.BLUE;
		
		move5.Row = 0;
		move5.Col = 2;
		move5.P = Piece.BLUE;
		move6.Row = 0;
		move6.Col = 3;
		move6.P = Piece.BLUE;
		move7.Row = 1;
		move7.Col = 4;
		move7.P = Piece.BLUE;
		move8.Row = 2;
		move8.Col = 3;
		move8.P = Piece.BLUE;
		move9.Row = 2;
		move9.Col = 4;
		move9.P = Piece.BLUE;
		
		Devman me = new Devman();

		me.init(3, Piece.RED);

		me.printBoard(System.out);

		me.makeMove();
		System.out.println();
		System.out.println();
		System.out.println();
		me.printBoard(System.out);

		me.opponentMove(move1);
		me.opponentMove(move2);
		me.opponentMove(move3);
		me.opponentMove(move4);
		me.opponentMove(move5);
		me.opponentMove(move6);
		me.opponentMove(move7);
		me.opponentMove(move8);
		me.opponentMove(move9);
		
		System.out.println();
		System.out.println();
		System.out.println();
		me.printBoard(System.out);
		
		me.opponentMove(move);
		System.out.println();
		System.out.println();
		System.out.println();
		me.printBoard(System.out);

		System.out.println(me.getWinner());
	}

}
