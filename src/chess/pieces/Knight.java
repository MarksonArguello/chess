package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "N";
    }


    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        int row = this.position.getRow();
        int column = this.position.getColumn();
        Position newPosition = new Position(row, column);
        int[] longDirection = {-2,2};
        int[] shortDirection = {-1,1};

        for (int i = 0; i < 2; i++) {
            newPosition.setRow(row+longDirection[i]);
            for (int j = 0; j < 2; j++) {
                newPosition.setColumn(column+shortDirection[j]);
                if (getBoard().positionExists(newPosition)) {
                    if (!getBoard().thereIsAPiece(newPosition) || isThereOpponentPiece(newPosition))
                        mat[newPosition.getRow()][newPosition.getColumn()] = true;
                }
            }

        }

        for (int i = 0; i < 2; i++) {
            newPosition.setColumn(column+longDirection[i]);
            for (int j = 0; j < 2; j++) {
                newPosition.setRow(row+shortDirection[j]);
                if (getBoard().positionExists(newPosition)) {
                    if (!getBoard().thereIsAPiece(newPosition) || isThereOpponentPiece(newPosition))
                        mat[newPosition.getRow()][newPosition.getColumn()] = true;
                }
            }

        }
        return mat;
    }
}
