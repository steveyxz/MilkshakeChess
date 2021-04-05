package com.milkshakeChess.screens;

import com.milkshakeChess.Game;
import com.milkshakeChess.models.Screen;
import com.milkshakeChess.util.Board;

import java.awt.*;

public class MainOverlay extends Screen {

    private final Board board;

    public MainOverlay(Game game, Board board) {
        super(game);
        this.board = board;
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void tick() {

    }
}
