package com.milkshakeChess.models.interfaces;

public interface IObjectContainer<E> {

    void addItem(E object);

    void removeItem(E object);

    void clearItems();
}
