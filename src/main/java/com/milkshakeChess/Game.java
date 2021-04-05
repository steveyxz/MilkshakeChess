package com.milkshakeChess;

import com.milkshakeChess.enums.gameChoice.GameType;
import com.milkshakeChess.enums.gameChoice.WindowState;
import com.milkshakeChess.processors.GraphicsProcessor;
import com.milkshakeChess.screens.CheckmateScreen;
import com.milkshakeChess.screens.MainOverlay;
import com.milkshakeChess.screens.StartScreen;
import com.milkshakeChess.util.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Game extends Canvas implements Runnable {

    public static boolean running = false;
    public static Board board;
    public static Image whiteKingIMG; //White Pieces
    public static Image whiteBishopIMG;
    public static Image whitePawnIMG;
    public static Image whiteRookIMG;
    public static Image whiteQueenIMG;
    public static Image whiteKnightIMG;
    public static Image blackKingIMG; //Black Pieces
    public static Image blackBishopIMG;
    public static Image blackPawnIMG;
    public static Image blackRookIMG;
    public static Image blackQueenIMG;
    public static Image blackKnightIMG;
    public static ArrayList<Image> pieceImages = new ArrayList<>();
    public static int oldWidth = 0;
    public static int newWidth = 0;
    public static int oldHeight = 0;
    public static int newHeight = 0;
    public static GameType currentGame = GameType.PlayerBoth;
    public static WindowState currentState = WindowState.Start;
    public static StartScreen startScreen;
    public static MainOverlay mainOverlay;
    public static CheckmateScreen checkmateScreen;
    public static int WIDTH = 640;
    public static int HEIGHT = WIDTH / 12 * 9;
    private static int FPS = 0;
    private Thread graphicsProcessor;
    private Thread moveProcessor;
    private Thread thread;
    public FontManager fontManager;

    public Game() {

        fontManager = new FontManager();

        board = new Board(20, 20, 60);
        initIMG();

        board.setThisBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

        checkmateScreen = new CheckmateScreen(this);
        startScreen = new StartScreen(this);
        mainOverlay = new MainOverlay(this, board);

        this.addMouseListener(checkmateScreen);
        this.addMouseListener(startScreen);
        this.addMouseListener(mainOverlay);

        new Window(WIDTH, HEIGHT, "MilkshakeChess (1.0.0)", this);
    }

    @SuppressWarnings("all")
    public Image renderPiece(int x, int y, int width, int height, String path) {
        try {
            if (Game.class.getResource(path) == null) {
                System.out.println("Invalid path: " + path);
                return null;
            }
            byte[] imageSrc = Game.class.getResourceAsStream(path).readAllBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageSrc);
            BufferedImage IMG = ImageIO.read(byteArrayInputStream);
            IMG = IMG.getSubimage((2000 / 6) * (x - 1), (y - 1) * (668 / 2), 2000 / 6, 668 / 2);
            return IMG.getScaledInstance(width, height, BufferedImage.SCALE_AREA_AVERAGING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        new Game();
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 10.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        Thread graphicsProcessor = new Thread(new GraphicsProcessor(board, this));
        graphicsProcessor.start();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Main FPS: " + frames);
                FPS = frames;
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        if (currentState == WindowState.Game) {
            board.tick();
        } else if (currentState == WindowState.Start) {
            startScreen.tick();
        }
    }

    private void initIMG() {
        String path = "/img/pieces.png";
        whiteBishopIMG = renderPiece(3, 1, board.squareWidth - 5, board.squareWidth - 5, path);
        whiteKingIMG = renderPiece(1, 1, board.squareWidth - 5, board.squareWidth - 5, path);
        whiteKnightIMG = renderPiece(4, 1, board.squareWidth - 5, board.squareWidth - 5, path);
        whiteRookIMG = renderPiece(5, 1, board.squareWidth - 5, board.squareWidth - 5, path);
        whiteQueenIMG = renderPiece(2, 1, board.squareWidth - 5, board.squareWidth - 5, path);
        whitePawnIMG = renderPiece(6, 1, board.squareWidth - 5, board.squareWidth - 5, path);
        blackBishopIMG = renderPiece(3, 2, board.squareWidth - 5, board.squareWidth - 5, path);
        blackKingIMG = renderPiece(1, 2, board.squareWidth - 5, board.squareWidth - 5, path);
        blackKnightIMG = renderPiece(4, 2, board.squareWidth - 5, board.squareWidth - 5, path);
        blackRookIMG = renderPiece(5, 2, board.squareWidth - 5, board.squareWidth - 5, path);
        blackQueenIMG = renderPiece(2, 2, board.squareWidth - 5, board.squareWidth - 5, path);
        blackPawnIMG = renderPiece(6, 2, board.squareWidth - 5, board.squareWidth - 5, path);
        pieceImages.add(blackBishopIMG);
        pieceImages.add(blackKingIMG);
        pieceImages.add(blackKnightIMG);
        pieceImages.add(blackPawnIMG);
        pieceImages.add(blackQueenIMG);
        pieceImages.add(blackRookIMG);
        pieceImages.add(whiteKingIMG);
        pieceImages.add(whiteKnightIMG);
        pieceImages.add(whiteBishopIMG);
        pieceImages.add(whiteRookIMG);
        pieceImages.add(whiteQueenIMG);
        pieceImages.add(whitePawnIMG);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
