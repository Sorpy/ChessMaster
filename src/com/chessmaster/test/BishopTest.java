package com.chessmaster.test;

import com.chessmaster.config.PieceColor;
import com.chessmaster.pieces.Bishop;
import com.chessmaster.pieces.common.Piece;

public class BishopTest {
    public static void testPieceMovement() {

        System.out.print("testBishopMovement - ");

        // AAA
        // 1. Arrange
        Piece testElement = new Bishop(PieceColor.BLACK, 5, 3);

        // 2. Act
        boolean isValid = testElement.isMoveActionValid(6, 2);

        // 3. Assert
        String testMessage = (isValid) ? "Valid" : "Fail";
        System.out.println(testMessage);
    }

    public static void run() {
        testPieceMovement();
    }
}
