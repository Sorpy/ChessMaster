package com.chessmaster.pieces;

import com.chessmaster.config.PieceColor;
import com.chessmaster.pieces.common.Piece;

public class Bishop  extends Piece {

	public int power;
	public int id;

	
	public Bishop(String color, int row, int col) {
		super(color,"B",row,col);

		this.power  = 5;
		this.id 	= 2;
	}

	@Override
	public boolean isMoveActionValid(int moveRow, int moveCol) {
			if (moveCol >9 ||
				moveCol < 0 ||
				moveRow > 9 ||
				moveRow < 0 ){
				return false;
			}
		return (Math.abs(row - moveRow) ==
				Math.abs(col - moveCol));
	}

	@Override
	public String render() {
		String path;
		if (this.color.equals(PieceColor.WHITE)){
			path = "resources/WhiteBishop.png";
		}
		else path = "resources/BlackBishop.png";
		return path;
	}
}
