package com.milkshakeChess.pieces;

import com.milkshakeChess.Game;
import com.milkshakeChess.enums.id.PieceID;
import com.milkshakeChess.enums.id.SideID;
import com.milkshakeChess.models.Piece;
import com.milkshakeChess.util.Board;

import java.awt.*;

public class Pawn extends Piece {

    public Pawn(int x, int y, int width, int height, Board board, PieceID pieceID, SideID sideID, int boardX, int boardY) {
        super(x, y, width, height, board, pieceID, sideID, boardX, boardY);
    }

    @Override
    public void render(Graphics g) {
        if (this.sideID == SideID.Black) {
            g.drawImage(Game.blackPawnIMG, x, y, board.squareWidth, board.squareWidth, null);
        } else {
            g.drawImage(Game.whitePawnIMG, x, y, board.squareWidth, board.squareWidth, null);
        }
    }

    @Override
    public void tick() {

    }
}
