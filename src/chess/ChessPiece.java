package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}

	protected boolean isThereOpponentPiece(Position position) {
		if (getBoard().thereIsAPiece(position)) {
			ChessPiece piece = (ChessPiece) this.getBoard().piece(position);
			return piece.getColor() != this.getColor();
		}
		return false;
	}

	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(super.position);
	}
}
