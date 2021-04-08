package com.milkshakeChess.util;

import com.milkshakeChess.enums.gameChoice.GameType;
import com.milkshakeChess.enums.id.PieceID;
import com.milkshakeChess.enums.id.SideID;
import com.milkshakeChess.inputs.BoardMouseInput;
import com.milkshakeChess.models.Piece;
import com.milkshakeChess.pieces.*;
import com.milkshakeChess.settings.GameChoiceStorage;

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

            if (GameChoiceStorage.gameType == GameType.PlayerWhite || GameChoiceStorage.gameType == GameType.PlayerBoth) {
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
            } else if (GameChoiceStorage.gameType == GameType.PlayerBlack) {
                switch (c) {
                    case 'b' -> board.add(new Bishop(convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[0], convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[1], squareWidth - 5, squareWidth - 5, this, PieceID.Bishop, SideID.Black, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                    case 'q' -> board.add(new Queen(convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[0], convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[1], squareWidth - 5, squareWidth - 5, this, PieceID.Queen, SideID.Black, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                    case 'k' -> board.add(new King(convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[0], convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[1], squareWidth - 5, squareWidth - 5, this, PieceID.King, SideID.Black, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                    case 'p' -> board.add(new Pawn(convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[0], convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[1], squareWidth - 5, squareWidth - 5, this, PieceID.Pawn, SideID.Black, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                    case 'r' -> board.add(new Rook(convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[0], convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[1], squareWidth - 5, squareWidth - 5, this, PieceID.Rook, SideID.Black, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                    case 'n' -> board.add(new Knight(convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[0], convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[1], squareWidth - 5, squareWidth - 5, this, PieceID.Knight, SideID.Black, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                    case 'B' -> board.add(new Bishop(convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[0], convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[1], squareWidth - 5, squareWidth - 5, this, PieceID.Bishop, SideID.White, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                    case 'Q' -> board.add(new Queen(convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[0], convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[1], squareWidth - 5, squareWidth - 5, this, PieceID.Queen, SideID.White, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                    case 'K' -> board.add(new King(convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[0], convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[1], squareWidth - 5, squareWidth - 5, this, PieceID.King, SideID.White, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                    case 'P' -> board.add(new Pawn(convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[0], convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[1], squareWidth - 5, squareWidth - 5, this, PieceID.Pawn, SideID.White, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                    case 'R' -> board.add(new Rook(convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[0], convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[1], squareWidth - 5, squareWidth - 5, this, PieceID.Rook, SideID.White, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                    case 'N' -> board.add(new Knight(convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[0], convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[1], squareWidth - 5, squareWidth - 5, this, PieceID.Knight, SideID.White, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                    case '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                        for (int i = 0; i < Integer.parseInt(String.valueOf(c)); i++) {
                            board.add(new Empty(convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[0], convertIndexToWindowXY(Piece.convIndexToOpposite(squareAt))[1], squareWidth - 5, squareWidth - 5, this, PieceID.Empty, SideID.Empty, Move.convIndexToXY(squareAt)[0], Move.convIndexToXY(squareAt)[1]));
                            squareAt++;
                        }
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
        if (squareIndex > 63) {
            return null;
        }
        if (squareIndex < 0) {
            return null;
        }
        return board.get(squareIndex);
    }

    public void clearObjects() {
        board.clear();
    }

    public boolean isSquareThreatened(int squareIndex) {
        return true;
    }

    /**
     * @param move  The move that is to be made.
     * @param force For when checking if a move is legal, you can force the move
     *              (meaning it won't check for legality for a second time causing
     *              a recursion error)
     * @return An integer containing either 0 or 1, 0 meaning a valid move, 1 meaning an invalid move.
     */

    public int move(Move move, boolean force) {
        boolean isEnPassant = true;
        if (getSquare(move.endIndex) == null) {
            return 1;
        }
        if (getSquare(move.startIndex) == null) {
            throw new NullPointerException("One of the pieces were off the board. ???");
        }
        Piece endSquarePiece = getSquare(move.endIndex);
        Piece startSquarePiece = getSquare(move.startIndex);
        if (startSquarePiece.getSideID() == endSquarePiece.getSideID()) {
            return 1;
        }
        ArrayList<ArrayList<Integer>> allValidMovesForPiece = getAllValidMovesForPiece(startSquarePiece);
        if (startSquarePiece instanceof Pawn) {
            if (!allValidMovesForPiece.get(0).contains(move.endIndex) && !allValidMovesForPiece.get(1).contains(move.endIndex) && !allValidMovesForPiece.get(2).contains(move.endIndex)) {
                return 1;
            }
        } else {
            if (!allValidMovesForPiece.get(0).contains(move.endIndex) && !allValidMovesForPiece.get(1).contains(move.endIndex)) {
                return 1;
            }
        }
        if (!force) {
            if (!Move.isMoveLegal()) {
                return 1;
            }
        }
        if (startSquarePiece instanceof Pawn) {
            ((Pawn) startSquarePiece).isOnFirstMove = false;
        }
        return 0;
    }

    /**
     * This method gets all the valid moves for a specified piece. Valid moves do not
     * include moves regarding "legality", e.g. moving into check
     *
     * @param piece The piece being tested.
     * @return An arraylist containing the valid moves in the format [[possibleMoves], [possibleCaptures], [enPassantMoves], [enPassantCaptures]]
     */
    public ArrayList<ArrayList<Integer>> getAllValidMovesForPiece(Piece piece) {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        ArrayList<Integer> possibleCaptures = new ArrayList<>();
        ArrayList<Integer> possibleEnPassantMoves = new ArrayList<>();
        ArrayList<Integer> possibleEnPassantCaptures = new ArrayList<>();
        switch (piece.getPieceID()) {
            case Pawn -> {
                int indexToAdd;
                if (piece.getSideID() == SideID.Black) {
                    //Movement
                    int indexOnBoard = piece.getBoardIndex();
                    indexToAdd = indexOnBoard + 8;
                    if (isSquareEmpty(indexToAdd)) {
                        if (indexOnBoard < 56) {
                            possibleMoves.add(indexToAdd);
                        }
                        indexToAdd = indexOnBoard + 16;
                        if (indexOnBoard < 48) {
                            if (((Pawn) piece).isOnFirstMove) {
                                if (isSquareEmpty(indexToAdd)) {
                                    possibleMoves.add(indexToAdd);
                                }
                            }
                        }
                    }
                    //Captures
                    indexToAdd = indexOnBoard + 9;
                    if (indexOnBoard < 55 && indexOnBoard % 8 != 7) {
                        if (isSquareEmpty(indexToAdd, SideID.Black)) {
                            possibleCaptures.add(indexToAdd);
                        }
                    }
                    indexToAdd = indexOnBoard + 7;
                    if (indexOnBoard < 56 && indexOnBoard % 8 != 0) {
                        if (isSquareEmpty(indexToAdd, SideID.Black)) {
                            possibleCaptures.add(indexToAdd);
                        }
                    }

                    //EnPassant
                    indexToAdd = indexOnBoard + 1;
                    if (getSquare(indexToAdd).getPieceID() == PieceID.Pawn && getSquare(indexToAdd).getSideID() == SideID.Black) {
                        if (((Pawn) getSquare(indexToAdd)).isOnFirstMove) {
                            possibleEnPassantCaptures.add(indexOnBoard);
                            possibleEnPassantMoves.add(indexOnBoard + 9);
                        }
                    }
                    indexToAdd = indexOnBoard - 1;
                    if (getSquare(indexToAdd).getPieceID() == PieceID.Pawn && getSquare(indexToAdd).getSideID() == SideID.Black) {
                        if (((Pawn) getSquare(indexToAdd)).isOnFirstMove) {
                            possibleEnPassantCaptures.add(indexOnBoard);
                            possibleEnPassantMoves.add(indexOnBoard + 7);
                        }
                    }
                }
            }
        }
        ArrayList<ArrayList<Integer>> returned = new ArrayList<>();
        returned.add(possibleMoves);
        returned.add(possibleCaptures);
        returned.add(possibleEnPassantMoves);
        returned.add(possibleEnPassantCaptures);
        return returned;
    }

    public boolean isSquareEmpty(int index) {
        if (index > 63 || index < 0) {
            return true;
        }
        return getSquare(index) instanceof Empty;
    }

    public boolean isSquareEmpty(int index, SideID ofSide) {
        return !(getSquare(index) instanceof Empty) && getSquare(index).getSideID() == ofSide;
    }

    /**
     * Note that this function does not give a result of a flipped board.
     * It only gives for if white is on the bottom.
     *
     * @param index The index of the piece to be tested.
     * @return The XY on the window.
     */
    public int[] convertIndexToWindowXY(int index) {
        int[] xy = Move.convIndexToXY(index);
        return new int[]{startX + xy[0] * squareWidth, startY + xy[1] * squareWidth};
    }

    /**
     * Note that this method accepts XY on a window not the board.
     *
     * @param XY The XY on the specific window
     * @return The index on this board (assuming it is rendered on the window)
     */
    public int convertWindowXYToIndex(int[] XY) {
        int returnValue = (((XY[0] - startX) / squareWidth) + ((XY[1] - startY) / squareWidth) * 8) + 1;
        if (GameChoiceStorage.gameType == GameType.PlayerBlack) {
            return 64 - returnValue;
        }
        return returnValue - 1;
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
