package chess;

import boardgame.Position;

public class ChessPosition {
	private int row;
	private char column;
	
	public ChessPosition(int row, char column) {
		if (row < 1 || row > 8 || column < 'a' || column > 'h') {
			throw new ChessException("Erro instanciando ChessPosition."
					+ " row = (" + row + ") col = (" + column + ")" );
		}
		this.row = row;
		this.column = column;
	}
	
	protected Position toPosition() {
		int row = 8 - this.row;
		int column = this.column - 'a';
		return new Position(row, column);
	}
	
	protected static ChessPosition fromPosition(Position position) {
		int row = 8 - position.getRow();
		char column =  'a';
		column += position.getColumn();
		return new ChessPosition(row, column);
	}

	public int getRow() {
		return row;
	}

	public char getColumn() {
		return column;
	}
	
	@Override
	public String toString() {
		return "(" + column + "," + row + ")";
	}
	
}
