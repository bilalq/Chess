package cs213.chess.pieces;

import java.util.ArrayList;

import cs213.chess.controls.Board;
import cs213.chess.exceptions.IllegalFileRankException;

/**
 * @author Bilal Quadri
 *
 */
public class Pawn extends Piece {

	/**
	 * Constructor for Pawn.
	 *
	 * @param color
	 * @param file
	 * @param rank
	 * @param board
	 */
	public Pawn(char color, char file, int rank, Board board) {
		super(color, file, rank, board);
		this.symbol = 'p';
	}


	/* (non-Javadoc)
	 * @see cs213.chess.pieces.Piece#getValidMoves()
	 */
	@Override
	public ArrayList<String> getValidMoves() {
		ArrayList<String> validMoves = new ArrayList<String>();
		String move;
		Piece square;

		String leftDiag, rightDiag;
		
//		String leftPasserby, rightPasserby;
//		leftPasserby = this.prevFile() + "" + this.rank;
//		rightPasserby = this.nextFile() + "" + this.rank;
		
		if (this.color == 'w') {
			// White

			leftDiag = this.prevFile() + "" + this.nextRank();
			rightDiag = this.nextFile() + "" + this.nextRank();
			
			// Moving forward
			move = this.file + "" + (this.rank + 1);
			try {
				square = this.board.getPieceAt(move);
				if (square == null) {
					validMoves.add(move);
					if (! this.hasMoved()) {
						move = this.file + "" + (this.rank + 2);
						square = this.board.getPieceAt(move);
						if (square == null) {
							validMoves.add(move);
						}
					}
				}
			} catch (IllegalFileRankException e) {}
			
			
		} else {
            // Black
			
			leftDiag = this.prevFile() + "" + this.prevRank();
			rightDiag = this.nextFile() + "" + this.prevRank();

			// Moving forward
			move = this.file + "" + (this.rank - 1);
			try {
				square = this.board.getPieceAt(move);
				if (square == null) {
					validMoves.add(move);
					if (! this.hasMoved()) {
						move = this.file + "" + (this.rank - 2);
						square = this.board.getPieceAt(move);
						if (square == null) {
							validMoves.add(move);
						}
					}
				}
			} catch (IllegalFileRankException e) {}

		}
		
		// Add diagonal moves for captures
		try { 
			square = this.board.getPieceAt(leftDiag);
			if (square != null && square.color != this.color) {
				validMoves.add(leftDiag);
			}
		} catch (IllegalFileRankException e) {}
		try { 
			square = this.board.getPieceAt(rightDiag);
			if (square != null && square.color != this.color) {
				validMoves.add(rightDiag);
			}
		} catch (IllegalFileRankException e) {}
		
		// Detect En Passant
		try {
			String epSquare = this.board.getEnPassantableSquare();
			if (epSquare != null) {
				Piece epTarget = this.board.getPieceAt(epSquare);
				if (epTarget != null && this.rank == epTarget.getRank()) {
					int epRank = (this.color == 'w') ? 6 : 3;
					move = epTarget.getFile() + "" + epRank;
					validMoves.add(move);
				}
			}
		} catch (IllegalFileRankException e) {}

		return validMoves;
	}

}
