package com.milkshakeChess.models;

import com.milkshakeChess.enums.id.PieceID;
import com.milkshakeChess.enums.id.SideID;
import com.milkshakeChess.util.Board;
import com.milkshakeChess.util.Move;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

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

    public abstract void render(Graphics g, boolean isWhite);

    public abstract void tick();

    /**
     * scale image
     *
     * @param sbi image to scale
     * @param imageType type of image
     * @param dWidth width of destination image
     * @param dHeight height of destination image
     * @param fWidth x-factor for transformation / scaling
     * @param fHeight y-factor for transformation / scaling
     * @return scaled image
     */
    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }

    public static int convIndexToOpposite(int index, int numberOfRows, int amountOnEachRow) {
        int rowNumber = index / amountOnEachRow + 1;
        int newRowNumber = numberOfRows - rowNumber;
        return Move.convXYToIndex(new int[] {index % 8, newRowNumber});
    }

    public static int convIndexToOpposite(int index) {
        return convIndexToOpposite(index, 8, 8);
    }
}
