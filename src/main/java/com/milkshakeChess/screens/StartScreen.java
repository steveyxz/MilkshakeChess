package com.milkshakeChess.screens;

import com.milkshakeChess.FontManager;
import com.milkshakeChess.Game;
import com.milkshakeChess.enums.gameChoice.Difficulty;
import com.milkshakeChess.enums.gameChoice.GameType;
import com.milkshakeChess.enums.gameChoice.WindowState;
import com.milkshakeChess.enums.id.SideID;
import com.milkshakeChess.interfaces.Constants;
import com.milkshakeChess.models.Screen;
import com.milkshakeChess.models.screenObjects.ScreenObject;
import com.milkshakeChess.objects.startScreen.Button;
import com.milkshakeChess.objects.startScreen.FakePawn;
import com.milkshakeChess.objects.startScreen.PageSwitcher;
import com.milkshakeChess.settings.GameChoiceStorage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import com.milkshakeChess.enums.gameChoice.Difficulty.*;

import static com.milkshakeChess.Game.*;

public class StartScreen extends Screen {

    public int stage = 0;
    public static double ratio = 1;

    public static int totalWidth;
    public static int totalHeight;
    public static double heightToWidthDifference;
    public static int startWidth = 0;
    public static int startHeight = 0;
    public static int fakePawnY = 0;

    public int page = 1;
    public int noPages = 1;

    private int tickTimer = 0;

    public StartScreen(Game game) {
        super(game);
        createObjects();
        getSizes();
    }

    @SuppressWarnings("all")
    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        for (int b = 0; b < clickySpots.size(); b++) {
            LinkedList<Integer> i = (LinkedList<Integer>) clickySpots.keySet().toArray()[b];
            if (i.get(4) == page) {
                if (squareContainsPoint(new Rectangle(i.get(0), i.get(1), i.get(2) - i.get(0), i.get(3) - i.get(1)), new Point(mouseX, mouseY))) {
                    clickySpots.get(i).accept(1);
                    break;
                }
            }
        }
        for (int i = 0; i < weirdShapeClickySpots.size(); i++) {
            if (weirdShapeClickySpots.size() < 1) {
                break;
            }
            Polygon polygon = ((Polygon) weirdShapeClickySpots.keySet().toArray()[i]);
            if (polygon.contains(new Point(mouseX, mouseY))) {
                weirdShapeClickySpots.get((Polygon) weirdShapeClickySpots.keySet().toArray()[i]).accept(1);
                break;
            }
        }
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
                page = 1;
                noPages = 1;
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
                page = 1;
                noPages = 1;
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
                page = 1;
                noPages = 3;
                if (GameChoiceStorage.gameType == GameType.PlayerBoth) {
                    clickySpots.clear();
                    clearItems();
                    Game.currentState = WindowState.Game;
                    return;
                }
                addItem(new Button(100, 150, 0, 200, 80, "Completely Stupid (<100)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.difficulty = Difficulty.CompletelyStupid;
                    createObjects();
                }, this));
                addItem(new Button(350, 150, 0, 200, 80, "Trash (200)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.difficulty = Difficulty.Trash;
                    createObjects();
                }, this));
                addItem(new Button(100, 250, 0, 200, 80, "Pretty bad still (400)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.difficulty = Difficulty.PrettyBadStill;
                    createObjects();
                }, this));
                addItem(new Button(350, 250, 0, 200, 80, "OK (600)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.difficulty = Difficulty.OK;
                    createObjects();
                }, this));
                addItem(new Button(350, 250, 0, 200, 80, "Average (800)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.difficulty = Difficulty.Average;
                    createObjects();
                }, this));
                addItem(new Button(100, 150, 0, 200, 80, "Regular Player (1000)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.difficulty = Difficulty.RegularPlayer;
                    createObjects();
                }, this, Color.white, Color.BLACK, 50, 2));
                addItem(new Button(350, 150, 0, 200, 80, "Intermediate Player (1200)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.difficulty = Difficulty.IntermediatePlayer;
                    createObjects();
                }, this, Color.white, Color.BLACK, 50, 2));
                addItem(new Button(100, 250, 0, 200, 80, "Sorta Sweaty Player (1400)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.difficulty = Difficulty.SortaSweatyPlayer;
                    createObjects();
                }, this, Color.white, Color.BLACK, 50, 2));
                addItem(new Button(350, 250, 0, 200, 80, "Sweaty Player (1600)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.difficulty = Difficulty.SweatyPlayer;
                    createObjects();
                }, this, Color.white, Color.BLACK, 50, 2));
                addItem(new Button(100, 150, 0, 200, 80, "Very Sweaty Player (1800)", integer -> {
                    stage++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameChoiceStorage.difficulty = Difficulty.VerySweatyPlayer;
                    createObjects();
                }, this, Color.white, Color.BLACK, 50, 3));
                addItem(new PageSwitcher(600, 200, 0, 20, 50, 1, this, integer -> {
                    if (page >= noPages) {
                        return;
                    }
                    page++;
                }));
                addItem(new PageSwitcher(30, 200, 0, 20, 50, 0, this, integer -> {
                    if (page <= 1) {
                        return;
                    }
                    page--;
                }));
            }
            case 3 -> {
                noPages = 1;
                page = 1;
                if (GameChoiceStorage.convertDifficultyToRating() < 1000) {
                    clickySpots.clear();
                    clearItems();
                    Game.currentState = WindowState.Game;
                    return;
                }

            }
        }
        if (WIDTH - StartScreen.heightToWidthDifference < HEIGHT) {
            resizeObjects(WIDTH / (Constants.GAME_START_WIDTH * 1F), 0);
        } else if (WIDTH - StartScreen.heightToWidthDifference > HEIGHT) {
            resizeObjects(HEIGHT / (Constants.GAME_START_HEIGHT * 1F), 1);
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
        weirdShapeClickySpots.clear();
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
            if (obj instanceof PageSwitcher) {
                ((PageSwitcher) obj).initAction(true);
            }
        }
        StartScreen.ratio = ratio;
    }

    @Override
    public void render(Graphics g) {
        switch (stage) {
            case 0, 1, 2, 3 -> {
                g.setColor(Color.PINK);
                g.fillRect(0, 0, 2000, 2000);
                g.setColor(Color.BLACK);
                g.setFont(FontManager.getResizedFont(FontManager.avengerTitleFont, Math.min(Game.HEIGHT, Game.WIDTH) / 15F));
                g.drawString("Milkshake", Game.WIDTH / 2 - g.getFontMetrics(FontManager.avengerTitleFont).stringWidth("Milkshake") / 2 - 20, (int) (fakePawnY + 30 + Game.WIDTH / Constants.GAME_START_WIDTH));
                g.drawString("Chess", Game.WIDTH / 2 - g.getFontMetrics(FontManager.avengerTitleFont).stringWidth("Chess") / 2 + 20, (int) ((int) (fakePawnY + 60 + Game.WIDTH / Constants.GAME_START_WIDTH * 2)));
                for (int i = 0; i < objects.size(); i++) {
                    if (objects.get(i) instanceof Button) {
                        if (objects.get(i).pageOn != page) {
                            continue;
                        }
                    }
                    if (objects.get(i) instanceof PageSwitcher) {
                        if (((PageSwitcher) objects.get(i)).direction == 0) {
                            if (page <= 1) {
                                continue;
                            }
                        } else {
                            if (page >= noPages) {
                                continue;
                            }
                        }
                    }
                    objects.get(i).render(g);
                }
            }
            default -> System.out.println("YEEPASYO");
        }
    }

    @Override
    public void tick() {
        if (tickTimer == 2) {
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
