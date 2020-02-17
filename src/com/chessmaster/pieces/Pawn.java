package com.chessmaster.pieces;

import com.chessmaster.config.PieceColor;
import com.chessmaster.manager.GameRunner;
import com.chessmaster.pieces.common.Piece;

public class Pawn extends Piece {

	boolean isFirstMove;

	public Pawn(String color, int row, int col, boolean isFirstMove) {
		super(color,"P", row, col);

		this.power  = 1;
		this.id 	= 1;

		this.isFirstMove = isFirstMove;
	}


	@Override
	public boolean isMoveActionValid(int moveRow, int moveCol) {
		if (moveCol >9 ||
				moveCol < 0 ||
				moveRow > 9 ||
				moveRow < 0 ){
			return false;
		}
		for (Piece piece : GameRunner.pieceList) {
			if (!piece.getColor().equals(getColor())){
				if ((piece.getRow() == moveRow) && (piece.getCol()==moveCol)){
					return true;
				}
			}
		}

		if ((moveRow - row == 1) && (moveCol == col)) {
			isFirstMove =false;
			return true;
		}
		else if (isFirstMove && (moveRow - row == 2) && (moveCol == col)){
			isFirstMove=false;
			return true;
		}
		return false;
	}

	@Override
	public String render() {
		String path;
		if (this.color.equals(PieceColor.WHITE)){
			path = "resources/WhitePawn.png";
		}
		else path = "resources/BlackPawn.png";
		return path;
	}
}
