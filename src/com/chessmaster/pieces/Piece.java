package com.chessmaster.pieces;

public interface Piece {

    boolean isMoveActionValid(int moveRow, int moveCol);
    void move(int row, int col);
    void attack();
    void render();
}
