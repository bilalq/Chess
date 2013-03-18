package cs213.chess.controls;

import cs213.chess.exceptions.IllegalCoordsException;
import cs213.chess.pieces.*;
import cs213.chess.utils.Helper;

/**
 * @author Bilal Quadri
 *
 */
public class Board {

	private Piece[][] pieces;
	
	
	/**
	 * Initializes the board with pieces in their default locations.
	 */
	public Board() {
		this.pieces = new Piece[8][8];
		
		// Place white pawns
		for	(int i = 0; i < this.pieces.length; i++) {
			this.pieces[1][i] = new Pawn('w', i, 1);
		}
		
		// Place black pawns
		for	(int i = 0; i < this.pieces.length; i++) {
			this.pieces[6][i] = new Pawn('b', i, 6);
		}
	}

	
	/**
	 * @return the pieces
	 */
	public Piece[][] getPieces() {
		return pieces;
	}

	
	/**
	 * @param pieces the pieces to set
	 */
	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
	}

	
	/**
	 * Turn the board into a string that can be displayed in the command line.
	 */
	@Override
	public String toString() {
		String display = "";
		
		for (int i = pieces.length - 1; i >= 0; i--) {
			String row = "";
			for (int j = 0; j < pieces.length; j++) {
				Piece piece = pieces[i][j];
				if (piece == null) {
					try {
						display += Helper.getSquareRepresentation(i, j);
					} catch (IllegalCoordsException e) {
						e.printStackTrace();
						System.out.println("Board is in an invalid state");
					}
				} else {
					display += piece.getIdentifier();
				}
				display += " ";
			}
			display += "\n";
		}
		
		return display;
	}
	
	public boolean isInCheckmate(char turn) {
		return false;
	}
	
	public boolean isInStalemate(char turn) {
		return false;
	}
	
}
