package com.chessmaster.pieces.common;

import com.chessmaster.config.PieceColor;
import com.chessmaster.manager.GameBoard;
import com.chessmaster.manager.GameRunner;

import java.util.Iterator;

public abstract class Piece {

    protected String color;
    protected int power;
    protected int id;

    public int row;
    public int col;

    protected String signature;

    public Piece(String color,String signature, int row, int col) {

        this.color = color;
        this.row = row;
        this.col = col;
        this.signature = signature;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row)  {
        if(row >= 0 && row <= 9) {
            this.row = row;
        }
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int col) {

        if(col >= 0 && col <= 9) {
            this.col = col;
        }
    }

    public String getSignature() {

        return (this.color.equals(PieceColor.WHITE))
                ? ("w" + this.signature)
                : ("b" + this.signature);
    }




    public abstract boolean isMoveActionValid(int moveRow, int moveCol);

    public void move(int moveRow, int moveCol) {

        if (isMoveActionValid(moveRow, moveCol)) {

            GameBoard.board[row][col] = GameBoard.board[this.row][this.col];
            GameBoard.board[this.row][this.col]= null;

            this.row = moveRow;
            this.col = moveCol;
            GameBoard.movePiece(this);


//            for (Iterator<Piece> iterator = GameRunner.pieceList.iterator(); iterator.hasNext();){
//                Piece piece = iterator.next();
//                if (piece.getRow() == moveRow &&
//                        piece.getCol() == moveCol) {
//                    if (!piece.getColor().equals(this.getColor())) {
//                        iterator.remove();
//                        System.out.printf("%s piece destroyed",piece.getClass().getSimpleName());
//                        this.row = moveRow;
//                        this.col = moveCol;
//                        return true;
//                    } else if (piece.getColor().equals(this.getColor())){
//                        System.out.println("Space occupied by a friendly piece");
//                        return false;
//                    }
//                    else {
//                        this.row = moveRow;
//                        this.col = moveCol;
//                        return true;
//                    }
//                }
//            }
        }
    }


    public abstract String render();

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " color='" + color + '\'' +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}