package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
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

        if (this.getMoveCount() == 0) {
            Position aux = new Position(row + direction, column);
            newPosition = new Position(row + 2 * direction, column);
            if (getBoard().positionExists(newPosition) && !getBoard().thereIsAPiece(newPosition) && getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
                mat[newPosition.getRow()][newPosition.getColumn()] = true;
            }
        }

        for (int i = -1; i < 2; i+=2) {
            newPosition = new Position(row + direction, column + i);
            if (getBoard().positionExists(newPosition) && isThereOpponentPiece(newPosition)) {
                mat[newPosition.getRow()][newPosition.getColumn()] = true;
            }
        }

        if (getColor() == Color.WHITE && position.getRow() == 3) {
            Position left = new Position(position.getRow(), position.getColumn() - 1);
            if (getBoard().positionExists(left) && isThereOpponentPiece(left) && chessMatch.getEnPassantVulnerable() == getBoard().piece(left)) {
                mat[left.getRow() - 1][left.getColumn()] = true;
            }

            Position right = new Position(position.getRow(), position.getColumn() + 1);
            if (getBoard().positionExists(right) && isThereOpponentPiece(right) && chessMatch.getEnPassantVulnerable() == getBoard().piece(right)) {
                mat[right.getRow() - 1][right.getColumn()] = true;
            }
        }

        if (getColor() == Color.BLACK && position.getRow() == 4) {
            Position left = new Position(position.getRow(), position.getColumn() - 1);
            if (getBoard().positionExists(left) && isThereOpponentPiece(left) && chessMatch.getEnPassantVulnerable() == getBoard().piece(left)) {
                mat[left.getRow() + 1][left.getColumn()] = true;
            }

            Position right = new Position(position.getRow(), position.getColumn() + 1);
            if (getBoard().positionExists(right) && isThereOpponentPiece(right) && chessMatch.getEnPassantVulnerable() == getBoard().piece(right)) {
                mat[right.getRow() + 1][right.getColumn()] = true;
            }
        }
        return mat;
    }
}
