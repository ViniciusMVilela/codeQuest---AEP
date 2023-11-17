package game.pieces;

import boardgame.Board;
import game.GamePiece;
import game.Color;

public class Barrier extends GamePiece {
    public Barrier(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "|";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColums()];
        return mat;
    }
}
