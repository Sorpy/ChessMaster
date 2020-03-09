package com.chessmaster.manager;

import com.chessmaster.pieces.common.Piece;

public class GameData {
    private int turnNumber;
    private int whitePlayerPoints;
    private int blackPlayerPoints;
    private String playerTurn;
    private Piece[][] board;

    public GameData(int turnNumber) {
        this.whitePlayerPoints = GameBoard.whitePlayerPoints;
        this.blackPlayerPoints = GameBoard.blackPlayerPoints;
        this.playerTurn = GameBoard.playerTurn;
        this.board = GameBoard.board;
        this.turnNumber=turnNumber;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public int getWhitePlayerPoints() {
        return whitePlayerPoints;
    }

    public void setWhitePlayerPoints(int whitePlayerPoints) {
        this.whitePlayerPoints = whitePlayerPoints;
    }

    public int getBlackPlayerPoints() {
        return blackPlayerPoints;
    }

    public void setBlackPlayerPoints(int blackPlayerPoints) {
        this.blackPlayerPoints = blackPlayerPoints;
    }

    public String getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(String playerTurn) {
        this.playerTurn = playerTurn;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }
}
