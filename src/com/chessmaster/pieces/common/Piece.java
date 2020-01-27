package com.chessmaster.pieces.common;

import com.chessmaster.manager.GameRunner;

import java.util.Iterator;

public abstract class Piece {

    private String color;
    public int row;
    public int col;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }



    public Piece(String color, int row, int col) {

        this.color = color;
        this.row = row;
        this.col = col;
    }
    public abstract boolean isMoveActionValid(int moveRow, int moveCol);

    public boolean move(int moveRow, int moveCol) {

        if (isMoveActionValid(moveRow, moveCol)) {

            for (Iterator<Piece> iterator = GameRunner.pieceList.iterator(); iterator.hasNext();){
                Piece piece = iterator.next();
                if (piece.getRow() == moveRow &&
                        piece.getCol() == moveCol) {
                    if (!piece.getColor().equals(this.getColor())) {
                        iterator.remove();
                        System.out.printf("%s piece destroyed",piece.getClass().getSimpleName());
                        this.row = moveRow;
                        this.col = moveCol;
                        return true;
                    } else if (piece.getColor().equals(this.getColor())){
                        System.out.println("Space occupied by a friendly piece");
                        return false;
                    }
                    else {
                        this.row = moveRow;
                        this.col = moveCol;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public abstract void render();

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " color='" + color + '\'' +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}