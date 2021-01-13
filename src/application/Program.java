package application;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		//TESTE
		ChessMatch chessMatch = new ChessMatch();
		UI.printBoard(chessMatch.getPieces());
		ChessPosition chessPosition = new ChessPosition(1, 'a');
		//System.out.print(ChessPosition.fromPosition(new Position(7,7)));
		//FIM TESTE
	}

}
