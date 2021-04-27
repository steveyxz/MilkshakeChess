package com.milkshakeChess.objects.startScreen;

import com.milkshakeChess.models.Screen;
import com.milkshakeChess.models.screenObjects.ScreenObject;

import java.awt.*;
import java.util.function.Consumer;

public class PageSwitcher extends ScreenObject {

    public int direction;
    private final Screen mainClass;
    private final Consumer<Integer> action;

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
        initAction(true);
    }

    public PageSwitcher(int x, int y, int rotation, Screen mainClass, Consumer<Integer> action) {
        this(x, y, rotation, 50, 50, 0, mainClass, action);
    }

    public void initAction(boolean enable) {
        if (enable) {
            if (direction == 0) {
                mainClass.weirdShapeClickySpots.put(new Polygon(new int[]{x, x + width, x + width}, new int[]{y + height / 2, y, y + height}, 3), action);
            } else {
                mainClass.weirdShapeClickySpots.put(new Polygon(new int[]{x, x, x + width}, new int[]{y, y + height, y + height / 2}, 3), action);
            }
        } else {
            if (direction == 0) {
                mainClass.weirdShapeClickySpots.remove(new Polygon(new int[]{x, x + width, x + width}, new int[]{y + height / 2, y, y + height}, 3));
            } else {
                mainClass.weirdShapeClickySpots.remove(new Polygon(new int[]{x, x, x + width}, new int[]{y, y + height, y + height / 2}, 3));
            }
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        if (direction == 0) {
            g.fillPolygon(new Polygon(new int[]{x, x + width, x + width}, new int[]{y + height / 2, y, y + height}, 3));
        } else {
            g.fillPolygon(new Polygon(new int[]{x, x, x + width}, new int[]{y, y + height, y + height / 2}, 3));
        }
    }
}
