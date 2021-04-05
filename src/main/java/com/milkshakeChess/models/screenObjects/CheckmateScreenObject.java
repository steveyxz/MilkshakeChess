package com.milkshakeChess.models.screenObjects;

public abstract class CheckmateScreenObject extends ScreenObject {
    public CheckmateScreenObject(int x, int y, int rotation, int width, int height) {
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

    public CheckmateScreenObject(int x, int y, int rotation) {
        this(x, y, rotation, 50, 50);
    }

    public CheckmateScreenObject(int x, int y) {
        this(x, y, 0);
    }

    public CheckmateScreenObject() {
        this(0, 0, 0);
    }
}
