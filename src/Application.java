import com.chessmaster.GUI.ApplicationWindow;
import com.chessmaster.manager.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {

		JFrame windowFrame = new JFrame();
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setSize(600, 600);
		windowFrame.add(new ApplicationWindow());
		windowFrame.setVisible(true);
		windowFrame.setLayout(new GridBagLayout());

		Scanner input = new Scanner(System.in);

		System.out.println("Wellkome chesmmasters");
		GameBoard.init();

		int selectRow;
		int selectCol;

		int moveRow;
		int moveCol;

		while(true) {


			System.out.print("Select row : ");
			selectRow = input.nextInt();

			System.out.print("Select col : ");
			selectCol = input.nextInt();

			if(GameBoard.isPieceSelectable(selectRow, selectCol)) {
				GameBoard.selectPiece(selectRow, selectCol);

				System.out.println("Piece : " + GameBoard.activePiece.getSignature() + " is selected");

				System.out.print("Move row : ");
				moveRow = input.nextInt();

				System.out.print("Move col : ");
				moveCol = input.nextInt();

				if(GameBoard.isPieceMoveValid(moveRow, moveCol)) {
					GameBoard.movePiece(moveRow, moveCol);
				}

			}

		}
	}
}