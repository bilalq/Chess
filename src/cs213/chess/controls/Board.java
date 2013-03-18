package cs213.chess.controls;

import cs213.chess.exceptions.IllegalCoordsException;
import cs213.chess.exceptions.IllegalFileRankException;
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
		
		// Place pawns
		for	(int i = 0; i < this.pieces.length; i++) {
			this.pieces[1][i] = new Pawn('w', i, 1);
			this.pieces[6][i] = new Pawn('b', i, 6);
		}
		
		// Place kings
		this.pieces[0][4] = new King('w', 'e', 1);
		this.pieces[7][4] = new King('b', 'e', 8);
		
		// Place queens
		this.pieces[0][3] = new Queen('w', 'd', 1);
		this.pieces[7][3] = new Queen('b', 'd', 1);
		
		// Place bishops
		this.pieces[0][2] = new Bishop('w', 'c', '1');
		this.pieces[0][5] = new Bishop('w', 'f', '1');
		this.pieces[7][2] = new Bishop('b', 'c', '8');
		this.pieces[7][5] = new Bishop('b', 'f', '8');
		
		// Place knights
		this.pieces[0][1] = new Knight('w', 'b', '1');
		this.pieces[0][6] = new Knight('w', 'g', '1');
		this.pieces[7][1] = new Knight('b', 'b', '8');
		this.pieces[7][6] = new Knight('b', 'g', '8');
		
		// Place rooks
		this.pieces[0][0] = new Rook('w', 'a', '1');
		this.pieces[0][7] = new Rook('w', 'h', '1');
		this.pieces[7][0] = new Rook('b', 'a', '8');
		this.pieces[7][7] = new Rook('b', 'h', '8');
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
