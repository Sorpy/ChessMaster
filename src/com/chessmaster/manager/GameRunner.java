package com.chessmaster.manager;

import com.chessmaster.config.PieceColor;
import com.chessmaster.pieces.*;
import com.chessmaster.pieces.common.Blastable;
import com.chessmaster.pieces.common.Piece;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameRunner {

    String playerColor = PieceColor.WHITE;
    String playerColorCreate;
    //boolean gameOver = false;
    private Scanner scanner = new Scanner(System.in);
    private Piece newPiece;
    public static List<Piece> pieceList = new ArrayList<>();

    public void startGame() {
//        PawnTest.run();
//        System.out.println();
//        RookTest.run();
//        System.out.println();
//        QueenTest.run();
//        System.out.println();
//        BishopTest.run();
//        System.out.println();
//        KnightTest.run();
//        System.out.println();
//        KingTest.run();


        //setUpNewGameBoard();

        int chooser = 0;
        do {

            System.out.println("1. Create a new piece\n" +
                    "2. Move an exiting piece\n" +
                    "3. Exit\n" +
                    "4. List all pieces");
            try {
                chooser = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
            }

            switch (chooser) {
                case 1:
                    createNewPiece();
                    break;
                case 2:
                    chooseCoordinates();
                    break;
                case 4:
                    pieceList.forEach(piece -> System.out.println(piece.toString()));
            }

        } while (chooser != 3);
    }


    private void createNewPiece() {
        int figureId = 0;
        boolean isFigureCreated = false;
        chooseColor();
        do {

            System.out.println("Choose a figure to create \n " +
                    "To choose between the types of pieces choose a number \n" +
                    "1 - PAWN \n2 - BISHOP \n3 - KNIGHT \n4 - ROOK \n" +
                    "5 - KING \n6 - QUEEN \n7 - CHUD \n8 - Quit figure Creating");

            try {
                figureId = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
            }

            if (figureId < 8 && figureId > 0) {
                chooseCoordinates(figureId);
                isFigureCreated = true;
            } else if (figureId != 8) {
                System.out.printf("%o is not a valid choice", figureId);
            }
        } while (!isFigureCreated && figureId != 8);
    }

    private void chooseColor() {
        System.out.println("Choose colour of the piece\n" +
                "1. BLACK\n" +
                "2. WHITE");
        int playerColorChooser = 0;
        do {
            try {
                playerColorChooser = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter 1 or 2");
            }
        } while (playerColorChooser != 1 && playerColorChooser != 2);

        switch (playerColorChooser) {
            case 1:
                playerColorCreate = PieceColor.BLACK;
                break;
            case 2:
                playerColorCreate = PieceColor.WHITE;
                break;
        }
    }

    private void chooseCoordinates(int pieceId) {
        scanner.nextLine();
        String startingPosition;
        boolean possibleCoordinates = false;
        boolean isCoordinates = false;
        int row = -1;
        int col = -1;
        System.out.printf("Create a new %s figure \n " +
                "To declare new position of a piece enter ROW,COL \n" +
                "in this order. example (8,3)\n", playerColorCreate);
        do {
            startingPosition = scanner.nextLine();

            if (startingPosition.split(",").length == 2) {
                isCoordinates = true;
                row = Integer.parseInt(startingPosition.split(",")[0]);
                col = Integer.parseInt(startingPosition.split(",")[1]);
            } else {
                System.out.println("Please enter coordinates as in the example");
            }

            if (row >= 0 && row <= 9 && col >= 0 && col <= 9 && checkForPiece(row,col)) {
                possibleCoordinates = true;
                createPiece(pieceId, row, col);
            } else if (isCoordinates) {
                System.out.println("Coordinates of the piece out of boundary or occupied");
            }

        } while (!possibleCoordinates);


    }

    boolean checkForPiece(int row, int col){
        for (Piece piece : pieceList) {
            if (piece.getCol()==col && piece.getRow()==row){
                return false;
            }
        }
        return true;
    }

    private void createPiece(int pieceId, int row, int col) {
        switch (pieceId) {
            case 1:
                newPiece = new Pawn(playerColorCreate, row, col, true);
                break;
            case 2:
                newPiece = new Bishop(playerColorCreate, row, col);
                break;
            case 3:
                newPiece = new Knight(playerColorCreate, row, col);
                break;
            case 4:
                newPiece = new Rook(playerColorCreate, row, col);
                break;
            case 5:
                newPiece = new King(playerColorCreate, row, col);
                break;
            case 6:
                newPiece = new Queen(playerColorCreate, row, col);
                break;
            case 7:
                newPiece = new Chud(playerColorCreate, row, col);
                break;
        }
        System.out.printf("%s has been created\n", newPiece.toString());
        pieceList.add(newPiece);
    }

    private void chooseCoordinates() {
        int row = -1;
        int col = -1;
        String findPiece;
        boolean pieceFound = false;
        System.out.printf("Enter coordinates of %s a figure\n",playerColor);
        scanner.nextLine();
        findPiece = scanner.nextLine();

        if (findPiece.split(",").length == 2) {
            try {
                row = Integer.parseInt(findPiece.split(",")[0]);
                col = Integer.parseInt(findPiece.split(",")[1]);
            } catch (InputMismatchException e) {
                System.out.println("Please enter numbers");
            }
        } else {
            System.out.println("Please enter coordinates as in the example");
        }
        for (Piece piece : pieceList) {
            if (piece.getCol() == col && piece.getRow() == row && piece.getColor().equals(playerColor)) {
                System.out.printf("%s found\n", piece.getClass().getSimpleName());
                performAction(piece);
                pieceFound = true;

                playerColor = (playerColor.equals(PieceColor.BLACK)) ? PieceColor.WHITE : PieceColor.BLACK;
            }
        }
        if (!pieceFound) {
            System.out.printf("No %s piece was found with the given coordinates\n", playerColor);
        }
    }

    private void performAction(Piece piece) {
        System.out.println("Enter coordinates to perform action ex (6,5)");
        String moveActionCoordinates;
        boolean pieceDidAction = false;
        int row = 0;
        int col = 0;
        do {
            moveActionCoordinates = scanner.nextLine();
            if (moveActionCoordinates.split(",").length == 2) {
                try {
                    row = Integer.parseInt(moveActionCoordinates.split(",")[0]);
                    col = Integer.parseInt(moveActionCoordinates.split(",")[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter numbers");
                }
                if (piece instanceof Blastable) {
                    System.out.println("Do you want to shoot (y/n)");
                    String shootOption = scanner.nextLine();
                    if (shootOption.toLowerCase().equals("y")) {
                        ((Blastable) piece).shoot(row, col);
                        pieceDidAction = true;
                    }
                }else {
                    piece.move(row, col);
                    pieceDidAction = true;
                }
            }
        } while (!pieceDidAction);
    }
}

