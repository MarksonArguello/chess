package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }


    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        int row = this.position.getRow();
        int column = this.position.getColumn();
        Position newPosition;
        int[] directionX = {-1, -1, 1, 1};
        int[] directionY = {-1, 1, -1, 1};

        for (int i = 0; i < 4; i++) {
            newPosition = new Position(row + directionY[i], column + directionX[i]);
            while (getBoard().positionExists(newPosition) && !getBoard().thereIsAPiece(newPosition)) {
                mat[newPosition.getRow()][newPosition.getColumn()] = true;
                newPosition.setValues(newPosition.getRow() + directionY[i], newPosition.getColumn() + directionX[i]);
            }
            if (getBoard().positionExists(newPosition) && isThereOpponentPiece(newPosition)) {
                mat[newPosition.getRow()][newPosition.getColumn()] = true;
            }
        }
        return mat;
    }
}
