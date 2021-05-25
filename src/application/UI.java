package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String line = sc.nextLine();
			char column = line.charAt(0);
			int row     = Integer.parseInt(line.substring(1));
			return new ChessPosition(row, column);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro lendo ChessPosition. Valores válidos de a1 à h8");
		}
		
	}
	
	public static void printBoard(ChessPiece[][] pieces) {
		for (int row = 0; row < pieces.length; row++) {
			int currentBoardRow = pieces.length - row;
			System.out.print(currentBoardRow + " ");
			
			for (int column = 0; column < pieces[row].length; column++) {
				printPiece(pieces[row][column]);
				
			}
			System.out.println();
			
		}
		
		System.out.print("  ");
		
		int lastRow = pieces.length-1;
		char charCurrentBoardColumn = 'a';
		
		for (int column = 0; column < pieces[lastRow].length; column++) {
			System.out.print(charCurrentBoardColumn + " ");
			charCurrentBoardColumn++;
			
		}
		System.out.println();
		
	}

	public static void printMatch(ChessMatch chessMatch) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		System.out.println("Turno: " + chessMatch.getTurn());
		System.out.println("Cor: " + chessMatch.getCurrentPlayer());
	}

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int row = 0; row < pieces.length; row++) {
			int currentBoardRow = pieces.length - row;
			System.out.print(currentBoardRow + " ");

			for (int column = 0; column < pieces[row].length; column++) {
				printPiece(pieces[row][column], possibleMoves[row][column]);

			}
			System.out.println();

		}

		System.out.print("  ");

		int lastRow = pieces.length-1;
		char charCurrentBoardColumn = 'a';

		for (int column = 0; column < pieces[lastRow].length; column++) {
			System.out.print(charCurrentBoardColumn + " ");
			charCurrentBoardColumn++;

		}
		System.out.println();

	}
	
	private static void printPiece(ChessPiece piece) {
		if (piece == null) {
			System.out.print("-");
		}
		else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE +piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW +piece + ANSI_RESET);
			}

		}
		System.out.print(" ");
	}

	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		}
		else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}

		}
		System.out.print(" ");
	}
}
