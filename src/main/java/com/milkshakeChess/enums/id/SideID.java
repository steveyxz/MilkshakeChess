package com.milkshakeChess.enums.id;

public enum SideID {
    Black,
    White,
    Board,
    Empty;

    public static SideID toggle(SideID id) {
        if (id == Black) {
            return White;
        }
        return Black;
    }
}
