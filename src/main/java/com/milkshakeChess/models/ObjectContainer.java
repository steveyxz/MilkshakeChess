package com.milkshakeChess.models;

import java.util.ArrayList;

public abstract class ObjectContainer<E> {

    protected ArrayList<E> objects = new ArrayList<>();

    public void addItem(E object) {
        this.objects.add(object);
    }

    public void removeItem(E object) {
        this.objects.remove(object);
    }

    public void clearItems() {
        this.objects.clear();
    }
}
