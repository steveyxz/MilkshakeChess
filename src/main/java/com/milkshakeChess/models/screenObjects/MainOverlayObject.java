package com.milkshakeChess.models.screenObjects;

import com.milkshakeChess.models.screenObjects.ScreenObject;

public abstract class MainOverlayObject extends ScreenObject {

    public MainOverlayObject(int x, int y, int rotation, int width, int height) {
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

    public MainOverlayObject(int x, int y, int rotation) {
        this(x, y, rotation, 50, 50);
    }

    public MainOverlayObject(int x, int y) {
        this(x, y, 0);
    }

    public MainOverlayObject() {
        this(0, 0, 0);
    }
}
