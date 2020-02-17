package com.chessmaster.GUI;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class ApplicationWindow extends JPanel {

    private final int tileSide = 50;

    public void paint(Graphics g) {

        //g.setColor(Color.CYAN);
        //g.fillRect(10, 10, 100, 100);

        for(int row = 0; row < 10; row++) {
            for(int col = 0; col < 10; col++) {
                render(g, row, col);
            }
        }
    }

    private void render(Graphics g, int row, int col) {

        boolean isRowEven   = (row % 2 == 0);
        boolean isColEven   = (col % 2 == 0);

        boolean isTileWhite = (isRowEven && isColEven) ||
                (!isRowEven && !isColEven);

        Color tileColor 	= (isTileWhite) ? Color.WHITE
                : Color.DARK_GRAY;

        int tileX = col * tileSide;
        int tileY = row * tileSide;

        g.setColor(tileColor);
        g.fillRect(tileX, tileY, tileSide, tileSide);
    }
}