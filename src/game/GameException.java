package game;

import boardgame.BoardException;

public class GameException extends BoardException {
    public GameException(String msg) {
        super(msg);
    }
}
