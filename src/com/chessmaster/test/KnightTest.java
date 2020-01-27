package com.chessmaster.test;

import com.chessmaster.config.PieceColor;
import com.chessmaster.pieces.Knight;
import com.chessmaster.pieces.common.Piece;

public class KnightTest {
    public static void testPieceMovement() {

        System.out.print("testKnightMovement - ");

        // AAA
        // 1. Arrange
        Piece testElement = new Knight(PieceColor.BLACK, 2, 1);

        // 2. Act
        boolean isValid = testElement.isMoveActionValid(4, 2);

        // 3. Assert
        String testMessage = (isValid) ? "Valid" : "Fail";
        System.out.println(testMessage);
    }

    public static void run() {
        testPieceMovement();
    }
}
