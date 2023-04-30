package com.ggl.connect.four.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.ggl.connect.four.controller.DropListener;
import com.ggl.connect.four.model.ConnectFourModel;

public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final ConnectFourModel model;

	private Point[] winningPieces;

	public DrawingPanel(ConnectFourFrame view, ConnectFourModel model) {
		this.model = model;
		this.winningPieces = new Point[0];
		int margin = model.getDrawingPanelConstants().getMargin();
		int pieceWidth = model.getDrawingPanelConstants().getPieceWidth();

		Dimension d = model.getBoardDimension();
		int width = 2 * margin + d.width * pieceWidth;
		int height = 2 * margin + (d.height + 1) * pieceWidth;
		this.setPreferredSize(new Dimension(width, height));
		this.addMouseListener(new DropListener(view, model));
	}

	public void setWinningPieces(Point[] pieces) {
		this.winningPieces = pieces;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);

		model.setDrawingPanelConstants(getWidth(), getHeight());
		drawBoard(g2d);
		drawBoardPieces(g2d);
		drawWinningPieces(g2d);
	}

	private void drawBoard(Graphics2D g2d) {
		int horizontalMargin = model.getDrawingPanelConstants()
				.getHorizontalMargin();
		int pieceMargin = model.getDrawingPanelConstants().getPieceMargin();
		int pieceWidth = model.getDrawingPanelConstants().getPieceWidth();
		int radius = model.getDrawingPanelConstants().getRadius();
		int verticalMargin = model.getDrawingPanelConstants()
				.getVerticalMargin();

		int x = horizontalMargin;
		int y = verticalMargin + pieceWidth;
		int height = getHeight() - verticalMargin - y;
		int width = getWidth() - horizontalMargin - x;
		g2d.setColor(Color.BLUE);
		g2d.fillRect(x, y, width, height);

		Dimension d = model.getBoardDimension();
		g2d.setColor(Color.WHITE);
		int diameter = radius + radius;
		for (int column = 0; column < d.width; column++) {
			for (int row = 0; row < d.height; row++) {
				g2d.fillOval(x + pieceMargin, y + pieceMargin, diameter,
						diameter);
				y += pieceWidth;
			}
			y = verticalMargin + pieceWidth;
			x += pieceWidth;
		}
	}

	private void drawBoardPieces(Graphics2D g2d) {
		int horizontalMargin = model.getDrawingPanelConstants()
				.getHorizontalMargin();
		int pieceMargin = model.getDrawingPanelConstants().getPieceMargin();
		int pieceWidth = model.getDrawingPanelConstants().getPieceWidth();
		int radius = model.getDrawingPanelConstants().getRadius();
		int verticalMargin = model.getDrawingPanelConstants()
				.getVerticalMargin();

		char[][] board = model.getBoard();
		int diameter = radius + radius;

		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				char c = board[row][column];
				if (c == 'Y' || c == 'R') {
					if (c == 'Y') {
						g2d.setColor(Color.YELLOW);
					} else {
						g2d.setColor(Color.RED);
					}
					int x = horizontalMargin + column * pieceWidth;
					int y = verticalMargin + (row + 1) * pieceWidth;
					g2d.fillOval(x + pieceMargin, y + pieceMargin, diameter,
							diameter);
				}
			}
		}
	}

	private void drawWinningPieces(Graphics2D g2d) {
		char[][] board = model.getBoard();
		int horizontalMargin = model.getDrawingPanelConstants()
				.getHorizontalMargin();
		int radius = model.getDrawingPanelConstants().getRadius();
		int pieceWidth = model.getDrawingPanelConstants().getPieceWidth();
		int verticalMargin = model.getDrawingPanelConstants()
				.getVerticalMargin();

		for (Point p : winningPieces) {
			char player = board[p.x][p.y];
			g2d.setColor(Color.WHITE);
			if (player == 'Y') {
				g2d.setColor(Color.BLACK);
			}

			int x = horizontalMargin + p.y * pieceWidth;
			x += (pieceWidth - radius) / 2;
			int y = verticalMargin + (p.x + 1) * pieceWidth;
			y += (pieceWidth - radius) / 2;
			g2d.fillOval(x, y, radius, radius);
		}
	}

}
