package com.chessmaster.manager;
import com.chessmaster.config.PieceColor;
import com.chessmaster.pieces.*;
import com.chessmaster.pieces.common.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameBoard extends JPanel implements Runnable{

	private final int tileSide = 50;

	public static Piece board[][] = new Piece[10][10];
	public static final int MAX_BOARD_SIZE = 10;
	public static final int INIT_BOARD_WHITE_MAIN_PIECE_POCITION = 9;
	public static final int INIT_BOARD_BLACK_MAIN_PIECE_POCITION = 0;

	public static final int INIT_BOARD_WHITE_EXTRA_PIECE_POCITION = 8;
	public static final int INIT_BOARD_BLACK_EXTRA_PIECE_POCITION = 1;

	public static Piece activePiece;

	public static void init() {

		board[INIT_BOARD_BLACK_MAIN_PIECE_POCITION][0] = new Rook(PieceColor.BLACK, 0, 0);
		board[INIT_BOARD_BLACK_MAIN_PIECE_POCITION][1] = new Knight(PieceColor.BLACK, 0, 1);
		board[INIT_BOARD_BLACK_MAIN_PIECE_POCITION][2] = new Bishop(PieceColor.BLACK, 0, 2);
		board[INIT_BOARD_BLACK_MAIN_PIECE_POCITION][3] = new King(PieceColor.BLACK, 0, 3);
		board[INIT_BOARD_BLACK_MAIN_PIECE_POCITION][4] = new Queen(PieceColor.BLACK, 0, 4);

		board[INIT_BOARD_BLACK_EXTRA_PIECE_POCITION][0] = new Pawn(PieceColor.BLACK, 1, 0,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POCITION][1] = new Pawn(PieceColor.BLACK, 1, 1,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POCITION][2] = new Pawn(PieceColor.BLACK, 1, 2,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POCITION][3] = new Pawn(PieceColor.BLACK, 1, 3,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POCITION][4] = new Pawn(PieceColor.BLACK, 1, 4,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POCITION][5] = new Pawn(PieceColor.BLACK, 1, 5,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POCITION][6] = new Pawn(PieceColor.BLACK, 1, 6,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POCITION][7] = new Pawn(PieceColor.BLACK, 1, 7,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POCITION][8] = new Pawn(PieceColor.BLACK, 1, 8,true);
		board[INIT_BOARD_BLACK_EXTRA_PIECE_POCITION][9] = new Pawn(PieceColor.BLACK, 1, 9,true);

		board[INIT_BOARD_WHITE_MAIN_PIECE_POCITION][0] = new Rook(PieceColor.WHITE, 9, 0);
		board[INIT_BOARD_WHITE_MAIN_PIECE_POCITION][1] = new Knight(PieceColor.WHITE, 9, 1);
		board[INIT_BOARD_WHITE_MAIN_PIECE_POCITION][2] = new Bishop(PieceColor.WHITE, 9, 2);
		board[INIT_BOARD_WHITE_MAIN_PIECE_POCITION][3] = new King(PieceColor.WHITE, 9, 3);
		board[INIT_BOARD_WHITE_MAIN_PIECE_POCITION][4] = new Queen(PieceColor.WHITE, 9, 4);

		board[INIT_BOARD_WHITE_EXTRA_PIECE_POCITION][0] = new Pawn(PieceColor.WHITE, 8, 0,true);
		board[INIT_BOARD_WHITE_EXTRA_PIECE_POCITION][1] = new Pawn(PieceColor.WHITE, 8, 1,true);
		board[INIT_BOARD_WHITE_EXTRA_PIECE_POCITION][2] = new Pawn(PieceColor.WHITE, 8, 2,true);
		board[INIT_BOARD_WHITE_EXTRA_PIECE_POCITION][3] = new Pawn(PieceColor.WHITE, 8, 3,true);
		board[INIT_BOARD_WHITE_EXTRA_PIECE_POCITION][4] = new Pawn(PieceColor.WHITE, 8, 4,true);
	}

	public static boolean isPieceSelectable(int row, int col) {

//		if(!isPlayable()) { // color of the active piece
//			return false;
//		}

		return board[row][col] != null;
	}

	public static void selectPiece(int row, int col) {
		activePiece = board[row][col];
	}

	public static boolean isPieceMoveValid(int row, int col) {
		return activePiece.isMoveActionValid(row, col);
	}

	// Bad design
	public static void movePiece(int row, int col) {

		// int currentRow = activePiece.row;
		int currentRow = activePiece.getRow();
		// int currentCol = activePiece.col;
		int currentCol = activePiece.getCol();

		board[row][col] = activePiece;
		activePiece.move(row, col);

		board[currentRow][currentCol] = null;
	}
	public void paint() {

		//g.setColor(Color.CYAN);
		//g.fillRect(10, 10, 100, 100);

		for(int row = 0; row < 10; row++) {
			for(int col = 0; col < 10; col++) {
				render(row, col);
			}
		}
	}

	private void render(int row, int col) {

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = col;
		constraints.gridy = row;

		boolean isRowEven   = (row % 2 == 0);
		boolean isColEven   = (col % 2 == 0);

		boolean isTileWhite = (isRowEven && isColEven) ||
				(!isRowEven && !isColEven);

		Color tileColor 	= (isTileWhite) ? Color.WHITE
				: Color.DARK_GRAY;

		ChessBox chessBox = new ChessBox();
		chessBox.setBackground(tileColor);

		BufferedImage chessPiece = null;
		if (board[row][col] != null) {
			try {
				chessPiece = ImageIO.read(new File(board[row][col].render()));
			} catch (IOException | NullPointerException e) {
				System.out.println("Something went wrong" + e);
			}

			JLabel picLabel = new JLabel(new ImageIcon(chessPiece));
			chessBox.add(picLabel);
		}
		add(chessBox, constraints);

	}

	@Override
	public void run() {
		paint();
	}
}