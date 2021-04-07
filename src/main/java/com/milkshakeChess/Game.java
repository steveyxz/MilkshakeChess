package com.milkshakeChess;

import com.milkshakeChess.enums.gameChoice.GameType;
import com.milkshakeChess.enums.gameChoice.WindowState;
import com.milkshakeChess.inputs.KeyInput;
import com.milkshakeChess.inputs.BoardMouseInput;
import com.milkshakeChess.processors.GraphicsProcessor;
import com.milkshakeChess.screens.CheckmateScreen;
import com.milkshakeChess.screens.MainOverlay;
import com.milkshakeChess.screens.StartScreen;
import com.milkshakeChess.util.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

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

    public static int oldSquareSide;
    public static int newSquareSide;

    public static double oldMilliDelay = 0;
    public static double newMilliDelay = 0;

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
    public KeyInput keyInput;
    public BoardMouseInput boardMouseInput;

    public Game() {

        fontManager = new FontManager();

        board = new Board(20, 20, 60);
        initIMG();

        board.setThisBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

        checkmateScreen = new CheckmateScreen(this);
        startScreen = new StartScreen(this);
        mainOverlay = new MainOverlay(this, board);

        keyInput = new KeyInput();
        boardMouseInput = new BoardMouseInput(this, board);

        this.addMouseListener(boardMouseInput);
        this.addMouseMotionListener(boardMouseInput);
        this.addMouseWheelListener(boardMouseInput);

        this.addKeyListener(keyInput);

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
            return IMG.getScaledInstance(width, height, BufferedImage.SCALE_DEFAULT);
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
        double amountOfTicks = 4.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        Thread graphicsProcessor = new Thread(new GraphicsProcessor(board, this));
        graphicsProcessor.setName("Graphics Thread (GraphicsProcessor.java)");
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

    @SuppressWarnings("all")
    public void resizeImages() {
        /*
        int sideLength = board.squareWidth;
        System.out.println(sideLength);
        BufferedImage image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(whiteBishopIMG, 0, 0, sideLength, sideLength, null);
        whiteBishopIMG = image;
        image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(whiteKingIMG, 0, 0, sideLength, sideLength, null);
        whiteKingIMG = image;
        image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(whiteKnightIMG, 0, 0, sideLength, sideLength, null);
        whiteKnightIMG = image;
        image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(whiteRookIMG, 0, 0, sideLength, sideLength, null);
        whiteRookIMG = image;
        image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(whiteQueenIMG, 0, 0, sideLength, sideLength, null);
        whiteQueenIMG = image;
        image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(whitePawnIMG, 0, 0, sideLength, sideLength, null);
        whitePawnIMG = image;
        image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(blackBishopIMG, 0, 0, sideLength, sideLength, null);
        blackBishopIMG = image;
        image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(blackKnightIMG, 0, 0, sideLength, sideLength, null);
        blackKingIMG = image;
        image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(blackKnightIMG, 0, 0, sideLength, sideLength, null);
        blackKnightIMG = image;
        image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(blackRookIMG, 0, 0, sideLength, sideLength, null);
        blackRookIMG = image;
        image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(blackQueenIMG, 0, 0, sideLength, sideLength, null);
        blackQueenIMG = image;
        image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(blackPawnIMG, 0, 0, sideLength, sideLength, null);
        blackPawnIMG = image;
         */
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.setName("Main Thread (Game.java)");
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
