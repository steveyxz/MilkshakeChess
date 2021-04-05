package com.milkshakeChess.models.screenObjects;

import java.awt.*;

public abstract class ScreenObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int rotation;
    protected int startingWidth;
    protected int startingHeight;
    protected int startingX;
    protected int startingY;
    protected boolean visible = true;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getStartingWidth() {
        return startingWidth;
    }

    public void setStartingWidth(int startingWidth) {
        this.startingWidth = startingWidth;
    }

    public int getStartingHeight() {
        return startingHeight;
    }

    public void setStartingHeight(int startingHeight) {
        this.startingHeight = startingHeight;
    }


    public int getStartingX() {
        return startingX;
    }

    public void setStartingX(int startingX) {
        this.startingX = startingX;
    }

    public int getStartingY() {
        return startingY;
    }

    public void setStartingY(int startingY) {
        this.startingY = startingY;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ScreenObject(int x, int y, int rotation, int width, int height) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.width = width;
        this.height = height;
        this.startingHeight = height;
        this.startingWidth = width;
        this.startingX = x;
        this.startingY = y;
    }

    public ScreenObject(int x, int y, int rotation) {
        this(x, y, rotation, 50, 50);
    }

    public ScreenObject(int x, int y) {
        this(x, y, 0);
    }

    public ScreenObject() {
        this(0, 0, 0);
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}
