package com.chessmaster.pieces;

import com.chessmaster.manager.GameRunner;
import com.chessmaster.pieces.common.Blastable;
import com.chessmaster.pieces.common.Piece;

import java.util.Iterator;

public class Chud extends Piece implements Blastable {

    public int power;
    public int id;

    public Chud(String color, int row, int col) {
        super(color,"CD", row, col);

        this.power  = 15;
        this.id 	= 7;
    }

    @Override
    public void blast(int attackRow, int attackCol) {
        boolean didShoot =false;
        if (isAttackActionValid(attackRow, attackCol)) {
            for (Iterator<Piece> iterator = GameRunner.pieceList.iterator(); iterator.hasNext(); ) {
                Piece piece = iterator.next();
                if (piece.getRow() == attackRow &&
                        piece.getCol() == attackCol &&
                        !piece.getColor().equals(this.getColor())){
                    iterator.remove();
                    didShoot= true;
                    System.out.printf("%s piece destroyed",piece.getClass().getSimpleName());
                }
            }
        }
        if (!didShoot){
            System.out.println("No enemies to blast");
        }
    }

    @Override
    public boolean isMoveActionValid(int moveRow, int moveCol) {
        if (moveCol >9 ||
                moveCol < 0 ||
                moveRow > 9 ||
                moveRow < 0 ){
            return false;
        }
        return ((moveRow - row == 1) && (moveCol == col));
    }
    private boolean isAttackActionValid(int attackRow, int attackCol){
        return (Math.abs(row - attackRow) <=1 &&
                Math.abs(col-attackCol)<=1);
    }


    @Override
    public String render() {
return null;
    }
}
