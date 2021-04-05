package com.milkshakeChess.models;

import com.milkshakeChess.Game;
import com.milkshakeChess.models.interfaces.IHasClickSpots;
import com.milkshakeChess.models.interfaces.IObjectContainer;
import com.milkshakeChess.models.screenObjects.ScreenObject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Consumer;

public abstract class Screen extends MouseAdapter implements IObjectContainer<ScreenObject>, IHasClickSpots {

    public ArrayList<ScreenObject> objects = new ArrayList<>();
    public HashMap<LinkedList<Integer>, Consumer<Integer>> clickySpots = new HashMap<>();
    protected Game game;


    public Screen(Game game) {
        this.game = game;
    }

    public static boolean squareContainsPoint(Rectangle rect, Point point) {
        return rect.contains(point);
    }

    @Override
    public void addItem(ScreenObject object) {
        objects.add(object);
    }

    @Override
    public void removeItem(ScreenObject object) {
        objects.remove(object);
    }

    @SuppressWarnings("all")
    @Override
    public void clearItems() {
        ArrayList<ScreenObject> objects1 = (ArrayList<ScreenObject>) objects.clone();
        for (int i = 0; i < objects1.size(); i++) {
            objects.remove(objects1.get(i));
        }
        objects.clear();
        objects1.clear();
    }

    @Override
    @SuppressWarnings("all")
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        for (int b = 0; b < clickySpots.size(); b++) {
            LinkedList<Integer> i = (LinkedList<Integer>) clickySpots.keySet().toArray()[b];
            if (squareContainsPoint(new Rectangle(i.get(0), i.get(1), i.get(2) - i.get(0), i.get(3) - i.get(1)), new Point(mouseX, mouseY))) {
                clickySpots.get(i).accept(1);
                break;
            }
        }
    }

    public abstract void render(Graphics g);

    public abstract void tick();
}
