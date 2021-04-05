package com.milkshakeChess.objects.startScreen;

import com.milkshakeChess.models.Screen;
import com.milkshakeChess.models.screenObjects.ScreenObject;

import java.awt.*;
import java.util.function.Consumer;

public class PageSwitcher extends ScreenObject {

    private int direction;
    private Screen mainClass;
    private Consumer<Integer> action;
    private Polygon rightPagePolygon;
    private Polygon leftPagePolygon;

    //direction is left (previous page) at 0 and right (next page) at 1
    public PageSwitcher(int x, int y, int rotation, int width, int height, int direction, Screen mainClass, Consumer<Integer> action) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.width = width;
        this.height = height;
        this.startingHeight = height;
        this.startingWidth = width;
        this.startingX = x;
        this.startingY = y;
        this.direction = direction;
        this.mainClass = mainClass;
        this.action = action;
        initAction();
    }

    private void initAction() {
        if (direction == 0) {
            mainClass.weirdShapeClickySpots.put(leftPagePolygon, action);
        } else {
            mainClass.weirdShapeClickySpots.put(rightPagePolygon, action);
        }
    }

    public PageSwitcher(int x, int y, int rotation, Screen mainClass, Consumer<Integer> action) {
        this(x, y, rotation, 50, 50, 0, mainClass, action);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        leftPagePolygon = new Polygon(new int[] {x, x + width, x + width}, new int[] {y + height / 2, y, y + height}, 3);
        rightPagePolygon = new Polygon(new int[] {x, x, x + width}, new int[] {y, y + height, y + height / 2}, 3);
        if (direction == 0) {
            g.fillPolygon(leftPagePolygon);
        } else {
            g.fillPolygon(rightPagePolygon);
        }
    }
}
