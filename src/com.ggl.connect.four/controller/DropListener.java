package com.ggl.connect.four.controller;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import com.ggl.connect.four.model.ConnectFourModel;
import com.ggl.connect.four.view.ConnectFourFrame;

public class DropListener extends MouseAdapter {

	private final ConnectFourFrame view;

	private final ConnectFourModel model;

	private final Rectangle[] dropRectangles;

	public DropListener(ConnectFourFrame view, ConnectFourModel model) {
		this.view = view;
		this.model = model;

		Dimension d = model.getBoardDimension();
		this.dropRectangles = new Rectangle[d.width];
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if (!model.isGameOver() && event.getButton() == MouseEvent.BUTTON1) {
			Dimension d = model.getBoardDimension();
			createDropRectangles(model);

			for (int column = 0; column < d.width; column++) {
				if (dropRectangles[column].contains(event.getPoint())) {
					model.dropPiece(column);
					char player = createPlayerCharacter();
					if (model.isTieGame()) {
						model.setGameOver(true);
						view.getStartGameButton().setEnabled(true);
						view.repaint();
						return;
					}

					Point[] pieces = checkForWinCondition(player);
					if (pieces.length > 0) {
						view.setWinningPieces(pieces);
						model.setGameOver(true);
						view.getStartGameButton().setEnabled(true);

						if (player == 'Y') {
							model.incrementYellowWins();
						} else {
							model.incrementRedWins();
						}
						view.updateControlPanel();
					} else {
						model.nextPlayersTurn();
					}

					view.repaint();
					return;
				}
			}
		}
	}

	private void createDropRectangles(ConnectFourModel model) {
		Dimension d = model.getBoardDimension();

		int horizontalMargin = model.getDrawingPanelConstants()
				.getHorizontalMargin();
		int pieceWidth = model.getDrawingPanelConstants().getPieceWidth();
		int verticalMargin = model.getDrawingPanelConstants()
				.getVerticalMargin();

		int x = horizontalMargin;
		int y = verticalMargin;
		for (int column = 0; column < d.width; column++) {
			dropRectangles[column] = new Rectangle(x, y, pieceWidth,
					pieceWidth);
			x += pieceWidth;
		}
	}

	private char createPlayerCharacter() {
		char player = 'R';
		if (model.isYellowsTurn()) {
			player = 'Y';
		}

		return player;
	}

	private Point[] checkForWinCondition(char player) {
		char[][] board = model.getBoard();
		Point[] empty = new Point[0];

		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				Point[] hPieces = checkHorizontal(board, player, column, row);
				Point[] vPieces = checkVertical(board, player, column, row);
				Point[] dPieces = checkDiagonal(board, player, column, row);
				Point[] aPieces = checkAntiDiagonal(board, player, column, row);

				if (hPieces.length > 0) {
					return hPieces;
				}
				if (vPieces.length > 0) {
					return vPieces;
				}
				if (dPieces.length > 0) {
					return dPieces;
				}
				if (aPieces.length > 0) {
					return aPieces;
				}
			}
		}

		return empty;
	}

	private Point[] checkHorizontal(char[][] board, char player, int column,
			int row) {
		int consecutivePieces = model.getConsecutivePieces();
		Point[] empty = new Point[0];
		Point[] pieces = new Point[consecutivePieces];

		int pieceIndex = 0;
		for (int index = column; index < column + consecutivePieces; index++) {
			if (checkBounds(board, index, row)) {
				if (board[row][index] == player) {
					pieces[pieceIndex++] = new Point(row, index);
				} else {
					return empty;
				}
			} else {
				return empty;
			}
		}

		if (pieceIndex >= (consecutivePieces - 1)) {
			return pieces;
		} else {
			return empty;
		}
	}

	private Point[] checkVertical(char[][] board, char player, int column,
			int row) {
		int consecutivePieces = model.getConsecutivePieces();
		Point[] empty = new Point[0];
		Point[] pieces = new Point[consecutivePieces];

		int pieceIndex = 0;
		for (int index = row; index < row + consecutivePieces; index++) {
			if (checkBounds(board, column, index)) {
				if (board[index][column] == player) {
					pieces[pieceIndex++] = new Point(index, column);
				} else {
					return empty;
				}
			} else {
				return empty;
			}
		}

		if (pieceIndex >= (consecutivePieces - 1)) {
			return pieces;
		} else {
			return empty;
		}
	}

	private Point[] checkDiagonal(char[][] board, char player, int column,
			int row) {
		int consecutivePieces = model.getConsecutivePieces();
		Point[] empty = new Point[0];
		Point[] pieces = new Point[consecutivePieces];

		int pieceIndex = 0;
		for (int index = 0; index < consecutivePieces; index++) {
			int r = row + index;
			int c = column + index;
			if (checkBounds(board, c, r)) {
				if (board[r][c] == player) {
					pieces[pieceIndex++] = new Point(r, c);
				} else {
					return empty;
				}
			} else {
				return empty;
			}
		}

		if (pieceIndex >= (consecutivePieces - 1)) {
			return pieces;
		} else {
			return empty;
		}
	}

	private Point[] checkAntiDiagonal(char[][] board, char player, int column,
			int row) {
		int consecutivePieces = model.getConsecutivePieces();
		Point[] empty = new Point[0];
		Point[] pieces = new Point[consecutivePieces];

		int pieceIndex = 0;
		for (int index = 0; index < consecutivePieces; index++) {
			int r = row + index;
			int c = column - index;
			if (checkBounds(board, c, r)) {
				if (board[r][c] == player) {
					pieces[pieceIndex++] = new Point(r, c);
				} else {
					return empty;
				}
			} else {
				return empty;
			}
		}

		if (pieceIndex >= (consecutivePieces - 1)) {
			return pieces;
		} else {
			return empty;
		}
	}

	private boolean checkBounds(char[][] board, int column, int row) {
		return column >= 0 && column < board[0].length && row >= 0
				&& row < board.length;
	}

}
