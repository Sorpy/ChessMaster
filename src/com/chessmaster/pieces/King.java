package com.chessmaster.pieces;

import com.chessmaster.config.PieceColor;
import com.chessmaster.pieces.common.Piece;

public class King extends Piece {

	public int power;
	public int id;


	public King(String color, int row, int col) {
		super(color,"K", row, col);
		this.power  = 6;
		this.id 	= 5;
	}


	@Override
	public boolean isMoveActionValid(int moveRow, int moveCol) {
		if (moveCol >9 ||
				moveCol < 0 ||
				moveRow > 9 ||
				moveRow < 0 ){
			return false;
		}

		return (Math.abs(row - moveRow) <=1 &&
				Math.abs(col-moveCol)<=1);
	}




	@Override
	public String render() {
		String path;
		if (this.color.equals(PieceColor.WHITE)){
			path = ".\\src\\resources\\WhiteKing.png";
		}
		else path = ".\\src\\resources\\BlackKing.png";
		return path;
	}


}