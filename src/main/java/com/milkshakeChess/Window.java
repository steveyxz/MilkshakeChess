package com.milkshakeChess;

import com.milkshakeChess.interfaces.Constants;
import com.milkshakeChess.processors.GraphicsProcessor;
import com.milkshakeChess.screens.StartScreen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

import static com.milkshakeChess.Game.*;

public class Window {

    public Window(int width, int height, String title, Game game) {

        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                oldWidth = WIDTH;
                oldHeight = HEIGHT;
                WIDTH = componentEvent.getComponent().getBounds().width;
                HEIGHT = componentEvent.getComponent().getBounds().height;
                newHeight = HEIGHT;
                newWidth = WIDTH;
                board.squareWidth = HEIGHT / 10;
                if (WIDTH / 10 < board.squareWidth) {
                    board.squareWidth = WIDTH / 10;
                }
                board.resizeBoardPieces();
                GraphicsProcessor.backgroundReRender = true;
                GraphicsProcessor.boardReRender = true;
                if (WIDTH - StartScreen.heightToWidthDifference < HEIGHT) {
                    startScreen.resizeObjects(WIDTH / (Constants.GAME_START_WIDTH * 1F), 0);
                } else if (WIDTH - StartScreen.heightToWidthDifference > HEIGHT) {
                    startScreen.resizeObjects(HEIGHT / (Constants.GAME_START_HEIGHT * 1F), 1);
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        String imageFileName = "src/main/java/com/milkshakeChess/res/img/icon.png";
        try {
            byte[] imageSrc = Objects.requireNonNull(Game.class.getResourceAsStream("/img/icon.png")).readAllBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageSrc);
            BufferedImage IMG = ImageIO.read(byteArrayInputStream);
            frame.setIconImage(IMG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        game.start();

    }
}

