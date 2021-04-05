package com.milkshakeChess.models.screenObjects;

public abstract class StartScreenObject extends ScreenObject {

    public StartScreenObject(int x, int y, int rotation, int width, int height) {
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

    public StartScreenObject(int x, int y, int rotation) {
        this(x, y, rotation, 50, 50);
    }

    public StartScreenObject(int x, int y) {
        this(x, y, 0);
    }

    public StartScreenObject() {
        this(0, 0, 0);
    }
}
