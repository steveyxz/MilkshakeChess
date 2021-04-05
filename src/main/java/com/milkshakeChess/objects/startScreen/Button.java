package com.milkshakeChess.objects.startScreen;

import com.milkshakeChess.FontManager;
import com.milkshakeChess.Game;
import com.milkshakeChess.interfaces.Constants;
import com.milkshakeChess.models.interfaces.IHasClickSpots;
import com.milkshakeChess.models.screenObjects.ScreenObject;
import com.milkshakeChess.screens.CheckmateScreen;
import com.milkshakeChess.screens.MainOverlay;
import com.milkshakeChess.screens.StartScreen;

import java.awt.*;
import java.util.LinkedList;
import java.util.function.Consumer;

public class Button extends ScreenObject {

    private final Consumer<Integer> action;
    private final IHasClickSpots mainClass;
    private final String text;
    private final Color textColor;
    private final Color buttonColor;
    private final double arc;

    public Button(int x, int y, int rotation, int width, int height, String text, Consumer<Integer> action, IHasClickSpots mainClass, Color textColor, Color buttonColor, double arc) {
        super(x, y, rotation, width, height);
        this.action = action;
        this.mainClass = mainClass;
        this.text = text;
        this.textColor = textColor;
        this.buttonColor = buttonColor;
        this.arc = arc;
        setObjectToAction();
    }

    public Button(int x, int y, int rotation, int width, int height, String text, Consumer<Integer> action, IHasClickSpots mainClass, Color textColor, Color bodyColor, double arc, int pageOn) {
        this(x, y, rotation, width, height, text, action, mainClass, textColor, bodyColor, arc);
        this.pageOn = pageOn;
    }

    public Button(int x, int y, int rotation, int width, int height, String text, Consumer<Integer> action, IHasClickSpots mainClass) {
        this(x, y, rotation, width, height, text, action, mainClass, Color.white, Color.black, 50);
    }

    public void setObjectToAction() {
        LinkedList<Integer> positionalValues = new LinkedList<>();
        positionalValues.add(x);
        positionalValues.add(y);
        positionalValues.add(x + width);
        positionalValues.add(y + height);
        positionalValues.add(pageOn);
        if (mainClass instanceof StartScreen) {
            ((StartScreen) mainClass).clickySpots.put(positionalValues, action);
        } else if (mainClass instanceof MainOverlay) {
            ((MainOverlay) mainClass).clickySpots.put(positionalValues, action);
        } else if (mainClass instanceof CheckmateScreen) {
            ((CheckmateScreen) mainClass).clickySpots.put(positionalValues, action);
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(buttonColor);
        g.fillRoundRect(x, y, width, height, (int) arc, (int) arc);
        g.setColor(textColor);
        float size = (5F / text.length()) * 40 * (Game.WIDTH < Game.HEIGHT ? Game.WIDTH / (Constants.GAME_START_WIDTH * 1F) : Game.HEIGHT / (Constants.GAME_START_HEIGHT * 1F));
        Font font = FontManager.getResizedFont(FontManager.kgTenK2, size);
        g.setFont(font);
        g.drawString(text, x + (width / 2) - g.getFontMetrics(font).stringWidth(text) / 2, y + (height / 2) + g.getFontMetrics(font).getHeight() / 4);
    }

}
