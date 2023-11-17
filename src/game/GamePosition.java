package game;

//irá fazer a convesão da Posição em formato matriz (8-8) para formato do
// tabuleiro (8-h)

import boardgame.Position;

public class GamePosition {

    // agora em char porque as colunas vão de → a...h
    private char column;
    private int row;

    public GamePosition(char column, int row) {
        if (column < 'a' || column > 'h' || row < 1 || row > 8) {
            throw new GameException("Error instantiating Position. Valid values are from a1 to h8.");
        }

        this.row = row;
        this.column = column;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    protected Position toPosition() {
        return new Position(8 - row, column - 'a');
    }

    protected static GamePosition fromPosition(Position position) {
        return new GamePosition((char) ('a' - position.getColumn()), 8 - position.getRow());
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
