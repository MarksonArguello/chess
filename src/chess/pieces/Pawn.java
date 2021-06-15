package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        int row = this.position.getRow();
        int column = this.position.getColumn();
        Position newPosition;
        int direction = (this.getColor() == Color.BLACK)?  1: -1;

        newPosition = new Position(row + direction, column);
        if (getBoard().positionExists(newPosition) && !getBoard().thereIsAPiece(newPosition)) {
            mat[newPosition.getRow()][newPosition.getColumn()] = true;
        }

        for (int i = -1; i < 2; i+=2) {
            newPosition = new Position(row + direction, column + i);
            if (getBoard().positionExists(newPosition) && isThereOpponentPiece(newPosition)) {
                mat[newPosition.getRow()][newPosition.getColumn()] = true;
            }
        }
        return mat;
    }
}
