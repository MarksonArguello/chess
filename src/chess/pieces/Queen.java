package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "Q";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        int row = this.position.getRow();
        int column = this.position.getColumn();

        int[] directionX = {-1, -1, 1, 1};
        int[] directionY = {-1, 1, -1, 1};
        Position newPosition = new Position(row, column);

        for (int i = 0; i < 4; i++) {
            newPosition = new Position(row + directionY[i], column + directionX[i]);
            while (getBoard().positionExists(newPosition) && !getBoard().thereIsAPiece(newPosition)) {
                mat[newPosition.getRow()][newPosition.getColumn()] = true;
                newPosition.setColumn(newPosition.getColumn() + directionX[i]);
                newPosition.setRow(newPosition.getRow() + directionY[i]);
            }
            if (getBoard().positionExists(newPosition) && isThereOpponentPiece(newPosition)) {
                mat[newPosition.getRow()][newPosition.getColumn()] = true;
            }
        }

        //below
        newPosition = new Position(row+1, column);
        while (getBoard().positionExists(newPosition) && !getBoard().thereIsAPiece(newPosition)) {
            mat[row][column] = true;
            row++;
            newPosition.setRow(row);
        }
        if (getBoard().positionExists(newPosition) && isThereOpponentPiece(newPosition)) {
            mat[row][column] = true;
        }

        //above
        row = this.position.getRow() - 1;
        column = this.position.getColumn();
        newPosition = new Position(row, column);
        while (getBoard().positionExists(newPosition) && !getBoard().thereIsAPiece(newPosition)) {
            mat[row][column] = true;
            row--;
            newPosition.setRow(row);
        }
        if (getBoard().positionExists(newPosition) && isThereOpponentPiece(newPosition)) {
            mat[row][column] = true;
        }

        //right
        row = this.position.getRow();
        column = this.position.getColumn() + 1;
        newPosition = new Position(row, column);
        while (getBoard().positionExists(newPosition) && !getBoard().thereIsAPiece(newPosition)) {
            mat[row][column] = true;
            column++;
            newPosition.setColumn(column);
        }
        if (getBoard().positionExists(newPosition) && isThereOpponentPiece(newPosition)) {
            mat[row][column] = true;
        }

        //left
        row = this.position.getRow();
        column = this.position.getColumn() - 1;
        newPosition = new Position(row, column);
        while (getBoard().positionExists(newPosition) && !getBoard().thereIsAPiece(newPosition)) {
            mat[row][column] = true;
            column--;
            newPosition.setColumn(column);
        }
        if (getBoard().positionExists(newPosition) && isThereOpponentPiece(newPosition)) {
            mat[row][column] = true;
        }

        return mat;
    }
}
