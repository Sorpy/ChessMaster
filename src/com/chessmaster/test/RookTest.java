package com.chessmaster.test;

import com.chessmaster.config.PieceColor;
import com.chessmaster.pieces.Rook;
import com.chessmaster.pieces.common.Piece;

public class RookTest {
    public static void testPieceMovement() {

        System.out.print("testRookMovement - ");

        // AAA
        // 1. Arrange
        Piece testElement = new Rook(PieceColor.BLACK, 10, 0);

        // 2. Act
        boolean isValid = testElement.isMoveActionValid(10, 9);

        // 3. Assert
        String testMessage = (isValid) ? "Valid" : "Fail";
        System.out.println(testMessage);
    }

    public static void run() {
    testPieceMovement();
    }
}
