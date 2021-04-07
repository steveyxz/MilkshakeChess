package com.milkshakeChess.util;

public class Move {

    public Board board;
    public int startIndex;
    public int endIndex;

    public Move(Board board, int startIndex, int endIndex) {
        this.board = board;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public Move(Board board, int startX, int startY, int endX, int endY) {
        this(board, new int[] {startX, startY}, new int[] {endX, endY});
    }

    public Move(Board board, int[] startXY, int[] endXY) {
        this(board, convXYToIndex(startXY), convXYToIndex(endXY));
    }

    public static int convXYToIndex(int[] xy) {
        return xy[0] + (xy[1] * 8);
    }

    public static int[] convIndexToXY(int index) {
        return new int[]{index % 8, index / 8};
    }

    public static boolean isMoveLegal() {
        return true;
    }

}
