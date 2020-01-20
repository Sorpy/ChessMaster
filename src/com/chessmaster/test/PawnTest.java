package com.chessmaster.test;

import com.chessmaster.pieces.*;
import com.chessmaster.config.*;

public class PawnTest {

	public static void testIfPieceCanMoveOnlyOneSquare() {
		
		System.out.print("testIfPieceCanMoveOnlyOneSquare - ");
		
		// AAA
		// 1. Arrange
		Piece testElement = new Pawn(PieceColor.BLACK, 5, 0,false);
		
		// 2. Act 
		boolean isValid = testElement.isMoveActionValid(6, 0);
		
		// 3. Assert
		String testMessage = (isValid) ? "Valid" : "Fail";
		System.out.println(testMessage);
	}
	
	
	public static void testIfPieceCannotMoveMoreThanOneSquare() {

		System.out.print("testIfPieceCannotMoveMoreThanOneSquare - ");
		
		// AAA
		// 1. Arrange
		Piece testElement = new Pawn(PieceColor.BLACK, 8, 0,false);
		
		// 2. Act 
		boolean isValid = (testElement.isMoveActionValid(9, 0));
		
		// 3. Assert
		String testMessage = (isValid) ? "Valid" : "Fail";
		System.out.println(testMessage);
	}
	
	
	public static void run() {
		
		testIfPieceCanMoveOnlyOneSquare();
		testIfPieceCannotMoveMoreThanOneSquare();
	}
	
}
