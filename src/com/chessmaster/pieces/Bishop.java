package com.chessmaster.pieces;
public class Bishop  implements Piece{
	
	public String color;
	public int power;
	public int id;

	public int row;
	public int col;
	
	public Bishop(String color, int row, int col) {
		
		this.color  = color;
		this.power  = 5;
		this.id 	= 2;

		this.row = row;
		this.col = col;
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
	public void move(int row, int col) {

		if(isMoveActionValid(row,col)){

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
