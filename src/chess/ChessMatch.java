package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;

	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	public boolean getCheck() {
		return this.check;
	}

	public boolean getCheckMate() {
		return this.checkMate;
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
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

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return this.board.piece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourceChessPosition, ChessPosition targetChessPosition) {
		Position sourcePosition = sourceChessPosition.toPosition();
		Position targetPosition = targetChessPosition.toPosition();
		
		validateSourcePosition(sourcePosition);
		validateTargetPosition(sourcePosition, targetPosition);

		ChessPiece capturedPiece = makeMove(sourcePosition, targetPosition);

		if (testCheck(currentPlayer)) {
			undoMove(sourcePosition, targetPosition, capturedPiece);
			throw new ChessException("Você não pode se colocar em cheque");
		}

		check = testCheck(opponent(currentPlayer));
		checkMate = testCheckMate(opponent(currentPlayer));
		if (!checkMate)
			nextTurn();


		return capturedPiece;
	}
	
	private void validateSourcePosition(Position sourcePosition) {
		if (!board.thereIsAPiece(sourcePosition)) {
			throw new ChessException("Não há peça na posição de origem");
		}
		Piece piece = board.piece(sourcePosition);

		if (this.currentPlayer != ((ChessPiece) piece).getColor()) {
			throw new ChessException("A peça escolhida não é sua");
		}

		if (!piece.isThereAnyPossibleMove()) {
			throw new ChessException("Não há movimentos válidos para a peça de origem");
		}
	}

	private void validateTargetPosition(Position sourcePosition, Position targetPosition) {
		Piece piece = board.piece(sourcePosition);
		if (!piece.possibleMove(targetPosition)) {
			throw new ChessException("Posição de destino não é uma posição válida");
		}
	}
	
	private ChessPiece makeMove(Position sourcePosition, Position targetPosition) {
		ChessPiece piece = (ChessPiece) board.removePiece(sourcePosition);
		piece.increaseMoveCount();
		Piece capturedPiece = board.removePiece(targetPosition);

		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}

		board.placePiece(piece, targetPosition);
		return (ChessPiece) capturedPiece;
	}

	private void undoMove(Position source, Position target, ChessPiece capturedPiece) {
		ChessPiece piece = (ChessPiece) board.removePiece(target);
		piece.decreaseMoveCount();
		board.placePiece(piece, source);

		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(row, column).toPosition());
		piecesOnTheBoard.add(piece);
	}

	private void nextTurn() {
		this.turn += 1;
		currentPlayer = (this.currentPlayer == Color.WHITE)? Color.BLACK : Color.WHITE;
	}
	
	private void initialSetup() {
		/*
		 * WHITE
		 */
		placeNewPiece('a', 1 ,  new Rook(this.board, Color.WHITE));
		placeNewPiece('b', 1 ,  new Knight(this.board, Color.WHITE));
		placeNewPiece('c', 1 ,  new Bishop(this.board, Color.WHITE));
		placeNewPiece('d', 1 ,  new Queen(this.board, Color.WHITE));
		placeNewPiece('e', 1 , new King(this.board, Color.WHITE));
		placeNewPiece('f', 1 ,  new Bishop(this.board, Color.WHITE));
		placeNewPiece('g', 1 ,  new Knight(this.board, Color.WHITE));
		placeNewPiece('h', 1 ,  new Rook(this.board, Color.WHITE));


		placeNewPiece('a', 2, new Pawn(this.board, Color.WHITE));
		placeNewPiece('b', 2, new Pawn(this.board, Color.WHITE));
		placeNewPiece('c', 2, new Pawn(this.board, Color.WHITE));
		placeNewPiece('d', 2, new Pawn(this.board, Color.WHITE));
		placeNewPiece('e', 2, new Pawn(this.board, Color.WHITE));
		placeNewPiece('f', 2, new Pawn(this.board, Color.WHITE));
		placeNewPiece('g', 2, new Pawn(this.board, Color.WHITE));
		placeNewPiece('h', 2, new Pawn(this.board, Color.WHITE));

		/*
		 * BLACK
		 */
		placeNewPiece('a', 8 ,  new Rook(this.board, Color.BLACK));
		placeNewPiece('b', 8 ,  new Knight(this.board, Color.BLACK));
		placeNewPiece('c', 8 ,  new Bishop(this.board, Color.BLACK));
		placeNewPiece('d', 8 ,  new Queen(this.board, Color.BLACK));
		placeNewPiece('e', 8 , new King(this.board, Color.BLACK));
		placeNewPiece('f', 8 ,  new Bishop(this.board, Color.BLACK));
		placeNewPiece('g', 8 ,  new Knight(this.board, Color.BLACK));
		placeNewPiece('h', 8 ,  new Rook(this.board, Color.BLACK));


		placeNewPiece('a', 7, new Pawn(this.board, Color.BLACK));
		placeNewPiece('b', 7, new Pawn(this.board, Color.BLACK));
		placeNewPiece('c', 7, new Pawn(this.board, Color.BLACK));
		placeNewPiece('d', 7, new Pawn(this.board, Color.BLACK));
		placeNewPiece('e', 7, new Pawn(this.board, Color.BLACK));
		placeNewPiece('f', 7, new Pawn(this.board, Color.BLACK));
		placeNewPiece('g', 7, new Pawn(this.board, Color.BLACK));
		placeNewPiece('h', 7, new Pawn(this.board, Color.BLACK));

	}

	private Color opponent(Color color) {
		return (color == Color.WHITE)? Color.BLACK: Color.WHITE;
	}

	private ChessPiece king(Color color) {
		List<Piece> pieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());

		for (Piece p: pieces) {
			if (p instanceof  King) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("Não há rei da cor " + color + " no tabuleiro");
	}

	private boolean testCheck(Color color) {
		ChessPiece king = king(color);
		Position kingPosition = king.getChessPosition().toPosition();

		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());

		for (Piece p : opponentPieces) {
			if (p.possibleMove(kingPosition)) {
				return true;
			}
		}
		return false;
	}

	private boolean testCheckMate(Color color) {
		List<Piece> pieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
		for (Piece p: pieces) {
			boolean[][] possibleMoves = p.possibleMoves();
			for (int row = 0; row < 8; row++) {
				for (int column = 0; column < 8; column++) {
					if (possibleMoves[row][column]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(row, column);
						ChessPiece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if (!testCheck)
							return false;
					}
				}
			}
		}
		return true;
	}
}
