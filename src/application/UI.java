package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;

public class UI {
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
	
	private static void printPiece(ChessPiece piece) {
		if (piece == null) {
			System.out.print("-");
		}
		else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
