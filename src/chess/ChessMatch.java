package chess;

import boardgame.Board;
import chess.pieces.King;

public class ChessMatch {
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] matrix = new ChessPiece[board.getRows()][board.getColumns()];
		
		for (int row = 0; row < board.getRows(); row++) {
			for (int column = 0; column < board.getColumns(); column++) {
				matrix[row][column] = (ChessPiece) board.piece(row, column);
			}
		}
		
		return matrix;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(row, column).toPosition());
	}
	
	private void initialSetup() {
		King whiteKing = new King(this.board, Color.WHITE);
		placeNewPiece('e', 1 , whiteKing);
		
		King blackKing = new King(this.board, Color.BLACK);
		placeNewPiece('e', 8 , blackKing);
	}
}
