package com.ggl.connect.four.model;

public class DrawingPanelConstants {
	
	private final int margin, pieceMargin;
	
	private int horizontalMargin, verticalMargin;
	private int pieceWidth, radius;
	
	public DrawingPanelConstants() {
		this.margin = 10;
		this.horizontalMargin = margin;
		this.verticalMargin = margin;
		this.pieceMargin = 10;
		this.radius = 36;
		this.pieceWidth = 2 * radius + 2 * pieceMargin;
	}

	public void setHorizontalMargin(int horizontalMargin) {
		this.horizontalMargin = horizontalMargin;
	}

	public void setVerticalMargin(int verticalMargin) {
		this.verticalMargin = verticalMargin;
	}

	public void setPieceWidth(int pieceWidth) {
		this.pieceWidth = pieceWidth;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getHorizontalMargin() {
		return horizontalMargin;
	}

	public int getVerticalMargin() {
		return verticalMargin;
	}

	public int getMargin() {
		return margin;
	}

	public int getPieceMargin() {
		return pieceMargin;
	}

	public int getPieceWidth() {
		return pieceWidth;
	}

	public int getRadius() {
		return radius;
	}

}
