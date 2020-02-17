package com.chessmaster.pieces;

import com.chessmaster.config.PieceColor;
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
		return (moveCol == col ^
                moveRow ==row) ^
                (Math.abs(row - moveRow) ==
                Math.abs(col - moveCol));
	}



    @Override
    public String render() {
        String path;
        if (this.color.equals(PieceColor.WHITE)){
            path = "resources/WhiteQueen.png";
        }
        else path = "resources/BlackQueen.png";
        return path;
    }
}