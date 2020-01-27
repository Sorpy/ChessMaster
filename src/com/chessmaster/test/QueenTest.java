package com.chessmaster.test;

import com.chessmaster.config.PieceColor;
import com.chessmaster.pieces.Queen;
import com.chessmaster.pieces.common.Piece;

public class QueenTest {
    public static void testPieceMovement() {

        System.out.print("testQueenMovement - ");

        // AAA
        // 1. Arrange
        Piece testElement = new Queen(PieceColor.BLACK, 5, 3);

        // 2. Act
        boolean isValid = testElement.isMoveActionValid(0, 3);

        // 3. Assert
        String testMessage = (isValid) ? "Valid" : "Fail";
        System.out.println(testMessage);
    }

    public static void run() {
        testPieceMovement();
    }
}
