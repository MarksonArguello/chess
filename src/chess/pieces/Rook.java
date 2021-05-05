package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);

	}
	
	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		int row = this.position.getRow() + 1;
		int column = this.position.getColumn();

		//below
		Position newPosition = new Position(row, column);
		while (getBoard().positionExists(newPosition) && !getBoard().thereIsAPiece(newPosition)) {
			mat[row][column] = true;
			row++;
			newPosition.setRow(row);
		}
		if (getBoard().positionExists(newPosition) && isThereOpponentPiece(newPosition)) {
			mat[row][column] = true;
		}

		//above
		row = this.position.getRow() - 1;
		column = this.position.getColumn();
		newPosition = new Position(row, column);
		while (getBoard().positionExists(newPosition) && !getBoard().thereIsAPiece(newPosition)) {
			mat[row][column] = true;
			row--;
			newPosition.setRow(row);
		}
		if (getBoard().positionExists(newPosition) && isThereOpponentPiece(newPosition)) {
			mat[row][column] = true;
		}

		//right
		row = this.position.getRow();
		column = this.position.getColumn() + 1;
		newPosition = new Position(row, column);
		while (getBoard().positionExists(newPosition) && !getBoard().thereIsAPiece(newPosition)) {
			mat[row][column] = true;
			column++;
			newPosition.setColumn(column);
		}
		if (getBoard().positionExists(newPosition) && isThereOpponentPiece(newPosition)) {
			mat[row][column] = true;
		}

		//left
		row = this.position.getRow();
		column = this.position.getColumn() - 1;
		newPosition = new Position(row, column);
		while (getBoard().positionExists(newPosition) && !getBoard().thereIsAPiece(newPosition)) {
			mat[row][column] = true;
			column--;
			newPosition.setColumn(column);
		}
		if (getBoard().positionExists(newPosition) && isThereOpponentPiece(newPosition)) {
			mat[row][column] = true;
		}

		return mat;
	}
}
