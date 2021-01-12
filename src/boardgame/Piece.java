package boardgame;

public abstract class Piece {
	protected Position position;
	private Board board;

	public Piece(Board board) {
		this.board = board;
	}
	
	public Piece[][] possibleMoves() {
		
	}
	
	public boolean possibleMove(Position position) {
		
	}
	
	public boolean isThereAnyPossibleMove() {
		
	}

	protected Board getBoard() {
		return board;
	}
	
}
