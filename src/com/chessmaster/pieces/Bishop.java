package com.chessmaster.pieces;

import com.chessmaster.config.PieceColor;
import com.chessmaster.manager.GameBoard;
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

			if (Math.abs(row - moveRow) ==
					Math.abs(col - moveCol)) {
				if (moveCol == col && moveRow == row) {
					return false;
				}
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

		return (Math.abs(row - moveRow) ==
				Math.abs(col - moveCol));
	}

	@Override
	public String render() {
		String path;
		if (this.color.equals(PieceColor.WHITE)){
			path = ".\\src\\resources\\WhiteBishop.png";
		}
		else path = ".\\src\\resources\\BlackBishop.png";
		return path;
	}
}
