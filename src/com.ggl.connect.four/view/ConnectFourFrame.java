package com.ggl.connect.four.view;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.ggl.connect.four.model.ConnectFourModel;

public class ConnectFourFrame {
	
	private final ConnectFourModel model;
	
	private final ControlPanel controlPanel;
	
	private final DrawingPanel drawingPanel;
	
	private final JFrame frame;

	public ConnectFourFrame(ConnectFourModel model) {
		this.model = model;
		
		this.frame = new JFrame("Connect Four");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.controlPanel = new ControlPanel(this, model);
		frame.add(controlPanel.getPanel(), BorderLayout.NORTH);
		this.drawingPanel = new DrawingPanel(this, model);
		frame.add(drawingPanel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
	
	public void updateControlPanel() {
		controlPanel.updateControlPanel();
	}

	public JButton getStartGameButton() {
		return controlPanel.getStartGameButton();
	}
	
	public void setWinningPieces(Point[] pieces) {
		drawingPanel.setWinningPieces(pieces);
	}
	
	public void repaint() {
		drawingPanel.repaint();
	}

}
