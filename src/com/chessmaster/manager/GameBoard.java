package com.chessmaster.manager;
import com.chessmaster.config.PieceColor;
import com.chessmaster.pieces.*;
import com.chessmaster.pieces.common.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameBoard extends JPanel{

	private final int TILE_SIDE = 50;

	boolean firstTileSelected = false;


	boolean pieceSelected = false;

	private int selectedRow = -1;
	private int selectedCol = -1;

	private int secondSelectRow = -1;
	private int secondSelectCol = -1;

	private int whitePlayerPoints = 0;
	private int blackPlayerPoints = 0;


	private int clickCounter = 0;
	public static String playerTurn = PieceColor.WHITE;

	public static Piece board[][] = new Piece[10][10];
	public static final int MAX_BOARD_SIZE = 10;
	public static final int INIT_BOARD_WHITE_MAIN_PIECE_POSITION = 9;
	public static final int INIT_BOARD_BLACK_MAIN_PIECE_POSITION = 0;

	public static final int INIT_BOARD_WHITE_EXTRA_PIECE_POSITION = 8;
	public static final int INIT_BOARD_BLACK_EXTRA_PIECE_POSITION = 1;

	public static Piece activePiece;

	public GameBoard() {
		addMouseListener(new MouseEventListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int x = e.getX();
				int y = e.getY();

				if (clickCounter==2){
					clickCounter = 0;
					secondSelectCol=-1;
					secondSelectRow=-1;
					selectedCol=-1;
					selectedRow=-1;
					firstTileSelected = false;
				}

				if (clickCounter==0) {
					selectedRow = y / TILE_SIDE;
					selectedCol = x / TILE_SIDE;
					clickCounter++;
				}
				else if(clickCounter==1){
					secondSelectRow = y / TILE_SIDE;
					secondSelectCol = x / TILE_SIDE;
					clickCounter++;
				}

				updateUI();
			}
		});
	}

	public static void init() {

		board[INIT_BOARD_BLACK_MAIN_PIECE_POSITION][0] = new Rook(PieceColor.BLACK, 0, 0);
		board[INIT_BOARD_BLACK_MAIN_PIECE_POSITION][1] = new Knight(PieceColor.BLACK, 0, 1);
		board[3][5] = new Bishop(PieceColor.BLACK, 3, 5);
		board[INIT_BOARD_BLACK_MAIN_PIECE_POSITION][3] = new King(PieceColor.BLACK, 0, 3);
		board[INIT_BOARD_BLACK_MAIN_PIECE_POSITION][4] = new Queen(PieceColor.BLACK, 0, 4);

//		board[INIT_BOARD_BLACK_EXTRA_PIECE_POSITION][0] = new Pawn(PieceColor.BLACK, 1, 0,false);
//		board[INIT_BOARD_BLACK_EXTRA_PIECE_POSITION][1] = new Pawn(PieceColor.BLACK, 1, 1,true);
//		board[INIT_BOARD_BLACK_EXTRA_PIECE_POSITION][2] = new Pawn(PieceColor.BLACK, 1, 2,true);
//		board[INIT_BOARD_BLACK_EXTRA_PIECE_POSITION][3] = new Pawn(PieceColor.BLACK, 1, 3,true);
//		board[INIT_BOARD_BLACK_EXTRA_PIECE_POSITION][4] = new Pawn(PieceColor.BLACK, 1, 4,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POSITION][5] = new Pawn(PieceColor.BLACK, 1, 5,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POSITION][6] = new Pawn(PieceColor.BLACK, 1, 6,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POSITION][7] = new Pawn(PieceColor.BLACK, 1, 7,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POSITION][8] = new Pawn(PieceColor.BLACK, 1, 8,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POSITION][9] = new Pawn(PieceColor.BLACK, 1, 9,true);

		board[INIT_BOARD_WHITE_MAIN_PIECE_POSITION][0] = new Rook(PieceColor.WHITE, 9, 0);
		board[INIT_BOARD_WHITE_MAIN_PIECE_POSITION][1] = new Knight(PieceColor.WHITE, 9, 1);
		board[INIT_BOARD_WHITE_MAIN_PIECE_POSITION][2] = new Bishop(PieceColor.WHITE, 9, 2);
		board[INIT_BOARD_WHITE_MAIN_PIECE_POSITION][3] = new King(PieceColor.WHITE, 9, 3);
		board[INIT_BOARD_WHITE_MAIN_PIECE_POSITION][4] = new Queen(PieceColor.WHITE, 9, 4);

		board[INIT_BOARD_WHITE_EXTRA_PIECE_POSITION][0] = new Pawn(PieceColor.WHITE, 8, 0,true);
		board[INIT_BOARD_WHITE_EXTRA_PIECE_POSITION][1] = new Pawn(PieceColor.WHITE, 8, 1,true);
		board[INIT_BOARD_WHITE_EXTRA_PIECE_POSITION][2] = new Pawn(PieceColor.WHITE, 8, 2,true);
		board[INIT_BOARD_WHITE_EXTRA_PIECE_POSITION][3] = new Pawn(PieceColor.WHITE, 8, 3,true);
		board[INIT_BOARD_WHITE_EXTRA_PIECE_POSITION][4] = new Pawn(PieceColor.WHITE, 8, 4,true);
	}

	public boolean isPieceSelectable(int row, int col) {
		if (board[row][col] != null) {
			if (board[row][col].getColor().toLowerCase().equals(playerTurn.toLowerCase())){
				return true;
			}
		}
		clickCounter=0;
		return false;
	}

	public static void selectPiece(int row, int col) {
		activePiece = board[row][col];
	}

	public static boolean isPieceMoveValid(int row, int col) {
		return activePiece.isMoveActionValid(row, col);
	}

	public static void movePiece(Piece piece) {

		int row = piece.getRow();
		int col = piece.getCol();

		board[row][col] = piece;

		playerTurn = playerTurn.equals(PieceColor.WHITE) ? "#000000" : "#ffffff";
	}

	public void paint(Graphics g) {

		for(int row = 0; row < 10; row++) {
			for(int col = 0; col < 10; col++) {
				render(g, row, col);
			}
		}
		if (pieceSelected) {
			for (int row = 0; row < 10; row++) {
				for (int col = 0; col < 10; col++) {
					if (isPieceSelectable(selectedRow, selectedCol))
						showPossibleMoves(g, selectedRow, selectedCol, row, col);
				}
			}
		}
		g.setColor(new Color(31, 224, 255));
		g.fillRect(0,500,500,100);
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Current Player",25,550);
		g.drawString("Black Points - " + blackPlayerPoints,250,540);
		g.drawString("White Points - " + whitePlayerPoints,250,570 );

		g.setColor(Color.GRAY);
		g.fillRect(160,515,70,70);

		g.setColor(Color.decode(playerTurn));
		g.fillRect(165,520,60,60);

	}

	private void render(Graphics g, int row, int col) {

		boolean isRowEven 	= (row % 2 == 0);
		boolean isColEvent 	= (col % 2 == 0);

		boolean isWhite 	= (isRowEven && isColEvent) ||
				(!isRowEven && !isColEvent);

		Color tileColor = isWhite ? Color.WHITE
				: Color.GRAY;

		boolean isSelected = (row == selectedRow) &&
				(col == selectedCol);

		boolean isSecondSelected = (row == secondSelectRow) &&
				(col == secondSelectCol);

		int tileX = col * TILE_SIDE;
		int tileY = row * TILE_SIDE;


		if(isSelected && isPieceSelectable(row,col)&& !firstTileSelected) {
			g.setColor(tileColor);
			pieceSelected =true;


			tileColor = new Color(255,0,0,50);
			g.setColor(tileColor);
			g.fillRect(tileX, tileY, TILE_SIDE, TILE_SIDE);
			firstTileSelected = true;
			return;
		}

		if(isSecondSelected && firstTileSelected) {
			g.setColor(tileColor);
			if(firstTileSelected) {
				if(board[selectedRow][selectedCol].move(secondSelectRow,secondSelectCol)){
					if (playerTurn.equals(PieceColor.WHITE)){
						blackPlayerPoints+=10;
					} else whitePlayerPoints+=10;
				}

				firstTileSelected = false;
			}
			return;
		}

		g.setColor(tileColor);
		g.fillRect(tileX, tileY, TILE_SIDE, TILE_SIDE);



		BufferedImage myPicture = null;
		if (board[row][col] != null) {
			try {
				myPicture = ImageIO.read(new File(board[row][col].render()));
			} catch (IOException | NullPointerException ex) {
				ex.printStackTrace();
			}

			//JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			g.drawImage(myPicture,tileX,tileY,this);
		}
	}

	private void showPossibleMoves(Graphics g, int pieceRow, int pieceCol, int row, int col) {
		if (board[pieceRow][pieceCol].isMoveActionValid(row, col)) {
			if (board[row][col] == null) {
				int tileX = col * TILE_SIDE;
				int tileY = row * TILE_SIDE;

				Color tileColor;
				tileColor = new Color(255, 0, 0, 25);
				g.setColor(tileColor);
				g.fillRect(tileX, tileY, TILE_SIDE, TILE_SIDE);
			} else {
				int tileX = col * TILE_SIDE;
				int tileY = row * TILE_SIDE;

				Color tileColor;
				tileColor = new Color(37, 255, 41, 65);
				g.setColor(tileColor);
				g.fillRect(tileX, tileY, TILE_SIDE, TILE_SIDE);
			}
		}
	}
}