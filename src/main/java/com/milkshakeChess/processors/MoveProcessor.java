package com.milkshakeChess.processors;

import com.milkshakeChess.Game;
import com.milkshakeChess.util.Board;

public class MoveProcessor implements Runnable {

    private final Game game;
    private final Board board;

    public MoveProcessor(Board board, Game game) {
        this.board = board;
        this.game = game;
    }

    @Override
    public void run() {

    }
}
