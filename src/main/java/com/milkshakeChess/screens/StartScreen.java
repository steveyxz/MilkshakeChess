package com.milkshakeChess.screens;

import com.milkshakeChess.FontManager;
import com.milkshakeChess.Game;
import com.milkshakeChess.enums.gameChoice.GameType;
import com.milkshakeChess.enums.gameChoice.WindowState;
import com.milkshakeChess.enums.id.SideID;
import com.milkshakeChess.interfaces.Constants;
import com.milkshakeChess.models.Screen;
import com.milkshakeChess.models.screenObjects.ScreenObject;
import com.milkshakeChess.objects.startScreen.Button;
import com.milkshakeChess.objects.startScreen.FakePawn;
import com.milkshakeChess.settings.GameChoiceStorage;

import java.awt.*;

public class StartScreen extends Screen {

    public int stage = 0;
    public static double ratio = 1;

    public static int totalWidth;
    public static int totalHeight;
    public static double heightToWidthDifference;
    public static int startWidth = 0;
    public static int startHeight = 0;
    public static int fakePawnY = 0;

    private int tickTimer = 0;

    public StartScreen(Game game) {
        super(game);
        createObjects();
        getSizes();
    }

    private void getSizes() {
        int smallX = 1080184976;
        int largeX = 0;
        int smallY = 1801880010;
        int largeY = 0;
        for (int i = 0; i < objects.size(); i++) {
            ScreenObject obj = objects.get(i);
            smallX = Math.min(obj.getX(), smallX);
            smallY = Math.min(obj.getY(), smallY);
            largeX = Math.max(obj.getX() + obj.getWidth(), largeX);
            largeY = Math.max(obj.getY() + obj.getHeight(), largeY);
        }
        totalWidth = largeX - smallX;
        totalHeight = largeY - smallY;
        startWidth = totalWidth;
        startHeight = totalHeight;
        heightToWidthDifference = totalWidth - totalHeight;
    }

    private void createObjects() {
        clearItems();
        clickySpots.clear();
        addItem(new FakePawn(80, 30, -10, SideID.White));
        addItem(new FakePawn(30, 30, 10, SideID.White));
        addItem(new FakePawn(130, 30, -10, SideID.White));
        addItem(new FakePawn(430, 30, 10, SideID.White));
        addItem(new FakePawn(480, 30, -10, SideID.White));
        addItem(new FakePawn(530, 30, 10, SideID.White));
        switch (stage) {
            case 0 -> {
                addItem(new Button(190, 150, 0, 250, 80, "Console", integer -> {
                    System.out.println("CONSOLE OPENS");
                }, this));
                addItem(new Button(190, 250, 0, 250, 80, "Graphical", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    createObjects();
                }, this));
            }
            case 1 -> {
                addItem(new Button(50, 150, 0, 200, 80, "Play Black", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.gameType = GameType.PlayerBlack;
                    createObjects();
                }, this));
                addItem(new Button(350, 150, 0, 200, 80, "Play White", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.gameType = GameType.PlayerWhite;
                    createObjects();
                }, this));
                addItem(new Button(150, 250, 0, 350, 80, "Play Both (No AI)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.gameType = GameType.PlayerBoth;
                    createObjects();
                }, this));
            }
            case 2 -> {
                if (GameChoiceStorage.gameType == GameType.PlayerBoth) {
                    clickySpots.clear();
                    clearItems();
                    Game.currentState = WindowState.Game;
                }
                addItem(new Button(50, 150, 0, 200, 80, "Completely Stupid (<100)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.gameType = GameType.PlayerBlack;
                    createObjects();
                }, this));
                addItem(new Button(350, 150, 0, 200, 80, "Trash (200)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.gameType = GameType.PlayerWhite;
                    createObjects();
                }, this));
                addItem(new Button(50, 250, 0, 200, 80, "Pretty bad still (400)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.gameType = GameType.PlayerBoth;
                    createObjects();
                }, this));
            }
        }
    }

    /**
     * This method resizes the objects on the startScreen and also relocates them on the screen.
     *
     * @param ratio               This parameter signifies the ratio of what the width, height, x or y will be resized to,
     *                            eg if you want the total occupied space to become 1.1 of the original, then you resize it
     *                            to 1.1 ratio.
     * @param directionOfMovement This parameter is either 0 or 1, and signifies which side is the resizing
     *                            based on. This means, say you resize the width, and the width ends up
     *                            being larger than the height (the ratio is now based on the height),
     *                            the number will be 1. The other case (the ratio is based on the width),
     *                            is 0.
     * @throws IllegalArgumentException If the directionOfMovement is not 0 or 1
     **/
    public void resizeObjects(double ratio, int directionOfMovement) {
        if (directionOfMovement != 0 && directionOfMovement != 1) {
            throw new IllegalArgumentException("The direction of movement given was not 0 or 1");
        }
        totalWidth = (int) (startWidth * ratio);
        totalHeight = (int) (startHeight * ratio);
        int anchorX = 0;
        int anchorY = 0;
        if (directionOfMovement == 0) {
            anchorY = (int) (Game.HEIGHT / 2 - totalHeight / 1.4F);
        } else {
            anchorX = (int) (Game.WIDTH / 2 - totalWidth / 1.8F);
        }
        clickySpots.clear();
        for (int i = 0; i < objects.size(); i++) {
            ScreenObject obj = objects.get(i);
            obj.setWidth((int) (obj.getStartingWidth() * ratio));
            obj.setHeight((int) (obj.getStartingHeight() * ratio));
            obj.setX((int) (obj.getStartingX() * ratio) + anchorX);
            obj.setY((int) ((obj.getStartingY() * ratio) + anchorY));
            if (obj instanceof FakePawn) {
                fakePawnY = obj.getY();
            }
            if (obj instanceof Button) {
                ((Button) obj).setObjectToAction();
            }
        }
        StartScreen.ratio = ratio;
    }

    @Override
    public void render(Graphics g) {
        switch (stage) {
            case 0, 1, 2 -> {
                g.setColor(Color.PINK);
                g.fillRect(0, 0, 2000, 2000);
                g.setColor(Color.BLACK);
                g.setFont(FontManager.getResizedFont(FontManager.avengerTitleFont, Math.min(Game.HEIGHT, Game.WIDTH) / 15F));
                g.drawString("Milkshake", Game.WIDTH / 2 - g.getFontMetrics(FontManager.avengerTitleFont).stringWidth("Milkshake") / 2 - 20, (int) (fakePawnY + 30 + Game.WIDTH / Constants.GAME_START_WIDTH));
                g.drawString("Chess", Game.WIDTH / 2 - g.getFontMetrics(FontManager.avengerTitleFont).stringWidth("Chess") / 2 + 20, (int) ((int) (fakePawnY + 60 + Game.WIDTH / Constants.GAME_START_WIDTH * 2)));
                for (int i = 0; i < objects.size(); i++) {
                    objects.get(i).render(g);
                }
            }
            default -> System.out.println("YEEPASYO");
        }
    }

    @Override
    public void tick() {
        if (tickTimer == 5) {
            for (int i = 0; i < objects.size(); i++) {
                ScreenObject obj = objects.get(i);
                if (obj instanceof FakePawn) {
                    obj.setRotation(obj.getRotation() * -1);
                }
            }
            tickTimer = 0;
        }
        tickTimer++;
    }
}
