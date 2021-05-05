package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{
	
	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		return (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position));
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		int row = this.position.getRow();
		int column = this.position.getColumn();
		Position position = new Position(row, column);

		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = column - 1; j <= column + 1; j++) {
				position.setValues(i, j);
				if (i == row && j == column) continue;
				if (getBoard().positionExists(position) && canMove(position)) {
					mat[i][j] = true;
				}
			}
		}

		return mat;
	}
}
