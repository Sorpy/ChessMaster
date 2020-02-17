package com.chessmaster.pieces;

import com.chessmaster.config.PieceColor;
import com.chessmaster.pieces.common.Piece;

public class Knight extends Piece {

	public int power;
	public int id;

	public Knight(String color, int row, int col) {
		super(color,"Kn", row, col);

		this.power  = 5;
		this.id 	= 3;
	}


	@Override
	public boolean isMoveActionValid(int moveRow, int moveCol) {
		if (moveCol > 9 ||
				moveCol < 0 ||
				moveRow > 9 ||
				moveRow < 0) {
			return false;
		}
		return ((Math.abs(row - moveRow) == 1 &&
				Math.abs(col - moveCol) == 2) ||

				(Math.abs(row - moveRow) == 2 &&
						Math.abs(col - moveCol) == 1));
	}


	@Override
	public String render() {
		String path;
		if (this.color.equals(PieceColor.WHITE)){
			path = "resources/WhiteHorse.png";
		}
		else path = "resources/BlackHorse.png";
		return path;
	}
}
