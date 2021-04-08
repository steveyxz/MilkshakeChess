package com.milkshakeChess.processors;

import com.milkshakeChess.Game;
import com.milkshakeChess.enums.gameChoice.GameType;
import com.milkshakeChess.enums.gameChoice.WindowState;
import com.milkshakeChess.pieces.*;
import com.milkshakeChess.settings.GameChoiceStorage;
import com.milkshakeChess.util.Board;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * This class helps a board process graphics. It also processes the start screen and end screen.
 **/
@SuppressWarnings("all")
public class GraphicsProcessor implements Runnable {

    private final Board board;
    private final Game game;
    public static boolean boardReRender = true;
    public static boolean backgroundReRender = true;

    public static PieceProcessor<Pawn> pawnRenderer;
    public static PieceProcessor<Knight> knightRenderer;
    public static PieceProcessor<King> kingRenderer;
    public static PieceProcessor<Bishop> bishopRenderer;
    public static PieceProcessor<Queen> queenRenderer;
    public static PieceProcessor<Rook> rookRenderer;

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

        //if (pawnRenderer == null) {
        //    try {
        //        Thread.sleep(2);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //    Thread.yield();
        //    initRenderers(g, board);
        //}

        if (Game.currentState == WindowState.Game) {

            if (backgroundReRender) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
                backgroundReRender = false;
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.yield();
                renderBoard(g);
            }

            if (boardReRender) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.yield();
                renderBoard(g);
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
            boolean isWhite = true;
            if (GameChoiceStorage.gameType == GameType.PlayerBlack) {
                isWhite = false;
            }
            board.render(g, isWhite);

            g.dispose();


        } else if (Game.currentState == WindowState.Start) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
            Game.startScreen.render(g);
        }

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bs.show();

    }

    private void initRenderers(Graphics g, Board board) {
        pawnRenderer = new PieceProcessor<Pawn>(game, board) {};
        knightRenderer = new PieceProcessor<Knight>(game, board){};
        kingRenderer = new PieceProcessor<King>(game, board) {};
        bishopRenderer = new PieceProcessor<Bishop>(game, board) {};
        rookRenderer = new PieceProcessor<Rook>(game, board) {};
        queenRenderer = new PieceProcessor<Queen>(game, board) {};
        Thread thread = new Thread(pawnRenderer);
        Thread thread2 = new Thread(knightRenderer);
        Thread thread3 = new Thread(kingRenderer);
        Thread thread4 = new Thread(bishopRenderer);
        Thread thread5 = new Thread(rookRenderer);
        Thread thread6 = new Thread(queenRenderer);
        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();

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

@SuppressWarnings("all")
abstract class PieceProcessor<E> implements Runnable {

    private Game game;
    private Board board;

    public PieceProcessor(Game game, Board board) {

        this.game = game;
        this.board = board;
    }

    public void run() {
        while (Game.running) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
            render();
        }
    }

    public Class<?> getGenericClass(){
        Class<?> result =null;
        Type type = this.getClass().getGenericSuperclass();

        if(type instanceof ParameterizedType){
            ParameterizedType pt =(ParameterizedType) type;
            Type[] fieldArgTypes = pt.getActualTypeArguments();
            result =(Class<?>) fieldArgTypes[0];
        }
        return result;
    }

    public Class<E> g() throws Exception {
        ParameterizedType superclass =
                (ParameterizedType) getClass().getGenericSuperclass();

        return (Class<E>) superclass.getActualTypeArguments()[0];
    }

    private void render() {
        BufferStrategy bs = game.getBufferStrategy();
        if (bs == null) {
            game.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        if (Game.currentState != WindowState.Game) {
            return;
        }
        Class<E> typeClass = null;
        try {
            typeClass = g();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean isWhite = GameChoiceStorage.gameType != GameType.PlayerBlack;
        for (int i = 0; i < board.getBoard().size(); i++) {
            if (board.getSquare(i).getClass().equals(typeClass)) {
                board.getSquare(i).render(g, isWhite);
            }
        }

        g.dispose();
        bs.show();

        System.gc();
    }

}