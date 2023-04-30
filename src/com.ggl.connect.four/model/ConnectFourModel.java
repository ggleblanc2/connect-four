package com.ggl.connect.four.model;

import java.awt.Dimension;
import java.util.Arrays;

public class ConnectFourModel {

	private boolean gameOver, yellowsTurn;
	
	private int redWins, yellowWins;

	private char[][] board;
	
	private final int consecutivePieces;

	private final Dimension boardDimension;

	private final DrawingPanelConstants drawingPanelConstants;

	public ConnectFourModel() {
		this.boardDimension = new Dimension(7, 6);
		this.consecutivePieces = 4;
		this.drawingPanelConstants = new DrawingPanelConstants();
		this.redWins = 0;
		this.yellowWins = 0;
		resetGame();
	}

	public void nextPlayersTurn() {
		this.yellowsTurn = !yellowsTurn;
	}

	public boolean isYellowsTurn() {
		return yellowsTurn;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void resetGame() {
		this.board = new char[boardDimension.height][boardDimension.width];
		this.gameOver = false;
		this.yellowsTurn = true;
	}
	
	public boolean isTieGame() {
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				if ((int) board[row][column] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}

	public boolean dropPiece(int column) {
		for (int index = board.length - 1; index >= 0; index--) {
			if ((int) board[index][column] == 0) {
				if (yellowsTurn) {
					board[index][column] = 'Y';
				} else {
					board[index][column] = 'R';
				}
				return true;
			}
		}

		return false;
	}

	public void setDrawingPanelConstants(int width, int height) {
		int margin = getDrawingPanelConstants().getMargin();
		int pieceMargin = getDrawingPanelConstants().getPieceMargin();

		int innerWidth = width - 2 * margin;
		int innerHeight = height - 2 * margin;
		int pieceWidth = innerWidth / boardDimension.width;
		int pieceHeight = innerHeight / (boardDimension.height + 1);
		pieceWidth = Math.min(pieceWidth, pieceHeight) / 2 * 2;
		int radius = (pieceWidth - 2 * pieceMargin) / 2;
		int horizontalMargin = width - boardDimension.width * pieceWidth;
		int verticalMargin = height - (boardDimension.height + 1) * pieceWidth;
		horizontalMargin /= 2;
		verticalMargin /= 2;

		getDrawingPanelConstants().setHorizontalMargin(horizontalMargin);
		getDrawingPanelConstants().setPieceWidth(pieceWidth);
		getDrawingPanelConstants().setRadius(radius);
		getDrawingPanelConstants().setVerticalMargin(verticalMargin);
	}
	
	public void incrementRedWins() {
		redWins++;
	}

	public int getRedWins() {
		return redWins;
	}
	
	public void incrementYellowWins() {
		yellowWins++;
	}

	public int getYellowWins() {
		return yellowWins;
	}

	public int getConsecutivePieces() {
		return consecutivePieces;
	}

	public char[][] getBoard() {
		return board;
	}

	public Dimension getBoardDimension() {
		return boardDimension;
	}

	public DrawingPanelConstants getDrawingPanelConstants() {
		return drawingPanelConstants;
	}

	public void printBoard() {
		for (int index = 0; index < board.length; index++) {
			System.out.println(Arrays.toString(board[index]));
		}
		System.out.println();
	}
}
