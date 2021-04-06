package com.milkshakeChess.util;

import com.milkshakeChess.enums.id.PieceID;
import com.milkshakeChess.enums.id.SideID;
import com.milkshakeChess.models.Piece;
import com.milkshakeChess.pieces.*;

import java.awt.*;
import java.util.ArrayList;

public class Board {

    public ArrayList<Piece> board = new ArrayList<>();
    public int startX = 0;
    public int startY = 0;
    public int squareWidth = 0;
    private boolean isUnrendered = false;

    public Board(int startX, int startY, int squareWidth, Board board) {
        this.startX = startX;
        this.startY = startY;
        this.squareWidth = squareWidth;
        this.board = board.getBoard();
    }

    public Board(int startX, int startY, int squareWidth) {
        this.startX = startX;
        this.startY = startY;
        this.squareWidth = squareWidth;
    }

    public Board() {
        isUnrendered = true;
    }

    public ArrayList<Piece> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<Piece> board) {
        if (board.size() != 64) {
            return;
        }
        this.board = board;
    }

    public void resizeBoardPieces() {
        for (int i = 0; i < board.size(); i++) {
            Piece currentPiece = board.get(i);
            currentPiece.setX(convertIndexToWindowXY(currentPiece.getBoardIndex())[0]);
            currentPiece.setY(convertIndexToWindowXY(currentPiece.getBoardIndex())[1]);
        }
    }

    public void setThisBoard(String FEN) {
        int squareAt = 0;
        for (char c : FEN.toCharArray()) {
            switch (c) {
                case 'b' -> board.add(new Bishop(convertIndexToWindowXY(squareAt)[0], convertIndexToWindowXY(squareAt)[1], squareWidth - 5, squareWidth - 5, this, PieceID.Bishop, SideID.Black, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                case 'q' -> board.add(new Queen(convertIndexToWindowXY(squareAt)[0], convertIndexToWindowXY(squareAt)[1], squareWidth - 5, squareWidth - 5, this, PieceID.Queen, SideID.Black, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                case 'k' -> board.add(new King(convertIndexToWindowXY(squareAt)[0], convertIndexToWindowXY(squareAt)[1], squareWidth - 5, squareWidth - 5, this, PieceID.King, SideID.Black, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                case 'p' -> board.add(new Pawn(convertIndexToWindowXY(squareAt)[0], convertIndexToWindowXY(squareAt)[1], squareWidth - 5, squareWidth - 5, this, PieceID.Pawn, SideID.Black, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                case 'r' -> board.add(new Rook(convertIndexToWindowXY(squareAt)[0], convertIndexToWindowXY(squareAt)[1], squareWidth - 5, squareWidth - 5, this, PieceID.Rook, SideID.Black, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                case 'n' -> board.add(new Knight(convertIndexToWindowXY(squareAt)[0], convertIndexToWindowXY(squareAt)[1], squareWidth - 5, squareWidth - 5, this, PieceID.Knight, SideID.Black, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                case 'B' -> board.add(new Bishop(convertIndexToWindowXY(squareAt)[0], convertIndexToWindowXY(squareAt)[1], squareWidth - 5, squareWidth - 5, this, PieceID.Bishop, SideID.White, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                case 'Q' -> board.add(new Queen(convertIndexToWindowXY(squareAt)[0], convertIndexToWindowXY(squareAt)[1], squareWidth - 5, squareWidth - 5, this, PieceID.Queen, SideID.White, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                case 'K' -> board.add(new King(convertIndexToWindowXY(squareAt)[0], convertIndexToWindowXY(squareAt)[1], squareWidth - 5, squareWidth - 5, this, PieceID.King, SideID.White, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                case 'P' -> board.add(new Pawn(convertIndexToWindowXY(squareAt)[0], convertIndexToWindowXY(squareAt)[1], squareWidth - 5, squareWidth - 5, this, PieceID.Pawn, SideID.White, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                case 'R' -> board.add(new Rook(convertIndexToWindowXY(squareAt)[0], convertIndexToWindowXY(squareAt)[1], squareWidth - 5, squareWidth - 5, this, PieceID.Rook, SideID.White, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                case 'N' -> board.add(new Knight(convertIndexToWindowXY(squareAt)[0], convertIndexToWindowXY(squareAt)[1], squareWidth - 5, squareWidth - 5, this, PieceID.Knight, SideID.White, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                case '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(c)); i++) {
                        board.add(new Empty(convertIndexToWindowXY(squareAt)[0], convertIndexToWindowXY(squareAt)[1], squareWidth - 5, squareWidth - 5, this, PieceID.Empty, SideID.Empty, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                        squareAt++;
                    }
                }
            }
            switch (c) {
                case 'n', 'p', 'q', 'r', 'b', 'k', 'N', 'P', 'Q', 'R', 'B', 'K' -> {
                    squareAt++;
                }
            }
        }
    }

    public void addPiece(Piece piece) {
        board.add(piece);
    }

    public void removePiece(Piece piece) {
        board.remove(piece);
    }

    public void setSquare(int squareIndex, Piece piece) {
        board.set(squareIndex, piece);
    }

    public Piece getSquare(int squareIndex) {
        return board.get(squareIndex);
    }

    public void clearObjects() {
        board.clear();
    }

    public boolean isSquareThreatened(int squareIndex) {
        return true;
    }

    public void move(int startX, int startY, int endX, int endY, boolean force) {
        move(Move.convXYToIndex(new int[]{startX, startY}), Move.convXYToIndex(new int[]{endX, endY}), force);
    }

    /**
     *
     **/
    public int move(int startIndex, int endIndex, boolean force) {
        if (getSquare(startIndex).getSideID() == getSquare(endIndex).getSideID()) {
            return 1;
        }
        return 0;
    }

    public int[] convertIndexToWindowXY(int index) {
        int[] xy = Move.convIndexToXY(index);
        return new int[]{startX + xy[0] * squareWidth, startY + xy[1] * squareWidth};
    }

    public void render(Graphics g, boolean isWhite) {
        if (isUnrendered) {
            return;
        }
        for (int i = 0; i < board.size(); i++) {
            board.get(i).render(g, isWhite);
        }
    }

    public void tick() {
        for (int i = 0; i < board.size(); i++) {
            board.get(i).tick();
        }
    }
}
