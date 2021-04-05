package com.milkshakeChess.screens;

import com.milkshakeChess.Game;
import com.milkshakeChess.enums.gameChoice.Result;
import com.milkshakeChess.models.Screen;

import java.awt.*;

public class CheckmateScreen extends Screen {

    public Result wonTeam = Result.Draw;

    public CheckmateScreen(Game game) {
        super(game);
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void tick() {

    }
}
