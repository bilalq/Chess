package cs213.chess.pieces;

import java.util.ArrayList;

import cs213.chess.exceptions.IllegalCoordsException;
import cs213.chess.exceptions.IllegalRankFileException;
import cs213.chess.utils.Helper;

/**
 * @author Bilal Quadri
 *
 */
public abstract class Piece {
	
	protected char color;
	protected char symbol;
	protected char file;
	protected int rank;
	
	protected Piece(char color, char file, int rank) {
		this.color = color;
		this.file = file;
		this.rank = rank;
	}
	
	protected Piece(char color, int i, int j) {
		this.color = color;
		
		try {
			String rankFile = Helper.coordsTofileRank(i, j);
			this.rank = rankFile.charAt(0);
			this.file = rankFile.charAt(1);
			System.out.println("RankFile:" + rankFile);
		} catch (IllegalCoordsException e) {
			e.printStackTrace();
		}
	}
	
	public char getColor() {
		return this.color;
	}
	
	public char getSymbol() {
		return this.symbol;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public char getFile() {
		return this.file;
	}
	
	public String getRankFile(){
		return this.rank + "" + this.file;
	}
	
	public int[] getCoords() {
		try {
			return Helper.fileRankToCoords(this.file, this.rank);
		} catch (IllegalRankFileException e) {
			return null;
		}
	}
	
	public String getIdentifier() {
		return this.color + "" + this.symbol;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public void setFile(char file) {
		this.file = file;
	}
	
	public abstract ArrayList<String> getValidMoves();

	public abstract boolean canMoveTo(String fileRank);

//	public abstract boolean canMoveTo(char rank, int file);
	
//	public abstract boolean canMoveTo(int i, int j);
	
}
