package com.milkshakeChess.models;

import com.milkshakeChess.enums.id.PieceID;
import com.milkshakeChess.enums.id.SideID;
import com.milkshakeChess.util.Board;
import com.milkshakeChess.util.Move;

import java.awt.*;

public abstract class Piece {

    protected int x;
    protected int y;
    protected int boardX;
    protected int boardY;
    protected int boardIndex;
    protected int width;
    protected int height;
    protected Board board;
    protected PieceID pieceID;
    protected SideID sideID;

    public Piece(int x, int y, int width, int height, Board board, PieceID pieceID, SideID sideID, int boardX, int boardY) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.board = board;
        this.sideID = sideID;
        this.pieceID = pieceID;
        this.boardX = boardX;
        this.boardY = boardY;
        boardIndex = Move.convXYToIndex(new int[]{boardX, boardY});
    }

    public int getBoardX() {
        return boardX;
    }

    public void setBoardX(int boardX) {
        this.boardX = boardX;
        boardIndex = Move.convXYToIndex(new int[]{boardX, boardY});
    }

    public int getBoardY() {
        return boardY;
    }

    public void setBoardY(int boardY) {
        this.boardY = boardY;
        boardIndex = Move.convXYToIndex(new int[]{boardX, boardY});
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public PieceID getPieceID() {
        return pieceID;
    }

    public void setPieceID(PieceID pieceID) {
        this.pieceID = pieceID;
    }

    public SideID getSideID() {
        return sideID;
    }

    public void setSideID(SideID sideID) {
        this.sideID = sideID;
    }

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

    //MakeMove moves a piece to somewhere, replacing the current piece with an empty and the previous square with an empty
    public void makeMove(Move move) {

    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public void setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
        this.boardX = Move.convIndexToXY(boardIndex)[0];
        this.boardY = Move.convIndexToXY(boardIndex)[1];
    }

    public void kill() {
        this.board.removePiece(this);
    }

    public abstract void render(Graphics g);

    public abstract void tick();
}
