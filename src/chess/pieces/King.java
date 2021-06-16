package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{
	private ChessMatch chessMatch;
	
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		return (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position));
	}

	private boolean testRookCastling(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().piece(position);
		return piece instanceof Rook && piece.getMoveCount() == 0 && piece.getColor() == getColor();
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


		//Castling
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			//king side
			Position rookPosition = new Position(this.position.getRow(), this.position.getColumn() + 3);
			if (testRookCastling(rookPosition)) {
				Position p1 = new Position(this.position.getRow(), this.position.getColumn() + 1);
				Position p2 = new Position(this.position.getRow(), this.position.getColumn() + 2);

				if (getBoard().positionExists(p1) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p1) && !getBoard().thereIsAPiece(p2)
					&& chessMatch.positionFreeCheck(this.position, p1) && chessMatch.positionFreeCheck(this.position, p2))
					mat[this.position.getRow()][this.position.getColumn() + 2] = true;
			}
			//Queen side
			rookPosition = new Position(this.position.getRow(), this.position.getColumn() - 4);
			if (testRookCastling(rookPosition)) {
				Position p1 = new Position(this.position.getRow(), this.position.getColumn() - 1);
				Position p2 = new Position(this.position.getRow(), this.position.getColumn() - 2);
				Position p3 = new Position(this.position.getRow(), this.position.getColumn() - 3);
				if (getBoard().positionExists(p1) && getBoard().positionExists(p2) && getBoard().positionExists(p3)
						&& !getBoard().thereIsAPiece(p1) && !getBoard().thereIsAPiece(p2) && !getBoard().thereIsAPiece(p3)
						&& chessMatch.positionFreeCheck(this.position, p1) && chessMatch.positionFreeCheck(this.position, p2) && chessMatch.positionFreeCheck(this.position, p3))
					mat[this.position.getRow()][this.position.getColumn() - 2] = true;
			}
		}
		return mat;
	}
}
