package game;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class GamePiece extends Piece {
    private Color color;

    public GamePiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return super.position;
    }

    protected boolean isThereOpponentPiece(Position position) {
        GamePiece p = (GamePiece) getBoard().piece(position);

        return p != null && p.getColor() != color;
    }
}
