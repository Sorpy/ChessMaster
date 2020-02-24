package com.chessmaster.pieces;

import com.chessmaster.config.PieceColor;
import com.chessmaster.manager.GameBoard;
import com.chessmaster.pieces.common.Piece;

public class Queen extends Piece {

    public int power;
    public int id;


    public Queen(String color, int row, int col) {
        super(color,"Q", row, col);

        this.power  = 5;
        this.id 	= 6;
    }


    @Override
	public boolean isMoveActionValid(int moveRow, int moveCol) {
        if (moveCol >9 ||
                moveCol < 0 ||
                moveRow > 9 ||
                moveRow < 0 ){
            return false;
        }
        if (moveCol == col && moveRow == row) {
            return false;
        }
        if ((moveCol == col ^
                moveRow ==row) ^
                (Math.abs(row - moveRow) ==
                        Math.abs(col - moveCol))) {
            if ((moveCol == col ^ moveRow == row)) {
                if (row > moveRow) {
                    int checkRow = row - 1;
                    while (checkRow >= moveRow) {
                        if (GameBoard.board[checkRow][moveCol] != null)
                            return false;
                        checkRow--;
                    }
                }
                if (row < moveRow) {
                    int checkRow = row + 1;
                    while (checkRow <= moveRow) {
                        if (GameBoard.board[checkRow][moveCol] != null)
                            return false;
                        checkRow++;
                    }
                }
                if (col < moveCol) {
                    int checkCol = col + 1;
                    while (checkCol <= moveCol) {
                        if (checkCol > 0) {
                            if (GameBoard.board[moveRow][checkCol] != null)
                                return false;
                            checkCol++;
                        }
                    }
                }
                if (col > moveCol) {
                    int checkCol = col - 1;
                    while (checkCol >= moveCol) {
                        if (checkCol > 0) {
                            if (GameBoard.board[moveRow][checkCol] != null)
                                return false;
                            checkCol++;
                        }
                    }
                }
            }

            if (Math.abs(row - moveRow) ==
                    Math.abs(col - moveCol)) {
                int rowOffset;
                int colOffset;
                if(row < moveRow){
                    rowOffset = 1;
                }else{
                    rowOffset = -1;
                }

                if(col < moveCol){
                    colOffset = 1;
                }else{
                    colOffset = -1;
                }

                int checkRow = row + rowOffset;
                int checkCol = col + colOffset;
                while (checkCol != moveCol && moveRow != checkRow) {
                    if (GameBoard.board[checkRow][checkCol] != null){
                        return false;
                    }
                    checkCol +=colOffset;
                    checkRow +=rowOffset;
                }

            }
        }
		return (moveCol == col ^
                moveRow ==row) ^
                (Math.abs(row - moveRow) ==
                Math.abs(col - moveCol));
	}



    @Override
    public String render() {
        String path;
        if (this.color.equals(PieceColor.WHITE)){
            path = ".\\src\\resources\\WhiteQueen.png";
        }
        else path = ".\\src\\resources\\BlackQueen.png";
        return path;
    }
}