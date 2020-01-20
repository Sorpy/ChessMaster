package com.chessmaster.test;

import com.chessmaster.config.PieceColor;
import com.chessmaster.pieces.King;
import com.chessmaster.pieces.Piece;

public class KingTest {
    public static void testPieceMovement() {

        System.out.print("testKingMovement - ");

        // AAA
        // 1. Arrange
        Piece testElement = new King(PieceColor.BLACK, 5, 3);

        // 2. Act
        boolean isValid = testElement.isMoveActionValid(5, 4);

        // 3. Assert
        String testMessage = (isValid) ? "Valid" : "Fail";
        System.out.println(testMessage);
    }

    public static void run() {
        testPieceMovement();
    }
}
