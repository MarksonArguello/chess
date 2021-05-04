package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

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
	
	public ChessPiece performChessMove(ChessPosition sourceChessPosition, ChessPosition targetChessPosition) {
		Position sourcePosition = sourceChessPosition.toPosition();
		Position targetPosition = targetChessPosition.toPosition();
		
		validateSourcePosition(sourcePosition);
		ChessPiece capturedPiece = makeMove(sourcePosition, targetPosition);
		
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position sourcePosition) {
		if (!board.thereIsAPiece(sourcePosition)) {
			throw new ChessException("Não há peça na posição de origem");
		}
		Piece piece = board.piece(sourcePosition);
		if (!piece.isThereAnyPossibleMove()) {
			throw new ChessException("Não há movimentos válidos para a peça de origem");
		}
	}
	
	private ChessPiece makeMove(Position sourcePosition, Position targetPosition) {
		Piece piece = board.removePiece(sourcePosition);
		Piece capturedPiece = board.removePiece(targetPosition);
		
		board.placePiece(piece, targetPosition);
		return (ChessPiece) capturedPiece;
	}
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(row, column).toPosition());
	}
	
	private void initialSetup() {
		/*
		 * WHITE
		 */
		King whiteKing = new King(this.board, Color.WHITE);
		placeNewPiece('e', 1 , whiteKing);
		
		Rook whiteRook= new Rook(this.board, Color.WHITE);
		placeNewPiece('a', 1 , whiteRook);
		placeNewPiece('a', 8 , whiteRook);
		
		
		/*
		 * BLACK
		 */
		King blackKing = new King(this.board, Color.BLACK);
		placeNewPiece('e', 8 , blackKing);
		
		Rook blackRook= new Rook(this.board, Color.BLACK);
		placeNewPiece('h', 1 , blackRook);
		placeNewPiece('h', 8 , blackRook);
	}
}
