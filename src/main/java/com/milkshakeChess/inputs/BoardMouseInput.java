package com.milkshakeChess.inputs;

import com.milkshakeChess.Game;
import com.milkshakeChess.enums.gameChoice.WindowState;
import com.milkshakeChess.enums.id.PieceID;
import com.milkshakeChess.enums.id.SideID;
import com.milkshakeChess.models.Piece;
import com.milkshakeChess.pieces.Empty;
import com.milkshakeChess.util.Board;
import com.milkshakeChess.util.Move;

import static com.milkshakeChess.Game.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardMouseInput extends MouseAdapter {

    public static SideID sideOn = SideID.White;
    private final Game game;
    private final Board focusedBoard;
    private int focusedIndex;

    private boolean hasFocused = false;

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

        System.out.println(focusedBoard.squareWidth);
        System.out.println(focusedBoard.startX);
        System.out.println(focusedBoard.startY);

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
            hasFocused = true;
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
        if (!hasFocused) {
            return;
        }
        Piece focusedPiece = focusedBoard.getSquare(focusedIndex);
        int releasedIndex = focusedBoard.convertWindowXYToIndex(new int[]{e.getX(), e.getY()});
        if (focusedBoard.move(new Move(focusedBoard, focusedIndex, releasedIndex), false) == 1) {
            int[] xy = focusedBoard.convertIndexToWindowXY(focusedIndex);
            focusedPiece.setX(xy[0]);
            focusedPiece.setY(xy[1]);
            return;
        }
        int[] releasedXY = focusedBoard.convertIndexToWindowXY(releasedIndex);
        int[] focusedXY = focusedBoard.convertIndexToWindowXY(focusedIndex);
        focusedBoard.setSquare(releasedIndex, focusedBoard.getSquare(focusedIndex));
        focusedBoard.setSquare(focusedIndex, new Empty(focusedXY[0], focusedXY[1], focusedBoard.squareWidth - 5, focusedBoard.squareWidth - 5, focusedBoard, PieceID.Bishop, SideID.Black, Move.convIndexToXY(focusedIndex)[0], Move.convIndexToXY(focusedIndex)[1]));
        focusedBoard.getSquare(releasedIndex).setX(releasedXY[0]);
        focusedBoard.getSquare(releasedIndex).setY(releasedXY[1]);
        sideOn = SideID.toggle(sideOn);
    }
}
