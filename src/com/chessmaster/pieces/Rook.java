package com.chessmaster.pieces;

import com.chessmaster.config.PieceColor;
import com.chessmaster.manager.GameBoard;
import com.chessmaster.pieces.common.Piece;

public class Rook extends Piece {

	public int power;
	public int id;


	public Rook(String color, int row, int col) {
		super(color,"R", row, col);

		this.power  = 5;
		this.id 	= 4;
	}


	@Override
	public boolean isMoveActionValid(int moveRow, int moveCol) {
		if (moveCol >9 ||
				moveCol < 0 ||
				moveRow > 9 ||
				moveRow < 0 ){
			return false;
		}
		if ((moveCol == col ^ moveRow ==row)){
			if (row >moveRow){
				int checkRow = row-1;
				while(checkRow>=moveRow){
					if (GameBoard.board[checkRow][moveCol]!=null)
						return false;
					checkRow--;
				}
			}
			if (row <moveRow){
				int checkRow = row+1;
				while(checkRow<=moveRow){
					if (GameBoard.board[checkRow][moveCol]!=null)
						return false;
					checkRow++;
				}
			}
			if (col <moveCol){
				int checkCol = col+1;
				while(checkCol<=moveCol){
					if (checkCol>0) {
						if (GameBoard.board[moveRow][checkCol] != null)
							return false;
						checkCol++;
					}
				}
			}
			if (col >moveCol){
				int checkCol = col-1;
				while(checkCol>=moveCol) {
					if (checkCol > 0) {
						if (GameBoard.board[moveRow][checkCol] != null)
							return false;
						checkCol++;
					}
				}
			}
		}
		return (moveCol == col ^ moveRow ==row);
	}

	@Override
	public String render() {
		String path;
		if (this.color.equals(PieceColor.WHITE)){
			path = ".\\src\\resources\\WhiteRook.png";
		}
		else path = ".\\src\\resources\\BlackRook.png";
		return path;
	}
}
