package com.milkshakeChess.processors;

import com.milkshakeChess.Game;
import com.milkshakeChess.enums.gameChoice.GameType;
import com.milkshakeChess.enums.gameChoice.WindowState;
import com.milkshakeChess.settings.GameChoiceStorage;
import com.milkshakeChess.util.Board;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * This class helps a board process graphics. It also processes the start screen and end screen.
 **/
public class GraphicsProcessor implements Runnable {

    private final Board board;
    private final Game game;
    public static boolean boardReRender = true;
    public static boolean backgroundReRender = true;

    public GraphicsProcessor(Board board, Game game) {
        this.board = board;
        this.game = game;
    }

    @Override
    public void run() {

        int frames = 0;
        double timer = System.currentTimeMillis();
        while (Game.running) {
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Graphics FPS: " + frames);
                frames = 0;
            }
        }
    }

    private void render() {
        BufferStrategy bs = game.getBufferStrategy();
        if (bs == null) {
            game.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        if (Game.currentState == WindowState.Game) {

            if (backgroundReRender) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
                backgroundReRender = false;
            }

            if (boardReRender) {
                renderBoard(g);
            }

            board.render(g);

        } else if (Game.currentState == WindowState.Start) {
            Game.startScreen.render(g);
        }

        g.dispose();
        bs.show();
    }

    private void renderBoard(Graphics g) {
        for (int i = 0; i < 4; i++) { //Loops through row sets
            if (GameChoiceStorage.gameType == GameType.PlayerWhite || GameChoiceStorage.gameType == GameType.PlayerBoth) {
                for (int j = 0; j < 4; j++) { //Loops through column set 1
                    g.setColor(new Color(153, 0, 0));
                    g.fillRect(board.startX + j * board.squareWidth * 2, board.startY + i * board.squareWidth * 2, board.squareWidth, board.squareWidth);
                    g.setColor(Color.WHITE);
                    g.fillRect(board.startX + j * board.squareWidth * 2 + board.squareWidth, board.startY + i * board.squareWidth * 2, board.squareWidth, board.squareWidth);
                }
                for (int j = 0; j < 4; j++) { //Loops through column set 2
                    g.setColor(Color.WHITE);
                    g.fillRect(board.startX + j * board.squareWidth * 2, board.startY + i * board.squareWidth * 2 + board.squareWidth, board.squareWidth, board.squareWidth);
                    g.setColor(new Color(153, 0, 0));
                    g.fillRect(board.startX + j * board.squareWidth * 2 + board.squareWidth, board.startY + i * board.squareWidth * 2 + board.squareWidth, board.squareWidth, board.squareWidth);
                }
            } else {
                for (int j = 0; j < 4; j++) { //Loops through column set 2
                    g.setColor(Color.WHITE);
                    g.fillRect(board.startX + j * board.squareWidth * 2, board.startY + i * board.squareWidth * 2 + board.squareWidth, board.squareWidth, board.squareWidth);
                    g.setColor(new Color(153, 0, 0));
                    g.fillRect(board.startX + j * board.squareWidth * 2 + board.squareWidth, board.startY + i * board.squareWidth * 2 + board.squareWidth, board.squareWidth, board.squareWidth);
                }
                for (int j = 0; j < 4; j++) { //Loops through column set 1
                    g.setColor(new Color(153, 0, 0));
                    g.fillRect(board.startX + j * board.squareWidth * 2, board.startY + i * board.squareWidth * 2, board.squareWidth, board.squareWidth);
                    g.setColor(Color.WHITE);
                    g.fillRect(board.startX + j * board.squareWidth * 2 + board.squareWidth, board.startY + i * board.squareWidth * 2, board.squareWidth, board.squareWidth);
                }
            }
        }
        boardReRender = false;
    }
}
