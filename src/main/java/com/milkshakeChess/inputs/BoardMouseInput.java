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

    public static SideID sideOn = SideID.White;
    private final Game game;
    private final Board focusedBoard;
    private int focusedIndex;

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
            int boardIndex = focusedBoard.convertWindowXYToIndex(new int[]{mouseX, mouseY});
            Piece testPiece = focusedBoard.getSquare(boardIndex);
            if (!(testPiece.getSideID().equals(sideOn))) {
                return;
            }
            if (testPiece instanceof Empty) {
                return;
            }

            focusedIndex = boardIndex;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (Game.currentState != WindowState.Game) {
            return;
        }
        Piece focusedPiece = focusedBoard.getSquare(focusedIndex);
        if (focusedPiece == null) {
            return;
        }
        if (focusedPiece.getSideID() != sideOn) {
            return;
        }
        focusedPiece.setX(e.getX() - 20);
        focusedPiece.setY(e.getY() - 20);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Piece focusedPiece = focusedBoard.getSquare(focusedIndex);
        if (focusedBoard.move(new Move(focusedBoard, focusedIndex, focusedBoard.convertWindowXYToIndex(new int[]{e.getX(), e.getY()})), false) == 1) {
            int[] xy = focusedBoard.convertIndexToWindowXY(focusedIndex);
            focusedPiece.setX(xy[0]);
            focusedPiece.setY(xy[1]);
        }
    }
}
