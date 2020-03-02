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

    public boolean move(int moveRow, int moveCol) {

        boolean hasAttacked = false;
        if (isMoveActionValid(moveRow, moveCol)) {

            if (GameBoard.board[moveRow][moveCol]!=null){
                hasAttacked=true;
            }
            GameBoard.board[row][col] = GameBoard.board[this.row][this.col];
            GameBoard.board[this.row][this.col]= null;

            this.row = moveRow;
            this.col = moveCol;
            GameBoard.movePiece(this);

        }
        return hasAttacked;
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