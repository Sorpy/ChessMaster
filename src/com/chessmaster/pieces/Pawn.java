package com.chessmaster.pieces;

import com.chessmaster.config.PieceColor;
import com.chessmaster.manager.GameBoard;
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
		if (getColor().equals("#000000")) {
			if ((moveRow-row == 1) && (moveCol == col)) {
				return true;
			}if (isFirstMove && (moveRow-row == 2) && (moveCol == col)) {
				return true;
			}
		}
		else {
			if ((row - moveRow == 1) && (moveCol == col)) {
				return true;
			}if (isFirstMove && (row - moveRow == 2) && (moveCol == col)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void move(int moveRow, int moveCol) {
		if (isMoveActionValid(moveRow, moveCol)) {
			GameBoard.board[row][col] = GameBoard.board[this.row][this.col];
			GameBoard.board[this.row][this.col]= null;

			this.row = moveRow;
			this.col = moveCol;
			GameBoard.movePiece(this);
			isFirstMove = false;
		}

	}

	@Override
	public String render() {
		String path;
		if (this.color.equals(PieceColor.WHITE)){
			path = ".\\src\\resources\\WhitePawn.png";
		}
		else path = ".\\src\\resources\\BlackPawn.png";
		return path;
	}
}
