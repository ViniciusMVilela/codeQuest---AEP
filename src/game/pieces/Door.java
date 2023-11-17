package game.pieces;

import boardgame.Board;
import game.GamePiece;
import game.Color;
import game.Pergunta;

public class Door extends GamePiece {

    private Integer id;
    private Pergunta pergunta;

    public Door(Board board, Color color, Integer id, Pergunta pergunta) {
        super(board, color);
        this.id = id;
        this.pergunta = pergunta;
    }

    public Integer getId() {
        return id;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    @Override
    public String toString() {
        return "-";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColums()];
        return mat;
    }
}
