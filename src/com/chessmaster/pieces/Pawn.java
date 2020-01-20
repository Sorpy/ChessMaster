package com.chessmaster.pieces;
public class Pawn implements Piece{

	boolean isFirstMove;
	
	public String color;
	public int power;
	public int id;
	
	public int row;
	public int col;
	
	public Pawn(String color, int row, int col, boolean isFirstMove) {
		
		this.color  = color;
		this.power  = 1;
		this.id 	= 1;
		
		this.row 	= row;
		this.col 	= col;
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
	public void move(int row, int col) {
		
		if(isMoveActionValid(row, col)) {
			
			this.row = row;
			this.col = col;
		}
	}

	@Override
	public void attack() {
		
	}

	@Override
	public void render() {
		
	}
}
