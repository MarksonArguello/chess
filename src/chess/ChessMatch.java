package chess;

import boardgame.Board;
import boardgame.Position;
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
	
	private void initialSetup() {
		King whiteKing = new King(this.board, Color.WHITE);
		Position whiteKingPosition = new Position(7, 4); // Position e1
		this.board.placePiece(whiteKing, whiteKingPosition);
		
		King blackKing = new King(this.board, Color.BLACK);
		Position blacKingPosition = new Position(0, 4); // Position e8
		this.board.placePiece(blackKing, blacKingPosition);
	}
}
