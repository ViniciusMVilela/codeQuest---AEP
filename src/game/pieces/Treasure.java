package game.pieces;

import boardgame.Board;
import game.GamePiece;
import game.Color;

public class Treasure extends GamePiece {

    public Treasure(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        return new boolean[0][];
    }

    @Override
    public String toString() {
        return "T";
    }


}
