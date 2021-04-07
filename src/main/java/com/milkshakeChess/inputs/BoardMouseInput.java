package com.milkshakeChess.inputs;

import com.milkshakeChess.Game;
import com.milkshakeChess.enums.gameChoice.WindowState;
import com.milkshakeChess.enums.id.SideID;
import com.milkshakeChess.models.Piece;
import com.milkshakeChess.pieces.Empty;
import com.milkshakeChess.util.Board;
import com.milkshakeChess.util.Move;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardMouseInput extends MouseAdapter {

    private Game game;
    private Board focusedBoard;
    private Piece focusedPiece;

    public static SideID sideOn = SideID.White;

    public BoardMouseInput(Game game, Board focusedBoard) {
        this.game = game;
        this.focusedBoard = focusedBoard;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (Game.currentState != WindowState.Game) {
            return;
        }

        int mouseX = e.getX();
        int mouseY = e.getY();

        if (!(mouseX < focusedBoard.startX ||
                mouseX > focusedBoard.startX + focusedBoard.squareWidth * 8 ||
                mouseY < focusedBoard.startY ||
                mouseY > focusedBoard.startY + focusedBoard.squareWidth * 8)) {
            int boardIndex = focusedBoard.convertWindowXYToIndex(new int[] {mouseX, mouseY});
            Piece testPiece = focusedBoard.getSquare(boardIndex);
            if (testPiece.getSideID() != sideOn) {
                return;
            }
            if (testPiece instanceof Empty) {
                return;
            }
            testPiece = null;

            focusedPiece = focusedBoard.getSquare(boardIndex);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (focusedPiece == null) {
            return;
        }
        if (Game.currentState != WindowState.Game) {
            return;
        }
        focusedPiece.setX(e.getX());
        focusedPiece.setY(e.getY());
    }
}
